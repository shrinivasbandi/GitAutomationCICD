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


public class ErrorValidationStepDefinition extends BaseTest{
	
	
	  public LandingPage landingpage; 
	    
	  @Given("landed on the Ecommerce page") 
	  public void landed_on_the_ecommerce_page() throws IOException 
	  { 
		  landingpage = launchApplication(); 
	  }

	  @And("Logged into the application using username {string} and password {string}")
	  //@Given("^Logged into the application using username (.+) and password (.+) $")
	  public void Logged_into_the_application_using_username_and_Password(String userEmail, String userPassword) 
	  { 
		  landingPage.loginApplication(userEmail, userPassword); 
	  }
	  	  
	  @Then("{string} message is displayed") 
	  public void message_is_displayed(String message) 
	  { 
		  String actualErrorMessage = landingPage.getErrorMessage();
		  Assert.assertEquals(actualErrorMessage, "Incorrect email or password.");	
	  }
	 
	 

}
