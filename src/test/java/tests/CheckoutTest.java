package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import step.CheckoutStep;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test (description = "Проверка оформления заказа с позитивными данными",
           testName = "Проверка оформления заказа с позитивными данными")
    @Description("Проверка оформления заказа с позитивными данными")
    @Epic("E2E")
    @Feature("Checking your order in SauceDemo")
    @Story("Positive Order")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Makarov Dmitriy")
    public void checkCheckoutWithPositiveData() {
        loginStep.auth("standard_user", "secret_sauce")
                .isPageOpened()
                .addProducts()
                .clickCartLinkButton()
                .isPageOpened()
                .clickCheckoutButton();
        checkoutStep.placingOrderWithPositiveData("test" ,"test","190000");
        assertEquals(checkoutCompletePage.getCompleteTitle(), "Thank you for your order!");
    }

    @DataProvider (name = "Тестовые данные для негативного оформления заказа")
    public Object [] [] checkoutData() {
        return new Object[][] {
                {"", "test", "000000", "Error: First Name is required"},
                {"test", "", "000000", "Error: Last Name is required"},
                {"test", "test", "", "Error: Postal Code is required"},
        };
    }

    @Test (dataProvider = "Тестовые данные для негативного оформления заказа",
           description = "Проверка оформления заказа с негативными данными",
           testName = "Проверка оформления заказа с негативными данными")
    @Description("Проверка оформления заказа с негативными данными")
    @Epic("E2E")
    @Feature("Checking your order in SauceDemo")
    @Story("Negative Order")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Makarov Dmitriy")
    public void negativeCheckout (String firstName, String lastName, String postalCode, String errorMessageCheckout) {
        loginStep.auth("standard_user", "secret_sauce")
                .isPageOpened()
                .addProducts()
                .clickCartLinkButton()
                .isPageOpened()
                .clickCheckoutButton();
        checkoutStep.placingOrderWithNegativeData(firstName, lastName, postalCode);
        assertEquals(checkoutPage.getErrorMessageCheckout(), errorMessageCheckout);
    }
}
