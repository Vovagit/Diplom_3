package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }

    public void initDriver(){
        if("yandex".equals(System.getProperty("browser"))){
            initYandexBrowser();
        }
        else {
            initChromeDriver();

        }
    }

    private void initYandexBrowser() {
        WebDriverManager.chromedriver().driverVersion(System.getProperty("driver.version")).setup();

        var opts = new ChromeOptions();
        opts.setBinary(System.getProperty("webdriver.yandex.bin"));
        driver = new ChromeDriver(opts);
    }

    private void initChromeDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }
}
