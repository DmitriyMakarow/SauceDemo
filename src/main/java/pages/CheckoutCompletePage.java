package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {

    private final By COMPLETE_TITLE = By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutCompletePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_TITLE));
        return this;
    }

    @Override
    public CheckoutCompletePage open() {
        driver.get(BASE_URL + "checkout-complete.html");
        return this;
    }

    public String getCompleteTitle() {
        return driver.findElement(COMPLETE_TITLE).getText();
    }
}
