package com.yourmoca.tests;

import com.yourmoca.base.BaseTest;

import com.yourmoca.pages.AllCategoriesPage;
import org.testng.annotations.Test;

public class AllCategoriesTest extends BaseTest {

    @Test
    public void testAllCategories() {
        AllCategoriesPage allCategoriesPage = new AllCategoriesPage(page);
        // allCategoriesPage.checkCategories();
    }
}
