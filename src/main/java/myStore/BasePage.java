package myStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasePage {
    public static WebDriver driver;

    public static HomePage homePage;
    public static SearchResultPage searchResultPage;
    public static ProductQuickView productQuickView;
    public static CartPreview cartPreview;
    public static NavigateSection navigate;
    public static CategoryPage categoryPage;
    public static ProductPage productPage;
    public static CartPage cartPage;

    public Actions actions;

    public BasePage() {
        this.actions = new Actions(driver);
    }

    public void searchFor(String text) {
        WebElement searchField = driver.findElement(By.name("s"));
        searchField.sendKeys(text);
        searchField.submit();
        searchResultPage = new SearchResultPage();
    }
}
