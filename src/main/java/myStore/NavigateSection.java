package myStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NavigateSection extends BasePage {
    public static void openClothes() {
        openLink("CLOTHES");
    }
    public static void openAccessories() {
        openLink("ACCESSORIES");
    }

    private static void openLink(String text) {
        List<WebElement> topNavItems = driver.findElements(By.cssSelector("#top-menu li"));
        for (WebElement item : topNavItems)
            if (item.getText().equals(text)) {
                item.click();
                break;
            }

        categoryPage = new CategoryPage();
    }
}
