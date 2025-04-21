package tests;

import base.baseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class ConfirmationTest extends baseClass {


	@BeforeClass
	public void setup() {
	    System.out.println("Running setup for ConfirmationTest");
	}
    @Test
    public void testOrderConfirmation() {
        //test = extent.createTest("Order Confirmation Test");
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
        checkout.continueToOverview();
        checkout.finishCheckout();
        test.get().pass("Order finished");

        ConfirmationPage confirmation = new ConfirmationPage(baseClass.getDriver());
        String msg = confirmation.getConfirmationText();
        Assert.assertEquals(msg, "Thank you for your order!");
        test.get().pass("Confirmation message validated");
    }
    public void teardown()
    {
   	 System.out.println("This is ConfirmationTest exit");
    }
}
