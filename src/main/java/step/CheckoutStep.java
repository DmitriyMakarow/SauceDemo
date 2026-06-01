package step;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.CheckoutCompletePage;
import pages.CheckoutPage;

@Log4j2
public class CheckoutStep {

    WebDriver driver;
    CheckoutPage checkoutPage;

    public CheckoutStep(WebDriver driver) {
        this.driver = driver;
        checkoutPage = new CheckoutPage(driver);
    }

    public CheckoutCompletePage placingOrderWithPositiveData(String firstName, String lastName, String postalCode) {
        log.info("Placing order with positive data");
        checkoutPage.isPageOpened()
                .fillTextField(firstName, lastName, postalCode)
                .isPageOpened()
                .clickFinishButton();
        return new CheckoutCompletePage(driver);
    }

    public CheckoutStep placingOrderWithNegativeData (String firstName, String lastName, String postalCode) {
        log.info("Placing order with negative data");
        checkoutPage.isPageOpened()
                .fillTextFieldWithNegativeData(firstName, lastName, postalCode);
        return this;
    }
}
