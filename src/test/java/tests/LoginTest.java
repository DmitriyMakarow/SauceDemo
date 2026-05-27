package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test (description = "Проверка входа в систему с позитивными кредами",
           testName = "Проверка входа в систему с позитивными кредами")
    @Description("Проверка входа в систему с позитивными кредами")
    @Epic("E2E")
    @Feature("Login in to SauceDemo")
    @Story("Positive Login")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Makarov Dmitriy")
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

        @DataProvider(name = "Тестовые данные для негативного логина")
        public Object[] [] loginData() {
            return new Object[][] {
                    {"", "secret_sauce", "Epic sadface: Username is required"},
                    {"standard_user", "", "Epic sadface: Password is required"},
                    {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
            };
    }

        @Test (dataProvider = "Тестовые данные для негативного логина",
               description = "Проверка входа в систему с негативными данными",
               testName = "Проверка входа в систему с негативными данными")
        @Description("Проверка входа в систему с негативными данными")
        @Epic("E2E")
        @Feature("Login in to SauceDemo")
        @Story("Negative Login")
        @Severity(SeverityLevel.CRITICAL)
        @Link("https://www.saucedemo.com/")
        @TmsLink("ITM-5")
        @Issue("ITM-5")
        @Owner("Makarov Dmitriy")
        public void negativeLogin(String user, String password, String errorMessage) {
            loginPage.open();
            loginPage.login(user, password);
            assertEquals(loginPage.getErrorMessage(), errorMessage);
        }
    }
