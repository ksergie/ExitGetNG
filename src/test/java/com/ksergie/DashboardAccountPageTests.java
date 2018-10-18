package com.ksergie;

import org.testng.annotations.Test;

public class DashboardAccountPageTests extends BaseTest{
    private DashboardAcoountPage dashboardAcoountPage;

    @Test(testName = "testCloseButton")
    void testCloseButton(){
        dashboardAcoountPage = new DashboardAcoountPage(eventDriver);
        dashboardAcoountPage.clickCloseButton();
    }

    @Test(testName = "testResetAccountWithPassword")
    void testResetAccountWithPassword(){
        dashboardAcoountPage = new DashboardAcoountPage(eventDriver);
        dashboardAcoountPage.resetAccountWithPassword();
    }
}
