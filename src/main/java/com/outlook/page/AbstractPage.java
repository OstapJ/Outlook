package com.outlook.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage
{
	protected WebDriver driver;
	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractPage.class);

	public AbstractPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	/**
	 * Returns the page title
	 *
	 * @return
	 */
	public String getPageTitle()
	{
		String text = driver.getTitle();
		LOGGER.debug("Obtained the '{}' page title", text);
		return text;
	}
}
