package com.hust.testcases;

import com.hust.common.BaseTest;
import com.hust.driver.DriverManager;
import com.hust.helpers.JsonHelper;
import com.hust.screens.dophinapp.HomeScreen;
import com.hust.screens.dophinapp.SignInScreen;
import com.hust.screens.dophinapp.SignUpScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.hust.keywords.MobileUI.sleep;

public class SignUpTest extends BaseTest {

    @Test(priority = 0)
    public void TC_signUpSuccessfullWithDolphinAccount() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SignInScreen().
                clickIconStar().
                clickButtonSignIn().
                clickButtonLoginDolphin().
                clickButtonSignUp();
        new SignUpScreen().
                verifySignUpScreen().
                inputEmailPassword("email","password").
                clickButtonRegister();

    }

    @Test(priority = 1)
    public void TC_signUpNOTSuccessfullWithNullEmail() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SignInScreen().
                clickIconStar().
                clickButtonSignIn().
                clickButtonLoginDolphin().
                clickButtonSignUp();
        new SignUpScreen().
                verifySignUpScreen().
                inputEmailPassword("emailnull","password").
                clickButtonRegister().
                verifyEmailNull();

    }

    @Test(priority = 2)
    public void TC_signUpNOTSuccessfullWithNullPassword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SignInScreen().
                clickIconStar().
                clickButtonSignIn().
                clickButtonLoginDolphin().
                clickButtonSignUp();
        new SignUpScreen().
                verifySignUpScreen().
                inputEmailPassword("email", "passwordnull").
                clickButtonRegister().
                verifyPasswordNull();

    }



    @Test(priority = 3)
    public void TC_signUpNOTSuccessfullWithInvalidEmail() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SignInScreen().
                clickIconStar().
                clickButtonSignIn().
                clickButtonLoginDolphin().
                clickButtonSignUp();
        new SignUpScreen().
                verifySignUpScreen().
                inputEmailPassword("invalidemail","password").
                clickButtonRegister().
                verifyInvalidEmailMessage();

    }
}
