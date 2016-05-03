package com.outlook.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WorkPage extends AbstractPage
{

	public WorkPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(css = "[alt='Perfected service']")
	private WebElement perfectedServiceImage;

	@FindBy(css = "[alt='Nike Rise 2.0']")
	private WebElement nikeRiseImage;

	@FindBy(css = "[alt='X marks the future.']")
	private WebElement xMarksFutureImage;

	@FindBy(css = "[alt='The world’s first music video that demands your attention']")
	private WebElement firstMusicVideoImage;

	public List<WebElement> getListImages()
	{
		return listImages;
	}

	@FindBy(css = "img[alt]")
	private List<WebElement> listImages;

	public WebElement getPerfectedServiceImage()
	{
		return perfectedServiceImage;
	}

	public WebElement getNikeRiseImage()
	{
		return nikeRiseImage;
	}

	public WebElement getxMarksFutureImage()
	{
		return xMarksFutureImage;
	}

	public WebElement getFirstMusicVideoImage()
	{
		return firstMusicVideoImage;
	}

}
