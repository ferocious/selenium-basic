import io.github.bonigarcia.wdm.WebDriverManager;
import myStore.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;


public class TestBase {
    WebDriver driver;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public final int IMPLICIT_WAIT_TIME_SECONDS = 5;

    @BeforeClass
    public void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME_SECONDS));
        BasePage.driver = driver;
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {

//        Thread.sleep(5000);
        driver.quit();

    }
}
