package com.ksergie;

import org.testng.annotations.DataProvider;

public class TestData {
    // Data for MainPage.checkFooterLinks() test
    @DataProvider(name = "getTitles1")
    public static Object[][] getTitles1() {
        return new Object[][] {
                new Object[] {
                        "Login - Exitget",
                        "Signup for Exitget",
                        "Terms of Service - Exitget",
                        "Privacy Policy - Exitget",
                        "Exitget.com (@exitgetcom) | Twitter",
                        "Exitget - Home | Facebook"
                }
        };
    }

    // Data for MainPage.checkFooterLinks() test
    @DataProvider(name = "getTitles2")
    public static Object[][] getTitles2() {
        return new Object[][] {
                new Object[] {
                        "About Us - Exitget",
                        "Exitget Blog",
                        "Pricing - Exitget",
                        "Exitget Help Center",
                        "Contact - Exitget"
                }
        };
    }

    // Data for MainPage.checkMainMenuLinks() test
    @DataProvider(name = "getTitles3")
    public static Object[][] getTitles3() {
        return new Object[][] {
                new Object[] {
                        "About Us - Exitget",
                        "Exitget Blog",
                        "Exitget Help Center",
                        "Pricing - Exitget"
                }
        };
    }

    // Data for LoginPage.testLoginWithCorrectData() test
    @DataProvider(name = "correctLogin")
    public static Object[][] correctLogin() {
        return new Object[][] {
            {"exitgetest@gmail.com", "Ckfdfnhele_1"}
        };
    }

    // Data for PricingPage.testMonthlySelectPlanButton() test
    @DataProvider(name = "monthlyPlan")
    public static Object[][] monthlyPlan() {
        return new Object[][] {
                {"1", "pro/monthly"},
                {"2", "business/monthly"},
                {"3", "enterprise/monthly"}
        };
    }

    // Data for PricingPage.testYearlySelectPlanButton() test
    @DataProvider(name = "yearlyPlan")
    public static Object[][] yearlyPlan() {
        return new Object[][] {
                {"1", "pro/yearly"},
                {"2", "business/yearly"},
                {"3", "enterprise/yearly"}
        };
    }
}
