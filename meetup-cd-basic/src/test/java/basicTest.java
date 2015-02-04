import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.URL;

public class basicTest {

    private WebDriver browser;
    private SeleniumServer seleniumServer;
    private String environment = System.getProperties().getProperty("environment", "local");

    private static final String USERNAME = "limsim1";
    private static final String AUTOMATE_KEY = "yU8WsQkyFgysDpuReGP3";
    private static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

    @BeforeSuite
    public void openBrowser() throws Exception {
        if (environment.equals("local")) {
            seleniumServer = new SeleniumServer();
            seleniumServer.start();

            browser = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
        } else if (environment.equals("dev")) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browser", "Firefox");
            caps.setCapability("browser_version", "35.0");
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "8.1");
            caps.setCapability("resolution", "1024x768");
            caps.setCapability("browserstack.debug", "true");

            browser = new RemoteWebDriver(new URL(URL), caps);
        }
    }

    @AfterSuite
    public void closeBrowser() {
        if (environment.equals("local")) {
            browser.quit();
            seleniumServer.stop();
        } else if (environment.equals("dev")) {
            browser.quit();
        }
    }

    @Test
    public void checkMessage() {
        browser.get("https://s3-eu-west-1.amazonaws.com/cd-meetup-dev/index.html");
        WebElement message = browser.findElement(By.id("tag1"));
        Assert.assertEquals(message.getText(), "my first page 5");
    }

}
