package com.ksergie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class SelectAccountPage {
    private EventFiringWebDriver driver;
    private static final Logger log = LogManager.getLogger(SelectAccountPage.class.getName());

    public SelectAccountPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private void openSelectAccountPage(){
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.inputNameAndEmail();
        PricingPage pricingPage = new PricingPage(driver);
        pricingPage.selectYearlyEnterprisePlan();
    }

    protected void selectBrandAccount(){
        openSelectAccountPage();
    }
}
