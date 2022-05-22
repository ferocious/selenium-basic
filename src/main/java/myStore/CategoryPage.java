package myStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryPage extends BasePage {
    public void clickProductTile(int i) {
        List<WebElement> productList2 = driver.findElements(By.className("product"));
        productList2.get(i-1).click();
        productPage = new ProductPage();
    }
}
