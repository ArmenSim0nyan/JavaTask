package UI.Data;

import org.testng.annotations.DataProvider;

public class ViewportProvider {
    @DataProvider(name = "viewports", parallel = true)
    public static Object[][] getData(){
        return new Object[][]{{1440, 900}, {1366, 768}, {1024, 768}};
    }
}
