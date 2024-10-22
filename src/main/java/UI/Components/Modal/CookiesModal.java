package UI.Components.Modal;

import UI.Support.Element.Locator;
import UI.Support.Element.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CookiesModal {
    private final Locator modal;

    public CookiesModal(WebDriver driver) {
        Wait wait = new Wait(driver);
        wait.waitForElementVisibility(Locator.identify("id{onetrust-banner-sdk}"));
        this.modal = new Locator("id{onetrust-banner-sdk}", driver);
    }

    public WebElement acceptButton() {
        return modal.child("id{onetrust-accept-btn-handler}");
    }
}
