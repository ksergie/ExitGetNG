package com.ksergie;

import org.testng.annotations.Test;

public class SelectAccountPageTests extends BaseTest {
    private SelectAccountPage selectAccountPage;

    @Test(testName = "testBrandAccount")
    void testBrandAccount(){
        selectAccountPage = new SelectAccountPage(eventDriver);
        selectAccountPage.selectBrandAccount();
    }
}
