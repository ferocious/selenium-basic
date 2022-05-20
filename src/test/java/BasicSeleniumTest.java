import org.testng.annotations.Test;

public class BasicSeleniumTest extends TestBase{

    @Test
    public void openAutomationPractice(){
        driver.get("http://www.python.org");
    }
}
