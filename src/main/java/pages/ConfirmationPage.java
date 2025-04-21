package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By confirmationMsg = By.className("complete-header");

    public String getConfirmationText() {
        return driver.findElement(confirmationMsg).getText();
    }
}