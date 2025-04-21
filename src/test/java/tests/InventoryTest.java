package tests;

import base.baseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends baseClass {

    @Test
    public void testAddItemToCart() {
        test = extent.createTest("Add Item to Cart Test");
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();
        test.pass("Login successful");

        InventoryPage inventory = new InventoryPage(driver);
        inventory.addItemToCart();
        test.pass("Item added to cart");
        inventory.goToCart();
        test.pass("Navigated to cart page");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("cart"), "Cart page URL is incorrect");
    }
}