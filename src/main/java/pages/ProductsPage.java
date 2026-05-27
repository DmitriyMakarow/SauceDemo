package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By CART_LINK = By.xpath("//*[@data-test='shopping-cart-link']");
    private final By ADD_PRODUCT = By.cssSelector("[id*='add']");
    private final By PRODUCTS = By.cssSelector("[data-test='inventory-item-name']");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Inventory")
    @Override
    public ProductsPage open() {
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }

    @Override
    public ProductsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Нажатие на кнопку 'Cart' для перехода в корзину")
    public CartPage clickCartLinkButton() {
        driver.findElement(CART_LINK).click();
        return new CartPage(driver);
    }

    @Step("Добавление товара в корзину")
    public ProductsPage addProducts() {
        driver.findElement(ADD_PRODUCT).click();
        return this;
    }

    public int getCountProducts() {
        return driver.findElements(PRODUCTS).size();
    }

    @Step("Добавление товара с именем: '{product}' в корзину")
    public ProductsPage addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }
}
