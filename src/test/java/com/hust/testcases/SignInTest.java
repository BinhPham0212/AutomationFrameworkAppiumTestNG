package com.hust.testcases;

import com.hust.common.BaseTest;
import com.hust.screens.dophinapp.HomeScreen;
import com.hust.screens.dophinapp.SignInScreen;
import com.hust.screens.dophinapp.SignUpScreen;
import org.testng.annotations.Test;

import static com.hust.keywords.MobileUI.*;

public class SignInTest extends BaseTest {

    @Test(priority = 0)
    public void TC_signInWithGoogleAccountAndClickButtonCancel() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SignInScreen().
                clickIconStar().
                clickButtonSignIn().
                clickButtonLoginGoogle().
                clickButtonCancel();
    }

    @Test(priority = 1)
    public void TC_signInWithGoogleAccountAlreadyExists() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SignInScreen().
                clickIconStar().
                clickButtonSignIn().
                clickButtonLoginGoogle().
                clickSelectFirstAccountGoogle();
        waitForElementVisible("xpath",SignInScreen.error403page);
        //Error 403 Page displayed
    }

    @Test(priority = 2)
    public void TC_signInWithAnotherGoogleAccount() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SignInScreen().
                clickIconStar().
                clickButtonSignIn().
                clickButtonLoginGoogle().
                clickLoginAnotherAccountGoogle().
                inputEmailPasswordGoogleAccount("inputEmailGoogle","inputPasswordGoogle");
        waitForElementVisible("xpath",SignInScreen.error403page);
        //Error 403 Page displayed

    }




}
