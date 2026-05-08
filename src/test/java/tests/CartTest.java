package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @Test (priority = 1,
           description = "Проверка перехода в корзину из Products Page",
           testName = "Проверка перехода в корзину из Product Page")
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
    public void checkCart() {
        //Авторизация
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        //Добавление товара в корзину
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")).click();
        //Переход в корзину
        productsPage.clickCartLinkButton();
        //Проверка, что в корзине именно тот товар, который добавляли
        String text = driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]")).getText();
        assertEquals(text, "Sauce Labs Bolt T-Shirt");
    }

    @Test (priority = 3,
           description = "Проверка добавления и удаления товаров из корзины",
           testName = "Проверка добавления и удаления товаров из корзины")
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
