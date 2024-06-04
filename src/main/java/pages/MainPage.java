package pages;

import constants.EnvConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalLoginButton = By.xpath(".//p[text()='Личный Кабинет']/..");
    private final By constructorHeader = By.xpath(".//h1[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }

    @Step("Constructor selection click")
    public String clickConstructorSelection(String selection){

        By locator = By.xpath(String.format(".//span[text()='%s']/..",selection));
        driver.findElement(locator).click();

        new WebDriverWait(driver,Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(locator,"class","current"));
        return driver.findElement(locator).getAttribute("class");
    }

    @Step("Wait for load constructor header")
    public void waitLoadConstructorHeader(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorHeader));
    }

    @Step("Click on login button")
    public LoginPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Click on personal login button")
    public LoginPage clickPersonalLoginButton(){
        driver.findElement(personalLoginButton).click();
        return new LoginPage(driver);
    }

    @Step("Click on personal button")
    public ProfilePage clickPersonalButton(){
        driver.findElement(personalLoginButton).click();
        return new ProfilePage(driver);
    }


}
