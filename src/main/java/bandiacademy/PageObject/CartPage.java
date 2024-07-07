package bandiacademy.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bandiacademy.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver =  driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkout;
	
	public boolean VerifyCart(String productName)
	{
		boolean match =  cartItems.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage checkout()
	{
		checkout.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
