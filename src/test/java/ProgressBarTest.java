import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProgressBarTest extends TestBase{

    String url = "https://seleniumui.moderntester.pl/progressbar.php";

    @Test
    public void isComplete() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(url);
        driver.findElement(By.cssSelector("#progressbar>div.ui-progressbar-complete"));
    }

    @Test
    public void progressBarIsCompleted() {
        String progressBarTest = "Complete!";
        driver.get(url);

        WebElement label = driver.findElement(By.cssSelector("#progressbar>.progress-label"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBePresentInElement(label, progressBarTest));
        //wait.until(c->label.getText().equals(progressBarTest));
    }

    @Test
    public void isProgressBarCompleted() {
        String progressBarCompleteText = "ui-progressbar-complete";

        driver.get(url);
        WebElement bar = driver.findElement(By.cssSelector("#progressbar>.ui-progressbar-value"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.attributeContains(bar, "class", progressBarCompleteText));

    }

    @AfterMethod
    public void resetImplicitWaitTime(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME_SECONDS));
    }
}
