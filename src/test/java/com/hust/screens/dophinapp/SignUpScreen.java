package com.hust.screens.dophinapp;

import com.hust.driver.DriverManager;
import com.hust.helpers.JsonHelper;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.net.MalformedURLException;

import static com.hust.keywords.MobileUI.*;
import static com.hust.keywords.MobileUI.waitForElementVisible;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

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
    public static String inputEmailInvalid = "//android.widget.AutoCompleteTextView[contains(@text,'quynhttd')]";
    public static String textboxEmailNull = "//android.widget.TextView[contains(@text,'Email')]";
    public static String textboxPasswordNull = "//android.widget.TextView[contains(@text,'Password')]";
    public static String passwordFilled = "//android.widget.EditText[contains(@text,'••••')]";
//    public static String messageEmail = "//label[contains(@class,'error')]";


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

    @Step("Verify message Email Null")
    public SignUpScreen verifyEmailNull() {
        String validationMessage = waitForElementVisible("id",inputEmail).getAttribute("validationMessage");
        System.out.println(validationMessage);
        sleep(1);
        String usernameErrorLabel = waitForElementVisible("xpath",textboxEmailNull).getText();
        verifyEquals(usernameErrorLabel,"Email","Email has been filled out");
        sleep(2);
        return this;
    }

    @Step("Verify message Password Null")
    public SignUpScreen verifyPasswordNull() {
        sleep(5);
        String passwordErrorlable = waitForElementVisible("xpath",textboxPasswordNull).getText();
        verifyEquals(passwordErrorlable,"Password","Email has been filled out");
        sleep(2);
        return this;
    }

    @Step("Verify message invalid Email")
    public SignUpScreen verifyInvalidEmailMessage() {
        sleep(5);
        String invalidEmail = waitForElementVisible("xpath",inputEmailInvalid).getText();
        verifyEquals(invalidEmail,JsonHelper.readValueJsonObject(filePath,"invalidemail"),"Email is filled out with VALID");
        sleep(2);
        return this;
    }



}
