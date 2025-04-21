package base;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {


	protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;
    private static ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();
	 @BeforeSuite
	    public void reportSetup() {
		 ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
		    extent = new ExtentReports();
		    extent.attachReporter(htmlReporter);
	    }
	   @BeforeMethod
		// TODO Auto-generated method stub
		public void setup(Method method)
		{
		WebDriverManager.chromedriver().setup();
		  driver=new ChromeDriver();
		  driver.get("https://www.saucedemo.com/");
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		  testThreadLocal.set(extent.createTest(method.getName()));
		  test = testThreadLocal.get();
	}
	   @AfterMethod
		public void teardown(ITestResult result)
		{

		   if (result.getStatus() == ITestResult.FAILURE) {
	            test.fail("Test Failed: " + result.getThrowable());
	        } else if (result.getStatus() == ITestResult.SUCCESS) {
	            test.pass("Test Passed");
	        }
	        driver.quit();
	        }


@AfterSuite
public void reportTearDown() {
    extent.flush();
}
}
