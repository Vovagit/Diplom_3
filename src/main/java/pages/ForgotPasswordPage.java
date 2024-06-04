package pages;

import constants.EnvConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPage {

    private final WebDriver driver;
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Wait load login button")
    public void waitLoadLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Login button click")
    public LoginPage loginButtonClick(){
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

}
