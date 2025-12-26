package com.yourmoca.tests;

import com.yourmoca.base.BaseTest;

import com.yourmoca.pages.CastingCallsPage;
import org.testng.annotations.Test;

public class CastingCallsTest extends BaseTest {

    @Test
    public void testCastingCalls() {
        CastingCallsPage castingCallsPage = new CastingCallsPage(page);
        // castingCallsPage.viewCastingCalls();
    }
}
