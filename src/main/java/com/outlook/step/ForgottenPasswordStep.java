package com.outlook.step;

import com.outlook.driver.AbstractStepDefinition;
import com.outlook.page.LoginPage;
import com.outlook.page.RecoverYourAccountPage;
import com.outlook.page.TroubleToSignInPage;
import com.outlook.property.PropertiesProvider;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class ForgottenPasswordStep extends AbstractStepDefinition
{

	LoginPage loginPage = new LoginPage(driver);
	TroubleToSignInPage troubleToSignInPage = new TroubleToSignInPage(driver);
	RecoverYourAccountPage recoverYourAccountPage = new RecoverYourAccountPage(driver);

	/**
	 * Recovers the password for the given login
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
				recoverYourAccountPage.getPageTitle()
						.contains(PropertiesProvider.getProperty("recoverYourAccountPage.title")));
	}

}
