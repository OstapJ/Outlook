package com.outlook.step;

import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.stream.Collectors;

import com.outlook.driver.BaseSelenium;
import com.outlook.page.LoginPage;
import com.outlook.page.RecoverYourAccountPage;
import com.outlook.page.TroubleToSignInPage;
import com.outlook.property.PropertiesProvider;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ForgottenPasswordStep extends BaseSelenium
{

	LoginPage loginPage = new LoginPage(driver);
	TroubleToSignInPage troubleToSignInPage = new TroubleToSignInPage(driver);
	RecoverYourAccountPage recoverYourAccountPage = new RecoverYourAccountPage(driver);



	/**
	 * Logs in into application with specified Email and Password
	 * <p>
	 * <b>Date Table Headers:</b> <ul> <li>Email <li>Password </ul>
	 *
	 * @param info
	 * @throws Throwable
	 */
	@When("^I recover the password using the following values:$")
	public void recoverPassword(Map<String, String> info) throws Throwable
	{
		loginPage.getForgotMyPasswordLink().click();
		int rowIndex = troubleToSignInPage.getReasonLables().stream().map(element -> element.getText()).collect(
				Collectors.toList()).indexOf(info.get("Reason"));
		troubleToSignInPage.getReasonRadioButtons().get(rowIndex).click();
		troubleToSignInPage.getNextButton().click();
		recoverYourAccountPage.getLoginTextField().sendKeys(info.get("Email"));
	}

	/**
	 * Verifies that Captcha is displayed
	 */
	@Then("^Captcha is displayed$")
	public void verifyCaptchaIsDisplayed()
	{
		assertTrue("It isn't a Recover your account page",
				recoverYourAccountPage.getPageTitle().contains(PropertiesProvider.getProperty("recoverYourAccountPage.title")));
	}

}
