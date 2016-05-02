package com.outlook;

import com.outlook.driver.BaseSelenium;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty", "json:target/Cucumber.json",
                "html:target/cucumber-html-report"
        },
        glue = { "com.outlook.step" },
        features = { "classpath:com.outlook.feature/responsiveDesign.feature" }
)
public class RunAcceptanceTest {
    @BeforeClass
    public static void initSelenium()
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException
    {
        BaseSelenium.init();
    }
    @AfterClass
    public static void closeSelenium(){
        BaseSelenium.close();
    }

}
