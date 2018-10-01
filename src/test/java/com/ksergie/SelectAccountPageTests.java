package com.ksergie;

import org.testng.annotations.Test;

public class SelectAccountPageTests extends BaseTest{

    private SelectAccountPage selectAccountPage;

    @Test(testName = "testSelectAccount")
    void testSelectAccount(){
        selectAccountPage = new SelectAccountPage(eventDriver);
        selectAccountPage.selectAccount();
    }
}
