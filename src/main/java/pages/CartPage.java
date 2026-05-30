package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class CartPage extends BasePage {

    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final By CART_TITLE = By.xpath("//span[@data-test='title']");
    private final By REMOVE_BUTTON = By.cssSelector("[id*='remove']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Cart")
    @Override
    public CartPage open() {
        log.info("CartPage opening");
        driver.get(BASE_URL + "/cart.html");
        return this;
    }

    @Override
    public CartPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't opened");
        }

        return this;
    }

    @Step("Нажатие на кнопку 'Checkout'")
    public CheckoutPage clickCheckoutButton() {
        log.info("Clicking checkout button");
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    public String getCartTitle() {
        log.info("Getting Cart title");
        return driver.findElement(CART_TITLE).getText();
    }

    @Step("Нажатие на кнопку 'Remove' в корзине")
    public CartPage clickRemoveButton() {
        log.info("Clicking remove button");
        driver.findElement(REMOVE_BUTTON).click();
        return this;
    }

    public int getCountProductsInCart() {
        log.info("Getting products count in cart");
        return driver.findElements(REMOVE_BUTTON).size();
    }
}
