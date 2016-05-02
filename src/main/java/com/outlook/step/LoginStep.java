package com.outlook.step;

import com.outlook.BaseConstants;
import com.outlook.driver.BaseSelenium;
import com.outlook.page.LoginPage;
import com.outlook.page.OutlookMailPage;
import com.outlook.property.PropertiesProvider;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginStep extends BaseSelenium
{

	LoginPage loginPage = new LoginPage(driver);
	OutlookMailPage outlookMailPage = new OutlookMailPage(driver);

	/**
	 * Navigates to Login page
	 */
	@When("^I navigate to Login page$")
	public void navigateToLoginPage()
	{
		driver.navigate().to(PropertiesProvider.getProperty(BaseConstants.APPLICATION_URL_KEY));
		waitForPageLoad(10);
	}

	/**
	 * Navigates to Outlook page
	 */
	@After(value = "@logout")
	@When("^I log out from Outlook$")
	public void logout() throws InterruptedException
	{
		outlookMailPage.getProfileSection().click();
		outlookMailPage.getSignOutLink().click();
		waitForPageLoad(10);
	}

	/**
	 * Logs in into application with specified Email and Password
	 * <p>
	 * <b>Date Table Headers:</b> <ul> <li>Email <li>Password </ul>
	 *
	 * @param credentials
	 * @throws Throwable
	 */
	@When("^I login with the following user credentials:$")
	public void loginDataTable(Map<String, String> credentials) throws Throwable
	{
		navigateToLoginPage();
		fillLoginForm(credentials);
		clickLogin();
		waitForPageLoad(10);

	}

	/**
	 * Inputs the values
	 * <p>
	 * <b>Date Table Headers:</b> <ul> <li>login <li>password </ul>
	 *
	 * @param credentials
	 */
	@When("^I fill the following user credentials:$")
	public void fillLoginForm(Map<String, String> credentials)
	{
		loginPage.getLoginTextField().sendKeys(credentials.get("Email"));
		loginPage.getPasswordTextField().sendKeys(credentials.get("Password"));
	}

	/**
	 * This step clicks on Sign In button to log in into Outlook
	 */
	@When("^I click log in$")
	public void clickLogin()
	{
		loginPage.getSignInButton().click();
		waitForPageLoad(10);

	}

	/**
	 * This step checks the 'keep me signed in' checkbox to hold on the credentials in the system
	 */
	@When("^I select to keep me signed in$")
	public void checkKeepMeSignedIn()
	{
		loginPage.getKeepMeSignedInCheckbox().click();
	}

	//	/**
	//	 * This step logs out of Outlook by opening a drop-down and clicking on LogOut link
	//	 */
	//	@When("^I close the Browser$")
	//	public void logout()
	//	{
	//
	//		driver.close();
	//		cookies = driver.manage().getCookies();
	//
	//	}

	/**
	 * This step logs out of Outlook by opening a drop-down and clicking on LogOut link
	 */
	@When("^I refresh the page$")
	public void refreshPage()
	{
		driver.navigate().refresh();
		waitForPageLoad(10);

	}

	/**
	 * This step logs out of Outlook by opening a drop-down and clicking on LogOut link
	 */
	@When("^I reopen the browser$")
	public void deleteCookie()
			throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
			IllegalAccessException
	{
		close();
		init();
		loginPage = new LoginPage(driver);
	}

	/**
	 * Verifies that Mail Outlook page is displayed
	 */
	@Then("^Mail Outlook page is displayed$")
	public void verifyMailOutlookPageTitle()
	{
		assertTrue("It isn't a Mail Outlook page",
				outlookMailPage.getPageTitle().contains(PropertiesProvider.getProperty("mailOutlook.title")));
	}

	/**
	 * Verifies that Login page is displayed
	 */
	@Then("^Login page is displayed$")
	public void verifyLoginPageTitle()
	{
		assertTrue("It isn't a Login page",
				outlookMailPage.getPageTitle().contains(PropertiesProvider.getProperty("loginPage.title")));
	}

	/**
	 * Verifies the specified error message on the page- first validates the error message title and then the actual
	 * message.
	 *
	 * @param message
	 * @throws Throwable
	 */
	@Then("^I should see \"([^\"]*)\" message displayed$")
	public void verifyMessageDisplayed(String message)
	{
		assertEquals(String.format("Error message '%s' is not displayed", message),
				message, loginPage.getCredentialsErrorLabel().getText());
	}
}
