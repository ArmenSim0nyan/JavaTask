package UI.Components.Modal;

import UI.Support.Element.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationModal {
    private final Locator modal;

    public RegistrationModal(WebDriver driver) {
        this.modal = new Locator("testid{registration-modal-container}", driver);
    }

    public WebElement container() {
        return modal.current();
    }

    public WebElement closeButton() {
        return modal.child("testId{modal-close-icon}");
    }
}
