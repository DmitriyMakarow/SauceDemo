package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @Test (description = "Проверка отображения количества товаров в Products Page",
           testName = "Проверка отображения количества товаров в Products Page")
    public void checkProductsCount() {
        //Авторизация
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        //Проверка, что отображается 6 товаров на странице
        int sizeProductsOnPage = productsPage.getCountProducts();
        assertEquals(sizeProductsOnPage, 6);
    }
}
