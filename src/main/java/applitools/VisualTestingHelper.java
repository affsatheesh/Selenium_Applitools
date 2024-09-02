package applitools;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FileLogger;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.config.Configuration;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.ChromeEmulationInfo;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.IosDeviceInfo;
import com.applitools.eyes.visualgrid.model.IosDeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;

public class VisualTestingHelper {
	VisualGridRunner runEyes;
	static Eyes eye;
	static WebDriver driver;

	public VisualTestingHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void eyeInit() {

		runEyes = new VisualGridRunner(10);
		eye = new Eyes(runEyes);
		eye.setApiKey("hl4bu64bDMbQ109gD97rLun36ffXwjBdmi4pXP4u01051061FY110");
		eye.setServerUrl("https://eyes.applitools.com");
		Configuration configuration = new Configuration();
		configuration.setServerUrl("https://eyes.applitools.com");
		configuration.setApiKey("hl4bu64bDMbQ109gD97rLun36ffXwjBdmi4pXP4u01051061FY110");
		BatchInfo info = new BatchInfo("Demo Test");
		configuration.setBatch(info);

		configuration.addBrowser(1366, 768, BrowserType.CHROME);
		configuration.addBrowser(1366, 768, BrowserType.CHROME_ONE_VERSION_BACK);
		configuration.addBrowser(1366, 768, BrowserType.CHROME_TWO_VERSIONS_BACK);
//
		configuration.addBrowser(1366, 768, BrowserType.FIREFOX);
		configuration.addBrowser(1366, 768, BrowserType.FIREFOX_ONE_VERSION_BACK);
		configuration.addBrowser(1366, 768, BrowserType.FIREFOX_TWO_VERSIONS_BACK);
//
		configuration.addBrowser(1366, 768, BrowserType.IE_11);
//		configuration.addBrowser(1366, 768, BrowserType.IE_10);
//
		configuration.addBrowser(1366, 768, BrowserType.EDGE_CHROMIUM);
//		configuration.addBrowser(1366, 768, BrowserType.EDGE_CHROMIUM_ONE_VERSION_BACK);
//		configuration.addBrowser(1366, 768, BrowserType.EDGE_CHROMIUM_TWO_VERSION_BACK);
//
//		configuration.addBrowser(1366, 768, BrowserType.SAFARI);
//		configuration.addBrowser(1366, 768, BrowserType.SAFARI_ONE_VERSION_BACK);
//		configuration.addBrowser(1366, 768, BrowserType.SAFARI_TWO_VERSIONS_BACK);

		IosDeviceInfo ios = new IosDeviceInfo(IosDeviceName.iPhone_12);
		ChromeEmulationInfo ce = new ChromeEmulationInfo(DeviceName.Galaxy_A5, ScreenOrientation.PORTRAIT);
		configuration.addBrowser(ios);
		configuration.addBrowser(ce);
		eye.setConfiguration(configuration);
		eye.setLogHandler(new FileLogger("applitools.log", true, true));
	}

	public static void stitchMode() {
		eye.setStitchMode(StitchMode.CSS);
	}

	public static void openApplitools(String batchDetails) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screen.height;
		int width = screen.width;
		eye.open(driver, "Demo User!", batchDetails, new RectangleSize(1263, 577));
	}

	public static void specficObject(String pageName, WebElement element) {
		eye.check(pageName, Target.window().fully().region(element).ignoreDisplacements(true));
	}

	public static void targetWindowScreenshot(String pageName) {
		eye.check(pageName, Target.window().layout().fully().ignoreDisplacements(true));
	}

	public static void targetWindowFullPageScreenshot(String pageName) {
		eye.setForceFullPageScreenshot(true);
	}

	public static void exactFilter() {
		eye.check(Target.window().exact().ignoreDisplacements(true));
	}

	public static void strictFilter() {
		eye.setMatchLevel(MatchLevel.STRICT);
	}

	public static void contentFilter() {
		eye.setMatchLevel(MatchLevel.IGNORE_COLORS);
	}

	public static void layoutFilter() {
		eye.check(Target.window().layout().ignoreDisplacements(true));
	}

	public static void closeApplitools() {
		try {
			eye.close();
		} catch (Exception e) {
			System.out.println("found differences");
		}
	}

	public static void closeforcefullyApplitools() {
		try {
			eye.abortIfNotClosed();
		} catch (Exception e) {
			System.out.println("found differences");
		}
	}

	public static void ignorePart(String value, WebElement loctor) {
		eye.check(value, Target.window().fully().ignore(loctor).ignoreDisplacements(true));
	}

}
