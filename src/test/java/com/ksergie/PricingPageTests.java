package com.ksergie;

import org.testng.annotations.Test;

public class PricingPageTests extends BaseTest{

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

    @Test(testName = "testMonthlySelectPlanButton", dataProvider = "monthlyPlan", dataProviderClass = TestData.class)
    void testMonthlySelectPlanButton(String number, String urlPart){
        pricingPage = new PricingPage(eventDriver);
        pricingPage.clickMonthlySelectPlanButton(number, urlPart);
    }

    @Test(testName = "testYearlySelectPlanButton", dataProvider = "yearlyPlan", dataProviderClass = TestData.class)
    void testYearlySelectPlanButton(String number, String urlPart){
        pricingPage = new PricingPage(eventDriver);
        pricingPage.clickYearlySelectPlanButton(number, urlPart);
    }

    @Test(testName = "testMailToLink")
    void testMailToLink(){
        pricingPage = new PricingPage(eventDriver);
        pricingPage.mailLink();
    }
}
