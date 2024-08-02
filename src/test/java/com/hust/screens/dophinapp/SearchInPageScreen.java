package com.hust.screens.dophinapp;

import com.hust.driver.DriverManager;
import com.hust.screens.CommonDolphin;
import com.hust.utils.logs.LogUtils;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.sql.Driver;

import static com.hust.keywords.MobileUI.*;

public class SearchInPageScreen extends CommonDolphin {
    /*
     * Locator Search In Page Screen
     */
    //--ID
    public static String searchBar = "mobi.mgeek.TunnyBrowser:id/title_bg";
    public static String inputTextBoxSearchInPage = "mobi.mgeek.TunnyBrowser:id/edit";
    public static String inputTextBoxSearchInPageControl = "mobi.mgeek.TunnyBrowser:id/find_edit_controls";
    public static String iconUP = "mobi.mgeek.TunnyBrowser:id/previous";
    public static String iconDOWN = "mobi.mgeek.TunnyBrowser:id/next";
    public static String iconCancel = "mobi.mgeek.TunnyBrowser:id/done";

    //--XPATH
    public static String iconVnexpressApp = "//android.widget.TextView[@text=\"Vnexpress\"]";
    public static String iconDolphinMenuBottom = "//android.widget.FrameLayout[@resource-id=\"mobi.mgeek.TunnyBrowser:id/main_menubar_holder\"]//android.widget.FrameLayout[3]";
    public static String totalMatches = "//android.widget.TextView[contains(@text,\"matche\")]";



    /*
     * Method Search In Page Screen
     */

    @Step("Click on icon Dolphin Menu bottom")
    public CommonDolphin clickOnIconDolphin() {
        clickElement("xpath",iconDolphinMenuBottom);
        return this;
    }
    @Step("Search In Page exists text {0}")
    public SearchInPageScreen searchInPage(String searchText) {
        clickElement("xpath", iconVnexpressApp);
        waitForElementVisible("id",searchBar);
        sleep(3);
        clickOnIconDolphin();
        sleep(2);
        //Click Button Menu Search In Page
        clickAtPosition(162,1490);
        //Verify Textbox Search In Page
        waitForElementVisible("id",inputTextBoxSearchInPage);
//        clickElement("id",inputTextBoxSearchInPage);
        sleep(2);
        typeTextFromKeyboard(searchText);
        sleep(3);


        verifySearchText(searchText);
        return this;
    }

    public static void waitForElementNotVisible() {
        waitForElementNOTVisible("id",inputTextBoxSearchInPage);
    }


    @Step("Verify search Text {0}")
    public SearchInPageScreen verifySearchText(String searchText) {
        sleep(3);
        String totalMatche = getWebElement("xpath",totalMatches).getText();
        LogUtils.info("Total matching with "+searchText+": " +totalMatche);

        return this;
    }

    @Step("Click icon down arrow")
    public SearchInPageScreen clickDownArrow() {
        clickElement("id",iconDOWN);
        sleep(2);
        LogUtils.info("Click down arrow");
        return this;
    }

    @Step("Click icon up arrow")
    public SearchInPageScreen clickUpArrow() {
        clickElement("id",iconUP);
        sleep(2);
        LogUtils.info("Click up arrow");
        return this;
    }

    @Step("Click icon cancel")
    public SearchInPageScreen clickIconCancel() {
        clickElement("id",iconCancel);
        sleep(2);
        LogUtils.info("Click icon cancel");
        return this;
    }

}
