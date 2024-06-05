package api.helpers.storage;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

public class StorageHelper {

    @Step("Get access token from storage")
    public String getStorageAccessToken(WebDriver driver){
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        return localStorage.getItem("accessToken");
    }

    @Step("Assert token not null")
    public void checkTokenNotNull(String token){
        Assert.assertNotNull(token);
    }

}
