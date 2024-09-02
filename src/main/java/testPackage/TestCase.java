package testPackage;

import static org.testng.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.event.LoggingEvent;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.service.ReportPortal;

import Config.Base;
import applitools.VisualTestingHelper;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.spi.LoggerContext;

public class TestCase extends Base {
	private static Logger Logg = LogManager.getLogger(Base.class);

	@BeforeMethod
	public void getdrivers() {
		driver = Base.getDriver();	
	}

	@Test(priority = 0)
	public void login() throws InterruptedException, IOException {
		driver.navigate().refresh();
		VisualTestingHelper hp = new VisualTestingHelper(driver);
		hp.stitchMode();
		hp.openApplitools("Home Page");
		hp.targetWindowScreenshot("Root Cause Analysis");
		hp.closeApplitools();
		hp.closeforcefullyApplitools();

	}

//	@Test(priority = 1)
//	public void Navigate_your_Test() throws InterruptedException, IOException {
//		driver.findElement(By.name("q")).sendKeys("Applitools \n");
//	}
}
