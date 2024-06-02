package com.hust.screens.dophinapp;

import com.hust.helpers.JsonHelper;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.qameta.allure.Step;

import static com.hust.keywords.MobileUI.*;

public class SignUpScreen {
    /*
    * Locator Sign Up Screen
    */
    //--ID
    public static String inputEmail = "mobi.mgeek.TunnyBrowser:id/ds_email";
    public static String inputPassword = "mobi.mgeek.TunnyBrowser:id/ds_password";
    public static String buttonRegister = "mobi.mgeek.TunnyBrowser:id/ds_sign_up";
    public static String buttonSignUp = "mobi.mgeek.TunnyBrowser:id/ds_sign_up";
    public static String labelSignUpScreen = "mobi.mgeek.TunnyBrowser:id/icon_title";

    //--XPATH
    public static String labelSignUpScreen1 = "//android.widget.TextView[@text=\"Sign in with your Dolphin account\"]";
    public static String warningEmailNull = "//android.widget.AutoCompleteTextView[@text=\"null\"]";


    /*
     *Method Sign Up Screen
     */
    String filePath = "src/test/resources/testdata/signInData.json";

    @Step("Click button Sign Up")
    public SignUpScreen clickButtonSignUp() {
        clickElement("id",buttonSignUp);
        sleep(1);
        return this;
    }

    @Step("Verify Label Sign In displayed")
    public SignUpScreen verifySignUpScreen() {
        waitForElementVisible("id",labelSignUpScreen);
        return this;
    }


    @Step("Input email and password")
    public SignUpScreen inputEmailPassword(String keyEmail, String keyPassword) {
        setText("id",inputEmail,JsonHelper.readValueJsonObject(filePath,keyEmail));
        setText("id",inputPassword,JsonHelper.readValueJsonObject(filePath,keyPassword));
        clickElement("id",inputPassword);
        pressKEY(AndroidKey.ENTER);
        return this;
    }

    @Step("Click button register")
    public SignUpScreen clickButtonRegister() {
        clickElement("id",buttonRegister);
        sleep(3);
        return this;
    }

    @Step("Click button register")
    public SignUpScreen verifyWarningEmailMessage() {
        sleep(5);
        verifyToastMessage("Please enter a valid email address","Message NOT match");

        sleep(2);
        return this;
    }

    @Step("Verify message invalid Email")
    public SignUpScreen verifyWarningPasswordMessage() {
        sleep(5);
        verifyToastMessage("Please enter a password","Message NOT match");

        sleep(2);
        return this;
    }

    @Step("Verify message invalid Email")
    public SignUpScreen verifyInvalidEmailMessage() {
        sleep(5);
        verifyToastMessage("Please enter a valid email address","Message NOT match");

        sleep(2);
        return this;
    }

}
