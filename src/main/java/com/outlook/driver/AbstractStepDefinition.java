package com.outlook.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

/**
 * Class represents a abstract step class in order to keep common methods in one place
 *
 * @author Ievgen_Ostapenko
 */
public class AbstractStepDefinition
{
	public static RemoteWebDriver driver;
	private static DesiredCapabilities capabilities;
	protected SoftAssert softAssert = new SoftAssert();

	/**
	 * Initializes the Firefox browser with default capabilities
	 */
	public static void init()
	{
		capabilities = DesiredCapabilities.firefox();
		driver = new FirefoxDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	/**
	 * Initializes the Firefox browser with custom FF profile. It has to be created manually.
	 */
	public static void reInit()
	{
		ProfilesIni profiles = new ProfilesIni();
		FirefoxProfile profile = profiles.getProfile("AutomationUser");
		capabilities.setCapability("firefox_profile", profile);
		driver = new FirefoxDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	/**
	 * Closes the current driver
	 */
	public static void close()
	{
		try
		{
			driver.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			driver = null;
		}
	}

	/**
	 * Waits for the page to finish posting/redirecting before proceeding.
	 *
	 * @param maxWaitInSeconds
	 */
	public void waitForPageLoad(int maxWaitInSeconds)
	{
		new WebDriverWait(driver, maxWaitInSeconds).until(
				(WebDriver e) -> (Boolean) driver.executeScript("return !(window.seleniumProcessing);"));
	}
}
