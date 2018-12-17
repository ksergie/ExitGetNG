package com.ksergie;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class SubscribePageTests extends BaseTest{
    private SubscribePage subscribePage;

    @Ignore
    @Test(testName = "testChooseSubscription")
    void testChooseSubscription(){
        subscribePage = new SubscribePage(eventDriver);
        subscribePage.chooseSubscription();
    }

    @Ignore
    @Test(testName = "testPlanLink")
    void testPlanLink(){
        subscribePage = new SubscribePage(eventDriver);
        subscribePage.clickPlanLink();
    }


    @Ignore
    @Test(testName = "testDisplayedPlans")
    void testDisplayedPlans(){
        subscribePage = new SubscribePage(eventDriver);
        subscribePage.checkDisplayedPlans();
    }

    @Ignore
    @Test(testName = "testSubscribeButton")
    void testSubscribeButton(){
        subscribePage = new SubscribePage(eventDriver);
        subscribePage.checkSubscribeButton();
    }
}
