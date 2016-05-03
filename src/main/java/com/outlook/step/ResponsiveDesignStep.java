package com.outlook.step;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.outlook.BaseConstants;
import com.outlook.Devices;
import com.outlook.driver.BaseSelenium;
import com.outlook.page.HomePage;
import com.outlook.property.PropertiesProvider;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by Ievgen_Ostapenko on 5/2/2016.
 */
public class ResponsiveDesignStep extends BaseSelenium
{
	HomePage homePage = new HomePage(driver);
	private final static Logger LOGGER = LoggerFactory.getLogger(ResponsiveDesignStep.class);

	/**
	 * Navigates to Home page
	 */
	@When("^I navigate to Home page$")
	public void navigateToHomePage()
	{
		driver.navigate().to(PropertiesProvider.getProperty(BaseConstants.APPLICATION_URL_KEY));
		waitForPageLoad(10);
	}

	/**
	 * Resize the page according to the following device
	 *
	 * @param info
	 */
	@When("^I resize the page according to the following device:")
	public void resizePage(Map<String, String> info)
	{
		Devices device = Devices.getByModelName(info.get("Device"));
		device.changeOrientation(info.get("Orientation"));
		LOGGER.info("The screen size is set according to '{}' device with '{}' Width and '{}' Height",
				info.get("Device"), device.getWidth(), device.getHeight());
		Dimension dimension = new Dimension(device.getWidth(), device.getHeight());
		driver.manage().window().setSize(dimension);
		waitForPageLoad(5);
	}

	/**
	 * Verifies that Captcha is displayed
	 */
	@Then("^Menu is displayed$")
	public void menuIsDisplayed()
	{
		assertTrue("Menu link isn't displayed",
				homePage.getMenuLink().isDisplayed());
	}

	/**
	 * Verifies that Captcha is displayed
	 */
	@Then("^Top navigation menu links isn't displayed$")
	public void topNavigationMenuIsNotDisplayed()
	{
		homePage.getTopNavigationLinks().stream().forEach(link -> assertTrue("The " + link.getAttribute("href") + " link is displayed", link.isDisplayed()));
	}
}
