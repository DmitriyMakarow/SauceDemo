package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        driver.get(BASE_URL + "/cart.html");
        return this;
    }

    @Override
    public CartPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CART_TITLE));
        return this;
    }

    @Step("Нажатие на кнопку 'Checkout'")
    public CheckoutPage clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    public String getCartTitle() {
        return driver.findElement(CART_TITLE).getText();
    }

    @Step("Нажатие на кнопку 'Remove' в корзине")
    public CartPage clickRemoveButton() {
        driver.findElement(REMOVE_BUTTON).click();
        return this;
    }

    public int getCountProductsInCart() {
        return driver.findElements(REMOVE_BUTTON).size();
    }
}
