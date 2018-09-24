package com.ksergie;

import org.testng.annotations.Test;

public class SignUpPageTests extends BaseTest{

    private SignUpPage signUpPage1;

    @Test(testName = "testLoginLink")
    void testLoginLink(){
        signUpPage1 = new SignUpPage(eventDriver);
        signUpPage1.clickLoginLink();
    }

    @Test(testName = "testTermOfServiceLink")
    void testTermOfServiceLink(){
        signUpPage1 = new SignUpPage(eventDriver);
        signUpPage1.clickTermOfServiceLink();
    }

    @Test(testName = "testPrivacyPolicyLink")
    void testPrivacyPolicyLink(){
        signUpPage1 = new SignUpPage(eventDriver);
        signUpPage1.clickPrivacyPolicyLink();
    }

    @Test(testName = "testUserRegistration")
    void testUserRegistration(){
        signUpPage1 = new SignUpPage(eventDriver);
        signUpPage1.inputNameAndEmail();
    }
}
