package com.ksergie;

import org.testng.annotations.Test;

public class MainPageTests extends BaseTest{

    private MainPage mainPage;


    @Test(testName = "testFooterMenuItems1", dataProvider = "getTitles1", dataProviderClass = TestData.class)
    void testFooterMenuItems1(String t[]){
        mainPage = new MainPage(eventDriver);
        mainPage.checkFooterLinks1(t);
    }

    @Test(testName = "testFooterMenuItems2", dataProvider = "getTitles2", dataProviderClass = TestData.class)
    void testFooterMenuItems2(String t[]){
        mainPage = new MainPage(eventDriver);
        mainPage.checkFooterLinks2(t);
    }

    @Test(testName = "testMainMenuItems", dataProvider = "getTitles3", dataProviderClass = TestData.class)
    void testMainMenuItems(String t[]){
        mainPage = new MainPage(eventDriver);
        mainPage.checkMainMenuLinks(t);
    }

    @Test(testName = "testSignUpButtons")
    void testSignUpButtons(){
        mainPage = new MainPage(eventDriver);
        mainPage.clickSignUpButtons();
    }

    @Test(testName = "testExitGetLogo")
    void testExitGetLogo(){
        mainPage = new MainPage(eventDriver);
        mainPage.clickLogoExitGet();
    }

    @Test(testName = "testBlogButtonAndLink")
    void testBlogButtonAndLink(){
        mainPage = new MainPage(eventDriver);
        mainPage.checkButtonAndLink();
    }

    @Test(testName = "testChatClient")
    void testChatClient(){
        mainPage = new MainPage(eventDriver);
        mainPage.checkChatClient();
    }

    @Test(testName = "testScreenshotMsg")
    void testScreenshotMsg(){
        mainPage = new MainPage(eventDriver);
        mainPage.checkScreenShotImg();
    }

    @Test(testName = "testPreviewPopupButtons")
    void testPreviewPopupButtons(){
        mainPage = new MainPage(eventDriver);
        mainPage.checkPreviewPopup();
    }

    @Test(testName = "testBlogLinks")
    void testBlogLinks(){
        mainPage = new MainPage(eventDriver);
        mainPage.checkBlogLink();
    }

    @Test(testName = "testLoginButton")
    void testLoginButton(){
        mainPage = new MainPage(eventDriver);
        mainPage.clickHeaderLoginButton();
    }
}
