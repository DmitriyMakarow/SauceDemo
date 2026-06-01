package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
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
        log.info("Opening CheckoutPage");
        driver.get(BASE_URL + "/checkout-step-one.html");
        return this;
    }

    public String getTitle() {
        log.info("Getting Checkout title");
        return driver.findElement(CHECKOUT_TITLE).getText();
    }

    @Step("Оформление заказа с именем: '{firstName}', фамилией: '{lastName}' и индексом: '{postalCode}'")
    public CheckoutS2Page fillTextField(String firstName, String lastName, String postalCode) {
        log.info("Placing an order with firstName: '{}', lastName: '{}', postalCode: {}",
                firstName, lastName, postalCode);
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstName);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTALCODE_FIELD).sendKeys(postalCode);
        log.info("Clicking continue button");
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutS2Page(driver);
    }

    @Step("Оформление заказа с именем: '{firstName}', фамилией: '{lastName}' и индексом: '{postalCode}'")
    public CheckoutPage fillTextFieldWithNegativeData(String firstName, String lastName, String postalCode) {
        log.info("Placing an order with firstName: '{}', lastName: '{}', postalCode: {}",
                firstName, lastName, postalCode);
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstName);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTALCODE_FIELD).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    public String getErrorMessageCheckout() {
        log.info("Getting error message");
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_CHECKOUT));
        return driver.findElement(ERROR_MESSAGE_CHECKOUT).getText();
    }

    @Override
    public CheckoutPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_TITLE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't opened");
        }
        return this;
    }
}
