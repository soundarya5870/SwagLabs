package tests;

import base.baseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CheckoutTest extends baseClass {

    @Test
    public void testCheckoutProcess() {
        test = extent.createTest("Checkout Info Test");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        test.pass("Login successful");

        InventoryPage inventory = new InventoryPage(driver);
        inventory.addItemToCart();
        inventory.goToCart();
        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterCustomerInfo("John", "Doe", "12345");
        test.pass("Entered customer info");
        checkout.continueToOverview();
        test.pass("Continued to overview");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-two"), "Checkout step two not reached");
    }
}
