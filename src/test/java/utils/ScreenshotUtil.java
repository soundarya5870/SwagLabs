package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "test-output/screenshots/" + testName + "_" + timestamp + ".png";
        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
	public static void cleanScreenshotFolder() {
        File screenshotDir = new File("test-output/screenshots");
        if (screenshotDir.exists()) {
            for (File file : screenshotDir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".png")) {
                    file.delete();
                }
            }
        } else {
            screenshotDir.mkdirs();
        }

	}
}
