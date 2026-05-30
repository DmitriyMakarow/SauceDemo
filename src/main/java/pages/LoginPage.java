package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.sql.Time;

@Log4j2
public class LoginPage extends BasePage{

    /*
    1. Описываем в классе элементы, с которыми мы взаимодействуем
    2. Описываем методы взаимодействия с этими элементами
     */

    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login")
    @Override
    public LoginPage open() {
        log.info("Opening LoginPage");
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Page isn't opened");
        }
        return this;
    }


    @Step("Вход в магазин с именем пользователя: '{user}' и паролем: '{password}'")
    public ProductsPage login(String user, String password) {
        log.info("Log in with credential: '{}' '{}'", user, password);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        log.info("Clicking login button");
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Вход в магазин с именем пользователя: '{user}' и паролем: '{password}'")
    public LoginPage loginWithNegativeCred(String user, String password) {
        log.info("Log in with credential: '{}' '{}'", user, password);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        log.info("Clicking login button");
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    public String getErrorMessage() {
        log.info("Getting error message");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
