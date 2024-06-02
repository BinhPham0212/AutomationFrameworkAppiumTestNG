package com.hust.testcases;

import com.hust.common.BaseTest;
import com.hust.keywords.MobileUI;
import com.hust.screens.dophinapp.BackUpAndRestoreScreen;
import com.hust.screens.dophinapp.HomeScreen;
import com.hust.screens.dophinapp.SearchInPageScreen;
import com.hust.screens.dophinapp.SignInScreen;
import org.testng.annotations.Test;

import static com.hust.keywords.MobileUI.waitForElementVisible;

public class BackUpAndRestoreTest extends BaseTest {

    @Test(priority = 0)
    public void TC_backupSuccessWithoutPassword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new BackUpAndRestoreScreen().
                clickOnIconDolphin().
                openBackUpAndRestore().
                setNamefileAndLink().
                clickOnbuttonBackUp();
        MobileUI.verifyToastMessage("Backup successfully!","Backup FAILED!");
    }

    @Test(priority = 1)
    public void TC_restoreSuccessWithoutPassword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new BackUpAndRestoreScreen().
                clickOnIconDolphin().
                openBackUpAndRestore().
                clickOnTabRestore().
                selectBackUpFile().
                clickOnbuttonRestore();
        MobileUI.verifyToastMessage("Restore successfully!","Restore FAILED!");
        //Verify Setting Screen displays
        waitForElementVisible("id",HomeScreen.logoDolphinApp);
        //Verify Most Visited, History
        new BackUpAndRestoreScreen().
                verifyMostVisitedandHistory();
    }

    @Test(priority = 2)
    public void TC_backupSuccessWithPassword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new BackUpAndRestoreScreen().
                clickOnIconDolphin().
                openBackUpAndRestore().
                setNamefileAndLink().
                setPasswordBackUp().
                clickOnbuttonBackUp();
        MobileUI.verifyToastMessage("Your latest backup is created. Don't forget your password.","Backup FAILED!");
    }

    @Test(priority = 3)
    public void TC_restoreSuccessWithRightPassword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new BackUpAndRestoreScreen().
                clickOnIconDolphin().
                openBackUpAndRestore().
                clickOnTabRestore().
                selectBackUpFile().
                setRightPasswordRestore().
                clickOnbuttonRestore();
//        MobileUI.verifyToastMessage("Restore successfully!","Restore FAILED!");
        //Verify Setting Screen displays
        waitForElementVisible("id",HomeScreen.logoDolphinApp);
        //Verify Most Visited, History
        new BackUpAndRestoreScreen().
                verifyMostVisitedandHistory();
    }

    @Test(priority = 4)
    public void TC_restoreFailWithWrongPassword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new BackUpAndRestoreScreen().
                clickOnIconDolphin().
                openBackUpAndRestore().
                clickOnTabRestore().
                selectBackUpFile().
                setWrongPasswordRestore().
                clickOnbuttonRestore();
//        MobileUI.verifyToastMessage("Restore successfully!","Restore FAILED!");
        //Verify Setting Screen displays
        waitForElementVisible("id",HomeScreen.logoDolphinApp);
        //Verify Most Visited, History
        new BackUpAndRestoreScreen().
                verifyMostVisitedandHistory();
    }


}
