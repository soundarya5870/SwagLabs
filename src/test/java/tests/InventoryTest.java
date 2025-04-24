package tests;

import base.baseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends baseClass {

	@BeforeClass
	public void setup() {
	    System.out.println("Running setup for InventoryTest");
	}
    @Test
    public void testAddItemToCart() {
       // test = extent.createTest("Add Item to Cart Test");
        LoginPage loginpage=new LoginPage(baseClass.getDriver());
 		loginpage.enterUsername("standard_user");
 		loginpage.enterPassword("secret_sauce");
 		loginpage.clickLogin();
 		test.get().pass("Login successful");

 		InventoryPage inventory = new InventoryPage(baseClass.getDriver());
        inventory.addItemToCart();
        test.get().pass("Item added to cart");
        inventory.goToCart();
        test.get().pass("Navigated to cart page");

        String currentUrl = baseClass.getDriver().getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("cart"), "Cart page URL is incorrect");
    }
    public void teardown()
    {
   	 System.out.println("This is InventoryTest exit");
    }
}