package com.ksergie;

import org.testng.annotations.Test;

public class SubscribePageTests extends BaseTest{
    private SubscribePage subscribePage;

    @Test(testName = "testChooseSubscription")
    void testChooseSubscription(){
        subscribePage = new SubscribePage(eventDriver);
        subscribePage.chooseSubscription();
    }

    @Test(testName = "testPlanLink")
    void testPlanLink(){
        subscribePage = new SubscribePage(eventDriver);
        subscribePage.clickPlanLink();
    }

    @Test(testName = "testDisplayedPlans")
    void testDisplayedPlans(){
        subscribePage = new SubscribePage(eventDriver);
        subscribePage.checkDisplayedPlans();
    }
}
