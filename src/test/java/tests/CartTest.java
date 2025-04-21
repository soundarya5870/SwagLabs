package tests;

import base.baseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTest extends baseClass {
	@BeforeClass
	public void setup() {
	    System.out.println("Running setup for CartTest");
	}
    @Test
    public void testProceedToCheckout() {
       // test = extent.createTest("Proceed to Checkout Test");
        LoginPage login = new LoginPage(baseClass.getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        test.get().pass("Login successful");

        InventoryPage inventory = new InventoryPage(baseClass.getDriver());
        inventory.addItemToCart();
        inventory.goToCart();
        test.get().pass("Navigated to cart page");

        CartPage cart = new CartPage(baseClass.getDriver());
        cart.clickCheckout();
        test.get().pass("Clicked checkout");

        String currentUrl = baseClass.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-one"), "Not navigated to checkout page");


    }
    public void teardown()
    {
   	 System.out.println("This is CartTest exit");
    }
}