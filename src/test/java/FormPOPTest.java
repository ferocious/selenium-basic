import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FormPage;
import pages.Sex;

import java.util.Arrays;

public class FormPOPTest extends TestBase{
    @Test
    public void checkRandoms() throws InterruptedException {
        driver.get("https://seleniumui.moderntester.pl/form.php");
        FormPage formPage = new FormPage(driver);
        formPage.selectRandomProfession();
        formPage.selectContinent("North America");
        formPage.setFirstName("Adam");
        formPage.setLastName("Kwiatkowski");
        formPage.setAge("26");
        formPage.setEmail("adam@test.pl");
        formPage.setSex(Sex.MALE);
        formPage.setFile("C:\\Test\\test.txt");
        formPage.setExperience(7);
        formPage.setSeleniumCommands(Arrays.asList(new String[]{"Browser Commands", "Wait Commands", "Navigation Commands"}));

        formPage.submitForm();

        Assert.assertEquals(formPage.getValidatorMessage(), "Form send with success");

        Thread.sleep(5000);
    }
}
