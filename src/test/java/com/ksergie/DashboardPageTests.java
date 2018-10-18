package com.ksergie;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class DashboardPageTests extends BaseTest{
    private DashboardPage dashboardPage;

    @Ignore
    @Test(testName = "testOpenDashboardPage")
    void testOpenDashboardPage(){
        dashboardPage = new DashboardPage(eventDriver);
        dashboardPage.openDashboardPage();
        dashboardPage.clickLogoutButton();
    }

    @Ignore
    @Test(testName = "testAccountButton")
    void testAccountButton(){
        dashboardPage = new DashboardPage(eventDriver);
        dashboardPage.openDashboardPage();
        dashboardPage.clickAccountButton();
        dashboardPage.clickLogoutButton();
    }

    @Test(testName = "testQuickStartGuide")
    void testQuickStartGuide(){
        dashboardPage = new DashboardPage(eventDriver);
        dashboardPage.clickQuickStartGuide();
    }
}
