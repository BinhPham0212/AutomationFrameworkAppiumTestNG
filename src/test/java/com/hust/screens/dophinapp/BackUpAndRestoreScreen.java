package com.hust.screens.dophinapp;

import com.hust.helpers.JsonHelper;
import com.hust.screens.CommonDolphin;
import com.hust.utils.logs.LogUtils;
import io.qameta.allure.Step;

import static com.hust.keywords.MobileUI.*;

public class BackUpAndRestoreScreen extends CommonDolphin {
    /*
     * Locator BackUp And Restore Screen
     */
    //--ID
    public static String tabBackup = "mobi.mgeek.TunnyBrowser:id/first_tab_title";
    public static String tabRestore = "mobi.mgeek.TunnyBrowser:id/second_tab_title";
    public static String fileLocation = "mobi.mgeek.TunnyBrowser:id/file_location";
    public static String nameFile = "mobi.mgeek.TunnyBrowser:id/file_name";
    public static String buttonConfirm = "mobi.mgeek.TunnyBrowser:id/btn_confirm";
    public static String buttonBackUp = "mobi.mgeek.TunnyBrowser:id/setup_button";
    public static String buttonRestoreFile = "mobi.mgeek.TunnyBrowser:id/setup_button";
    public static String labelRestoreDirectory = "mobi.mgeek.TunnyBrowser:id/download_path";
    public static String setFilelocationToRestore = "mobi.mgeek.TunnyBrowser:id/file_location";
    public static String setPasswordBackup = "mobi.mgeek.TunnyBrowser:id/set_password";
    public static String inputPasswordBackup = "mobi.mgeek.TunnyBrowser:id/input_password";
    public static String inputConfirmPasswordBackup = "mobi.mgeek.TunnyBrowser:id/input_confirm_password";

    //--XPATH
    public static String iconDolphinMenuBottom = "//android.widget.FrameLayout//android.widget.FrameLayout[3]";
    public static String iconVnexpressApp = "//android.widget.TextView[@text=\"Vnexpress\"]";
    public static String firstLocation = "//android.widget.ListView[@resource-id=\"mobi.mgeek.TunnyBrowser:id/list\"]/android.widget.LinearLayout[1]/android.widget.LinearLayout";
    public static String labelTabBackUpAndRestore = "//android.widget.TextView[@text=\"BACKUP AND RESTORE\"]";
    public static String labelTabBackUpDirectory = "//android.widget.TextView[@text=\"BACKUP DIRECTORY\"]";
    public static String buttonRestore = "//android.widget.TextView[@text=\"RESTORE\"]";
    public static String iconHomeMenuBottom = "//android.widget.FrameLayout[@resource-id=\"mobi.mgeek.TunnyBrowser:id/main_menubar_holder\"]//android.widget.FrameLayout[4]";
    public static String labelHUST = "//android.widget.TextView[@text=\"Đại học Bách khoa Hà Nội\"]";
    public static String iconHustHistory = "//android.widget.ImageView[@resource-id=\"mobi.mgeek.TunnyBrowser:id/icon\"]";


    //--UI Automator
    public static String tabBackupAndRestore = "new UiSelector().text(\"Backup and restore\")";
    public static String setfilePath = "new UiSelector().text(\"files\")";
    public static String labelTabSetting = "new UiSelector().text(\"SETTINGS\")";


    /*
     * Method BackUp And Restore Screen
     * BackUP
     */
    String filePath = "src/test/resources/testdata/backupdata.json";

    @Step("Click on icon Home Menu bottom")
    public BackUpAndRestoreScreen clickOnIconHomeBottom() {
        clickElement("xpath", iconHomeMenuBottom);
        return this;
    }

    @Step("Open backup and restore")
    public BackUpAndRestoreScreen openBackUpAndRestore() {
        //Click tab Setting
        clickAtPosition(900, 1730);
        //Verify Setting Screen displays
        waitForElementVisible("uiautomator", labelTabSetting);
        sleep(2);
        swipeAction("up");
        clickElement("uiautomator", tabBackupAndRestore);
        //Verify backup and restore Screen displays
        waitForElementVisible("xpath", labelTabBackUpAndRestore);
        return this;
    }

    @Step("Set name file and link")
    public BackUpAndRestoreScreen setNamefileAndLink() {
        //Set name backup
        setText("id", nameFile, JsonHelper.readValueJsonObject(filePath, "nameBackUp"));
        clickOnLinkLocation();
        return this;
    }

    @Step("Click on link location")
    public BackUpAndRestoreScreen clickOnLinkLocation() {
        clickElement("id", fileLocation);
        //Verify backup directory Screen displays
        waitForElementVisible("xpath", labelTabBackUpDirectory);
        clickElement("uiautomator", setfilePath);
        clickElement("id", buttonConfirm);
        return this;
    }

    @Step("Click on icon Dolphin Menu bottom")
    public BackUpAndRestoreScreen clickOnIconDolphin() {
        clickElement("xpath", iconDolphinMenuBottom);
        sleep(2);
        return this;
    }

    @Step("Click on tab Back Up")
    public BackUpAndRestoreScreen clickOnTabBackUP() {
        clickElement("id", tabBackup);
        return this;
    }


    @Step("Click on button BackUp")
    public BackUpAndRestoreScreen clickOnbuttonBackUp() {
        clickElement("id", buttonBackUp);
        //Verify Setting Screen displays
        waitForElementVisible("uiautomator", labelTabSetting);
        return this;
    }

    /*
     * Method BackUp And Restore Screen
     * Restore
     */
    @Step("Click on tab Restore")
    public BackUpAndRestoreScreen clickOnTabRestore() {
        clickElement("xpath", buttonRestore);
        sleep(2);
        waitForElementVisible("id", setFilelocationToRestore);
        return this;
    }

    @Step("Set path Restore location")
    public BackUpAndRestoreScreen selectBackUpFile() {
        String jsonNameFileRestore = JsonHelper.readValueJsonObject(filePath, "nameBackUp");
        String nameFileRestore = "//android.widget.TextView[@text=\"" + jsonNameFileRestore + ".dbk\"]";
        clickElement("id", setFilelocationToRestore);
        //Verify restore directory Screen displays
        waitForElementVisible("id", labelRestoreDirectory);
        clickElement("uiautomator", setfilePath);
        clickElement("xpath", nameFileRestore);
        return this;
    }

    @Step("Click on button Restore")
    public BackUpAndRestoreScreen clickOnbuttonRestore() {
        clickElement("id", buttonRestoreFile);
        return this;
    }

    @Step("Verify Most Visited, History")
    public void verifyMostVisitedandHistory() {
        String verifyURL = "(//android.widget.TextView[@text=\"Đại học Bách khoa Hà Nội\"])[2]";
        new SignInScreen().clickIconStar();
        //Verify Most Visited
        new HomeScreen().clickOnMostVisited();
        verifyElementExists("xpath", verifyURL);
        new HomeScreen().clickOnMostVisited();
        sleep(1);

        //Verify Histor
        new HomeScreen().clickOnHistory();
        sleep(2);
        verifyElementExists("xpath", iconHustHistory);
        new HomeScreen().clickOnHistory();

    }

    @Step("Create Most Visited, History")
    public BackUpAndRestoreScreen accessWebsite() {
        new SearchScreen().
                searchURL("https://hust.edu.vn");
        clickOnIconHomeBottom();
        //Create Most visited with second access
        new SearchScreen().
                searchURL("https://hust.edu.vn");
        return this;
    }

    @Step("Set Password backup")
    public BackUpAndRestoreScreen setPasswordBackUp() {
        clickElement("id", setPasswordBackup);
        setText("id", inputPasswordBackup, JsonHelper.readValueJsonObject(filePath, "passbackup"));
        setText("id", inputConfirmPasswordBackup, JsonHelper.readValueJsonObject(filePath, "confirmpassbackup"));
        return this;
    }

    @Step("Set Right Password backup")
    public BackUpAndRestoreScreen setRightPasswordRestore() {
        setText("id", inputPasswordBackup, JsonHelper.readValueJsonObject(filePath, "passbackup"));
        return this;
    }

    @Step("Set Wrong Password backup")
    public BackUpAndRestoreScreen setWrongPasswordRestore() {
        setText("id", inputPasswordBackup, JsonHelper.readValueJsonObject(filePath, "wrongpassword"));
        return this;
    }

    public BackUpAndRestoreScreen handleVerifyOtherScreen() {
        if (isElementDisplayed("id", HomeScreen.logoDolphinApp) == true) {
            LogUtils.info("Home screen is displayed");
            verifyMostVisitedandHistory();
        } else if (isElementDisplayed("uiautomator", HomeScreen.iconDolphinApp) == true) {
            new HomeScreen().clickOnOpenApp();
            verifyMostVisitedandHistory();
        } else if (isElementDisplayed("xpath", BackUpAndRestoreScreen.labelHUST) == true) {
            verifyMostVisitedandHistory();
        }
        return this;
    }
}
