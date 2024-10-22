package UI.Pages.Images;

import UI.Pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditImage extends BasePage {
    public EditImage(WebDriver driver) {
        super(driver);
    }

    public WebElement canvas() {
        return locate("[data-test='canvas-container'] canvas");
    }
}
