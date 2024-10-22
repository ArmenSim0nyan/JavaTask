package UI;

import UI.Components.Images.ImageCard;
import UI.Components.Images.ImageType;
import UI.Components.Modal.CookiesModal;
import UI.Components.Modal.RegistrationModal;
import UI.Data.ViewportProvider;
import UI.Pages.Images.EditImage;
import UI.Pages.Images.SearchImage;

import UI.Settings.Settings;
import UI.Support.Canvas.Canvas;
import UI.Support.Element.Operations;
import UI.Support.Element.Wait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class UITask {
    Settings settings = new Settings();

    @Test(dataProvider = "viewports", dataProviderClass = ViewportProvider.class)
    public void testing(int width, int height) throws InterruptedException {
        // Set up
        WebDriver driver = settings.chromedriver(width, height);
        SearchImage searchImage = new SearchImage(driver);
        EditImage editImage = new EditImage(driver);
        Operations operations = new Operations(driver);
        Wait wait = new Wait(driver);

        // Open and wait
        searchImage.open("https://picsart.com");
        wait.waitForPageToLoad();
        searchImage.searchIcon().click();
        wait.waitForPageToLoad();
        assertTrue(searchImage.url().contains("/search/images"));

        // Accept Cookies
        CookiesModal cookies = new CookiesModal(driver);
        cookies.acceptButton().click();

        // Hide Filter
        searchImage.switchToFrame(searchImage.mainFrame());
        searchImage.filterCollapseButton().click();
        assertTrue(searchImage.filtersContainer().getAttribute("class").contains("hide"));

        // Open Filter
        searchImage.filterCollapseButton().click();
        assertFalse(searchImage.filtersContainer().getAttribute("class").contains("hide"));

        // Apply Personal Licence Filter and Check absence of PLUS icons
        searchImage.personalCheckbox().click();
        assertTrue(searchImage.removeFilterButton().isDisplayed());
        assertTrue(searchImage.premiumIcons().isEmpty());

        // Hover and check buttons
        ImageCard personalImage = new ImageCard(ImageType.DEFAULT, driver);
        operations.hover(personalImage.cardContainer());
        assertTrue(personalImage.bookmarkButton().isDisplayed());
        assertTrue(personalImage.tryNowButton().isDisplayed());
        assertTrue(personalImage.likeButton().isDisplayed());

        // Click on the Like button and assert Registration modal
        personalImage.likeButton().click();
        searchImage.switchToDefault();
        RegistrationModal modal = new RegistrationModal(driver);
        assertTrue(modal.container().isDisplayed());

        // Close Modal and Clear filters
        modal.closeButton().click();
        searchImage.switchToFrame(searchImage.mainFrame());
        searchImage.clearAllButton().click();

        // Hover and check PLUS image buttons
        ImageCard premiumImage = new ImageCard(ImageType.PLUS, driver);
        operations.hover(premiumImage.cardContainer());
        assertTrue(premiumImage.tryNowButton().isDisplayed());
        assertThrows(NoSuchElementException.class, premiumImage::likeButton);
        assertThrows(NoSuchElementException.class, premiumImage::bookmarkButton);

        // Click on Try Now button
        premiumImage.tryNowButton().click();
        Thread.sleep(2000);
        wait.waitForPageToLoad();
        assertTrue(searchImage.url().contains("/create/editor"));
        assertTrue(searchImage.url().contains("license=premium"));

        // Check canvas and the image which is added to it
        RegistrationModal registrationModal = new RegistrationModal(driver);
        registrationModal.closeButton().click();
        Canvas canvas = new Canvas(editImage.canvas(), driver);
        assertTrue(canvas.hasImage());

        // Tear Down
        searchImage.terminate();
    }
}
