package Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import applitools.VisualTestingHelper;

import org.apache.logging.log4j.*;
public class Base {

	public static Properties properties = null;
	protected static WebDriver driver;
	private static Logger Logg = LogManager.getLogger(Base.class);
	public Properties loadpropertiesfile() throws IOException {
		FileInputStream fileinputstream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\Test\\resources\\Config\\config.properties");
		properties = new Properties();
		properties.load(fileinputstream);
		return properties;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	public void launchbrowser() throws IOException, InterruptedException {

		loadpropertiesfile();
		String browser = properties.getProperty("Browser");
		String URL = properties.getProperty("projectURL");

		if (browser.equalsIgnoreCase("Chrome")) {
			
			ChromeOptions op = new ChromeOptions();
			op.setBrowserVersion("116");
			driver = new ChromeDriver(op);
			// driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(URL);
			Logg.info("Username Entered");
			Logg.info("Password Entered");
			Logg.info("Click on Submit button");
			
			VisualTestingHelper hp = new VisualTestingHelper(driver);
			hp.eyeInit();

		}

		else if (browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
			// driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(URL);
		}
	}

	@AfterSuite
	public void teardown() throws IOException {
		    driver.close();
			//Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
	}

	public static String takeScreenshotAtEndOfTest(String testname) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir") + "/reports/" + testname + ".png";
		FileUtils.copyFile(scrFile, new File(currentDir));
		return currentDir;

	}

}
