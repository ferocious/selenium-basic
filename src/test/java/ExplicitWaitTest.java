import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class ExplicitWaitTest extends TestBase{

//    Otwórz stronę https://seleniumui.moderntester.pl/alerts.php
//    Kliknij w przycisk Delayed alert
//    Poczekaj na pojawienie się alertu
//    Kliknij OK w alercie
//    Asercją sprawdź czy wyświetlił się tekst OK button pressed

    String url = "https://seleniumui.moderntester.pl/alerts.php";


    @Test
    public void delayedAlertWait() {
        driver.get(url);
        driver.findElement(By.id("delayed-alert")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("delayed-alert")));

        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();

        String buttonPressedText = driver.findElement(By.id("delayed-alert-label")).getText();
        Assert.assertEquals(buttonPressedText, "OK button pressed");
    }


}
