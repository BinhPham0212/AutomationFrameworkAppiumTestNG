package com.hust.screens.dophinapp;

import com.hust.screens.CommonDolphin;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.hust.keywords.MobileUI.clickElement;

public class HomeScreen extends CommonDolphin {
    //Object Home Screen

    /*
     * Locator Home Screen
     */
    //--ID

    public static String logoDolphinApp = "mobi.mgeek.TunnyBrowser:id/browser_logo";
    public static String buttonAgreeAndEnter = "mobi.mgeek.TunnyBrowser:id/start";
    public static String buttonAccesLocation = "com.android.permissioncontroller:id/permission_allow_foreground_only_button";
    public static String buttonAllowAccessPhoto = "com.android.permissioncontroller:id/permission_allow_button";
    //--XPATH


    //--UIAutomator
    public static String submenuMostVisited = "new UiSelector().text(\"Most Visited\")";
    public static String submenuHistory = "new UiSelector().text(\"History\")";
    public static String iconDolphinApp = "new UiSelector().text(\"Dolphin\")";



    /*
     * Method Home Screen
     * @return HomeScreen
     */

    @Step("clickOnOpenAppDolphin")
    public HomeScreen clickOnOpenApp() {
        clickElement("uiautomator",iconDolphinApp);
        return this;
    }

    @Step("clickOnButtonAgreeAndEnter")
    public HomeScreen clickOnButtonAgreeAndEnter() {
        clickElement("id",buttonAgreeAndEnter);
        return this;
    }

    @Step("clickOnMostVisited")
    public HomeScreen clickOnMostVisited() {
        clickElement("uiautomator",submenuMostVisited);
        return this;
    }

    @Step("clickOnHistory")
    public HomeScreen clickOnHistory() {
        clickElement("uiautomator",submenuHistory);
        return this;
    }

    /**
     * @return ProductsScreen
     */
//    @Step("clickOnProduct1")
//    public ProductsScreen clickOnProduct1() {
//        driver.findElement(product1).click();
//        return new ProductsScreen(driver);
//    }
}
