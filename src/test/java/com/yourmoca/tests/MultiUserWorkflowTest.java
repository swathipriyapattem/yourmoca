package com.yourmoca.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.yourmoca.base.BaseTest;
import com.yourmoca.pages.FindWorkPage;
import com.yourmoca.pages.LoginPage;
import com.yourmoca.pages.PostARequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MultiUserWorkflowTest extends BaseTest {

    @Test
    public void multiUserJobWorkflowTest() {
        String appPassword = prop.getProperty("app.password");

        // 1. Context 1: Job Poster (User 1) - Reusing the default page from BaseTest
        System.out.println("--- Starting Poster Workflow ---");
        Page pagePoster = this.page;

        // Note: App password dialog is already handled in PlaywrightFactory for this
        // page.

        LoginPage loginPoster = new LoginPage(pagePoster);
        System.out.println("Poster Logging in...");
        loginPoster.doLogin("swathipriya+and1@krify.com", "Krify@123");
        Assert.assertTrue(loginPoster.isDashboardVisible(), "Poster login failed - Dashboard not visible");
        System.out.println("Login Successful");

        PostARequest postPage = new PostARequest(pagePoster);
        String jobTitle = "Automation";

        System.out.println("Poster Posting a Request: " + jobTitle);
        postPage.clickPostRequestButton();
        postPage.fillPostRequestForm(jobTitle, "Short Film", "Details", "2025-12-31",
                "India", "Andhra Pradesh", "Kakinada",
                "Actor", "2025-12-30", "2025-12-31", "2025-12-29", "Requirement");
        postPage.clickSubmit();
        Assert.assertTrue(postPage.isSuccessMessageVisible(), "Job posting failed");
        System.out.println("Poster Closing success popup...");
        postPage.closeSuccessPopup();

        // 2. Context 2: Applicant (User 2) - Fresh context for independence
        System.out.println("--- Starting Applicant Workflow ---");
        BrowserContext contextApplicant = page.context().browser().newContext();
        Page pageApplicant = contextApplicant.newPage();

        // Handle app password dialog for this fresh context
        if (appPassword != null && !appPassword.isEmpty()) {
            pageApplicant.onDialog(dialog -> {
                System.out.println("Applicant Dialog detected: " + dialog.message());
                dialog.accept(appPassword);
            });
        }

        pageApplicant.navigate(prop.getProperty("url"));

        LoginPage loginApplicant = new LoginPage(pageApplicant);
        System.out.println("Applicant Logging in...");
        loginApplicant.doLogin("8640572116", "krify@123");
        Assert.assertTrue(loginApplicant.isDashboardVisible(), "Applicant login failed - Dashboard not visible");
        System.out.println("Login Successful");

        FindWorkPage findWorkPage = new FindWorkPage(pageApplicant);
        System.out.println("Applicant Navigating to Find Work...");
        findWorkPage.navigateToFindWork();
        System.out.println("Applicant Applying for first job...");
        findWorkPage.applyForFirstJob();

        // 3. Context 1: Poster accepts the application via Notifications
        System.out.println("--- Poster Accepting Application via Notifications ---");
        loginPoster.viewRequests();
        loginPoster.goToNotifications();
        loginPoster.clickOnAppliedNotification();
        loginPoster.clickAccept();

        // Success Cleanup
        System.out.println("--- Workflow Completed Successfully ---");
        contextApplicant.close();
    }

}
