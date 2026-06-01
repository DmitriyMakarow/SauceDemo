package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
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
        log.info("Opening ProductsPage");
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }

    @Override
    public ProductsPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't opened");
        }
        return this;
    }

    public String getTitle() {
        log.info("Getting products title");
        return driver.findElement(TITLE).getText();
    }

    @Step("Нажатие на кнопку 'Cart' для перехода в корзину")
    public CartPage clickCartLinkButton() {
        log.info("Clicking Cart button");
        driver.findElement(CART_LINK).click();
        return new CartPage(driver);
    }

    @Step("Добавление товара в корзину")
    public ProductsPage addProducts() {
        log.info("Add product in cart");
        driver.findElement(ADD_PRODUCT).click();
        return this;
    }

    public int getCountProducts() {
        log.info("Getting count products on ProductsPage");
        return driver.findElements(PRODUCTS).size();
    }

    @Step("Добавление товара с именем: '{product}' в корзину")
    public ProductsPage addToCart(String product) {
        log.info("Add product with name: '{}' in cart", product);
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }
}
