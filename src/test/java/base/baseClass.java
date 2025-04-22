package base;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {


	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ExtentReports extent;

    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	 @BeforeSuite
	    public void reportSetup() {
		 if (extent == null)
		 {
		 ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
		    extent = new ExtentReports();
		    extent.attachReporter(htmlReporter);
	    }
}

	   @BeforeMethod
		// TODO Auto-generated method stub
		public void setup(Method method)
		{
		   ChromeOptions options = new ChromeOptions();
		   options.addArguments("--disable-save-password-bubble");
		   options.setExperimentalOption("prefs", Map.of(
		       "credentials_enable_service", false,
		       "profile.password_manager_enabled", false
		   ));
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
		getDriver().get("https://www.saucedemo.com/");
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    getDriver().manage().window().maximize();

	    driver.set(getDriver());
	    if (extent != null) {
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
	    }
        else {
            throw new IllegalStateException("ExtentReports not initialized");
        }
	}
	   @AfterMethod
		public void teardown(ITestResult result)
		{

		   if (result.getStatus() == ITestResult.FAILURE) {
	            test.get().fail(result.getThrowable());
	        } else if (result.getStatus() == ITestResult.SUCCESS) {
	        	test.get().pass("Test Passed");
	        }
		   if (getDriver() != null) {
			   getDriver().quit();
	           driver.remove();
		   }
		   test.remove();
	        }


@AfterSuite
public void reportTearDown() {
    extent.flush();
}
public static WebDriver getDriver() {
    return driver.get();
}

public ExtentTest getTest() {
    return test.get();
}
}