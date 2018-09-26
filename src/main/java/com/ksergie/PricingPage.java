package com.ksergie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PricingPage {
    private EventFiringWebDriver driver;
    private static final Logger log = LogManager.getLogger(PricingPage.class.getName());

    public PricingPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private By buttonMonthly = By.xpath("//div[@id='myOff']/div[@class='myMonthly']");
    private By buttonYearly = By.className("myYearly");
    private By priceProPlan = By.xpath("(//div[@class='damount'])[1]");
    private By priceBusinessPlan = By.xpath("(//div[@class='damount'])[2]");
    private By priceEnterprisePlan = By.xpath("(//div[@class='damount'])[3]");
    private By linkMailTo = By.xpath("//a[text()='support@exitget.com']");


    private void openPricingPage(){
        MainPage mainPage = new MainPage(driver);
        driver.get(mainPage.url);
        mainPage.clickPricingPageLink();
    }

    protected PricingPage clickMonthlyButton(){
        clickButton();
        Assert.assertEquals("123", driver.findElement(priceProPlan).getText(), "Error clicking Monthly button");
        Assert.assertEquals("498", driver.findElement(priceBusinessPlan).getText(), "Error clicking Monthly button");
        Assert.assertEquals("1248+", driver.findElement(priceEnterprisePlan).getText(), "Error clicking Monthly button");
        return this;
    }

    protected PricingPage clickYearlyButton(){
        clickButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonYearly)).click();
        wait.until(ExpectedConditions.textToBe(priceProPlan, "99"));
        Assert.assertEquals("99", driver.findElement(priceProPlan).getText(), "Error clicking Monthly button");
        Assert.assertEquals("399", driver.findElement(priceBusinessPlan).getText(), "Error clicking Monthly button");
        Assert.assertEquals("999+", driver.findElement(priceEnterprisePlan).getText(), "Error clicking Monthly button");
        return this;
    }

    private void clickButton(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        openPricingPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonMonthly)).click();
        wait.until(ExpectedConditions.textToBe(priceProPlan, "123"));
    }

    protected void clickMonthlySelectPlanButton(String number, String urlPart){
        clickMonthlyButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String xpath = "(//a[text()='SELECT PLAN'])[" + number +"]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
        wait.until(ExpectedConditions.titleIs("Signup for Exitget"));
        Assert.assertTrue(driver.getCurrentUrl().contains(urlPart));
    }

    protected void clickYearlySelectPlanButton(String number, String urlPart){
        clickYearlyButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String xpath = "(//a[text()='SELECT PLAN'])[" + number +"]";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
        wait.until(ExpectedConditions.titleIs("Signup for Exitget"));
        Assert.assertTrue(driver.getCurrentUrl().contains(urlPart));
    }

    protected void mailLink(){
        openPricingPage();
        Assert.assertEquals("mailto:support@exitget.com", driver.findElement(linkMailTo).getAttribute("href"), "Link mailto:support@exitget.com incorrect");
    }
}
