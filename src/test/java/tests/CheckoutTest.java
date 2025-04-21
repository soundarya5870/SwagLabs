package tests;

import base.baseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CheckoutTest extends baseClass {

	@BeforeClass
	public void setup() {
	    System.out.println("Running setup for CheckoutTest");
	}
    @Test
    public void testCheckoutProcess() {
        //test = extent.createTest("Checkout Info Test");
        LoginPage login = new LoginPage(baseClass.getDriver());
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        test.get().pass("Login successful");

        InventoryPage inventory = new InventoryPage(baseClass.getDriver());
        inventory.addItemToCart();
        inventory.goToCart();
        CartPage cart = new CartPage(baseClass.getDriver());
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(baseClass.getDriver());
        checkout.enterCustomerInfo("John", "Doe", "12345");
        test.get().pass("Entered customer info");
        checkout.continueToOverview();
        test.get().pass("Continued to overview");

        String currentUrl = baseClass.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-two"), "Checkout step two not reached");
    }
    public void teardown()
    {
   	 System.out.println("This is CheckoutTest exit");
    }
}
