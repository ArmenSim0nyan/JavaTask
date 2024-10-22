package UI.Support.Canvas;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Canvas {
    private final WebDriver driver;
    private final WebElement canvas;

    public Canvas(WebElement canvas, WebDriver driver) {
        this.driver = driver;
        this.canvas = canvas;
    }

    public boolean hasImage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = """
                const ctx = arguments[0].getContext("2d");
                const imageData = ctx.getImageData(0, 0, arguments[0].width, arguments[0].height);
                const data = imageData.data;

                for (let i = 0; i < data.length; i += 4) {
                    if (data[i + 3] !== 0) {
                        return true;
                    }
                }
                return false;
                """;

        return (Boolean) js.executeScript(script, canvas);
    }
}
