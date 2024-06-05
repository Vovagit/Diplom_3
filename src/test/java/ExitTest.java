import api.helpers.delete.user.DeleteUserChecks;
import api.helpers.delete.user.DeleteUserClient;
import api.helpers.create.user.CreateUserHelper;
import api.helpers.storage.StorageHelper;
import driver.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.*;

public class ExitTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private WebDriver driver;
    private final CreateUserHelper createUserHelper= new CreateUserHelper();
    private final DeleteUserClient delClient = new DeleteUserClient();
    private final DeleteUserChecks delChecks = new DeleteUserChecks();
    private final StorageHelper storageHelper = new StorageHelper();
    private LoginPage login;
    private MainPage main;
    private ProfilePage profile;
    private String name = "TerehovExitTest";
    private String email = "Terehov.exit@exit.ru";
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
    @DisplayName("Test exit from account")
    public void exitFromAccount(){
        main.open();
        login = main.clickLoginButton();
        login.waitLoadHeader();
        login.fillRegistrationData(email,password);
        main = login.enterButtonClick();
        main.waitLoadConstructorHeader();
        token = storageHelper.getStorageAccessToken(driver);
        storageHelper.checkTokenNotNull(token);
        profile = main.clickPersonalButton();
        profile.exitButtonWaitLoad();
        profile.exitButtonClick();
        profile.waitLoginRedirect();
        String nulltoken = storageHelper.getStorageAccessToken(driver);
        Assert.assertNull(nulltoken);

    }
}
