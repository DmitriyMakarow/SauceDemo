package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutS2Page extends BasePage {

    private final By FINISH_BUTTON = By.id("finish");

    public CheckoutS2Page(WebDriver driver) {
        super(driver);
    }

    @Override
    public CheckoutS2Page isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='title']")));
        return this;
    }

    @Override
    public CheckoutS2Page open() {
        driver.get(BASE_URL + "/checkout-step-two.html");
        return this;
    }

    @Step("Нажатие на кнопку 'Finish'")
    public CheckoutCompletePage clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
        return new CheckoutCompletePage(driver);
    }
}
