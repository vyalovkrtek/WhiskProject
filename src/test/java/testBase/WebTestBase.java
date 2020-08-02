package testBase;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import core.driver.TestConfig;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class WebTestBase {

    @BeforeClass
    public void setUp() {
        TestConfig.initConfig();

        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;


        switch (TestConfig.browser)
        {
            case "chrome":
                Configuration.browser = Browsers.CHROME;
                break;
            case "firefox":
                Configuration.browser = Browsers.FIREFOX;
                break;
            default:
                Configuration.browser = Browsers.CHROME;
                break;
        }
    }

    @AfterMethod
    public void clearCookies() {
        Selenide.clearBrowserCookies();
    }

    @AfterClass
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
