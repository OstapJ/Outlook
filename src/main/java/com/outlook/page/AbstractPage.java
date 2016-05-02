package com.outlook.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    WebDriver driver;
    public  AbstractPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }




    /**
     * Returns the page title
     *
     * @return
     */
    public String getPageTitle()
    {
        String text = driver.getTitle();
//        logger.debug("Obtained the '{}' page title", text);
        return text;
    }
}
