package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @Test (description = "Проверка отображения количества товаров в Products Page",
           testName = "Проверка отображения количества товаров в Products Page")
    @Description("Проверка отображения количества товаров в Products Page")
    @Epic("E2E")
    @Feature("Checking Products")
    @Story("Checking the display of the number of products")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    @Owner("Makarov Dmitriy")
    public void checkProductsCount() {
        //Авторизация
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened();
        int sizeProductsOnPage = productsPage.getCountProducts();
        assertEquals(sizeProductsOnPage, 6);
    }
}
