package tests;

import base.baseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTest extends baseClass {

    @Test
    public void testProceedToCheckout() {
        test = extent.createTest("Proceed to Checkout Test");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        test.pass("Login successful");

        InventoryPage inventory = new InventoryPage(driver);
        inventory.addItemToCart();
        inventory.goToCart();
        test.pass("Navigated to cart page");

        CartPage cart = new CartPage(driver);
        cart.clickCheckout();
        test.pass("Clicked checkout");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-one"), "Not navigated to checkout page");
    }
}