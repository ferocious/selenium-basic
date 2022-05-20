import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.testng.annotations.Test;

public class KeysTest extends TestBase{
    String url = "https://seleniumui.moderntester.pl/slider.php";

    @Test
    public void slide50to30(){
        driver.get(url);
        WebElement handle = driver.findElement(By.id("custom-handle"));

        while (Integer.parseInt(handle.getText()) < 50)
            handle.sendKeys(Keys.ARROW_RIGHT);
        logger.info(handle.getText());
        while (Integer.parseInt(handle.getText()) > 30)
            handle.sendKeys(Keys.ARROW_LEFT);
        logger.info(handle.getText());
    }

    @Test
    public void slideMultipleTimes(){
        driver.get(url);
        WebElement handle = driver.findElement(By.id("custom-handle"));

        slideTo(handle, 50);
        slideTo(handle, 30);
        slideTo(handle, 30);
        slideTo(handle, 20);
    }

    private void slideTo(WebElement handle, int dest) {
        int pos = Integer.parseInt(handle.getText());
        if (pos == dest) {
            logger.info("Juz jestem na pozycji "+dest);
            return;
        }

//        if (pos > dest) {
//            while (Integer.parseInt(handle.getText()) > dest)
//                handle.sendKeys(Keys.ARROW_LEFT);
//        }
//        else {
//            while (Integer.parseInt(handle.getText()) < dest)
//                handle.sendKeys(Keys.ARROW_RIGHT);
//        }
        Keys arrow;
        if (pos > dest) {
            arrow = Keys.ARROW_LEFT;
        }
        else {
            arrow = Keys.ARROW_RIGHT;
        }

        while(Math.abs(Integer.parseInt(handle.getText()) - dest) >0)
            handle.sendKeys(arrow);

        logger.info("Osiagnieto pozycje "+ dest);
    }
}
