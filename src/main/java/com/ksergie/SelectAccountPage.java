package com.ksergie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class SelectAccountPage {
    private EventFiringWebDriver driver;
    private static final Logger log = LogManager.getLogger(SelectAccountPage.class.getName());

    public SelectAccountPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private By dropboxSelectAccountType = By.id("accountType");
    private String name = "Ksergie";
    private String webSite = "https://ksergie.com";
    private String jobPosition = "Manager";


    protected void openSelectAccountPage(){
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.inputNameAndEmail();
        PricingPage pricingPage = new PricingPage(driver);
        pricingPage.selectYearlyEnterprisePlan();
    }

    protected void selectAccount(){
        openSelectAccountPage();
        Select accountType = new Select(driver.findElement(dropboxSelectAccountType));
        selectBrandAccount(accountType);
        selectSmallBusinessAccount(accountType);
        selectMarketerAccount(accountType);
        selectBloggerAccount(accountType);
        selectStreamerAccount(accountType);
        selectAgencyAccount(accountType);
        selectOtherAccount(accountType);
    }

    protected void selectBrandAccount(Select aType){
        By fieldName = By.name("brand_name");
        By fieldWebSite = By.name("brand_website");
        By fieldJobPosition = By.name("brand_job");
        By buttonContinue = By.xpath("//input[@class='sendInput']");

        aType.selectByVisibleText("Brand");
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldWebSite).sendKeys(webSite);
        driver.findElement(fieldJobPosition).sendKeys(jobPosition);
        Assert.assertTrue(driver.findElement(buttonContinue).isEnabled(), "Error filling the Brand Account");
    }

    private void selectSmallBusinessAccount(Select aType){
        By fieldName = By.name("business_name");
        By fieldWebSite = By.name("business_website");
        By fieldJobPosition = By.name("business_job");
        By buttonContinue = By.xpath("//input[@class='sendInput']");

        aType.selectByVisibleText("Small Business");
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldWebSite).sendKeys(webSite);
        driver.findElement(fieldJobPosition).sendKeys(jobPosition);
        Assert.assertTrue(driver.findElement(buttonContinue).isEnabled(), "Error filling the Small Business Account");
    }

    private void selectMarketerAccount(Select aType){
        By fieldName = By.name("marketer_name");
        By fieldWebSite = By.name("marketer_website");
        By buttonContinue = By.xpath("//input[@class='sendInput']");

        aType.selectByVisibleText("Marketer");
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldWebSite).sendKeys(webSite);
        Assert.assertTrue(driver.findElement(buttonContinue).isEnabled(), "Error filling the Marketer Account");
    }

    private void selectBloggerAccount(Select aType){
        By fieldName = By.name("blog_name");
        By fieldWebSite = By.name("blog_website");
        By buttonContinue = By.xpath("//input[@class='sendInput']");

        aType.selectByVisibleText("Blogger");
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldWebSite).sendKeys(webSite);
        Assert.assertTrue(driver.findElement(buttonContinue).isEnabled(), "Error filling the Blogger Account");
    }

    private void selectStreamerAccount(Select aType){
        By fieldName = By.name("streamer_name");
        By fieldWebSite = By.name("streamer_website");
        By buttonContinue = By.xpath("//input[@class='sendInput']");

        aType.selectByVisibleText("Streamer");
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldWebSite).sendKeys(webSite);
        Assert.assertTrue(driver.findElement(buttonContinue).isEnabled(), "Error filling the Streamer Account");
    }

    private void selectAgencyAccount(Select aType){
        By fieldName = By.name("agency_name");
        By fieldWebSite = By.name("agency_website");
        By fieldJobPosition = By.name("agency_job");
        By buttonContinue = By.xpath("//input[@class='sendInput']");

        aType.selectByVisibleText("Agency");
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldWebSite).sendKeys(webSite);
        driver.findElement(fieldJobPosition).sendKeys(jobPosition);
        Assert.assertTrue(driver.findElement(buttonContinue).isEnabled(), "Error filling the Agency Account");
    }

    private void selectOtherAccount(Select aType){
        By fieldName = By.name("other_name");
        By fieldWebSite = By.name("other_website");
        By fieldWebSiteDesc = By.name("other_description");
        By fieldJobPosition = By.name("other_job");
        By buttonContinue = By.xpath("//input[@class='sendInput']");

        aType.selectByVisibleText("Other");
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldWebSite).sendKeys(webSite);
        driver.findElement(fieldWebSiteDesc).sendKeys("This is our site ");
        driver.findElement(fieldJobPosition).sendKeys(jobPosition);
        Assert.assertTrue(driver.findElement(buttonContinue).isEnabled(), "Error filling the Other Account");
    }

    protected void selectTestAccount(){
        By buttonContinue = By.xpath("//input[@class='sendInput']");
        By fieldSiteName = By.xpath("(//tbody//div[@class='input2'])[1]");
        openSelectAccountPage();
        Select accountType = new Select(driver.findElement(dropboxSelectAccountType));
        selectBrandAccount(accountType);
        driver.findElement(buttonContinue).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleIs("Setup your Exitget Account"));
        Assert.assertEquals(name, trim(driver.findElement(fieldSiteName).getText()), "Wrong Site Name");
    }
}
