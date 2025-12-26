package com.yourmoca.tests;

import com.yourmoca.base.BaseTest;

import com.yourmoca.pages.FindWorkPage;
import org.testng.annotations.Test;

public class FindWorkTest extends BaseTest {

    @Test
    public void testFindWork() {
        FindWorkPage findWorkPage = new FindWorkPage(page);
        // findWorkPage.searchForWork();
    }
}
