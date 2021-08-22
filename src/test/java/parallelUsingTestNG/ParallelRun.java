package parallelUsingTestNG;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"./src/test/resources/parallelUsingTestNG/LoginPage.feature"},
		glue = { "parallelUsingTestNG" },
		plugin = {"pretty", "json:target/MyReports/report.json", "junit:target/MyReports/report.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				"rerun:target/failedrerun.txt"
				}, publish = true,
		dryRun = false,
		monochrome = true
		
		
)

public class ParallelRun extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
