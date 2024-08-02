package com.hust.screens.dophinapp;

import com.hust.driver.DriverManager;
import com.hust.screens.CommonDolphin;
import com.hust.utils.logs.LogUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.hust.keywords.MobileUI.*;

public class SearchGesturesScreen extends CommonDolphin {
    /*
     * Locator Search Gestures Screen
     */
    //--ID
    public static String headerDrawGestureScreen = "mobi.mgeek.TunnyBrowser:id/vg_header_layout";
    public static String messageDrawGesture = "mobi.mgeek.TunnyBrowser:id/title";

    //--XPATH
    public static String iconDolphinMenuBottom = "//android.widget.FrameLayout//android.widget.FrameLayout[3]";
    public static String logoYoutube = "//android.view.View[@content-desc=\"YouTube\"]";
    public static String logoFaceBook = "//android.widget.TextView[@text=\"Facebook from Meta\"]";


    /*
     * Method Search Gestures Screen
     */

    @Step("Drag to Genesture screen")
    public SearchGesturesScreen dragGenestureScreen() {
        longPressAndDrag("xpath",iconDolphinMenuBottom,298,1385);
//        dragAction("xpath",iconDolphinMenuBottom,298,1385);
        isElementDisplayed("id",headerDrawGestureScreen);
        return this;
    }

    @Step("Draw the letter_{0}")
    public SearchGesturesScreen drawLetterInScreen(char nameLetter) {
        String signaturePad = "mobi.mgeek.TunnyBrowser:id/gestures_overlay";
        WebElement signaturePad1 = getWebElement("id",signaturePad);
        drawLetter(signaturePad1, nameLetter);
        sleep(3);
        return this;
    }

    @Step("Verify element is displayed {0}")
    public SearchGesturesScreen accessWebsite(String screenVerify) {
        sleep(1);
        waitForElementVisible("xpath",screenVerify);
        return this;
    }

    @Step("Verify message gesture is displayed {0}")
    public SearchGesturesScreen verifyMessageGesture(String expectedMessage) {
        String messageGesture = getWebElement("id",messageDrawGesture).getText();
        verifyEquals(messageGesture,expectedMessage,"Website is displayed");
        return this;
    }




}
