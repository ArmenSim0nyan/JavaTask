package UI.Pages;

import UI.Support.Element.Locator;
import org.openqa.selenium.*;

public class BasePage implements IPage {
    public WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void open(String url) {
        driver.get(url);
    }

    @Override
    public void refresh() {
        driver.navigate().refresh();
    }

    @Override
    public WebElement locate(String locator) {
        return new Locator(driver.findElement(Locator.identify(locator))).current();
    }

    @Override
    public Locator locator(String locator) {
        return new Locator(driver.findElement(Locator.identify(locator)));
    }

    @Override
    public void terminate() {
        this.driver.quit();
    }

    @Override
    public void maximize() {
        this.driver.manage().window().maximize();
    }

    @Override
    public void minimize() {
        this.driver.manage().window().minimize();
    }

    @Override
    public String url() {
        return this.driver.getCurrentUrl();
    }

    @Override
    public void setResolution(int width, int height) {
        this.driver.manage().window().setSize(new Dimension(width, height));
    }

    @Override
    public void switchToFrame(WebElement frame) {
        this.driver.switchTo().frame(frame);
    }

    @Override
    public void switchToDefault() {
        this.driver.switchTo().defaultContent();
    }
}