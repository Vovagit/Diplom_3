package pages;

import constants.EnvConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {

    private final WebDriver driver;
    private final By exitButton = new By.ByXPath(".//button[text()='Выход']");
    private final By constructorHeader = new By.ByXPath(".//p[text()='Конструктор']");
    private final By stellarBurger = new By.ByClassName("AppHeader_header__logo__2D0X2");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Stellar Burger click")
    public MainPage stellaBurgerClick(){
        driver.findElement(stellarBurger).click();
        return new MainPage(driver);
    }

    @Step("Constructor button click")
    public MainPage constructorHeaderClick(){
        driver.findElement(constructorHeader).click();
        return new MainPage(driver);
    }

    @Step("Wait for load exit button")
    public void exitButtonWaitLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }

    @Step("Exit button click")
    public LoginPage exitButtonClick(){
        driver.findElement(exitButton).click();
        return new LoginPage(driver);
    }

    @Step("Wait redirect login")
    public void waitLoginRedirect(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(EnvConfig.BASE_URL+"login"));

    }



}
