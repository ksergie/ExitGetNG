package com.ksergie;

import org.testng.annotations.Test;

public class DashboardAccountPageTests extends BaseTest{
    private DashboardAcoountPage dashboardAcoountPage;

    @Test(testName = "testCloseButton")
    void testCloseButton(){
        dashboardAcoountPage = new DashboardAcoountPage(eventDriver);
        dashboardAcoountPage.clickCloseButton();
        DashboardPage dashboardPage = new DashboardPage(eventDriver);
        dashboardPage.clickLogoutButton();
    }

    @Test(testName = "testResetAccountWithPassword")
    void testResetAccountWithPassword(){
        dashboardAcoountPage = new DashboardAcoountPage(eventDriver);
        dashboardAcoountPage.resetAccountWithPassword();
        DashboardPage dashboardPage = new DashboardPage(eventDriver);
        dashboardPage.clickLogoutButton();
    }
}
