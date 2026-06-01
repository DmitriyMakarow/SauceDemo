package step;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;

@Log4j2
public class LoginStep {

    WebDriver driver;
    LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public ProductsPage auth(String user, String password) {
        log.info("Auth with positive cred");
        loginPage.open()
                .isPageOpened()
                .login(user, password);
        return new ProductsPage(driver);
    }

    public LoginStep authWithNegativeCred(String user, String password) {
        log.info("Auth with negative cred");
        loginPage.open()
                .isPageOpened()
                .loginWithNegativeCred(user, password);
        return this;
    }
}
