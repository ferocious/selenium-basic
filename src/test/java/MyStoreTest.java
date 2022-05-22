import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static myStore.BasePage.driver;

public class MyStoreTest {
    @Test
    public void verifySearchAndAddToCart() throws InterruptedException {
        driver.get("http://146.59.32.4");
        WebElement searchField = driver.findElement(By.name("s"));
        searchField.sendKeys("poster");
        searchField.submit();
        List<Float> itemPrice = new ArrayList<>();
        List<Integer> itemQty = new ArrayList<>();
        List<WebElement> productList = driver.findElements(By.className("product"));
        Assert.assertEquals(productList.size(), 3);

        Actions act = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i = 0; i < productList.size(); i++) {
            WebElement item = productList.get(i);
            WebElement titleEl =item.findElement(By.cssSelector(".h3.product-title"));
            String title = titleEl.getText();
            if (title.equals("TODAY POSTER")) {
                //wersja js jesli juz wszystko zawiedzie
//                String css = ".product:nth-of-type("+(i+1)+") .quick-view";
//                String jsScript = "$('"+css+"').click();";
//                js.executeScript(jsScript);
                //wersja z explicit wait
                WebElement quickView = item.findElement(By.cssSelector(".quick-view"));
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                act.moveToElement(titleEl).perform();
                wait.until(ExpectedConditions.visibilityOf(quickView));
                quickView.click();
                break;
            }
        }



        String selectCss = ".modal-body .product-variants select";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selectCss)));

        String oldPrice = driver.findElement(By.cssSelector("div[id^='quickview-modal'] .current-price>span")).getAttribute("content");
        Select variants = new Select(driver.findElement(By.cssSelector(selectCss)));
        variants.selectByVisibleText("80x120cm");

        wait.until(c -> !driver.findElement(By.cssSelector("div[id^='quickview-modal'] .current-price>span")).getAttribute("content").equals(oldPrice));


        itemPrice.add(Float.parseFloat(driver.findElement(By.cssSelector("div[id^='quickview-modal'] .current-price>span")).getAttribute("content")));
        WebElement qty = driver.findElement(By.cssSelector(".modal-body input[name='qty']"));
        qty.clear();
        qty.sendKeys("2");
        itemQty.add(2);
        //qty.submit();
        qty.sendKeys(Keys.ENTER);



        WebElement closeBtn = driver.findElement(By.cssSelector("#blockcart-modal button.close"));
        wait.until(ExpectedConditions.elementToBeClickable(closeBtn));
        closeBtn.click();


        List<WebElement> topNavItems = driver.findElements(By.cssSelector("#top-menu li"));
        for (WebElement item : topNavItems)
            if (item.getText().equals("CLOTHES")) {
                item.click();
                break;
            }

        List<WebElement> productList2 = driver.findElements(By.className("product"));
        productList2.get(0).click();

        itemPrice.add(Float.parseFloat(driver.findElement(By.cssSelector(".current-price>span[content]")).getAttribute("content")));
        itemQty.add(1);
        driver.findElement(By.cssSelector("button.add-to-cart")).click();

        String btnCss = "#blockcart-modal .cart-content-btn a";
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(btnCss)));
        proceed.click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector("ul.cart-items>li"));

        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');

        DecimalFormat formatter = new DecimalFormat("0.00", otherSymbols);
        for (int i=0; i<cartItems.size(); i++) {
            WebElement item = cartItems.get(i);

            String expectedPrice = formatter.format(itemPrice.get(i)*itemQty.get(i));

            Assert.assertEquals(item.findElement(By.name("product-quantity-spin")).getAttribute("value"), ""+itemQty.get(i));
            Assert.assertEquals(item.findElement(By.cssSelector("div.product-line-actions .product-price")).getText()
                    , "$"+expectedPrice);
        }

        float itemsSubtotal = 0;
        for (int i=0; i< itemPrice.size(); i++)
            itemsSubtotal+=itemPrice.get(i)*itemQty.get(i);
        float itemsTotal = itemsSubtotal+7;

        String cartTotalStr = driver.findElement(By.cssSelector(".cart-total>.value")).getText();
        String cartSubTotal = driver.findElement(By.cssSelector("#cart-subtotal-products>.value")).getText();

        Assert.assertEquals(cartSubTotal,"$"+formatter.format(itemsSubtotal));
        Assert.assertEquals(cartTotalStr,"$"+formatter.format(itemsTotal));
    }
}
