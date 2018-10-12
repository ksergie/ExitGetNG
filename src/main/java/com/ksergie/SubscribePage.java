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
    private By subscriptionPrice = By.id("costRating");
    private By subscriptionYearPrice = By.id("paymentAmount");
    private By fieldPlan = By.xpath("//a[@class='input plan']/span");
    private By buttonSubscribe = By.id("addCreditCard");
    private By titlePayWindow = By.xpath("//h1[@class='Header-companyName u-textTruncate']");

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

    protected void checkDisplayedPlans(){
        PricingPage pricingPage = new PricingPage(driver);
        openSubscribePage();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //check yearly plan
        for(int i = 1; i < 4; i++){
            wait.until(ExpectedConditions.visibilityOfElementLocated(linkPlan)).click();
            wait.until(ExpectedConditions.titleIs("Pricing - Exitget"));

            // getting the plan and amount
            getPlanAndAmountYearly(i);
        }

        //check monthly plan
        for(int i = 1; i < 4; i++){
            wait.until(ExpectedConditions.visibilityOfElementLocated(linkPlan)).click();
            wait.until(ExpectedConditions.titleIs("Pricing - Exitget"));
            pricingPage.clickAndCheckMonthlyButton();

            // getting the plan and amount
            getPlanAndAmountMonthly(i);
        }

        clickLogOutButton();

    }

    private void getPlanAndAmountMonthly(int i){
        // getting the plan and amount
        String planXpath = "//div[@class='boxes box" + i + "']//span";
        String amountXpath = "//div[@class='boxes box" + i + "']//div[@class='damount']";
        String buttonXpath = "//div[@class='boxes box" + i + "']//a[text()='SELECT PLAN']";

        String plan = driver.findElement(By.xpath(planXpath)).getText();
        String amount = driver.findElement(By.xpath(amountXpath)).getText();
        if(amount.endsWith("+")){
            amount = amount.substring(0, amount.length() - 1);
        }
        driver.findElement(By.xpath(buttonXpath)).click();

        String price = driver.findElement(subscriptionPrice).getText();
        // checking
        Assert.assertEquals(plan, driver.findElement(fieldPlan).getText(), "The selected plan and displayed plan are not match");
        Assert.assertEquals(amount, stringWithoutComma(price), "The selected price and displayed price are not match");
    }

    private void getPlanAndAmountYearly(int i){
        // getting the plan and amount
        String planXpath = "//div[@class='boxes box" + i + "']//span";
        String amountXpath = "//div[@class='boxes box" + i + "']//div[@class='damount']";
        String buttonXpath = "//div[@class='boxes box" + i + "']//a[text()='SELECT PLAN']";

        String plan = driver.findElement(By.xpath(planXpath)).getText();
        String amount = driver.findElement(By.xpath(amountXpath)).getText();
        if(amount.endsWith("+")){
            amount = amount.substring(0, amount.length() - 1);
        }
        driver.findElement(By.xpath(buttonXpath)).click();

        String price = driver.findElement(subscriptionPrice).getText();
        String yearPrice = driver.findElement(subscriptionYearPrice).getText();

        // checking
        Assert.assertEquals(plan, driver.findElement(fieldPlan).getText(), "The selected plan and displayed plan are not match");
        Assert.assertEquals(amount, price, "The selected price and displayed price are not match");
        Assert.assertEquals(Integer.parseInt(stringWithoutComma(yearPrice)), Integer.parseInt(price) * 12, "The monthly price * 12 does not equal the yearly price");
    }

    private String stringWithoutComma(String str){
        String result = "";
        for(int i = 0; i < str.length(); i++){
            if (Character.toString(str.charAt(i)).equals(",")){
                continue;
            } else {
                result = result.concat(Character.toString(str.charAt(i)));
            }
        }
        return result;
    }

    protected void checkSubscribeButton(){
        MainPage mainPage = new MainPage(driver);
        openSubscribePage();
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.elementToBeClickable(buttonSubscribe)).click();
        mainPage.pause(1000);
        driver.switchTo().frame(0);
        wait.until(ExpectedConditions.visibilityOfElementLocated(titlePayWindow));
        Assert.assertEquals("Exitget", driver.findElement(titlePayWindow).getText(), "We are not on the Stripe Payment page");
    }
}
