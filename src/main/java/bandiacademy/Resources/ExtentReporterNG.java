
package bandiacademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG 
{
  
  public static ExtentReports getReportObject() 
  {
	  
	  	String path = System.getProperty("user.dir")+ "\\reports\\SeleniumFrameworkDesign.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Extent Reports");
		reporter.config().setDocumentTitle("Test Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		return extent;  
  }
  
}
 
