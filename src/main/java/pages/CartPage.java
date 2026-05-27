package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final By CART_TITLE = By.xpath("//span[@data-test='title']");
    private final By REMOVE_BUTTON = By.cssSelector("[id*='remove']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Cart")
    public void open() {
        driver.get(BASE_URL + "/cart.html");
    }

    @Step("Нажатие на кнопку 'Checkout'")
    public void clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public String getCartTitle() {
        return driver.findElement(CART_TITLE).getText();
    }

    @Step("Нажатие на кнопку 'Remove' в корзине")
    public void clickRemoveButton() {
        driver.findElement(REMOVE_BUTTON).click();
    }

    public int getCountProductsInCart() {
        return driver.findElements(REMOVE_BUTTON).size();
    }
}
