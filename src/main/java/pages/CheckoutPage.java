package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private final By CHECKOUT_TITLE = By.cssSelector("[data-test=title]");
    private final By FIRSTNAME_FIELD = By.id("first-name");
    private final By LASTNAME_FIELD = By.id("last-name");
    private final By POSTALCODE_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.id("continue");
    private final By FINISH_BUTTON = By.id("finish");
    private final By COMPLETE_TITLE = By.cssSelector("[data-test='complete-header']");
    private final By ERROR_MESSAGE_CHECKOUT = By.xpath("//*[@data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "/checkout-step-one.html");
    }

    public String getTitle() {
        return driver.findElement(CHECKOUT_TITLE).getText();
    }

    public void fillTextField(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstName);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTALCODE_FIELD).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public String getErrorMessageCheckout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_CHECKOUT));
        return driver.findElement(ERROR_MESSAGE_CHECKOUT).getText();
    }

    public void clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public String getCompleteTitle() {
        return driver.findElement(COMPLETE_TITLE).getText();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_TITLE));
    }
}
