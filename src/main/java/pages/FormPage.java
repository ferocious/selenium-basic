package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class FormPage {

        @FindBy(id="inputFirstName3")
        WebElement firstName;

        @FindBy(id="selectContinents")
        WebElement continents;

        @FindBy(name="gridCheckboxProfession")
        List<WebElement> proffesions;

        @FindBy(id="inputLastName3")
        WebElement lastName;

        @FindBy(id="inputEmail3")
        WebElement email;

        @FindBy(id="inputAge3")
        WebElement age;

        @FindBy(name="gridRadiosExperience")
        List<WebElement> gridRadiosExperience;

        @FindBy(name="gridRadiosSex")
        List<WebElement> gridRadiosSex;

        @FindBy(id="selectSeleniumCommands")
        WebElement seleniumCommands;

        @FindBy(id="chooseFile")
        WebElement chooseFile;

        @FindBy(css="button[type=\"submit\"]")
        WebElement submitButton;

        @FindBy(id="validator-message")
        WebElement validatorMessage;


        public void setLastName(String lastName) {
            this.lastName.sendKeys(lastName);
        }

        public void setEmail(String email) {
            this.email.sendKeys(email);
        }

        public void setAge(String age) {
            this.age.sendKeys(age);
        }

        public void setExperience(int i) {
            for (WebElement radio : gridRadiosExperience) {
                if (Integer.parseInt(radio.getAttribute("value")) == i)
                    radio.click();
            }
        }

        public void setSex(Sex sex) {
            for (WebElement radio : gridRadiosSex) {
                if (radio.getAttribute("value")
                        .toLowerCase(Locale.ROOT)
                        .equals(
                                sex.toString().toLowerCase(Locale.ROOT)
                        )
                )
                    radio.click();
            }
        }

        public void setFile(String path){
            chooseFile.sendKeys(path);
        }

        public void setSeleniumCommands(List<String> list) {
            Select dp = new Select(seleniumCommands);
            for (String s : list) {
                dp.selectByVisibleText(s);
            }
        }

        public FormPage(WebDriver driver) {
            PageFactory.initElements(driver, this);
        }

        public void setFirstName(String firstName) {
            this.firstName.sendKeys(firstName);
        }

        public void selectContinent(String continent) {
            Select con = new Select(continents);
            con.selectByVisibleText(continent);
        }

        public void selectRandomProfession() {
            List<Integer> randoms = new ArrayList<>();
            //0-proffesions.size
            randoms.add(ThreadLocalRandom.current().nextInt(0, proffesions.size()));

            Integer k = ThreadLocalRandom.current().nextInt(0, proffesions.size());
            while (randoms.contains(k)) {
                k = ThreadLocalRandom.current().nextInt(0, proffesions.size());

            }
            randoms.add(k);
            for (Integer i : randoms)
                proffesions.get(i).click();


        }

        public void submitForm() {
            submitButton.click();
        }

        public String getValidatorMessage(){
            return validatorMessage.getText();
        }
}
