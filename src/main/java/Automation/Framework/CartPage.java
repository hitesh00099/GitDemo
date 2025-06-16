package Automation.Framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.AbstractComponents.AbstractComponents;


public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}



	
	

}
