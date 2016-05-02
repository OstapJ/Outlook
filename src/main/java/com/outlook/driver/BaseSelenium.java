package com.outlook.driver;

import com.google.common.base.Predicate;
import com.outlook.BaseConstants;
import com.outlook.property.PropertiesProvider;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class BaseSelenium {
    public static RemoteWebDriver driver;
    private static DesiredCapabilities capabilities;


    public static void init()
    {
        capabilities = DesiredCapabilities.firefox();
        driver = new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    public static void reInit()
    {
        ProfilesIni profiles = new ProfilesIni();
        FirefoxProfile profile = profiles.getProfile("AutomationUser");
        capabilities.setCapability("firefox_profile", profile);
        driver = new FirefoxDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    public static void close(){
        try {
            driver.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally{
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
        new WebDriverWait(driver, maxWaitInSeconds).until(new Predicate<WebDriver>()
        {
            public boolean apply(WebDriver e)
            {
                return (Boolean) driver.executeScript("return !(window.seleniumProcessing);");
            }
        });
    }
}
