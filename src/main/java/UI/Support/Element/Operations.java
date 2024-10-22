package UI.Support.Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Operations {
    private final Actions action;

    public Operations(WebDriver driver) {
        this.action = new Actions(driver);
    }

    public void hover(WebElement element) {
        action.moveToElement(element).perform();
    }
}
