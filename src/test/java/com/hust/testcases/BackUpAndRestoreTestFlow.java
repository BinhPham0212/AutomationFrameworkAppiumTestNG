package com.hust.testcases;

import com.hust.common.BaseTest;
import com.hust.keywords.MobileUI;
import com.hust.screens.dophinapp.BackUpAndRestoreScreen;
import com.hust.screens.dophinapp.HomeScreen;
import com.hust.utils.logs.LogUtils;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import static com.hust.keywords.MobileUI.*;

public class BackUpAndRestoreTestFlow extends BaseTest {

    @Test(priority = 0)
    public void TC_backupAndRestoreSuccessWithoutPassword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new BackUpAndRestoreScreen().
                accessWebsite().
                clickOnIconDolphin().
                openBackUpAndRestore().
                setNamefileAndLink().
                clickOnbuttonBackUp();
        MobileUI.verifyToastMessage("Backup successfully!", "Backup FAILED!");
        //Restore the backup
        clickElement("uiautomator", BackUpAndRestoreScreen.tabBackupAndRestore);
        new BackUpAndRestoreScreen().
                clickOnTabRestore().
                selectBackUpFile().
                clickOnbuttonRestore();
        sleep(1);
//        MobileUI.verifyToastMessage("Restore successfully!", "Restore FAILED!");
        sleep(3);
        //Verify Setting Screen displays
        new BackUpAndRestoreScreen().
                handleVerifyOtherScreen();
    }

    @Test(priority = 1)
    public void TC_backupAndRestoreSuccessWithRightPassword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new BackUpAndRestoreScreen().
                accessWebsite().
                clickOnIconDolphin().
                openBackUpAndRestore().
                setNamefileAndLink().
                setPasswordBackUp().
                clickOnbuttonBackUp();
        MobileUI.verifyToastMessage("Your latest backup is created. Don't forget your password.", "Backup FAILED!");
        //Restore the backup
        clickElement("uiautomator", BackUpAndRestoreScreen.tabBackupAndRestore);
        new BackUpAndRestoreScreen().
                clickOnTabRestore().
                selectBackUpFile().
                setRightPasswordRestore().
                clickOnbuttonRestore();
        sleep(1);
        MobileUI.verifyToastMessage("Restore successfully!", "Restore FAILED!");
        sleep(3);
        //Verify Most Visited and History
        new BackUpAndRestoreScreen().
                handleVerifyOtherScreen();
    }

    @Test(priority = 2)
    public void TC_backupAndRestoreSuccessWithWrongPassword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new BackUpAndRestoreScreen().
                accessWebsite().
                clickOnIconDolphin().
                openBackUpAndRestore().
                setNamefileAndLink().
                setPasswordBackUp().
                clickOnbuttonBackUp();
        MobileUI.verifyToastMessage("Your latest backup is created. Don't forget your password.", "Backup FAILED!");
        //Restore the backup
        clickElement("uiautomator", BackUpAndRestoreScreen.tabBackupAndRestore);
        new BackUpAndRestoreScreen().
                clickOnTabRestore().
                selectBackUpFile().
                setWrongPasswordRestore().
                clickOnbuttonRestore();
    }
}
