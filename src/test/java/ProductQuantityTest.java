import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class ProductQuantityTest extends TestBase {

    String url = "http://146.59.32.4/index.php?id_product=6&rewrite=mug-the-best-is-yet-to-come&controller=product&id_lang=2";
    private int price;
    private WebElement inputQty;

    @BeforeMethod
    public void openPage() {
        driver.get(url);
        price = Integer.parseInt(driver.findElement(By.cssSelector(".current-price>span")).getAttribute("content"));
        inputQty = driver.findElement(By.id("quantity_wanted"));
    }

    @Test
    public void userCanUseKeyUpAndDownOnQuantity() {
        String cssButton = "#blockart-modal .cart-content.btn a";
        int price = Integer.parseInt(driver.findElement(By.cssSelector(".current-price>span")).getAttribute(""));

        WebElement inputQty = driver.findElement(By.id("quantity-wanted"));
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_UP);
        inputQty.sendKeys(Keys.ARROW_DOWN);
        inputQty.sendKeys(Keys.ENTER);


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
        price = Integer.parseInt(driver.findElement(By.cssSelector(".current-price>span")).getAttribute("content"));
        inputQty = driver.findElement(By.id("quantity_wanted"));
        WebElement inputQty = driver.findElement(By.id("quantity-wanted"));

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


    //Zadanie 2 - napisac test ktory sprawdza ze uzytkownik moze zmienic qty za pomoca dwoch przyciskow, asercje jak wyzej.
    @Test
    public void userCanChangeQtyUsingButtons() {
        WebElement arrowUpButton = driver.findElement(By.id(".input-group-btn-vertical>.bootstrap-touchspin-up"));
        WebElement arrowDownButton = driver.findElement(By.id(".input-group-btn-vertical>.bootstrap-touchspin-down"));
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
