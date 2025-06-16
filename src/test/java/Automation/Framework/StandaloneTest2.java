package Automation.Framework;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import framework.AbstractComponents.AbstractComponents;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest2 extends BaseTest {
	String productName = "ZARA COAT 3";
	
	

	@Test(dataProvider = "getData",groups = {"Purchase"})
	
	public void Standalone(HashMap<String,String>  input) throws IOException, InterruptedException
	{
		
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		
		List<WebElement>products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));		

	}
	
	/*@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("hb@outlook.com", "Xoon@2364136");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
	}*/
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data =getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\Automation\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	/*@DataProvider
	public Object[][] getData()
	{
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.put("email", "hb@outlook.com");
		map.put("password","Xoon@2364136");
		map.put("productName", "ZARA COAT 3");
	
		HashMap<Object,Object> map1 = new HashMap<Object,Object>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password","Iamking@000");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	}*/

}
