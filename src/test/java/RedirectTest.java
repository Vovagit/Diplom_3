import api.helpers.delete.user.DeleteUserChecks;
import api.helpers.delete.user.DeleteUserClient;
import api.helpers.create.user.CreateUserHelper;
import api.helpers.storage.StorageHelper;
import constants.EnvConfig;
import driver.DriverRule;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

public class RedirectTest {

    @Rule
    public DriverRule driverRule = new DriverRule();
    private LoginPage login;
    private WebDriver driver;
    private MainPage main;
    private ProfilePage profile;
    private final CreateUserHelper createUserHelper= new CreateUserHelper();
    private final DeleteUserClient delClient = new DeleteUserClient();
    private final DeleteUserChecks delChecks = new DeleteUserChecks();
    private StorageHelper storageHelper = new StorageHelper();
    private String name = "REdirTerehov";
    private String email = "Terehiv.redir@redirecttest.ru";
    private String password = "new123456";
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
    @DisplayName("Test redirect personal page")
    public void redirectToPersonalPageTest(){

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
        Assert.assertEquals(EnvConfig.BASE_URL+"account/profile",driver.getCurrentUrl());

    }

    @Test
    @DisplayName("Test redirect constructor button")
    public void redirectConstructorButton(){

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
        main = profile.constructorHeaderClick();
        main.waitLoadConstructorHeader();
        Assert.assertEquals(EnvConfig.BASE_URL,driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Test redirect stella burger button")
    public void redirectStellaBurgerTest(){

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
        main = profile.stellaBurgerClick();
        main.waitLoadConstructorHeader();
        Assert.assertEquals(EnvConfig.BASE_URL,driver.getCurrentUrl());

    }
}
