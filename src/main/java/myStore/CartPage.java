package myStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{
    List<CartItem> cartItems = new ArrayList<>();

    public CartPage() {
        readItems();
    }

    private void readItems() {
        List<WebElement> cartItems = driver.findElements(By.cssSelector("ul.cart-items>li"));
        for (WebElement item : cartItems ) {
            this.cartItems.add(new CartItem(driver, item));
        }
    }

    public CartItem getCartItem(int i) {
        return cartItems.get(i);
    }

    public String getCartSubtotal() {
        return driver.findElement(By.cssSelector("#cart-subtotal-products>.value")).getText();
    }

    public String getCartTotal() {
        return driver.findElement(By.cssSelector(".cart-total>.value")).getText();
    }
}
