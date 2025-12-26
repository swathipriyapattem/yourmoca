package com.yourmoca.testdata;

import org.testng.annotations.DataProvider;

public class LoginData {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                { "swathipriya+and1@krify.com", "Krify@123", "valid" },
                { "invalid.email@example.com", "validPassword", "invalid" },
                { "valid.email@example.com", "invalidPassword", "invalid" },
                { "validMobileNumber", "validPassword", "valid" },
                { "invalidMobileNumber", "validPassword", "invalid" },
                { "validMobileNumber", "invalidPassword", "invalid" }
        };
    }
}
