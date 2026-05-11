package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test (description = "Проверка оформления заказа с позитивными данными",
           testName = "Проверка оформления заказа с позитивными данными")
    public void checkCheckoutWithPositiveData() {
        //Авторизация
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        //Добавление товара
        productsPage.addProducts();
        //Переход в корзину
        productsPage.clickCartLinkButton();
        //Переход к странице оформления заказа
        cartPage.clickCheckoutButton();
        checkoutPage.isPageOpened();
        //Заполнение полей валидными данными
        checkoutPage.fillTextField("test", "test", "000000");
        //Нажатие на кнопку финиш
        checkoutPage.clickFinishButton();
        //Проверка, что заказ оформлен
        assertEquals(checkoutPage.getCompleteTitle(), "Thank you for your order!");
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
    public void negativeCheckout (String firstName, String lastName, String postalCode, String errorMessageCheckout) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProducts();
        productsPage.clickCartLinkButton();
        cartPage.clickCheckoutButton();
        checkoutPage.fillTextField(firstName, lastName, postalCode);
        assertEquals(checkoutPage.getErrorMessageCheckout(), errorMessageCheckout);
    }
}
