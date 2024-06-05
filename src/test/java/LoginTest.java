import api.helpers.delete.user.DeleteUserChecks;
import api.helpers.delete.user.DeleteUserClient;
import api.helpers.create.user.CreateUserHelper;
import api.helpers.storage.StorageHelper;
import driver.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

public class LoginTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private LoginPage login;
    private RegisterPage register;
    private MainPage main;
    private WebDriver driver;
    private ForgotPasswordPage forgot;
    private final CreateUserHelper createUserHelper= new CreateUserHelper();
    private final DeleteUserClient delClient = new DeleteUserClient();
    private final DeleteUserChecks delChecks = new DeleteUserChecks();
    private StorageHelper storageHelper = new StorageHelper();
    private String name = "LoginTest";
    private String email = "Terehov.test@login.ru";
    private String password = "123456";
    private String token;

    @Before
    public void before(){

        driver = driverRule.getDriver();
        main = new MainPage(driver);
        createUserHelper.createUser(email,name,password);

    }

    @After
    public void deleteUser(){
        if (token != null) {
            Response delResp = delClient.deleteUserRequest(token);
            delChecks.checkSuccessDeleteUser(delResp);
        }else{
            Response delResp = delClient.deleteUserRequest(createUserHelper.getToken());
            delChecks.checkSuccessDeleteUser(delResp);
        }
    }

    @Test
    @DisplayName("Test login button from registration page")
    public void loginFromRegistrationTest(){

        main.open();
        login = main.clickLoginButton();
        register = login.clickRegistation();
        login = register.loginButtonClick();
        login.fillRegistrationData(email,password);
        login.enterButtonClick();
        login.waitRedirectMain();
        login.assertRedirectMain();
        token = storageHelper.getStorageAccessToken(driver);
        storageHelper.checkTokenNotNull(token);

    }

    @Test
    @DisplayName("Test login button from forgot password page")
    public void loginFromForgotPasswordTest(){

        main.open();
        login = main.clickLoginButton();
        forgot = login.forgotPasswordClick();
        forgot.waitLoadLoginButton();
        login = forgot.loginButtonClick();
        login.fillRegistrationData(email,password);
        login.enterButtonClick();
        login.waitRedirectMain();
        login.assertRedirectMain();
        token = storageHelper.getStorageAccessToken(driver);
        storageHelper.checkTokenNotNull(token);

    }
}
