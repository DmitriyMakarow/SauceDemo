package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @Test (priority = 1,
           description = "Проверка перехода в корзину из Products Page",
           testName = "Проверка перехода в корзину из Product Page")
    @Description("Проверка перехода в корзину из Products Page")
    @Epic("E2E")
    @Feature("Checking navigation")
    @Story("Checking navigation buttons")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Makarov Dmitriy")
    public void checkCartPage() {
        loginPage.open();
        //Авторизация
        loginPage.login("standard_user", "secret_sauce");
        //Переход в корзину
        productsPage.clickCartLinkButton();
        //Проверка, что мы в корзине
        String checkCartTitle = cartPage.getCartTitle();
        assertEquals(checkCartTitle, "Your Cart");
    }

    @Test (priority = 2,
           description = "Проверка добавления товара в корзину",
           testName = "Проверка добавления товара в корзину")
    @Description("Проверка добавления товара в корзину")
    @Epic("E2E")
    @Feature("Working with the cart")
    @Story("Сhecking if an product has been added to the cart")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Makarov Dmitriy")
    public void checkCart() {
        //Авторизация
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        //Добавление товара в корзину
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        //Переход в корзину
        productsPage.clickCartLinkButton();
        //Проверка, что в корзине именно тот товар, который добавляли
        String text = driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]")).getText();
        assertEquals(text, "Sauce Labs Bolt T-Shirt");
    }

    @Test (priority = 3,
           description = "Проверка добавления и удаления товаров из корзины",
           testName = "Проверка добавления и удаления товаров из корзины")
    @Description("Проверка добавления и удаления товаров из корзины")
    @Epic("E2E")
    @Feature("Working with the cart")
    @Story("The addition and removal of products from the cart")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Makarov Dmitriy")
    public void checkAddRemoveProductsFromCart() {
        //Авторизация
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        //Добавление двух товаров в корзину
        productsPage.addProducts();
        productsPage.addProducts();
        //Переход в корзину
        productsPage.clickCartLinkButton();
        //Удаление одного товара
        cartPage.clickRemoveButton();
        cartPage.getCountProductsInCart();
        //Проверка, что в корзине остался один товар
        int sizeProductsInCart = cartPage.getCountProductsInCart();
        assertEquals(sizeProductsInCart, 1);
    }
}
