package bandiacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bandiacademy.PageObject.CartPage;
import bandiacademy.PageObject.CheckoutPage;
import bandiacademy.PageObject.ConfirmationPage;
import bandiacademy.PageObject.OrderHistoryPage;
import bandiacademy.PageObject.ProductCatalogue;
import bandiacademy.TestComponent.BaseTest;

public class SubmitOrderTest extends BaseTest {

	static String productName2 = "ADIDAS ORIGINAL";
	static String countryName = "india";
	

	@Test(dataProvider = "getData")
	public static void submitOrder(String userEmail, String userPassword, String productName) throws InterruptedException, IOException {
	//public static void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {	
		// TODO Auto-generated method stub
	
		ProductCatalogue productCatalogue = landingPage.loginApplication(userEmail, userPassword);
		//ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		productCatalogue.addToCart(productName);
		//productCatalogue.addToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCart();
		
		boolean match = cartPage.VerifyCart(productName);
		//boolean match = cartPage.VerifyCart(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.checkout();
			
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmPage = checkoutPage.submitOrder();
		
		String actualMsg = confirmPage.getConfirmationMessage();
		Assert.assertTrue(actualMsg.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public static void validateOrderHistory() throws InterruptedException, IOException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("shrinivasbandi@gmail.com", "@Swarit101213");
		OrderHistoryPage orders = productCatalogue.goToOrder();
		Assert.assertTrue(orders.verifyProductInOrderHistory(productName2));
		
	}
	
	
	
	  @DataProvider public Object[][] getData() { return new Object[][] {
	  {"shrinivasbandi@gmail.com","@Swarit101213", "ADIDAS ORIGINAL"},
	  {"sbandi1979@gmail.com", "@Chinna101213", "IPHONE 13 PRO"} };
	  
	  }
	 
	 
	
	/*
	 * @DataProvider 
	 * public Object[][] getData() throws IOException { String
	 * filepath = System.getProperty("user-dir") +
	 * "\\src\\test\\java\\bandiacademy\\data\\LoginData.json";
	 * List<HashMap<String,String>> data = getDataReader(filepath); return new
	 * Object[][] { {data.get(0)}, {data.get(1)} }; }
	 */
	
}
