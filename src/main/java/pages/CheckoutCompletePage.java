package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class CheckoutCompletePage extends BasePage {

    private final By COMPLETE_TITLE = By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutCompletePage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_TITLE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't opened");
        }
        return this;
    }

    @Override
    public CheckoutCompletePage open() {
        log.info("Opening CheckoutCompletePage");
        driver.get(BASE_URL + "checkout-complete.html");
        return this;
    }

    public String getCompleteTitle() {
        log.info("Getting complete title");
        return driver.findElement(COMPLETE_TITLE).getText();
    }
}
