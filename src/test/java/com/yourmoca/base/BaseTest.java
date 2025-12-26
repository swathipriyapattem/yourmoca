package com.yourmoca.base;

import com.microsoft.playwright.Page;
import com.yourmoca.factory.PlaywrightFactory;
import com.yourmoca.utils.ConfigReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.util.Properties;

public class BaseTest {
    protected PlaywrightFactory pf;
    protected Page page;
    protected Properties prop;

    @BeforeClass
    public void setup() {
        pf = new PlaywrightFactory();
        ConfigReader configReader = new ConfigReader();
        prop = configReader.initProp();
        page = pf.initBrowser(prop);
    }

    @BeforeMethod
    public void setupTest() {
        if (page != null) {
            // clear cookies and local storage
            page.context().clearCookies();
            page.evaluate("window.localStorage.clear();");
            page.evaluate("window.sessionStorage.clear();");
            // Navigate to base URL
            page.navigate(prop.getProperty("url").trim());
        }
    }

    @AfterClass
    public void tearDown() {
        if (page != null) {
            page.context().browser().close();
        }
    }
}
