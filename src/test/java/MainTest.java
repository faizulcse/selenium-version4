import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTest {
    ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    String url = "https://google.com";
    String hubUrl = "http://hub:4444/wd/hub";
    String localhostUrl = "http://localhost:4444/wd/hub";

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
        if (getCurrentDriver() != null)
            getCurrentDriver().quit();
    }

    @Test
    public void chromeDriverTest() throws InterruptedException {
        RemoteWebDriver driver = getBrowser("chrome");
        setCurrentDriver(driver);
        getCurrentDriver().get(url);
        System.out.println("Page Title: " + getCurrentDriver().getTitle());
        System.out.println("Browser Name: " + getCurrentDriver().getCapabilities().getBrowserName());
    }

    @Test
    public void firefoxDriverTest() {
        RemoteWebDriver driver = getBrowser("firefox");
        setCurrentDriver(driver);
        getCurrentDriver().get(url);
        System.out.println("Page Title: " + getCurrentDriver().getTitle());
        System.out.println("Browser Name: " + getCurrentDriver().getCapabilities().getBrowserName());
    }

    public RemoteWebDriver getBrowser(String browser) {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        try {
            switch (browser) {
                case "chrome":
                    return new RemoteWebDriver(new URL(localhostUrl), getChromeOptions());
                case "firefox":
                    return new RemoteWebDriver(new URL(localhostUrl), getFirefoxOptions());
                default:
                    throw new RuntimeException("Please enter correct browser name [" + browser + "]");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChromeOptions getChromeOptions() {
        return new ChromeOptions();
    }

    public FirefoxOptions getFirefoxOptions() {
        return new FirefoxOptions();
    }

    public InternetExplorerOptions getInternetExplorerOptions() {
        return new InternetExplorerOptions();
    }

    public RemoteWebDriver getCurrentDriver() {
        return driver.get();
    }

    public void setCurrentDriver(RemoteWebDriver driver) {
        this.driver.set(driver);
    }
}