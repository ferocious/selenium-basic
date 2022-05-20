import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TableTest extends TestBase{
    private String url="https://seleniumui.moderntester.pl/table.php";

    @Test
    public void shouldBe9rowsAbove4000() {
        driver.get(url);
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));
        int count = 0;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String nazwa = cells.get(0).getText();
            String pasmo = cells.get(1).getText();
            String panstwo = cells.get(2).getText();
            Integer wysokosc = Integer.valueOf(cells.get(3).getText());

            if (wysokosc > 4000) {
                System.out.println(nazwa + " " + pasmo + " " + panstwo + " " + wysokosc);
                count++;
            }
        }
        Assert.assertEquals(count, 9);
    }
}
