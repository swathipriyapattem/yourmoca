package com.yourmoca.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.yourmoca.base.BasePage;

public class PostARequest extends BasePage {

    // Locators
    private String postRequestBtn = "//div[@class=\"MuiStack-root css-1ps3d0q\"]//a[text()=\"Post a Request\"]";

    // Form Fields
    private String projectNameInput = "//input[@name=\"iamLookingFor\"]"; // Placeholder: Project Name
    private String projectTypeDropdown = "//div[contains(@class, 'css-qiwgdb')]"; // Placeholder:
                                                                                  // Project
                                                                                  // Type
                                                                                  // (Dropdown)
    private String projectDetailsInput = "//textarea[@name=\"projectDetails\"]"; // Placeholder: Project Details
    private String projectEndDateInput = "//input[@id=\"dateInputTag\" and @aria-label=\"Choose date\"]"; // Placeholder:
                                                                                                          // Project End
                                                                                                          // Date
    private String attachDocumentsInput = "//input[@type='file']"; // Placeholder: Attach documents

    // Location
    private String countryDropdown = "//div[@name=\"country\"]//following-sibling::div//button[contains(@class, 'css-uge3vf')]"; // Placeholder:
    // Country
    private String stateDropdown = "//div[@name=\"state\"]//following-sibling::div//button[contains(@class, 'css-uge3vf')]"; // Placeholder:
    // State
    private String cityInput = "//input[@name=\"city\"]"; // Placeholder: City

    // Service Details
    private String subCategoryInput = "//*[text()=\"Subcategory / Requested Services\"]//following-sibling::div//button"; // Placeholder:
                                                                                                                          // Subcategory
    private String serviceFromDateInput = "//input[@id=\"dateInputTag\" and @placeholder=\"From date\"]"; // Placeholder:
                                                                                                          // Service
                                                                                                          // From Date
    private String serviceToDateInput = "//input[@id=\"dateInputTag\" and @placeholder=\"To date\"]"; // Placeholder:
                                                                                                      // Service To Date
    private String lastSubmissionDateInput = "//input[@id=\"dateInputTag\" and @placeholder=\"Select date\"]"; // Placeholder:
                                                                                                               // Last
                                                                                                               // Date
                                                                                                               // of
    // Submission
    private String payRateDropdown = "//*[normalize-space(text())='Pay Rate:']/following::div[@aria-haspopup=\"listbox\"]"; // Placeholder:
                                                                                                                            // Pay
    private String payRateOption = "//li[text()=\"Discussion on payment\"]"; // Rate

    // Requirements
    private String requirementsInput = "//textarea[@placeholder=\"Enter requirement details\"]"; // Placeholder:
                                                                                                 // Requirements
    private String addRequirementBtn = "//button[contains(@class, 'css-17w1i5c')]"; // Placeholder: Add Req Button

    private String submitRequestBtn = "//button[contains(@class, 'css-16jxrl6')]";
    private String successMessage = "//*[contains(normalize-space(text()),'posted your request successfully')]";

    public PostARequest(Page page) {
        super(page);
    }

    public void clickPostRequestButton() {
        System.out.println("Clicking Post Request button: " + postRequestBtn);
        page.click(postRequestBtn);
        System.out.println("Current URL after click: " + page.url());
    }

    public void fillPostRequestForm(String projectName, String projectType, String details,
            String endDate,
            String country, String state, String city,
            String subCategory, String fromDate, String toDate, String submissionDate, String requirement) {

        System.out.println("Filling form - Project Name: " + projectName);
        page.fill(projectNameInput, projectName);

        // Handle Dropdowns
        System.out.println("Selecting Project Type: " + projectType);
        page.click(projectTypeDropdown);
        // Click the option that exactly matches the projectType text
        // User provided xpath base: //li[contains(@class, 'css-7quhgo')]
        page.click("//li[contains(@class, 'css-7quhgo') and text()='" + projectType + "']");
        page.fill(projectDetailsInput, details);

        // Date Fields
        selectDate(projectEndDateInput, endDate);

        // if (docPath != null && !docPath.isEmpty()) {
        // page.setInputFiles(attachDocumentsInput, java.nio.file.Paths.get(docPath));
        // }

        // Location
        page.click(countryDropdown);
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(country).setExact(true)).click();
        page.click(stateDropdown);
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(state).setExact(true)).click();
        page.fill(cityInput, city);

        // Service
        page.click(subCategoryInput);
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(subCategory).setExact(true)).click();
        selectDate(serviceFromDateInput, fromDate);
        selectDate(serviceToDateInput, toDate);
        selectDate(lastSubmissionDateInput, submissionDate);
        page.click(payRateDropdown);
        page.click(payRateOption);

        // Requirements
        page.fill(requirementsInput, requirement);
        page.click(addRequirementBtn);
    }

    private void selectDate(String selector, String date) {
        if (date == null || date.isEmpty())
            return;

        // 1. Click to open calendar
        page.click(selector);

        // 2. Parse day from "YYYY-MM-DD"
        // Assumption: date format is YYYY-MM-DD
        String day = date.split("-")[2];

        // Remove leading zero if present (e.g. "05" -> "5") as calendars usually
        // display single digits
        if (day.startsWith("0")) {
            day = day.substring(1);
        }

        // 3. Select the day from the calendar
        // Targeting a button with the specific text of the day.
        // We use exact text match to avoid matching "1" inside "10", "11", etc.
        // Also added role='gridcell' which is common for date pickers
        // (MUI/React-Date-Picker)
        String dayLocator = "//button[@role='gridcell' and text()='" + day + "']";

        // Fallback or more specific locator if role='gridcell' isn't used
        // This attempts to find a button in the dialog that exactly matches the day
        if (!page.isVisible(dayLocator)) {
            dayLocator = "//button[text()='" + day + "']";
        }

        page.click(dayLocator);
    }

    public void clickSubmit() {
        System.out.println("Clicking Submit button: " + submitRequestBtn);
        page.click(submitRequestBtn);
    }

    public boolean isSuccessMessageVisible() {
        try {
            page.waitForSelector(
                    successMessage,
                    new Page.WaitForSelectorOptions()
                            .setState(WaitForSelectorState.ATTACHED)
                            .setTimeout(8000));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

}
