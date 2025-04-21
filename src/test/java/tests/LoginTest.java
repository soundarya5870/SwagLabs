package tests;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.baseClass;
import dataproviders.DataProviders;
import pages.LoginPage;

public class LoginTest extends baseClass {
	@BeforeTest
	public void setup() {
	    System.out.println("Running setup for LoginTest");
	}
     @Test(dataProvider = "loginData", dataProviderClass = dataproviders.DataProviders.class)
	public void testLoginWithValidCredentials(String username, String password)
	{
    	 if (username == null || username.isEmpty()) {
    		    System.out.println("DataProvider might be empty or not loaded correctly.");
    		}
    	 System.out.println("==> Running: " + username);
    	 ExtentTest test = extent.createTest("Valid Login Test: " + username);
		LoginPage loginpage=new LoginPage(driver);
		test.log(Status.INFO, "Navigated to Login Page");
		loginpage.enterUsername(username);
		test.log(Status.INFO, "Entered username: " + username);
		System.out.println("Running test for" +username);
		loginpage.enterPassword(password);
		test.log(Status.INFO, "Entered password");
		loginpage.clickLogin();
		test.log(Status.INFO, "Clicked login button");
	}

     public void teardown()
     {
    	 System.out.println("This is exit");
     }
     @Test
     public void testLoginWithInvalidCredentials()
 	{
 		LoginPage loginpage=new LoginPage(driver);
 		loginpage.enterUsername("standard_user");
 		loginpage.enterPassword("secret_sauce123");
 		loginpage.clickLogin();

 		String errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Epic sadface: Username and password')]")).getText();
        Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");
 	}
     @Test
     public void LoginWithBlankCredentials()
  	{
  		LoginPage loginpage=new LoginPage(driver);
  		loginpage.enterUsername("");
  		loginpage.enterPassword("");
  		loginpage.clickLogin();

  		String errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Epic sadface: Username is required')]")).getText();
         Assert.assertEquals(errorMsg, "Epic sadface: Username is required");
  	}

}
