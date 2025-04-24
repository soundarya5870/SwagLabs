package base;

import java.lang.reflect.Method;
import java.util.HashMap;
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
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class baseClass {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void reportSetup() {
    	ScreenshotUtil.cleanScreenshotFolder();
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setup(Method method) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        WebDriver localDriver = new ChromeDriver(options);
        driver.set(localDriver);
        localDriver.get("https://www.saucedemo.com/");
        localDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        localDriver.manage().window().maximize();

        driver.set(localDriver);

        ExtentReports ext = ExtentManager.getInstance();
        ExtentTest extentTest = ext.createTest(method.getName());
        test.set(extentTest);
    }

    @AfterMethod
    public void teardown(ITestResult result) {
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

    @AfterSuite(alwaysRun = true)
    public void reportTearDown() {
    	//if (extent != null) {
        //    System.out.println("Flushing extent report...");
       //     extent.flush();
       // } else {
        //    System.out.println("Extent report was not initialized.");
        //}
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public ExtentTest getTest() {
        return test.get();
    }
}
