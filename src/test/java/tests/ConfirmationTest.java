package tests;

import base.baseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ConfirmationTest extends baseClass {

    @Test
    public void testOrderConfirmation() {
        test = extent.createTest("Order Confirmation Test");
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
        checkout.continueToOverview();
        checkout.finishCheckout();
        test.pass("Order finished");

        ConfirmationPage confirmation = new ConfirmationPage(driver);
        String msg = confirmation.getConfirmationText();
        Assert.assertEquals(msg, "Thank you for your order!");
        test.pass("Confirmation message validated");
    }
}
