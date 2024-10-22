package UI.Support.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class Wait {
    private final WebDriver driver;

    public Wait(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(this::waitForPageViaJS);
    }

    public void waitForPageToLoad(int duration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(this::waitForPageViaJS);
    }

    public void waitForElementVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(_ -> ExpectedConditions.visibilityOfElementLocated(by));
    }

    private Boolean waitForPageViaJS(WebDriver _driver) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;

        return Objects.equals(js.executeScript("return document.readyState"), "complete");
    }
}
