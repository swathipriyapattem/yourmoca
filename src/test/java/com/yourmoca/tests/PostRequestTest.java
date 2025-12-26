package com.yourmoca.tests;

import com.yourmoca.base.BaseTest;
import com.yourmoca.pages.LoginPage;
import com.yourmoca.pages.PostARequest;
import com.yourmoca.testdata.LoginData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PostRequestTest extends BaseTest {

    private LoginPage loginPage;
    private PostARequest postRequestPage;

    @BeforeMethod
    public void pageSetup() {
        loginPage = new LoginPage(page);
        postRequestPage = new PostARequest(page);
    }

    @Test(priority = 1, dataProvider = "postRequestData", dataProviderClass = com.yourmoca.testdata.PostRequestData.class)
    public void postNewRequestTest(String projectName, String projectType, String details,
            String endDate,
            String country, String state, String city,
            String subCategory, String fromDate, String toDate, String submissionDate, String requirement) {
        // 1. Login
        loginPage.doLogin("swathipriya+and1@krify.com", "Krify@123"); // Needs valid data
        if (loginPage.isDashboardVisible()) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed or Dashboard not visible yet");
        }

        // 2. Navigate to Post Request
        postRequestPage.clickPostRequestButton();

        // 3. Fill Form
        postRequestPage.fillPostRequestForm(
                projectName, projectType, details, endDate,
                country, state, city, subCategory, fromDate, toDate,
                submissionDate, requirement);

        postRequestPage.clickSubmit();

        // 4. Verify
        Assert.assertTrue(postRequestPage.isSuccessMessageVisible(),
                "Post Request failed!");
        System.out.println("test passed");

    }
}
