package Automation.Framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.AbstractComponents.AbstractComponents;


public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".action__submit")
	WebElement order;
	
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()	
	{
		order.click();
		return new ConfirmationPage(driver);
		
	}
	
	

}
