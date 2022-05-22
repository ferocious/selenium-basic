package myStore;

public class HomePage extends BasePage{
    public static void navigateTo() {
        driver.get("http://146.59.32.4/index.php");
        homePage = new HomePage();
    }
}
