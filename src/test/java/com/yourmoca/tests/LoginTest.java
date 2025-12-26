package com.yourmoca.tests;

import com.yourmoca.base.BaseTest;
import com.yourmoca.pages.LoginPage;
import com.yourmoca.testdata.LoginData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void loginPageSetup() {
        loginPage = new LoginPage(page);
        // If site is locked, unlock it
        // loginPage.enterAppPassword(prop.getProperty("app.password"));
    }

    @Test(priority = 1)
    public void loginPageTitleTest() {
        String actTitle = loginPage.getLoginPageTitle();
        System.out.println("page title: " + actTitle);
        // Assert.assertEquals(actTitle, "YourMoca Login"); // Update with actual title
    }

    @Test(priority = 2)
    public void forgotPwdLinkExistTest() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @Test(priority = 3, dataProvider = "loginData", dataProviderClass = LoginData.class)
    public void loginTest(String username, String password, String type) {
        loginPage.doLogin(username, password);

        if (type.equalsIgnoreCase("valid")) {
            Assert.assertTrue(loginPage.isDashboardVisible(), "Login failed for valid credentials!");
        } else {
            // Assert error message exists or url didn't change
            // Assert.assertTrue(loginPage.isErrorMessageExist());
            System.out.println("Negative test executed for: " + username);
        }
    }
}
