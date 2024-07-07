package bandiacademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bandiacademy.PageObject.LandingPage;

public class BaseTest {
	
	public static WebDriver driver;
	public static LandingPage landingPage;
	
	public static WebDriver Initialize() throws IOException
	{
		Properties prop = new Properties();
		//FileInputStream fis = new FileInputStream(System.getProperty("user-dir") + "\\src\\main\\java\\bandiacademy\\Resources\\GlobalData.properties");
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\bandiacademy\\Resources\\GlobalData.properties");		
		prop.load(fis);
		
		//String browserName = prop.getProperty("browser");
		//New code to check Maven parameter first else read the global data json file
		String browserName =  System.getProperty("browser") != null  ?  System.getProperty("browser") : prop.getProperty("browser"); 
		
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Testing_Automation\\Softwares\\chromedriver-win64\\chromedriver.exe");
			
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			
		}
		
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		return driver;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\" + testCaseName + ".png";
		File file = new File(path);
		FileHandler.copy(source, file);
		return path;
		
	}
	
	
	
	  public List<HashMap<String,String>> getDataReader(String filePath) throws IOException {
	  
		  String jsonContent = FileUtils.readFileToString(new File(filePath),  StandardCharsets.UTF_8); 
		  
		//String to HashMap- Jackson Databind
		  ObjectMapper mapper = new ObjectMapper();
		  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		  return data;
	  }
	 
	 
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = Initialize();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public static void tearDown()
	{
		driver.close();
	}
}
