package myStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPreview extends BasePage {
    private String closeModalCss = "#blockcart-modal button.close";

    public CartPreview() {
        waitForModal();
    }

    private void waitForModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(closeModalCss)));
    }


    public void close() {
        WebElement buttonClose = driver.findElement(By.cssSelector(closeModalCss));
        buttonClose.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(buttonClose));
    }

    public void clickProceedToCheckout() {
        String btnCss = "#blockcart-modal .cart-content-btn a";
        driver.findElement(By.cssSelector(btnCss)).click();
        cartPage = new CartPage();
    }
}
