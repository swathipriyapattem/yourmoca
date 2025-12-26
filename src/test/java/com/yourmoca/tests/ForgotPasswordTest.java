package com.yourmoca.tests;

import com.yourmoca.base.BaseTest;

import com.yourmoca.pages.ForgotPasswordPage;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseTest {

    @Test
    public void testForgotPassword() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(page);
        // forgotPasswordPage.recoverPassword();
    }
}
