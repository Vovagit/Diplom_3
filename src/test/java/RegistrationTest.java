import api.helpers.delete.user.DeleteUserChecks;
import api.helpers.delete.user.DeleteUserClient;
import api.helpers.storage.StorageHelper;
import driver.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

public class RegistrationTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private LoginPage login;
    private RegisterPage register;
    private WebDriver driver;
    private MainPage main;
    private final DeleteUserClient delClient = new DeleteUserClient();
    private final DeleteUserChecks delChecks = new DeleteUserChecks();
    private StorageHelper storageHelper = new StorageHelper();
    private String userName = "TerehovTestUser";
    private String email = "TerehovTestUser@create.ru";
    private String successPassword = "123456";
    private String badPassword = "12345";
    private String token;


    @Before
    public void before(){

        driver = driverRule.getDriver();
        main = new MainPage(driver);
    }

    @After
    public void deleteUser(){
        if (token != null) {
            Response delResp = delClient.deleteUserRequest(token);
            delChecks.checkSuccessDeleteUser(delResp);
        }
    }

    @Test
    @DisplayName("Test success registration")
    public void successRegistrationTest(){

        main.open();
        login=main.clickLoginButton();
        register=login.clickRegistation();
        register.fillRegistrationData(userName,email,successPassword);
        register.registerButtonClick();
        register.waitRedirectLoginPage();
        login.fillRegistrationData(email,successPassword);
        login.enterButtonClick();
        login.waitRedirectMain();
        login.assertRedirectMain();
        token = storageHelper.getStorageAccessToken(driver);
        storageHelper.checkTokenNotNull(token);

    }

    @Test
    @DisplayName("Test incorrect message password")
    public void minimumPasswordMessageTest(){

        main.open();
        login=main.clickLoginButton();
        register=login.clickRegistation();
        register.fillRegistrationData(userName,email,badPassword);
        register.registerButtonClick();
        String actualMessage = register.getInputPasswordMessage();
        Assert.assertEquals("Некорректный пароль",actualMessage);

    }

}
