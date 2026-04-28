package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By CART_LINK = By.xpath("//*[@data-test='shopping-cart-link']");
    private final By ADD_PRODUCT = By.cssSelector("[id*='add']");
    private final By PRODUCTS = By.cssSelector("[data-test='inventory-item-name']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void clickCartLinkButton() {
        driver.findElement(CART_LINK).click();
    }

    public void addProducts() {
        driver.findElement(ADD_PRODUCT).click();
    }

    public int getCountProducts() {
        return driver.findElements(PRODUCTS).size();
    }
}
