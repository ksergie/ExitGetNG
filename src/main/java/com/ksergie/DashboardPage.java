package com.ksergie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class DashboardPage {
    private EventFiringWebDriver driver;
    private static final Logger log = LogManager.getLogger(DashboardPage.class.getName());

    public DashboardPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private By buttonLoguot = By.id("logoutButton");
    private By buttonAccount = By.id("accountButton");
    private By stringAPIKey = By.xpath("(//div[@class='inputlabel'])[1]");
    private By userName = By.xpath("//div[@id='header_links']");
    private By fieldAccountName = By.id("accountname");
    private By fieldAccountEmail = By.id("accountemail");
    private By itemQuickStartGuide = By.xpath("//a[@class='dashboard_quickstart sub_item']");
    private By titleInstallationUrl = By.xpath("//div[@id='inputUrl']//span[@class='webAddress']");
    private By fieldInstallUrl = By.id("installUrl");
    private String installUrl = "https://exitgetester.tumblr.com";
    private By buttonNextInstallUrl = By.xpath("//div[@id='inputUrl']//input[@value='Next']");
    private By titleDebugMode= By.xpath("//div[@id='debugMode']//span[@class='webAddress']");
    private By buttonNextDevelopmentMode = By.xpath("//div[@id='debugMode']//button[text()='Next']");
    private By indicatorDevelopmentMode = By.xpath("//div[@id='debugMode']//span[@class='indicator']");
    private By titleAdvertismentBackgrount = By.xpath("//div[@id='chooseImage']//span[@class='webAddress']");
    private By firstAdvertisementBackgroundIcon = By.xpath("(//div[@id='campaignpick']//img[@class='icon'])[2]");
    private By titleCustomizeDesign = By.xpath("//div[@id='customizeDesign']//span[@class='webAddress']");
    private By buttonNextDesignCustomize = By.xpath("//div[@id='customizeDesign']//button[text()='Next']");
    private By titleEditRedirectionUrl = By.xpath("//div[@id='inputRedirectionUrl']//span[@class='webAddress']");
    private By fieldRedirectionUrl = By.id("redirectionUrl");
    private String redirectionUrl = "https://exitget.com";
    private By buttonNextRedirectionUrl = By.xpath("//div[@id='inputRedirectionUrl']//input[@type='button']");
    private By titleRedirectionUrl = By.xpath("//div[@id='inputRedirectionUrl']//span[@class='webAddress']");
    private By titleTriggersForm = By.xpath("//div[@id='triggerType']//span[@class='webAddress']");
    private By itemTimeDelay = By.xpath("//span[text()='Time Delay']");
    private By itemExitIntent = By.xpath("//span[text()='Exit Intent']");
    private By buttonNextTriggersForm = By.xpath("//div[@id='triggerType']//button[text()='Next']");
    private By titleConfirmInstallation = By.xpath("//div[@id='testInstallation']//span[@class='webAddress']");
    private By testSuccessful = By.xpath("(//div[@class='listPadded'])[1]");
    private By buttonOpen = By.xpath("//button[text()='Open']");
    private By titleSourcePage = By.xpath("//a[@class='navbar-brand']");
    private By buttonPopupWindow = By.id("exitget_input_3");
    private By titleExitget = By.xpath("//h1/span");

    private void clickLoginButton(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickHeaderLoginButton();
    }

    private void fillLoginForm(){
        String email = "exitgetest@gmail.com";
        String password = "Ckfdfnhele_1";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCorrectData(email, password);
    }

    protected void openDashboardPage(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        clickLoginButton();
        fillLoginForm();
        wait.until(ExpectedConditions.titleIs("Dashboard - Exitget"));
        Assert.assertEquals("Dashboard - Exitget", driver.getTitle(), "We are not on the Dashboard page");
    }

    protected void clickLogoutButton(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLoguot)).click();
        wait.until(ExpectedConditions.titleIs("Exitget. A Popup Platform for Everyone"));
        Assert.assertEquals("Exitget. A Popup Platform for Everyone", driver.getTitle(), "We are not on the main page after clicking LogOut button");
    }

    protected void clickAccountButton(){
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonAccount)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
        Assert.assertEquals("exitgetest", driver.findElement(userName).getText().trim(), "The user name is not displayed on the top of the Dashboard");
        Assert.assertEquals("API Key: a12f41728769c1", driver.findElement(stringAPIKey).getText(), "The API Key is wrong");
        Assert.assertEquals("exitgetest", driver.findElement(fieldAccountName).getAttribute("value"), "The Account Name is not displayed into the Name field");
        Assert.assertEquals("exitgetest@gmail.com", driver.findElement(fieldAccountEmail).getAttribute("value"), "The Account Email is not displayed into the Email field");
    }

    protected void clickQuickStartGuide(){
        WebDriverWait wait = new WebDriverWait(driver, 7);
        DashboardAcoountPage dashboardAcoountPage = new DashboardAcoountPage(driver);
        dashboardAcoountPage.resetAccountWithPassword();
        driver.findElement(itemQuickStartGuide).click();
        Assert.assertEquals("Enter the installation URL", driver.findElement(titleInstallationUrl).getText(),
                "The Enter the installation URL form is not displayed after clicking Quick Start Guide item");

        // Input the installation URL and press Next button

        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldInstallUrl)).sendKeys(installUrl);
        driver.findElement(buttonNextInstallUrl).click();
        Assert.assertEquals("Development mode", wait.until(ExpectedConditions.visibilityOfElementLocated(titleDebugMode)).getText(),
                "The Debug Mode form is not displayed after clicking the Next button");
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(titleInstallationUrl)).getText().contains(installUrl),
                "The Enter the installation URL form is not closed after clicking Next button");

        // Click the Next button on the Development mode form

        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonNextDevelopmentMode)).click();
        Assert.assertEquals("OFF", driver.findElement(indicatorDevelopmentMode).getText(),
                "The indicator of the Development mode is not displayed after clicking the Next button");
        Assert.assertEquals("Choose the advertisement background", driver.findElement(titleAdvertismentBackgrount).getText(),
                "The Choose the advertisement background form is not displayed after clicking the Next button");

        // Click the first Advertisement background icon

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstAdvertisementBackgroundIcon)).click();
        Assert.assertEquals("Royalty free background selected", driver.findElement(titleAdvertismentBackgrount).getText(),
                "The Choose the advertisement background form is not closed after clicking the first template");
        Assert.assertEquals("Profesional Design Customization", driver.findElement(titleCustomizeDesign).getText(),
                "The Profesional Design Customization form is not displayed after clicking the first title");

        // Click the Next button on the Profesional Design Customization form

        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonNextDesignCustomize)).click();
        Assert.assertEquals("Enter the redirection URL", driver.findElement(titleEditRedirectionUrl).getText(),
                "The Enter the redirection URL form is not displayed after clicking the Next button");

        // Enter the redirection URL and click the Next button

        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldRedirectionUrl)).sendKeys(redirectionUrl);
        driver.findElement(buttonNextRedirectionUrl).click();
        Assert.assertTrue(driver.findElement(titleRedirectionUrl).getText().contains(redirectionUrl),
                "The Enter the redirection URL form is not closed after clicking Next button");
        Assert.assertEquals("1 rules configred", driver.findElement(titleTriggersForm).getText(),
                "The Triggers form is not displayed after clicking the Next button");

        // Unselect Time delay trigger, select Exit Intent trigger and click the Next button

        wait.until(ExpectedConditions.visibilityOfElementLocated(itemTimeDelay));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(itemTimeDelay));
        wait.until(ExpectedConditions.elementToBeClickable(itemExitIntent));
        executor.executeScript("arguments[0].click();", driver.findElement(itemExitIntent));
        driver.findElement(buttonNextTriggersForm).click();
        Assert.assertEquals("1 rules configred", wait.until(ExpectedConditions.visibilityOfElementLocated(titleTriggersForm)).getText(),
                "The Confirm installation form is not displayed after clicking the Next button");
        Assert.assertEquals("Final step: Confirm installation", wait.until(ExpectedConditions.visibilityOfElementLocated(titleConfirmInstallation)).getText(),
                "The Confirm Installation form is not displayed after clicking the Next button");
        Assert.assertTrue(driver.findElement(testSuccessful).getText().contains("Good job!"),
                "Automated installation test failed");

        // Click the Open button on the Confirm installation form

        wait.until(ExpectedConditions.elementToBeClickable(buttonOpen)).click();
        selectNewWindow();
        wait.until(ExpectedConditions.titleIs("Exitget is a first of its kind, completely free popup platform"));
        Assert.assertEquals("Exitget is a first of its kind, completely free popup platform", driver.findElement(titleSourcePage).getText(),
                "The source page is not displayed after clicking the Open button");

        // Try to start popup window

        MainPage mainPage = new MainPage(driver);
        mainPage.pause(600);
        mainPage.moveCoursor(800, 80);
        mainPage.pause(300);
        mainPage.moveCoursor(800, 10);
        mainPage.pause(300);

        // Switch to Popup window

        selectNewWindow();
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonPopupWindow));
        executor.executeScript("arguments[0].click();", driver.findElement(buttonPopupWindow));
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleExitget));
        Assert.assertEquals("Engage", driver.findElement(titleExitget).getText(),
                "The redirect page is not displayed after clicking the popup window");
    }

    private void selectNewWindow(){
        for(String windowHandle: driver.getWindowHandles()){
            driver.switchTo().window(windowHandle);
        }
    }
}
