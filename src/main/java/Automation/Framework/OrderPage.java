package Automation.Framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.AbstractComponents.AbstractComponents;


public class OrderPage extends AbstractComponents {
	
	WebDriver driver;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	

	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}


	
	

}
