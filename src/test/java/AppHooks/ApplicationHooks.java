package AppHooks;

import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	private static Logger logger = LogManager.getLogger(ApplicationHooks.class.getName());
	
	@Before(order=0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	 }
	@Before(order=1)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
	}
	
	@After(order=0)
	public void quitBrowser() {
		System.out.println("Scenario is Completed");
		driver.quit();
	}
	@After(order=1)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			//take ScreenShot
			String screenShotName = scenario.getName().replaceAll(" ", "_");
			logger.info("Screen Shot Name Is : "+screenShotName);
			byte [] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenShotName);
			/*String fileName = getScreenshotName(screenShotName);
			String directory = System.getProperty("user.dir")+"/ScreenShotS/";
			new File(directory).mkdirs();
			String path = directory+fileName;
			try {
				File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenShot, new File(path));
				System.out.println("**********************************");
				System.out.println("ScreenShot Stored At : "+path);
				System.out.println("**********************************");
			}catch(Exception e) {
				e.printStackTrace();
				
			}*/
 		}
	}
	private String getScreenshotName(String methodName) {
		Date d = new Date();
        String fileName =methodName+"_"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		return fileName;
	}
	
	

}
