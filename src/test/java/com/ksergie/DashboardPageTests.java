package com.ksergie;

import org.testng.annotations.Test;

public class DashboardPageTests extends BaseTest{
    private DashboardPage dashboardPage;

    @Test(testName = "testOpenDashboardPage")
    void testOpenDashboardPage(){
        dashboardPage = new DashboardPage(eventDriver);
        dashboardPage.openDashboardPage();
        dashboardPage.clickLogoutButton();
    }

    @Test(testName = "testAccountButton")
    void testAccountButton(){
        dashboardPage = new DashboardPage(eventDriver);
        dashboardPage.openDashboardPage();
        dashboardPage.clickAccountButton();
        dashboardPage.clickLogoutButton();
    }
}
