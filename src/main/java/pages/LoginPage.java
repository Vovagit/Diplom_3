package pages;

import constants.EnvConfig;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class LoginPage {

    private final WebDriver driver;
    private final By registrationHeader = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By emailInput = By.xpath(".//label[text()='Email']/../input");
    private final By passInput = By.xpath(".//label[text()='Пароль']/../input");
    private final By headerLogin = By.xpath(".//h2[text()='Вход']");
    private final By enterButton = By.xpath(".//button[text()='Войти']");
    private final By forgotPassword = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on registration header")
    public RegisterPage clickRegistation() {
        driver.findElement(registrationHeader).click();
        return new RegisterPage(driver);
    }

    @Step("Click on forgot password")
    public ForgotPasswordPage forgotPasswordClick(){
        driver.findElement(forgotPassword).click();
        return new ForgotPasswordPage(driver);
    }

    @Step("Input email send keys")
    public void inputEmailSendKeys(String input){
        driver.findElement(emailInput).sendKeys(input);
    }

    @Step("Input password send keys")
    public void inputPassSendKeys(String input){
        driver.findElement(passInput).sendKeys(input);
    }

    @Step("Fill login Data")
    public void fillRegistrationData(String email,String password){
        inputEmailSendKeys(email);
        inputPassSendKeys(password);
    }

    @Step("Click on enter button")
    public MainPage enterButtonClick(){
        driver.findElement(enterButton).click();
        return new MainPage(driver);
    }

    @Step("Wait redirect to main page")
    public void waitRedirectMain(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT)).until(ExpectedConditions.urlToBe(EnvConfig.BASE_URL));
    }

    @Step("Assert redirect to main page")
    public void assertRedirectMain(){
        Assert.assertEquals(EnvConfig.BASE_URL,driver.getCurrentUrl());
    }

    @Step("Wait for load page by header")
    public void waitLoadHeader(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(driver.findElement(headerLogin)));
    }
}
