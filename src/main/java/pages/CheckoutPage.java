package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private final By CHECKOUT_TITLE = By.cssSelector("[data-test=title]");
    private final By FIRSTNAME_FIELD = By.id("first-name");
    private final By LASTNAME_FIELD = By.id("last-name");
    private final By POSTALCODE_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By ERROR_MESSAGE_CHECKOUT = By.xpath("//*[@data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Сheckout-step-one")
    @Override
    public CheckoutPage open() {
        driver.get(BASE_URL + "/checkout-step-one.html");
        return this;
    }

    public String getTitle() {
        return driver.findElement(CHECKOUT_TITLE).getText();
    }

    @Step("Оформление заказа с именем: '{firstName}', фамилией: '{lastName}' и индексом: '{postalCode}'")
    public CheckoutS2Page fillTextField(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstName);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTALCODE_FIELD).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutS2Page(driver);
    }

    @Step("Оформление заказа с именем: '{firstName}', фамилией: '{lastName}' и индексом: '{postalCode}'")
    public CheckoutPage fillTextFieldWithNegativeData(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstName);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTALCODE_FIELD).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    public String getErrorMessageCheckout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_CHECKOUT));
        return driver.findElement(ERROR_MESSAGE_CHECKOUT).getText();
    }

    @Override
    public CheckoutPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_TITLE));
        return this;
    }
}
