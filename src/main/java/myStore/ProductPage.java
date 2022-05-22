package myStore;

import org.openqa.selenium.By;

public class ProductPage extends BasePage {
    public Float getItemPrice() {
        return Float.parseFloat(driver.findElement(By.cssSelector(".current-price>span[content]")).getAttribute("content"));
    }

    public void addToBasket() {
        driver.findElement(By.cssSelector("button.add-to-cart")).click();
        cartPreview = new CartPreview();
    }
}
