package com.outlook.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TroubleToSignInPage extends AbstractPage
{

	public TroubleToSignInPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath = "//span[contains(text(), 'I forgot my password')]/preceding-sibling::input[@name='whyResetRadio']")
	private WebElement forgotMyPasswordRadioButton;


	@FindBy(xpath = "//span[contains(text(), 'I know my password, but can't sign in')]/preceding-sibling::input[@name='whyResetRadio']")
	private WebElement cannotSignInRadioButton;

	@FindBy(xpath = "//span[contains(text(), 'I think someone else is using my Microsoft account')]/preceding-sibling::input[@name='whyResetRadio']")
	private WebElement someoneUsingMyPasswordRadioButton;

	@FindBy(css = "input[name='whyResetRadio']")
	private List<WebElement> reasonRadioButtons;

	@FindBy(xpath = "//input[@name='whyResetRadio']/following-sibling::span")
	private List<WebElement> reasonLables;

	public List<WebElement> getReasonRadioButtons()
	{
		return reasonRadioButtons;
	}

	public List<WebElement> getReasonLables()
	{
		return reasonLables;
	}

	public WebElement getCancelButton()
	{
		return cancelButton;
	}

	public WebElement getForgotMyPasswordRadioButton()
	{
		return forgotMyPasswordRadioButton;
	}

	public WebElement getCannotSignInRadioButton()
	{
		return cannotSignInRadioButton;
	}

	public WebElement getSomeoneUsingMyPasswordRadioButton()
	{
		return someoneUsingMyPasswordRadioButton;
	}

	public WebElement getNextButton()
	{
		return nextButton;
	}

	public WebElement getHowCompromisedDropDown()
	{
		return howCompromisedDropDown;
	}

	@FindBy(id = "resetPwdWhyCancel")
	private WebElement cancelButton;

	@FindBy(id = "resetPwdWhyAction")
	private WebElement nextButton;

	@FindBy(id = "howCompromisedDropdown")
	private WebElement howCompromisedDropDown;


}
