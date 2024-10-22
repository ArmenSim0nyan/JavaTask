package UI.Support.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Locator {
    private final WebElement element;

    public Locator(String selector, WebDriver driver) {
        this.element = driver.findElement(identify(selector));
    }
    public Locator(WebElement element) {
        this.element = element;
    }

    public WebElement parent() {
        return element.findElement(identify("xpath{./..}"));
    }

    public WebElement child(String locator) {
        return element.findElement(identify(locator));
    }

    public List<WebElement> children() {
        return element.findElements(identify("xpath{./*}"));
    }

    public WebElement next() {
        return element.findElement(identify("xpath{following-sibling::div[1]}"));
    }

    public WebElement previous() {
        return element.findElement(identify("xpath{preceding-sibling::*[1]}"));
    }

    public WebElement current() {
        return element;
    }

    public static By identify(String locator) {
        Identifier type;
        String selector = locator;
        int separator = selector.indexOf("{");

        if(separator == -1) return By.cssSelector(selector);

        type = Identifier.valueOf(locator.substring(0, separator).toUpperCase());
        selector = locator.substring(separator+1, locator.length()-1);

        return switch (type) {
            case Identifier.TESTID -> By.cssSelector("[data-testid='%s']".formatted(selector));
            case Identifier.ID -> By.id(selector);
            case Identifier.CLASS -> By.className(selector);
            case Identifier.CSS -> By.cssSelector(selector);
            case Identifier.XPATH -> By.xpath(selector);
        };
    }
}
