package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @Test
    public void checkProductsCount() {
        //Авторизация
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        //Проверка, что отображается 6 товаров на странице
        int sizeProductsOnPage = productsPage.getCountProducts();
        assertEquals(sizeProductsOnPage, 6);
    }
}
