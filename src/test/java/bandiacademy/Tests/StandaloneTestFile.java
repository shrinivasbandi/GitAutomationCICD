package bandiacademy.Tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandaloneTestFile {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("shrinivasbandi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("@Swarit101213");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		
		WebElement product = products.stream().filter(p -> 
		p.findElement(By.cssSelector("h5")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		
		product.findElement(By.cssSelector("button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3")); 
		
		boolean match = cartItems.stream().anyMatch(p -> p.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		
		assertTrue(match);
		
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		
		Actions a = new Actions(driver);
		
		//driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-child(3)")).click();
		
		//driver.findElement(By.cssSelector(".action__submit")).click();  // was not working
		a.moveToElement((driver.findElement(By.cssSelector(".action__submit")))).click().build().perform();
		
		String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		assertTrue(confirmation.equalsIgnoreCase("Thankyou for the order."));
	
		
	}


}
