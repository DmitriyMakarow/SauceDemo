package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test
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

    @Test
    public void checkCheckoutWithEmptyFirstName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProducts();
        productsPage.clickCartLinkButton();
        cartPage.clickCheckoutButton();
        checkoutPage.isPageOpened();
        checkoutPage.fillTextField("", "test", "000000");
        assertEquals(checkoutPage.getErrorMessageCheckout(), "Error: First Name is required");
    }

    @Test
    public void checkCheckoutWithEmptyLastName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProducts();
        productsPage.clickCartLinkButton();
        cartPage.clickCheckoutButton();
        checkoutPage.fillTextField("test", "", "000000");
        assertEquals(checkoutPage.getErrorMessageCheckout(), "Error: Last Name is required");
    }

    @Test
    public void checkCheckoutWithEmptyPostalCode() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProducts();
        productsPage.clickCartLinkButton();
        cartPage.clickCheckoutButton();
        checkoutPage.fillTextField("test", "test", "");
        assertEquals(checkoutPage.getErrorMessageCheckout(), "Error: Postal Code is required");
    }
}
