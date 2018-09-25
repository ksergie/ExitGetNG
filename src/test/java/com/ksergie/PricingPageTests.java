package com.ksergie;

import org.testng.annotations.Test;

public class PricingPageTests extends BaseTest {
    private PricingPage pricingPage;
    @Test(testName = "testMonthlyButton")
    void testMonthlyButton(){
        pricingPage = new PricingPage(eventDriver);
        pricingPage.clickMonthlyButton();
    }

    @Test(testName = "testYearlyButton")
    void testYearlyButton(){
        pricingPage = new PricingPage(eventDriver);
        pricingPage.clickYearlyButton();
    }
}
