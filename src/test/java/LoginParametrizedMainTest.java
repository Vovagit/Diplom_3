import api.helpers.delete.user.DeleteUserChecks;
import api.helpers.delete.user.DeleteUserClient;
import api.helpers.create.user.CreateUserHelper;
import api.helpers.storage.StorageHelper;
import driver.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;

@RunWith(Parameterized.class)
public class LoginParametrizedMainTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private LoginPage login;
    private MainPage main;
    private WebDriver driver;
    private final CreateUserHelper createUserHelper= new CreateUserHelper();
    private final DeleteUserClient delClient = new DeleteUserClient();
    private final DeleteUserChecks delChecks = new DeleteUserChecks();
    private StorageHelper storageHelper = new StorageHelper();
    private final String loginButton;

    private String name = "LoginTest";
    private String email = "Terehov.test@login.ru";
    private String password = "123456";
    private String token;


    public LoginParametrizedMainTest(String loginButton) {
        this.loginButton = loginButton;
    }



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

    @Parameterized.Parameters
    public static Object[] getCredentials() {
        return new Object[]{
                "personal",
                "login"
        };

    }

    @Test
    @DisplayName("Test login button from main page")
    public void loginButtonMainPageTest(){

        main.open();

        if("personal".equals(loginButton)){
            login =main.clickPersonalLoginButton();
        }else{
            login = main.clickLoginButton();
        }
        login.waitLoadHeader();
        login.fillRegistrationData(email,password);
        login.enterButtonClick();
        login.waitRedirectMain();
        login.assertRedirectMain();
        token = storageHelper.getStorageAccessToken(driver);
        storageHelper.checkTokenNotNull(token);
    }
}
