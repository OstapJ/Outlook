package com.outlook.step;

import com.outlook.BaseConstants;
import com.outlook.Devices;
import com.outlook.driver.AbstractStepDefinition;
import com.outlook.page.HomePage;
import com.outlook.page.WorkPage;
import com.outlook.property.PropertiesProvider;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Ievgen_Ostapenko on 5/2/2016.
 */
public class ResponsiveDesignStep extends AbstractStepDefinition
{
	HomePage homePage = new HomePage(driver);
	WorkPage workPage = new WorkPage(driver);
	private final static Logger LOGGER = LoggerFactory.getLogger(ResponsiveDesignStep.class);

	/**
	 * Navigates to Home page
	 */
	@When("^I navigate to Home page$")
	public void navigateToHomePage()
	{
		driver.manage().window().maximize();
		driver.navigate().to(PropertiesProvider.getProperty(BaseConstants.AKQA_URL_KEY));
	}

	/**
	 * Opens the tab
	 *
	 * @param tabName
	 */
	@When("^I open the (\\w+) tab$")
	public void openWorkTab(String tabName)
	{
		int rowIndex = homePage.getTopNavigationLinks().stream()
				.map(element -> element.getText())
				.collect(Collectors.toList()).indexOf(tabName.toUpperCase());
		homePage.getTopNavigationLinks().get(rowIndex).click();
	}

	/**
	 * Resize the page according to the specified device
	 *
	 * @param info
	 */
	@When("^I resize the page according to the following device:")
	public void resizePage(Map<String, String> info)
	{
		if (info.get("Device").equalsIgnoreCase("Laptop"))
		{
			driver.manage().window().maximize();
		}
		else
		{
			Devices device = Devices.getByModelName(info.get("Device"));
			device.changeOrientation(info.get("Orientation"));

			LOGGER.info("The screen size is set according to '{}' device with '{}' Width and '{}' Height",
					info.get("Device"), device.getWidth(), device.getHeight());
			Dimension dimension = new Dimension(device.getWidth(), device.getHeight());
			driver.manage().window().setSize(dimension);
		}
		waitForPageLoad(5);
	}

	/**
	 * Verifies either Menu link is displayed or not.
	 */
	@Then("^I (should|should not) see the Menu link$")
	public void menuIsNotDisplayed(String isDisplayed)
	{
		boolean bool = !isDisplayed.endsWith("not");
		assertEquals("Menu link isn't displayed",
				homePage.getMenuLink().isDisplayed(), bool);
	}

	/**
	 * Verifies either Top navigation menu links are displayed or not.
	 *
	 * @param isDisplayed
	 */
	@Then("^I (should|should not) see the Top navigation menu links$")
	public void topNavigationMenuIsDisplayed(String isDisplayed)
	{
		boolean bool = !isDisplayed.endsWith("not");
		homePage.getTopNavigationLinks().stream().forEach(
				link -> softAssert.assertEquals(link.isDisplayed(), bool,
						"The " + link.getAttribute("href") + " link is not displayed"));
		softAssert.assertAll();
	}

	/**
	 * Verifies amount of Top navigation menu links
	 *
	 * @param linkAmount
	 */
	@Then("^I should see (\\d+) the Top navigation menu links$")
	public void verifyLinkAmount(int linkAmount)
	{
		assertEquals("Amount of expected top navigation link doesn't equal", linkAmount,
				homePage.getTopNavigationLinks().size());

	}

	/**
	 * Verifies amount of Images
	 *
	 * @param imageAmount
	 */
	@Then("^Amount of Images on the page is (\\d+)")
	public void verifyImagesAmount(int imageAmount)
	{
		assertEquals("Amount of Images doesn't match the expected one", imageAmount,
				workPage.getListImages().size());

	}

	/**
	 * Verifies amount of displayed Images
	 *
	 * @param imageAmount
	 */
	@Then("^I should see (\\d+) Images$")
	public void verifyImagesAmountIsDisplayed(int imageAmount)
	{
		assertEquals("Amount of displayed Images doesn't match the expected one", imageAmount,
				workPage.getListImages().stream().filter(WebElement::isDisplayed).collect(Collectors.toList()).size());
	}

	/**
	 * Verifies Image View representation
	 *
	 * @param imageView
	 */
	@Then("^I should see the Images in (\\w+) representation$")
	public void verifyImageView(String imageView)
	{
		if (imageView.equalsIgnoreCase("Gallery"))
		{
			verifyElementsInGalleryView(workPage.getListImages());
		}
		if (imageView.equalsIgnoreCase("View"))
		{
			verifyElementsInListView(workPage.getListImages());
		}
	}

	/**
	 * It's a helper method which verifies that the elements reside in Gallery view
	 *
	 * @param listImages
	 */
	private void verifyElementsInGalleryView(List<WebElement> listImages)
	{
		for (int i = 0, j = 1; i < listImages.size(); i++, j++)
		{
			if (i % 2 == 0)
			{
				WebElement firstImage = listImages.get(i);
				WebElement secondImage = listImages.get(j);
				softAssert.assertTrue(firstImage.getLocation().getX() < secondImage.getLocation().getX(),
						"The first image to the right of the second");
			}
		}
		softAssert.assertAll();
	}

	/**
	 * It's a helper method which verifies that the elements reside in List view
	 *
	 * @param listImages
	 */
	private void verifyElementsInListView(List<WebElement> listImages)
	{
		for (int i = 0, j = 1; i < listImages.size(); i++, j++)
		{
			WebElement firstImage = listImages.get(i);
			WebElement secondImage = listImages.get(j);
			softAssert.assertTrue(firstImage.getLocation().getY() < secondImage.getLocation().getY(),
					"The first image below the second");
		}
		softAssert.assertAll();
	}
}
