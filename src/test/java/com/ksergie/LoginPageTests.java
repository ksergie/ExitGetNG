package com.ksergie;

import org.testng.annotations.Test;

public class LoginPageTests extends BaseTest{

    private LoginPage loginPage;

    @Test(testName = "testLoginWithCorrectData", dataProvider = "correctLogin", dataProviderClass = TestData.class)
    void testLoginWithCorrectData(String mail, String password){
        loginPage = new LoginPage(eventDriver);
        DashboardPage dashboardPage = new DashboardPage(eventDriver);
        loginPage.loginWithCorrectData(mail, password);
        dashboardPage.clickLogoutButton();

    }

    @Test(testName = "testNeedHelpLink")
    void testNeedHelpLink(){
        loginPage = new LoginPage(eventDriver);
        loginPage.checkNeedHelpLink();
    }

    @Test(testName = "testForgotPasswordLink")
    void testForgotPasswordLink(){
        loginPage = new LoginPage(eventDriver);
        loginPage.checkForgotPasswordLink();
    }

    @Test(testName = "testCreateNewOneLink")
    void testCreateNewOneLink(){
        loginPage = new LoginPage(eventDriver);
        loginPage.checkCreateNewOneLink();
    }
}
