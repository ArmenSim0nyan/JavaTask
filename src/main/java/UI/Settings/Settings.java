package UI.Settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Settings {
    public WebDriver driver;

    public WebDriver chromedriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        manageDriver(driver);
        driver.manage().window().maximize();
        this.driver = driver;

        return driver;
    }

    public WebDriver chromedriver(int width, int height) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        manageDriver(driver);
        driver.manage().window().setSize(new Dimension(width, height));
        this.driver = driver;

        return driver;
    }

    private void manageDriver(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
        driver.manage().deleteAllCookies();
    }
}
