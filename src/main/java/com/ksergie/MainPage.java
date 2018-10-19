package com.ksergie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.trim;

//TODO: preview button

public class MainPage {

    private EventFiringWebDriver driver;
    private static final Logger log = LogManager.getLogger(MainPage.class.getName());

    public MainPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    private By linksMainMenu = By.xpath("//div[@id='menuLinks']/a[@class='menuLink']");
    private By titleExitgetBlog = By.xpath("//div[@class='blogMenuRecent']/h3");
    private By buttonHeaderLogin = By.xpath("(//div[@id='screenMenu']/a)[1]");
    private By buttonSignUp = By.xpath("(//a[text()='SIGN UP'])[2]");
    private By buttonGetStarted1 = By.xpath("(//a[text()='GET STARTED'])[1]");
    private By buttonGetStarted2 = By.xpath("(//a[text()='GET STARTED'])[2]");
    private By logoExitGet = By.id("logotext");
    private By logoExitGetSmall = By.xpath("//div[@class='logotext']");
    private By linksFooter2 = By.xpath("//div[@id='footerLinks2']/a");
    private By linksFooter1 = By.xpath("//div[@id='footerLinks1']/a");
    private By buttonContinueReading = By.xpath("//a[text()='Continue Reading']");
    private By linkViewAllBlogs = By.xpath("//a[text()='VIEW ALL']");
    private By buttonChatClient = By.id("chatClient");
    private By headerChatClient = By.xpath("//div[@id='chatClientHeader']/div");
    private By buttonMinimizeChatClient = By.id("chatClientMinimize");
    private By newmsgChatClient = By.id("chatClientNewMessages");
    private By textareaChatClient = By.id("chatClientText");
    private By chatClientContent = By.id("chatClientContent");
    private By chatClientClose = By.id("chatClientClose");
    private By buttonPreviewPromo = By.id("previewPromo");
    private By promoPopupWindow = By.id("exitget_controls");
    private By iconClosePopup = By.id("exitget_close");
    private By buttonEmailMarketing = By.id("previewLeads");
    private By buttonExitIntent = By.id("previewExit");
    private By linkBlogsLinks = By.xpath("//div[@id='linkList']/a[@class='link']");
    private By linkContent = By.xpath("//div[@class='linkContent']");
    private By linkD3Center = By.xpath("//div[@id='footerInfo']/div/a");
    private By linkPricingPage = By.xpath("//div[@id='menuLinks']/a[4]");

    protected static String url = "https://exitget.com";

    // List of Href's
    List<String> hrefs = new ArrayList<>();

    public void checkD3CenterLink(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(linkD3Center)).click();
        wait.until(ExpectedConditions.titleIs("District 3 Innovation Center – Be at the Center of Innovation"));
        Assert.assertEquals("District 3 Innovation Center – Be at the Center of Innovation", driver.getTitle(), "We are not on the D3Center page");
    }

    public void checkBlogLink(){
        List<String> content = new ArrayList<>();
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(linkBlogsLinks));
        List<WebElement>blogLinks = driver.findElements(linkBlogsLinks);
        for (WebElement link: blogLinks) {
            hrefs.add(link.getAttribute("href"));
        }
        List<WebElement>contentTitles = driver.findElements(linkContent);
        for (WebElement title: contentTitles) {
            content.add(title.getText());
        }
        for (int i = 0; i < 7; i++){
            driver.get(hrefs.get(i));
            Assert.assertEquals(trim(content.get(i)) + " - Exitget Blog", driver.getTitle(), "We are not on the " + content.get(i) + " page");
            driver.navigate().back();
        }
        hrefs.clear();
        content.clear();
    }

    public void checkPreviewPopup(){
        driver.get(url);
        checkPromotionalOffersButton();
        checkEmailMarketingButton();
        checkExitIntent();
    }

    private void checkExitIntent(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonExitIntent)).click();
        pause(500);
        moveCoursor(800, 80);
        pause(300);
        moveCoursor(800, 10);
        pause(300);
        wait.until(ExpectedConditions.visibilityOfElementLocated(promoPopupWindow));
        wait.until(ExpectedConditions.elementToBeClickable(iconClosePopup)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(promoPopupWindow));
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(promoPopupWindow)),
                "The Exit Intent Popup window is not closed after moving mouse out the page");
        moveCoursor(250, 250);
    }

    protected void moveCoursor(int x, int y){
        try {
            Robot robot = new Robot();
            robot.mouseMove(x, y);
            robot.delay(1000);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void checkEmailMarketingButton(){
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(buttonEmailMarketing)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(promoPopupWindow));
        actions.click().build().perform();
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(promoPopupWindow)),
                "The Email Marketing Popup window is not closed after clicking Preview Popup button");
    }
    private void checkPromotionalOffersButton(){
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.elementToBeClickable(buttonPreviewPromo)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(promoPopupWindow));
        wait.until(ExpectedConditions.elementToBeClickable(iconClosePopup)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(promoPopupWindow));
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(promoPopupWindow)),
                "The Promotions Popup window is not closed after clicking Close icon");
    }

    public void checkScreenShotImg(){
        String[] img = {
                "campaign-design",
                "create-design",
                "websites-hits-overview",
                "overview-screenshot"
        };
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        for(int i = 0; i < 4; i++){
            String xpath = "//a[contains(@class,'button toggle_down') and @item='" + i + "']";
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
            String xpathImg = "//div[@id='screenshotImages']//img[" + (i + 1) +"]";
            Assert.assertEquals("image on", wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathImg))).getAttribute("class"),
                    "The Class attribute does not equal Image On");
            Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathImg))).getAttribute("src").contains(img[i]),
                    "The wrong image is displayed");
        }

    }

    public void checkChatClient(){
        driver.get(url);
        openChatClient();
        minimizeChat();
        sendMsgChat();
        closeMsgChat();
    }


    private void closeMsgChat(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(chatClientClose)).click();
        // switch to Alert window and click the OK button
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    private void sendMsgChat(){
        String testMsg = "Hello Andrew! That is a test message to verify the working of the Chat Client. Sincerely your, Automation test";
        openChatClient();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(textareaChatClient)).sendKeys(testMsg + Keys.ENTER);
        pause(1200);
        Assert.assertTrue(driver.findElement(chatClientContent).getText().contains(testMsg), "Message is not sent correctly");
    }

    private void minimizeChat(){
        openChatClient();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonMinimizeChatClient)).click();
        String newmsgChat = wait.until(ExpectedConditions.visibilityOfElementLocated(newmsgChatClient)).getText();
        Assert.assertEquals("0", newmsgChat, "Minimize Chat Client is incorrect!");
    }
    private void openChatClient(){
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonChatClient)).click();
        String headerChat = wait.until(ExpectedConditions.visibilityOfElementLocated(headerChatClient)).getText();
        Assert.assertEquals("Online Support", headerChat, "The Chat Client does not open!");
    }

    public void clickHeaderLoginButton(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get(url);
        wait.until(ExpectedConditions.titleIs("Exitget. A Popup Platform for Everyone"));
        log.info("We are opening the " + url);
        executor.executeScript("arguments[0].click();", driver.findElement(buttonHeaderLogin));
        wait.until(ExpectedConditions.titleIs("Login - Exitget"));
        Assert.assertEquals("Login - Exitget", driver.getTitle(), "We are not on Login Page");
    }

    // Check the "Continue Reading" button and "View All" link
    public void checkButtonAndLink(){
        driver.get(url);
        goToBlog(buttonContinueReading);
        goToBlog(linkViewAllBlogs);
    }

    private void goToBlog(By xpath){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        executor.executeScript("arguments[0].click();", driver.findElement(xpath));
        String titleExitBlog = wait.until(ExpectedConditions.visibilityOfElementLocated(titleExitgetBlog)).getText();
        Assert.assertEquals("Exitget Blog", titleExitBlog, "We are not on the ExitGet Blog page");
        driver.navigate().back();
    }

    public void checkMainMenuLinks(String titles[]){
        driver.get(url);
        goAroundMenu(linksMainMenu, titles);
    }

    public void checkFooterLinks1(String titles[]) {
        driver.get(url);
        goAroundFooterLinks(linksFooter1, titles);
    }

    public void checkFooterLinks2(String titles[]){
        driver.get(url);
        goAroundMenu(linksFooter2, titles);
    }

    private void goAroundFooterLinks(By xpathFooter, String titles[]){

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpathFooter));
        // List of Menu links
        List<WebElement>menuLinks = driver.findElements(xpathFooter);
        // Fill out href's array
        for (WebElement link: menuLinks) {
            hrefs.add(link.getAttribute("href"));
        }
        // go around the pages
        int i = 0;
        for (String href : hrefs) {
            driver.get(href);
            wait.until(ExpectedConditions.titleIs(titles[i]));
            String errorMessage = "We are not on the " + titles[i] + " page";
            Assert.assertEquals(titles[i], driver.getTitle(), errorMessage);
            i++;
            driver.get(url);
        }
        hrefs.clear();
        menuLinks.clear();

    }

    public void clickSignUpButtons(){
        driver.get(url);
        log.info("We are opening the " + url);
//        pause(1000);
        clickSignUpButton(buttonGetStarted1);
        log.info("Click the Get Started 1 button");
//        pause(1000);
        clickSignUpButton(buttonGetStarted2);
        log.info("Click the Get Started 2 button");
        clickSignUpButton(buttonSignUp);
        log.info("Click the Get Started 3 button");

    }

    private void clickSignUpButton(By xpath){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver, 5);
        executor.executeScript("arguments[0].click();", driver.findElement(xpath));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
        Assert.assertEquals("Signup for Exitget", driver.getTitle(), "We are not on SignUp page");
        driver.navigate().back();
    }

    public void clickLogoExitGet(){
        driver.get(url);
        log.info("We are opening the " + url);
        log.info("Click the big logo ");
        clickLogo(logoExitGet);
        log.info("Click the small logo ");
        clickLogo(logoExitGetSmall);
    }

    private void clickLogo(By xpath){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(xpath));
        Assert.assertEquals("Exitget. A Popup Platform for Everyone", driver.getTitle(), "Main Page. Test ExitGet Logo is failure");
    }

    protected void clickPricingPageLink(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver, 5);
        executor.executeScript("arguments[0].click();", driver.findElement(linkPricingPage));
        wait.until(ExpectedConditions.titleIs("Pricing - Exitget"));
        Assert.assertEquals("Pricing - Exitget", driver.getTitle(), "We are not on Pricing page");
    }

    private void goAroundMenu(By xpathLinks, String[] titles){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpathLinks));
        // List of Menu links
        List<WebElement>menuLinks = driver.findElements(xpathLinks);
        // Fill out href's array
        for (WebElement link: menuLinks) {
            hrefs.add(link.getAttribute("href"));
        }
        // go around the pages
        int i = 0;
        for (String href : hrefs) {
            driver.get(href);
            if (i != 1){
                wait.until(ExpectedConditions.titleIs(titles[i]));
                String errorMessage = "We are not on the " + titles[i] + " page";
                Assert.assertEquals(titles[i], driver.getTitle(), errorMessage);
            } else {
                String titleExitBlog = wait.until(ExpectedConditions.visibilityOfElementLocated(titleExitgetBlog)).getText();
                Assert.assertEquals(titles[i], titleExitBlog, "We are not on the ExitGet Blog page");
            }
            i++;
            driver.navigate().back();
        }
        hrefs.clear();
        menuLinks.clear();
    }
    protected void pause(int msec){
    try {
        Thread.sleep(msec);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

}
