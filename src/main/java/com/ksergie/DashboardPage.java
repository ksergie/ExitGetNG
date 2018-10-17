package com.ksergie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonAccount)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
        Assert.assertEquals("exitgetest", driver.findElement(userName).getText().trim(), "The user name is not displayed on the top of the Dashboard");
        Assert.assertEquals("API Key: a12f41728769c1", driver.findElement(stringAPIKey).getText(), "The API Key is wrong");
    }
}
