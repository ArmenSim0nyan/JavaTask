package UI.Pages;

import UI.Support.Element.Locator;
import org.openqa.selenium.WebElement;

public interface IPage {
    void setResolution(int width, int height);
    void switchToFrame(WebElement frame);
    void switchToDefault();
    void open(String url);
    void refresh();
    void terminate();
    void maximize();
    void minimize();
    String url();
    WebElement locate(String locator);
    Locator locator(String locator);
}
