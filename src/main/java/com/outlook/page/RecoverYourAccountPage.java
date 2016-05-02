package com.outlook.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecoverYourAccountPage extends AbstractPage
{

	public RecoverYourAccountPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(name = "iSigninName")
	private WebElement loginTextField;

	public WebElement getLoginTextField()
	{
		return loginTextField;
	}

	public WebElement getCaptchTextField()
	{
		return captchTextField;
	}

	@FindBy(xpath = "input[@placeholder='Enter the characters you see']")
	private WebElement captchTextField;

}
