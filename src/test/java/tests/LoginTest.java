package tests;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import base.baseClass;
import dataproviders.DataProviders;
import pages.LoginPage;

public class LoginTest extends baseClass {
	@BeforeClass
	public void setup() {
	    System.out.println("Running setup for LoginTest");
	}
	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void testLoginWithValidCredentials(String username, String password) {
        if (username == null || username.isEmpty()) {
            System.out.println("DataProvider might be empty or not loaded correctly.");
        }
        test.get().log(Status.INFO, "Navigated to Login Page");

        LoginPage loginpage = new LoginPage(baseClass.getDriver());
        loginpage.enterUsername(username);
        test.get().log(Status.INFO, "Entered username: " + username);

        loginpage.enterPassword(password);
        test.get().log(Status.INFO, "Entered password");

        loginpage.clickLogin();
        test.get().log(Status.INFO, "Clicked login button");
    }

     public void teardown()
     {
    	 System.out.println("This is exit");
     }
     @Test
     public void testLoginWithInvalidCredentials()
 	{
 		LoginPage loginpage=new LoginPage(baseClass.getDriver());
 		loginpage.enterUsername("standard_user");
 		loginpage.enterPassword("secret_sauce123");
 		loginpage.clickLogin();

 		String errorMsg = baseClass.getDriver().findElement(By.xpath("//*[contains(text(),'Epic sadface: Username and password')]")).getText();
        Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");
 	}
     @Test
     public void LoginWithBlankCredentials()
  	{
  		LoginPage loginpage=new LoginPage(baseClass.getDriver());
  		loginpage.enterUsername("");
  		loginpage.enterPassword("");
  		loginpage.clickLogin();

  		String errorMsg = baseClass.getDriver().findElement(By.xpath("//*[contains(text(),'Epic sadface: Username is required')]")).getText();
         Assert.assertEquals(errorMsg, "Epic sadface: Username is required");
  	}

}
