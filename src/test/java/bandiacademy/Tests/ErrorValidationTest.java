package bandiacademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import bandiacademy.PageObject.CartPage;
import bandiacademy.PageObject.ProductCatalogue;
import bandiacademy.TestComponent.BaseTest;
import bandiacademy.TestComponent.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(retryAnalyzer = Retry.class)
	public static void invalidCredentials() throws InterruptedException, IOException {
		// TODO Auto-generated method stub ..............................
		
		landingPage.loginApplication("shrinivasbandi44444@gmail.com", "@Swarit101213");
		String actualErrorMessage = landingPage.getErrorMessage();
		Assert.assertEquals(actualErrorMessage, "Incorrect email or password.");		
	}
	
	@Test
	public static void submitOrderInvalid() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("shrinivasbandi@gmail.com", "@Swarit101213");

		productCatalogue.addToCart("ADIDAS ORIGINAL");
		CartPage cartPage = productCatalogue.goToCart();
		
		boolean match = cartPage.VerifyCart("ADIDAS ORIGINAL 123456");
		Assert.assertFalse(match);
	}

}
