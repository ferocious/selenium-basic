package myStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductQuickView extends BasePage {
    String addToCartButtonCss = ".modal-body button[type=\"submit\"]";

    public ProductQuickView() {
        this.waitForModal();
    }

    private void waitForModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(addToCartButtonCss)));
    }

    public void changeProductVariant(String visibleText) {

        String selectCss = ".modal-body .product-variants select";
        String oldPrice = driver.findElement(By.cssSelector("div[id^='quickview-modal'] .current-price>span")).getAttribute("content");

        WebElement optionSelected = driver.findElement(By.cssSelector(selectCss+">option[selected]"));
        if (optionSelected.getText().equals(visibleText))
            return;

        Select variants = new Select(driver.findElement(By.cssSelector(selectCss)));
        variants.selectByVisibleText(visibleText);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(c -> !driver.findElement(By.cssSelector("div[id^='quickview-modal'] .current-price>span")).getAttribute("content").equals(oldPrice));
    }

    public Float getItemPrice() {
        return Float.parseFloat(driver.findElement(By.cssSelector("div[id^='quickview-modal'] .current-price>span")).getAttribute("content"));
    }

    public void changeQuantity(int i) {
        WebElement qty = driver.findElement(By.cssSelector(".modal-body input[name='qty']"));
        qty.clear();
        qty.sendKeys(""+i);
    }

    public void addToCart() {
        WebElement addButton = driver.findElement(By.cssSelector(addToCartButtonCss));
        addButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.stalenessOf(addButton));
        cartPreview = new CartPreview();
    }
}
