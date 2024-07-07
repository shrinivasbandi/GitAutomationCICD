package bandiacademy.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bandiacademy.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver =  driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".col-lg-4")
	List<WebElement> products;
	
	By productsBy = By.cssSelector(".col-lg-4");
	
	By addToCart = By.cssSelector("button:last-of-type");
	
	By toast = By.cssSelector(".toast-container");
	
	By animate = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList()
	{
		waitForVisibility(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement product = getProductList().stream().filter(p -> 
		p.findElement(By.cssSelector("h5")).getText().equals(productName)).findFirst().orElse(null);
		return product;
	}
	
	public void addToCart(String productName) throws InterruptedException
	{
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		
		waitForVisibility(toast);
		//waitForInvisibility(animate);
		Thread.sleep(2000);
	
	}
	
}
