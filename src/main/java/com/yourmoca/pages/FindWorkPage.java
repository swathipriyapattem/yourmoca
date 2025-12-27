package com.yourmoca.pages;

import com.yourmoca.base.BasePage;

import com.microsoft.playwright.Page;

public class FindWorkPage extends BasePage {

    public FindWorkPage(Page page) {
        super(page);
    }

    // Locators
    private String findWorkLink = "//div[@class=\"MuiStack-root css-1ps3d0q\"]//a[@href=\"/findwork\"]";
    private String viewDetailsBtn = "(//button[normalize-space()='View Details'])[1]";
    private String submitApplicationBtn = "//span[text()=\"Apply\"]";

    public void navigateToFindWork() {
        page.click(findWorkLink);
    }

    public void applyForFirstJob() {
        page.click(viewDetailsBtn);
        page.click(submitApplicationBtn);
    }
}
