package com.yourmoca.tests;

import com.yourmoca.base.BaseTest;

import com.yourmoca.pages.HomePage;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePage() {
        HomePage homePage = new HomePage(page);
        // homePage.verifyElements();
    }
}
