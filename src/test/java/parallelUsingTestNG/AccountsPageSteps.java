package parallelUsingTestNG;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import com.pages.AccountsPage;
import com.pages.LoginPage;
import com.qa.factory.DriverFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AccountsPageSteps {
	
	private static Logger logger = LogManager.getLogger(AccountsPageSteps.class.getName());
	
	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private AccountsPage accountsPage;
	
	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable credTable) {
		List<Map<String, String>> credList =  credTable.asMaps();
		String userName = credList.get(0).get("username");
		String password = credList.get(0).get("password");
		DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		accountsPage = loginPage.doLogin(userName, password);
		logger.debug("This is a debug message");
	    logger.info("This is an info message");
	    logger.warn("This is a warn message");
	    logger.error("This is an error message");
	    logger.fatal("This is a fatal message");
	}

	@Given("user is on Accounts page")
	public void user_is_on_accounts_page() {
		String title = accountsPage.getAccountsPageTiltle();
		System.out.println("Accpimts Page Title is : "+title);
	}

	@Then("user gets accounts section")
	public void user_gets_accounts_section(DataTable sectionsTable) {
		List<String> expAccountSectionsList = sectionsTable.asList();
		System.out.println("Expected Accounts Sections List : "+expAccountSectionsList);
		List<String> actAccountSectionsList = accountsPage.getAccountSectionList();
		System.out.println("Actual Accounts Sections List : "+actAccountSectionsList);
		Assert.assertTrue(expAccountSectionsList.containsAll(actAccountSectionsList));
	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer expectedSectionCount) {
		Assert.assertTrue(accountsPage.getAccountSectionCount() == expectedSectionCount);
		
	}

}
