package com.outlook.driver;

import com.google.common.base.Predicate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class BaseSelenium {
    public static RemoteWebDriver driver;


    public static void init()
    {
        driver = new FirefoxDriver();
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
