package Automation.Framework;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import Automation.TestComponents.Retry;
import framework.AbstractComponents.AbstractComponents;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest {

	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	
	public void LogInErrorValidation() throws IOException, InterruptedException
	{
		
		
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("hb@outlook.com", "Xoon@236413");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());	
		

	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("hb@outlook.com", "Xoon@2364136");
		
		
		List<WebElement>products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage(); 
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		
		Assert.assertFalse(match);		
		System.out.println("code is in develop branch");
		System.out.println("US guy able to access code");
		
	}

}
