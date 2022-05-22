import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PosterTest extends TestBase {
//    otworz strone glowna
//    wyszukaj 'poster'
//    otworz quickview produktu 'today poster'
//    dodaj 2 sztuki do koszyka w rozmiarze 80x120 (zapamietaj cene sztuki)
//    nacisnij zakladke clothing
//    otworz pierwszy produkt z wynikow klikajac na jego zdjecie
//    dodaj jedna sztuke do koszyka (zapamietaj cene)
//    nacisnij proceed do checkout
//    sprawdz zawartosc koszyka - ilosc poszczegolnych produktow, ceny, subtotal i total

    private WebElement input;

    @BeforeMethod
    public void prepareTestData() {
        String url = "http://146.59.32.4/index.php";
        driver.get(url);
        input = driver.findElement(By.name("s"));
    }

    @Test
    public void addToCartProcessTest() {
        input.sendKeys("poster");
        input.submit();
        driver.findElement(By.id("5"));

    }
}
