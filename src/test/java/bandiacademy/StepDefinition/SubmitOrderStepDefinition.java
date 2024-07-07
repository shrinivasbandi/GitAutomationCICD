package bandiacademy.StepDefinition;

import java.io.IOException;

import org.testng.Assert;

import bandiacademy.PageObject.CartPage;
import bandiacademy.PageObject.CheckoutPage;
import bandiacademy.PageObject.ConfirmationPage;
import bandiacademy.PageObject.LandingPage;
import bandiacademy.PageObject.ProductCatalogue;
import bandiacademy.TestComponent.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class SubmitOrderStepDefinition extends BaseTest{
	
	
	  public LandingPage landingpage; 
	  public ProductCatalogue productCatalogue;
	  public CheckoutPage checkoutPage; 
	  public ConfirmationPage confirmPage;
	  
	  @Given("landed on Ecommerce page") 
	  public void landed_on_ecommerce_page() throws IOException 
	  { 
		  landingpage = launchApplication(); 
	  }

	  @Given("Logged into application using username {string} and password {string}")
	  //@Given("^Logged into application using username (.+) and password (.+) $")
	  public void Logged_into_application_using_username_and_Password(String userEmail, String userPassword) 
	  { 
		  productCatalogue = landingPage.loginApplication(userEmail, userPassword); 
	  }
	  	  
	  @When("I added the product {string} on cart") 
	  //@When("^I added the product (.+) on cart$") 
	  public void I_added_the_product_on_cart(String productName) throws Throwable 
	  {
		  productCatalogue.addToCart(productName); 
	  }
	  
	  @And("Checkout {string} and submit the order")
	  //@And("^Checkout (.+) and submit the order$") 
	  public void Checkout_and_submit_the_order(String productName) 
	  { 
		  CartPage cartPage = productCatalogue.goToCart(); 
		  boolean match = cartPage.VerifyCart(productName); Assert.assertTrue(match); 
		  checkoutPage = cartPage.checkout(); 
		  checkoutPage.selectCountry("india"); 
		  confirmPage = checkoutPage.submitOrder(); 
	  }
	  
	  @Then("{string} message is displayed on confirmation page.") 
	  public void message_is_displayed_on_confirmation_page(String message) 
	  { 
		  String actualMsg = confirmPage.getConfirmationMessage();
		  Assert.assertTrue(actualMsg.equalsIgnoreCase(message)); 
	  }
	 
	 

}
