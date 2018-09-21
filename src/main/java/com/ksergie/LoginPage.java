package com.ksergie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import static jdk.nashorn.internal.objects.NativeString.trim;

public class LoginPage {
    private EventFiringWebDriver driver;
    private static final Logger log = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private By fieldEmail = By.id("userInput");
    private By fieldPassword = By.id("passwordInput");
    private By buttonLogin = By.id("sendInput");
    private By header = By.xpath("//div[@id='login_frame']//div[@class='contentlabel font_size_by_height']");
    private static String url = "https://exitget.com";
    private By linkNeedHelp = By.xpath("//a[text()='Need help?']");
    private By linkForgotPassword = By.xpath("//a[@href='//exitget.com/login/forgot']");
    private By linkCreateNewOne = By.xpath("//a[@href='//exitget.com/pricing']");


    public String getHeader(){
        return trim(driver.findElement(header).getText());
    }

    private LoginPage inputEmail(String email){
        driver.findElement(fieldEmail).sendKeys(email);
        return this;
    }

    private LoginPage inputPassword(String passwd){
        driver.findElement(fieldPassword).sendKeys(passwd);
        return this;
    }

    public void login(String email, String passwd){
        inputEmail(email);
        inputPassword(passwd);
        driver.findElement(buttonLogin).submit();
    }

    public void loginWithCorrectData(String email, String passwd){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        openLoginPage();
        login(email, passwd);
        wait.until(ExpectedConditions.titleIs("Dashboard - Exitget"));
        Assert.assertEquals("Dashboard - Exitget", driver.getTitle(),
                "Login with correct data - We are not on the Overview page");
    }

    public void checkNeedHelpLink(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        openLoginPage();
        wait.until(ExpectedConditions.elementToBeClickable(linkNeedHelp));
        String attrLink = driver.findElement(linkNeedHelp).getAttribute("href");
        Assert.assertEquals("mailto:support@exitget.com", attrLink, "Need Help link isn't refered to mailto:support@exitget.com");
    }

    public void checkForgotPasswordLink(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        openLoginPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkForgotPassword)).click();
        wait.until(ExpectedConditions.titleIs("Forgot password - Exitget"));
        Assert.assertEquals("Forgot password - Exitget", driver.getTitle(), "Check Forgot password link - We are not on the Account Recovery page" );
    }

    public void checkCreateNewOneLink(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        openLoginPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkCreateNewOne)).click();
        wait.until(ExpectedConditions.titleIs("Pricing - Exitget"));
        Assert.assertEquals("Pricing - Exitget", driver.getTitle(), "Check Create a New One link - We are not on the Price page" );
    }
    private void openLoginPage(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickHeaderLoginButton();
    }
}
