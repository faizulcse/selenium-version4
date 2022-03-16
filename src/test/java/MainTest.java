import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTest {
    RemoteWebDriver driver;

    @BeforeMethod
    public void setUp() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        ChromeOptions options = new ChromeOptions();
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void driverSetupTest() {
        System.out.println(driver.getTitle());
    }
}
