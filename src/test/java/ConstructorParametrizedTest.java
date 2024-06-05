import driver.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@RunWith(Parameterized.class)
public class ConstructorParametrizedTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private WebDriver driver;
    private MainPage main;
    private String selection;
    private final String selectionBun ="Булки";
    private final String selectionSauce = "Соусы";

    public ConstructorParametrizedTest(String selection) {

        this.selection = selection;

    }

    @Parameterized.Parameters
    public static Object[] getCredentials() {

        return new Object[]{
                "Булки",
                "Соусы",
                "Начинки"
        };

    }

    @Before
    public void before(){

        driver = driverRule.getDriver();
        main = new MainPage(driver);

    }


    @Test
    @DisplayName("Test selection in constructor")
    public void constructorSelectionTest(){

        main.open();
        main.waitLoadConstructorHeader();
        if (selection.equals(selectionBun)){
            main.clickConstructorSelection(selectionSauce);
        }
        String className = main.clickConstructorSelection(selection);
        Assert.assertThat(className,CoreMatchers.containsString("current"));

    }
}
