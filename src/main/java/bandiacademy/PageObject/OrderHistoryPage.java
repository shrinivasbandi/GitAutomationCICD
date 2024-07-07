package bandiacademy.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bandiacademy.AbstractComponent.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderHistoryPage(WebDriver driver)
	{
		super(driver);
		this.driver =  driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderProductList;
	
	@FindBy(xpath = "//button[text()='View']")
	WebElement orderView;
	
	
	public boolean verifyProductInOrderHistory(String productName)
	{
		waitForVisibilityOf(orderView);
		boolean match =  orderProductList.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
