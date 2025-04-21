package listeners;

import base.baseClass;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;

public class TestListener extends baseClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Capture the test name
        String testName = result.getName();

        // Capture screenshot
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);

        // Log in Extent Report
        testThreadLocal.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        try {
            testThreadLocal.get().fail("Screenshot:",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
