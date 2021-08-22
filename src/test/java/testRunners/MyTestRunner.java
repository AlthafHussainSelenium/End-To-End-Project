package testRunners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/parallelUsingTestNG/LoginPage.feature"},
		glue = { "parallelUsingTestNG" },
		plugin = {"pretty",
				"json:target/MyReports/report.json", "junit:target/MyReports/report.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				"rerun:target/failedrerun.txt"
				}
		, dryRun = false
		, monochrome = true
		)

public class MyTestRunner {

}
