package Automation.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Automation.Framework.CartPage;
import Automation.Framework.CheckoutPage;
import Automation.Framework.ConfirmationPage;
import Automation.Framework.LandingPage;
import Automation.Framework.ProductCatalogue;
import Automation.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page.")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement>products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
        CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		
		checkoutPage.selectCountry("India");
		 confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed(String string)
	{
         String confirmMessage = confirmationPage.getConfirmationMessage();
		

		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));	
		driver.close();
	}
	@Then("{string} message is displayed")
	public void something_message_is_displayed (String strArg1) throws Throwable{
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());	
	}
	

}