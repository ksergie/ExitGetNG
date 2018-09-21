package com.ksergie;

import org.testng.annotations.Test;

public class SignUpPage1Tests extends BaseTest{

    private SignUpPage1 signUpPage1;

    @Test(testName = "testLoginLink")
    void testLoginLink(){
        signUpPage1 = new SignUpPage1(eventDriver);
        signUpPage1.clickLoginLink();
    }

    @Test(testName = "testTermOfServiceLink")
    void testTermOfServiceLink(){
        signUpPage1 = new SignUpPage1(eventDriver);
        signUpPage1.clickTermOfServiceLink();
    }

    @Test(testName = "testPrivacyPolicyLink")
    void testPrivacyPolicyLink(){
        signUpPage1 = new SignUpPage1(eventDriver);
        signUpPage1.clickPrivacyPolicyLink();
    }
}
