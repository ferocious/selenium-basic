import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartAndDeleteTest extends TestBase {

    String url = "http://146.59.32.4/index.php?id_product=6&rewrite=mug-the-best-is-yet-to-come&controller=product&id_lang=2";

    @Test
    public void addToCartAndDeleteTest() {
        driver.get(url);
        driver.findElement(By.className("add-to-cart")).click();
        String cssButton = "#blockart-modal .cart-content.btn a";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssButton)));
        btn.click();

        String cssBin = ".remove-from-cart";

        driver.findElement(By.cssSelector(cssBin)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cssBin)));
        Assert.assertEquals(driver.findElement(By.cssSelector(".cart-overview")).getText(),
                "There are no more items in your cart");
    }
}
