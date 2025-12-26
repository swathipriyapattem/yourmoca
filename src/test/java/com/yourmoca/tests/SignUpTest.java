package com.yourmoca.tests;

import com.yourmoca.base.BaseTest;

import com.yourmoca.pages.SignUpPage;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    @Test
    public void testSignUp() {
        SignUpPage signUpPage = new SignUpPage(page);
        // signUpPage.performSignUp();
    }
}
