package com.outlook.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OutlookMailPage extends AbstractPage
{

	@FindBy(css = "[aria-label*='offline menu with submenu'],[aria-label='Open menu']")
	private WebElement profileSection;

	@FindBy(css = "[aria-label='Sign out']")
	private WebElement signOutLink;

	public WebElement getProfileSection()
	{
		return profileSection;
	}

	public WebElement getSignOutLink()
	{
		return signOutLink;
	}

	public OutlookMailPage(WebDriver driver)
	{
		super(driver);
	}

}
