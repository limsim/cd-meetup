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

public class basicTestIT {

    private WebDriver browser;
    private SeleniumServer seleniumServer;

    @BeforeSuite
    public void openBrowser() throws Exception {
        seleniumServer = new SeleniumServer();
        seleniumServer.start();

        browser = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
                DesiredCapabilities.firefox());
    }

    @AfterSuite
    public void closeBrowser() {
        browser.quit();
        seleniumServer.stop();
    }

    @Test
    public void checkMessage() {
        browser.get("https://s3-eu-west-1.amazonaws.com/cd-meetup-dev/index.html");
        WebElement message = browser.findElement(By.id("tag1"));
        Assert.assertEquals(message.getText(), "my first page");
    }

}
