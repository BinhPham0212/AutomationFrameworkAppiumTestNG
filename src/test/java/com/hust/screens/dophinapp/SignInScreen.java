package com.hust.screens.dophinapp;

import com.hust.driver.DriverManager;
import com.hust.helpers.JsonHelper;
import com.hust.helpers.JsonReader;
import com.hust.utils.logs.LogUtils;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static com.hust.keywords.MobileUI.*;

public class SignInScreen {
    /*
     * Locator Sign In Screen
     */
    //--ID
    public static String iconStar = "mobi.mgeek.TunnyBrowser:id/homepage_bookmark_icon";
    public static String iconStar1 = "mobi.mgeek.TunnyBrowser:id/left_sidebar";
    public static String buttonSignIn = "mobi.mgeek.TunnyBrowser:id/name_txt";
    public static String titleSignInScreen = "mobi.mgeek.TunnyBrowser:id/title";
    public static String buttonLogInDolphin = "mobi.mgeek.TunnyBrowser:id/btn_login_dolphin";
    public static String buttonLogInGoogle = "mobi.mgeek.TunnyBrowser:id/btn_login_google";
    public static String buttonCancel = "mobi.mgeek.TunnyBrowser:id/button2";
    public static String inputEmail = "mobi.mgeek.TunnyBrowser:id/ds_email";
    public static String inputPassword = "mobi.mgeek.TunnyBrowser:id/ds_password";
    public static String buttonRegister = "mobi.mgeek.TunnyBrowser:id/ds_sign_up";
    public static String buttonSignUp = "mobi.mgeek.TunnyBrowser:id/ds_sign_up";
    public static String labelSignUpScreen = "mobi.mgeek.TunnyBrowser:id/icon_title";

    public static String labelSelectAnAccount = "mobi.mgeek.TunnyBrowser:id/alertTitle";

    //--XPATH
    public static String labelSignUpScreen1 = "//android.widget.TextView[@text=\"Sign in with your Dolphin account\"]";
    public static String warningEmailNull = "//android.widget.AutoCompleteTextView[@text=\"null\"]";
    public static String selectFirstAccount = "//android.widget.ListView[@resource-id=\"mobi.mgeek.TunnyBrowser:id/select_dialog_listview\"]/android.widget.TextView[1]";
    public static String selectSecondAccount = "//android.widget.ListView[@resource-id=\"mobi.mgeek.TunnyBrowser:id/select_dialog_listview\"]/android.widget.TextView[2]";
    public static String chooseFirstAccount = "//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.CheckedTextView[@resource-id=\"android:id/text1\"][1]";
    public static String chooseSecondAccount = "//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.CheckedTextView[@resource-id=\"android:id/text1\"][2]";
    public static String error403page = "//android.widget.TextView[@text=\"403 Forbidden\"]";
    public static String buttonOK = "//android.widget.Button[@text=\"OK\"]";
    public static String pageSignInGoogle = "//android.webkit.WebView";
    public static String buttonNext = "//android.widget.Button[@text=\"Next\"]";
    public static String verifyLogoGoogle = "//android.widget.TextView[@text=\"Google\"]";
    public static String buttonIAgree = "//android.widget.Button[@text=\"I agree\"]";

    //--UIAutomator
    public static String selectLoginWithAnotherAccount = "new UiSelector().text(\"Login with another account\")";
    public static String chooseAddAccount = "new UiSelector().text(\"Add account\")";
    public static String inputEmailGoogle = "new UiSelector().className(\"android.widget.EditText\")";
    public static String labelNOTRobot = "new UiSelector().text(\"Confirm you’re not a robot\")";
    public static String inputPasswordGoogle = "new UiSelector().className(\"android.widget.EditText\")";
    public static String buttonAgree = "new UiSelector().text(\"Tôi đồng ý\")";





    /*
     *Method Sign In Screen
     */

    @Step("Click icon star")
    public SignInScreen clickIconStar() {
        if (isElementDisplayed("id", iconStar1) ==true){
//            waitForElementVisible("id", iconStar1);
            clickElement("id", iconStar1);
        }else {
            waitForElementVisible("id", iconStar);
            clickElement("id", iconStar);
        }
        return this;
    }

    @Step("Click button Sign In")
    public SignInScreen clickButtonSignIn() {
        clickElement("id", buttonSignIn);
        waitForElementVisible("id", titleSignInScreen);
        return this;
    }

    @Step("Click button Login Dolphin")
    public SignInScreen clickButtonLoginDolphin() {
        clickElement("id", buttonLogInDolphin);
        return this;
    }

    @Step("Click button Login Google")
    public SignInScreen clickButtonLoginGoogle() {
        clickElement("id", buttonLogInGoogle);
        return this;
    }

    @Step("Click button Sign Up")
    public SignInScreen clickButtonSignUp() {
        clickElement("id", buttonSignUp);
        sleep(1);
        return this;
    }

    /*
     *  Login With Google
     */
    @Step("Click button Cancel")
    public SignInScreen clickButtonCancel() {
        clickElement("id", buttonCancel);
        waitForElementVisible("id", titleSignInScreen);
        return this;
    }

    @Step("Select Already Exists Account")
    public SignInScreen clickSelectFirstAccountGoogle() {
        try {
            clickElement("xpath", selectFirstAccount);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + selectFirstAccount.toString());
            e.printStackTrace();
        }
        sleep(4);
        //Click two times
        clickElement("xpath", chooseFirstAccount);
        clickElement("xpath", buttonOK);

        clickElement("xpath", selectFirstAccount);
        sleep(3);

        return this;
    }

    /*
     *  Login With Other Google Account
     */
    @Step("Select Login With Another Account")
    public SignInScreen clickLoginAnotherAccountGoogle() {
        clickElement("uiautomator", selectLoginWithAnotherAccount);
        clickElement("uiautomator", chooseAddAccount);
        clickElement("xpath", buttonOK);
        sleep(8);
        waitForElementVisible("xpath", pageSignInGoogle, 20);

        return this;
    }

    String filePath = "src/test/resources/testdata/signInData.json";

    @Step("Input email and password Google Account")
    public SignInScreen inputEmailPasswordGoogleAccount(String keyEmail, String keyPassword) {
        setText("uiautomator", inputEmailGoogle, JsonHelper.readValueJsonObject(filePath, keyEmail));
        pressKEY(AndroidKey.ENTER);
        clickElement("xpath", buttonNext);
        sleep(5);
        setText("uiautomator", inputPasswordGoogle, JsonHelper.readValueJsonObject(filePath, keyPassword));
        pressKEY(AndroidKey.ENTER);
//        clickElement("xpath",buttonNext);

        //Wait For action choose image To verify not Robot
        if ((isElementDisplayed("xpath", verifyLogoGoogle)) == true) {
            try {
                clickElement("xpath", buttonIAgree);
                sleep(35);
                clickElement("xpath",selectSecondAccount);
                clickElement("xpath",chooseSecondAccount);
                clickElement("xpath", buttonOK);
                //waitForElementVisible("xpath", verifyLogoGoogle, 10);
            } catch (Exception e) {
                LogUtils.error("Element not displayed." + e);
            }
        }
        else {
            waitForElementVisible("uiautomator", labelNOTRobot, 20);
            //Verify displayed Google
            waitForElementVisible("xpath", verifyLogoGoogle, 10);
            //swipe down
            swipeAction("DOWN");
            clickElement("uiautomator", buttonAgree);
            sleep(3);
            clickElement("uiautomator", buttonAgree);
        }
//        waitForElementVisible("xpath",verifyLogoGoogle,10);

        return this;
    }


}
