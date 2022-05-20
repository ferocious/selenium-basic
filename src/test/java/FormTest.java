import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FormTest extends TestBase{
    String url = "https://seleniumui.moderntester.pl/form.php";

    @Test
    public void shouldFillFormWithSuccess() {
        driver.get(url);
        WebElement fname = driver.findElement(By.id("inputFirstName3"));
        fname.sendKeys("Dorota");

        List<WebElement> radioSex = driver.findElements(By.name("gridRadioSex"));

        for(WebElement element : radioSex) {
            if(element.getAttribute("value").equals("other")) {
                element.click();
                break;
            }
        }

        driver.findElement(By.id("inputLastName3")).sendKeys("Dziuba");
        driver.findElement(By.id("inputEmail3")).sendKeys("dorotka.899@gmail.com");
        driver.findElement(By.id("inputAge3")).sendKeys("32");
        driver.findElement(By.cssSelector("*[name=\"gridRadiosExperience\"][value=\"7\"]")).click();
        driver.findElement(By.id("gridCheckAutomationTester")).click();
        driver.findElement(By.id("gridCheckOther")).click();
        Assert.assertEquals(driver.findElement(By.id("gridCheckOther")).isSelected(), true);

        Select continent = new Select(driver.findElement(By.id("selectContinents")));
        continent.selectByValue("europe");

        Select commands = new Select(driver.findElement(By.id("selectSeleniumCommands")));
        commands.selectByValue("browser-commands");
        commands.selectByValue("switch-commands");
        commands.selectByValue("wait-commands");

        driver.findElement(By.id("chooseFile")).sendKeys("C:\\test\\selenium_test.txt");
        driver.findElement(By.id("additionalInformations")).sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        //submit
//        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
//        driver.findElement(By.tagName("form")).submit();
        fname.submit();

        //asercje
        WebElement message = driver.findElement(By.id("validator-message"));
        Assert.assertEquals(message.getText(), "Form send with success");
    }
}
