package pages;

import constants.EnvConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final By nameInput = By.xpath(".//label[text()='Имя']/../input");
    private final By emailInput = By.xpath(".//label[text()='Email']/../input");
    private final By passInput = By.xpath(".//label[text()='Пароль']/../input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By inputError = By.className("input__error");
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Input name send keys")
    public void inputNameSendKeys(String input){
        driver.findElement(nameInput).sendKeys(input);
    }

    @Step("Input email send keys")
    public void inputEmailSendKeys(String input){
        driver.findElement(emailInput).sendKeys(input);
    }

    @Step("Input password send keys")
    public void inputPassSendKeys(String input){
        driver.findElement(passInput).sendKeys(input);
    }

    @Step("Register button click")
    public void registerButtonClick(){
        driver.findElement(registerButton).click();
    }

    @Step("Wait redirect login page")
    public LoginPage waitRedirectLoginPage(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT)).until(ExpectedConditions.urlToBe(EnvConfig.BASE_URL+"login"));
        return new LoginPage(driver);
    }

    @Step("Get input error password message")
    public String getInputPasswordMessage(){
        return driver.findElement(inputError).getText();
    }

    @Step("Login button click")
    public LoginPage loginButtonClick(){
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Fill registration Data")
    public void fillRegistrationData(String userName,String email,String password){
        inputNameSendKeys(userName);
        inputEmailSendKeys(email);
        inputPassSendKeys(password);
    }

}
