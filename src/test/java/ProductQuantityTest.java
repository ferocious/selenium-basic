import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductQuantityTest extends TestBase {

    private int price;
    private WebElement inputQty;

    @BeforeMethod
    public void openPage() {
        String url = "http://146.59.32.4/index.php?id_product=5&id_product_attribute=19&rewrite=today-is-a-good-day-framed-poster&controller=product&id_lang=2#/19-dimension-40x60cm";
        driver.get(url);
        price = Integer.parseInt(driver.findElement(By.cssSelector(".current-price>span")).getAttribute("content"));
        inputQty = driver.findElement(By.id("quantity_wanted"));
    }

    @Test
    public void userCanUseKeyUpAndDownOnQuantity() {
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_DOWN);
        inputQty.sendKeys(Keys.ENTER);

        String cssButton = "#blockcart-modal .cart-content-btn a";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssButton)));

        Assert.assertEquals(driver.findElement(
                By.cssSelector("#blockart-modal .product-quantity>strong")).getText(), "5");
        Assert.assertEquals(driver.findElement(
                By.cssSelector("#blockart-modal span.subtotal")).getText(), "$" + (5 * price) + ".00");
    }

    //Zadanie 1 - napisac test ktory sprawdza ze uzytkownik moze wprowadzic quatity z klawiatury np '11' -
    // asercje na podgladzie koszyka na ilosc oraz subtotal
    @Test
    public void userCanPutQuantityFromKeyboard() {
        int number = 11;
        inputQty.clear();

        Actions action = new Actions(driver);
        action.click(inputQty).doubleClick(inputQty).sendKeys("" + number).sendKeys(Keys.ENTER).perform();
//        inputQty.sendKeys("" + number);
//        inputQty.sendKeys(Keys.ENTER);

        String btnCss = "#blockcart-modal .cart-content-btn a";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnCss)));

        Assert.assertEquals(driver.findElement(By.cssSelector("#blockcart-modal .product-quantity>strong")).getText()
                , ""+number);
        Assert.assertEquals(driver.findElement(By.cssSelector("#blockcart-modal span.subtotal")).getText()
                , "$"+(number*price)+".00");
    }


    //Zadanie 2 - napisac test ktory sprawdza ze uzytkownik moze zmienic qty
    // za pomoca dwoch przyciskow, asercje jak wyzej.
    @Test
    public void userCanChangeQtyUsingButtons() {
        WebElement arrowUpButton = driver.findElement(By.cssSelector(".input-group-btn-vertical>.bootstrap-touchspin-up"));
        WebElement arrowDownButton = driver.findElement(By.cssSelector(".input-group-btn-vertical>.bootstrap-touchspin-down"));
        WebElement addButton = driver.findElement(By.cssSelector("button.add-to-cart"));
        arrowUpButton.click();
        arrowUpButton.click();
        arrowUpButton.click();
        arrowDownButton.click();
        arrowDownButton.click();
        addButton.click();

        String btnCss = "#blockcart-modal .cart-content-btn a";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnCss)));

        Assert.assertEquals(driver.findElement(By.cssSelector("#blockcart-modal .product-quantity>strong")).getText()
                , "2");
        Assert.assertEquals(driver.findElement(By.cssSelector("#blockcart-modal span.subtotal")).getText()
                , "$"+(2*price)+".00");
    }
}
