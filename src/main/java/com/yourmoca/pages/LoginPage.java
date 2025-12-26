package com.yourmoca.pages;

import com.yourmoca.base.BasePage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    // 1. String Locators - Dummy placeholders
    private String login_signupBtn = "//div[@class=\"MuiGrid-root MuiGrid-container MuiGrid-item MuiGrid-grid-xs-auto css-17jney1\"]";
    private String emailInput = "//input[@name=\"email\"]"; // e.g. input[name='email']
    private String passwordInput = "//input[@name=\"password\"]"; // e.g. input[name='password']
    private String loginBtn = "//span[text()=\"Login\"]"; // e.g. button[type='submit']
    private String forgotPwdLink = "//div[text()=\"Forgot Password?\"]";
    private String dashboardLink = "//a[@href=\"/dashboard\"]";
    private String appPasswordInput = "#dummy_app_password_id"; // If site lock exists
    private String appPasswordBtn = "#dummy_app_password_btn_id";

    // 2. Page Constructor
    public LoginPage(Page page) {
        super(page);
    }

    // 3. Page Actions/Methods
    public String getLoginPageTitle() {
        return page.title();
    }

    public void enterAppPassword(String appPwd) {
        if (page.isVisible(appPasswordInput)) {
            page.fill(appPasswordInput, appPwd);
            page.click(appPasswordBtn);
        }
    }

    public boolean isForgotPwdLinkExist() {
        page.click(login_signupBtn);
        return page.isVisible(forgotPwdLink);
    }

    public void doLogin(String appUserName, String appPassword) {
        System.out.println("App creds: " + appUserName + ":" + appPassword);
        page.click(login_signupBtn);
        page.fill(emailInput, appUserName);
        page.fill(passwordInput, appPassword);
        page.click(loginBtn);
    }

    public boolean isDashboardVisible() {
        return page.isVisible(dashboardLink);
    }
}
