import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LocatorTest {
    @Test
    public void checkLocator() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("login_credentials"));
        driver.findElement(By.name("user-name"));
        driver.findElement(By.className("form_group"));
        driver.findElement(By.tagName("h4"));

        //Авторизация
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.linkText("Sauce Labs Fleece Jacket"));
        driver.findElement(By.partialLinkText("Backpack"));

        //xpath
        driver.findElement(By.xpath("//div[@class='app_logo']"));
        driver.findElement(By.xpath("//span[text()='Products']"));
        driver.findElement(By.xpath("//div[contains(@data-test, 'price')]"));
        driver.findElement(By.xpath("//*[contains(text(), 'Labs')]"));
        driver.findElement(By.xpath("//span[text()='Products']/ancestor::div[@id='header_container']"));
        driver.findElement(By.xpath("//*[@class='inventory_item_label']//descendant::div[@class='inventory_item_desc']"));
        driver.findElement(By.xpath("//*[@class='app_logo']/following::span[@class='active_option']"));
        driver.findElement(By.xpath("//*[@id=\"item_2_title_link\"]/parent::div"));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/preceding::div[@class='bm-burger-button']"));
        driver.findElement(By.xpath("//*[@class='btn btn_primary btn_small btn_inventory ' and @id='add-to-cart-sauce-labs-backpack']"));

        //css
        driver.findElement(By.cssSelector(".inventory_item"));
        driver.findElement(By.cssSelector(".btn_primary.btn_inventory"));
        driver.findElement(By.cssSelector(".inventory_item .inventory_item_img"));
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-fleece-jacket"));
        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']"));
        driver.findElement(By.cssSelector("span[data-test='active-option']"));
        driver.findElement(By.cssSelector("[name='add-to-cart-test.allthethings()-t-shirt-(red)']"));
        driver.findElement(By.cssSelector("[class~=\"btn\"]"));
        driver.findElement(By.cssSelector("[name|=add]"));
        driver.findElement(By.cssSelector("[class^= 'social']"));
        driver.findElement(By.cssSelector("[data-test$='(red)']"));
        driver.findElement(By.cssSelector("[name*='sauce']"));

        driver.quit();
    }
}
