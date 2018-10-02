package com.ksergie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpPage {
    private EventFiringWebDriver driver;
    private static final Logger log = LogManager.getLogger(SignUpPage.class.getName());

    public SignUpPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private By buttonGetStarted1 = By.xpath("(//a[text()='GET STARTED'])[1]");
    private By linkLogin = By.xpath("//div[@class='haveAnAccount']/a");
    private By linkTermOfService = By.xpath("//a[text()='Terms of Service']");
    private By linkPrivacyPolicy = By.xpath("//label/a[text()='Privacy Policy']");
    private By fieldUserName = By.id("nameInput");
    private By fieldUserEmail = By.id("emailInput");
    private By checkboxIAccept = By.id("legalCheckbox");
    private By checkboxEmailCheck = By.id("emailCheckbox");
    private By buttonContinue = By.id("sendInput");
    private By fieldPassword = By.id("passwordInput");

    private void clickSignUpButton(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        MainPage mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 7);
        driver.get(mainPage.url);
        executor.executeScript("arguments[0].click();", driver.findElement(buttonGetStarted1));
        wait.until(ExpectedConditions.titleIs("Signup for Exitget"));
        Assert.assertEquals("Signup for Exitget", driver.getTitle(), "We are not on the SignUp page");
    }

    public void clickLoginLink(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        clickSignUpButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkLogin)).click();
        wait.until(ExpectedConditions.titleIs("Login - Exitget"));
        Assert.assertEquals("Login - Exitget", driver.getTitle(), "We are not on the Login page");
        driver.navigate().back();
    }

    public void clickTermOfServiceLink(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        clickSignUpButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkTermOfService)).click();
        switchWindows();
        wait.until(ExpectedConditions.titleIs("Terms of Service - Exitget"));
        Assert.assertEquals("Terms of Service - Exitget", driver.getTitle(), "We are not on the Terms of Service page");
    }

    public void clickPrivacyPolicyLink(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        clickSignUpButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkPrivacyPolicy)).click();
        switchWindows();
        wait.until(ExpectedConditions.titleIs("Privacy Policy - Exitget"));
        Assert.assertEquals("Privacy Policy - Exitget", driver.getTitle(), "We are not on the Privacy Policy page");
    }

    private void switchWindows(){
        for(String windowHandle: driver.getWindowHandles()){
            driver.switchTo().window(windowHandle);
        }
    }

    private SignUpPage inputName(String name){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldUserName)).sendKeys(name);
        return this;
    }

    private SignUpPage inputEmail(String email){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldUserEmail)).sendKeys(email);
        return this;
    }

    private SignUpPage tickCheckboxes(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxIAccept)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxEmailCheck)).click();
        return this;
    }

    private String getUser(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddmmss");
        Date date = new Date();
        return "tester647382" + dateFormat.format(date);
    }

    public void inputNameAndEmail(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        clickSignUpButton();
        String user = getUser();
        inputName(user);
        String email = user + "@exitget.com";
        inputEmail(email);
        tickCheckboxes();
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonContinue)).click();
        wait.until(ExpectedConditions.titleIs("Choose a password to sign into Exitget"));
        Assert.assertEquals("Choose a password to sign into Exitget", driver.getTitle(), "We are not on Choose a password page");
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldPassword)).sendKeys("20ExitGett_18");
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonContinue)).click();
        wait.until(ExpectedConditions.titleIs("Pricing - Exitget"));
        Assert.assertEquals("Pricing - Exitget", driver.getTitle(), "We are not on Price page");
    }
}
