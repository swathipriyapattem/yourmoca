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
    private String dashboardLink = "(//a[@href=\"/dashboard\"])[1]";
    private String viewRequestBtn = "//span[text()=\"View My Requests\"]";
    private String notificationsLink = "//div[text()=\"Notifications\"]";
    private String notificationItem = "//*[text()=\" has applied for your Job \"][1]";
    private String acceptBtn = "//span[text()=\"Accept\"]";
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

    public boolean doLogin(String appUserName, String appPassword) {
        System.out.println("App creds: " + appUserName + ":" + appPassword);
        System.out.println("Clicking Login/Signup button: " + login_signupBtn);
        page.click(login_signupBtn);
        System.out.println("Filling email: " + appUserName);
        page.fill(emailInput, appUserName);
        System.out.println("Filling password...");
        page.fill(passwordInput, appPassword);
        System.out.println("Clicking Login submit button: " + loginBtn);
        page.click(loginBtn);
        return isDashboardVisible();
    }

    public boolean isDashboardVisible() {
        page.waitForLoadState();
        page.locator(dashboardLink).waitFor();
        return page.locator(dashboardLink).isVisible();
    }

    public void viewRequests() {

        page.waitForLoadState();
        page.click(viewRequestBtn);
    }

    public void goToNotifications() {
        page.waitForLoadState();
        page.click(notificationsLink);
    }

    public void clickOnAppliedNotification() {
        page.waitForLoadState();
        page.click(notificationItem);
    }

    public void clickAccept() {
        page.waitForLoadState();
        page.click(acceptBtn);
    }
}
