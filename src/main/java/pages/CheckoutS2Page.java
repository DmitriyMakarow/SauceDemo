package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class CheckoutS2Page extends BasePage {

    private final By FINISH_BUTTON = By.id("finish");

    public CheckoutS2Page(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutS2Page isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='title']")));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't opened");
        }
        return this;
    }

    @Override
    public CheckoutS2Page open() {
        log.info("Opening CheckoutStep2Page");
        driver.get(BASE_URL + "/checkout-step-two.html");
        return this;
    }

    @Step("Нажатие на кнопку 'Finish'")
    public CheckoutCompletePage clickFinishButton() {
        log.info("Clicking finish button");
        driver.findElement(FINISH_BUTTON).click();
        return new CheckoutCompletePage(driver);
    }
}
