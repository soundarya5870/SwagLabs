package ui;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class homepage {
	public static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  WebDriverManager.chromedriver().setup();
  driver=new ChromeDriver();
  driver.get("https://the-internet.herokuapp.com//");
  driver.manage().timeouts().implicitlyWait(400, TimeUnit.SECONDS);
  driver.manage().window().maximize();
  String name=driver.getTitle();
  System.out.println(name);
  TakesScreenshot ts = (TakesScreenshot)driver;

//Capture the screenshot and save it as a file
File screenshotFile = ts.getScreenshotAs(OutputType.FILE);

//Save the captured screenshot to a desired location on your computer
try {
FileUtils.copyFile(screenshotFile, new File("C:\\Users\\Admin\\Desktop\\Interview Prep\\screenshot.png"));
} catch (Exception e) {
e.printStackTrace();
}

//Close the browser
  driver.close();
	}

}
 