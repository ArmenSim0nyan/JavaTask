package UI.Pages.Images;

import UI.Components.Images.ImageCard;
import UI.Pages.BasePage;
import UI.Support.Element.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchImage extends BasePage {
    public SearchImage(WebDriver driver) {
        super(driver);
    }

    public WebElement mainFrame() {
        return locate("testid{com.picsart.social.search}");
    }

    public WebElement searchIcon() {
        return locate("testid{search-icon-test}");
    }

    public WebElement searchInput() {
        return locate("testid{search_field_input}");
    }

    public WebElement filterCollapseButton() {
        return locate("testid{search-header-filter}");
    }

    public WebElement filtersContainer() {
        return locate("testid{search-filter-root}");
    }

    public WebElement personalCheckbox() {
        return locate("[aria-label='licenses-Personal-checkbox']");
    }

    public WebElement removeFilterButton() {
        return locate("[data-testid='search-filter-header-item'] [data-testid='end-icon']");
    }

    public WebElement clearAllButton() {
        return locate("testid{search-filter-header-clear}");
    }

    public List<ImageCard> all() {
        return locator("testid{content-grid-root}").children().stream().map(ImageCard::new).toList();
    }

    public List<WebElement> premiumIcons() {
        return driver.findElements(Locator.identify("testid{search-card-premium}"));
    }
}

