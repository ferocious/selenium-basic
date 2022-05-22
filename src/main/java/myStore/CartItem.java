package myStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartItem {
    WebDriver driver;
    WebElement itemElement;


    public CartItem(WebDriver driver, WebElement itemElement) {
        this.driver = driver;
        this.itemElement = itemElement;
    }


    public int getQty() {
        return Integer.parseInt(itemElement.findElement(By.name("product-quantity-spin")).getAttribute("value"));
    }

    public String getTotalPrice() {
        return itemElement.findElement(By.cssSelector("div.product-line-actions .product-price")).getText();
    }
}
