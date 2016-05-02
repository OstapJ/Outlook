package com.outlook.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage
{

	public LoginPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(name = "loginfmt")
	private WebElement loginTextField;

	@FindBy(name = "passwd")
	private WebElement passwordTextField;

	@FindBy(name = "KMSI")
	private WebElement keepMeSignedInCheckbox;

	@FindBy(id = "idSIButton9")
	private WebElement signInButton;

	@FindBy(id = "idA_PWD_ForgotPassword")
	private WebElement forgotMyPasswordLink;

	public WebElement getCredentialsErrorLabel()
	{
		return credentialsErrorLabel;
	}

	@FindBy(id = "usernameError")
	private WebElement credentialsErrorLabel;

	public WebElement getLoginTextField()
	{
		return loginTextField;
	}

	public WebElement getPasswordTextField()
	{
		return passwordTextField;
	}

	public WebElement getKeepMeSignedInCheckbox()
	{
		return keepMeSignedInCheckbox;
	}

	public WebElement getSignInButton()
	{
		return signInButton;
	}

	public WebElement getForgotMyPasswordLink()
	{
		return forgotMyPasswordLink;
	}
}
