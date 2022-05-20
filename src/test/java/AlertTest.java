import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class AlertTest extends TestBase{

    String url = "https://seleniumui.moderntester.pl/alerts.php";

    @BeforeMethod
    public void testSetup() {
        driver.get(url);
    }

    @Test
    public void shouldAcceptAlert() {
        driver.findElement(By.id("simple-alert")).click();
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        Assert.assertEquals(driver.findElement(By.id("simple-alert-label")).getText(), "OK button pressed");
    }

    @Test
    public void shouldFillPromptAlert() {
        driver.findElement(By.id("prompt-alert")).click();
        driver.switchTo().alert().sendKeys("Dorota");
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        Assert.assertEquals(driver.findElement(By.id("prompt-label")).getText(),"Hello Dorota! How are you today?");

    }

    @Test
    public void shouldDismissAlert() {
        driver.findElement(By.id("confirm-alert")).click();
        driver.switchTo().alert().dismiss();
        driver.switchTo().defaultContent();
        Assert.assertEquals(driver.findElement(By.id("confirm-label")).getText(),"You pressed Cancel!");

    }
}
