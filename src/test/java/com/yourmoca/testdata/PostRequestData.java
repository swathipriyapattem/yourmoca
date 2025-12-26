package com.yourmoca.testdata;

import org.testng.annotations.DataProvider;

public class PostRequestData {

        @DataProvider(name = "postRequestData")
        public Object[][] getPostRequestData() {
                return new Object[][] {
                                {
                                                "Isha", // Project Name
                                                "Short Film", // Project Type
                                                "It is a thriller content", // Project
                                                                            // Details
                                                "2025-12-31", // Project End Date
                                                "India", // Country
                                                "Andhra Pradesh", // State
                                                "Kakinada", // City
                                                "Actor", // Subcategory
                                                "2025-12-30", // Service From Date
                                                "2025-12-31", // Service To Date
                                                "2025-12-29", // Last date of submission
                                                "Must have experience in acting field" // Requirements
                                }
                };
        }
}
