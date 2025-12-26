package com.yourmoca.tests;

import com.yourmoca.base.BaseTest;

import com.yourmoca.pages.CompetitionsPage;
import org.testng.annotations.Test;

public class CompetitionsTest extends BaseTest {

    @Test
    public void testCompetitions() {
        CompetitionsPage competitionsPage = new CompetitionsPage(page);
        // competitionsPage.enterCompetition();
    }
}
