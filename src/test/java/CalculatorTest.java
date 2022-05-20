import org.testng.Assert;
import org.testng.annotations.*;

public class CalculatorTest {

    @BeforeClass
    public void beforeClass() {
        System.out.println("run once before all tests");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("run before each test method");
    }

    @Test
    public void verifyAdd() {
        Assert.assertEquals(Calculator.add(2, 3), 5);
    }

    @Test
    public void verifyMultiply() {
        int result = Calculator.multiply(2,3);
//        Assert.assertTrue(result == 10);
        Assert.assertEquals(result, 10, "Wynik mnozenia jest inny niz spodziewany");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("run after each test method");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("run after all test methods");
    }
}