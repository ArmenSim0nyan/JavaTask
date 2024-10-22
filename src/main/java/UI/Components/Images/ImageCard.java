package UI.Components.Images;

import UI.Support.Element.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ImageCard {
    private final Locator container;

    public ImageCard(WebElement element) {
        this.container = new Locator(element);
    }
    public ImageCard(ImageType type, WebDriver driver) {
        String locator = switch (type) {
            case AI -> "testid{ai-search-card-root}";
            case PLUS -> "[data-automation='search-item-premium']";
            case DEFAULT -> "[data-testid='content-grid-root'] > div";
        };

        this.container = new Locator(driver.findElements(Locator.identify(locator)).getFirst());
    }

    public WebElement cardContainer() {
        return container.current();
    }

    public WebElement likeButton() {
        return this.container.child("testid{like-button-root}");
    }

    public WebElement bookmarkButton() {
        return this.container.child("testid{save-button-root}");
    }

    public WebElement tryNowButton() {
        return this.container.child("testid{try-now-button-root}");
    }
}
