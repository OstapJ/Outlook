package com.outlook.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends AbstractPage
{

	public HomePage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(css = ".header .nav>ul a")
	private List<WebElement> topNavigationLinks;

	@FindBy(css = ".footer .nav a")
	private List<WebElement> bottomNavigationLinks;

	@FindBy(css = "a.menu")
	private WebElement menuLink;

	public List<WebElement> getTopNavigationLinks()
	{
		return topNavigationLinks;
	}

	public List<WebElement> getBottomNavigationLinks()
	{
		return bottomNavigationLinks;
	}

	public WebElement getMenuLink()
	{
		return menuLink;
	}
}
