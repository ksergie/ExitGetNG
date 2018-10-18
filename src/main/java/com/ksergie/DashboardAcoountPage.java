package com.ksergie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DashboardAcoountPage {
    private EventFiringWebDriver driver;
    private static final Logger log = LogManager.getLogger(DashboardAcoountPage.class.getName());

    public DashboardAcoountPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private By buttonClose = By.xpath("//button[text()='CLOSE']");
    private By stringVisitations = By.xpath("(//div[@class='helptext'])[1]");
    private By fieldCurrentPassword = By.id("accountpass2");
    private By buttonResetAccount = By.xpath("//button[text()='RESET ACCOUNT']");
    private By stringAccountResult = By.id("accountResultMessage");
    private By stringAccountResult1 = By.xpath("//div[text()='Reset complete']");
    private String password = "Ckfdfnhele_1";

    private void openDashboardAccountPage(){
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.openDashboardPage();
        dashboardPage.clickAccountButton();
    }

    protected void clickCloseButton(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        openDashboardAccountPage();
        wait.until(ExpectedConditions.elementToBeClickable(buttonClose)).click();
        Assert.assertEquals("VISITATIONS", wait.until(ExpectedConditions.visibilityOfElementLocated(stringVisitations)).getText(),
                "The Dashboard Account page is not closed after clicking Close button");
    }

    protected void resetAccountWithPassword(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        openDashboardAccountPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldCurrentPassword)).sendKeys(password);
        driver.findElement(buttonResetAccount).click();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.attributeContains(fieldCurrentPassword, "placeholder", "Enter your current password"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(stringAccountResult1));
        Assert.assertEquals("Reset complete", driver.findElement(stringAccountResult).getText(),
                "The Reset complete string is not displayed after clicking Reset Account button");
    }
}
