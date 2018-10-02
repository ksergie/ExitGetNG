package com.ksergie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SubscribePage {
    private EventFiringWebDriver driver;
    private static final Logger log = LogManager.getLogger(SubscribePage.class.getName());

    public SubscribePage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private By checkboxSubscription = By.xpath("//label[@id='cycle_choose']/span");
    private By fieldBillingCycle = By.id("billing_cycle");
    private By linkPlan = By.xpath("//a[@class='input plan']");
    private By buttonLogOut = By.xpath("//button[@class='logout']");

    private void openSubscribePage(){
        SelectAccountPage selectAccountPage = new SelectAccountPage(driver);
        selectAccountPage.selectTestAccount();
    }

    protected void chooseSubscription(){
        openSubscribePage();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxSubscription)).click();
        Assert.assertEquals("Monthly", driver.findElement(fieldBillingCycle).getText(), "Billing cycle (Monthly) is not correct");
        driver.findElement(checkboxSubscription).click();
        Assert.assertEquals("Yearly", driver.findElement(fieldBillingCycle).getText(), "Billing cycle (Yearly) is not correct");
        clickLogOutButton();
    }

    protected void clickPlanLink(){
        openSubscribePage();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkPlan)).click();
        wait.until(ExpectedConditions.titleIs("Pricing - Exitget"));
        Assert.assertEquals("Pricing - Exitget", driver.getTitle(), "We are not on the Price Page");
        clickLogOutButton();
    }

    protected void clickLogOutButton(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogOut)).click();
        wait.until(ExpectedConditions.titleIs("Exitget. A Popup Platform for Everyone"));
        Assert.assertEquals("Exitget. A Popup Platform for Everyone", driver.getTitle(), "We are not on the Main Page");
    }
}
