package com.hust.testcases;

import com.hust.common.BaseTest;
import com.hust.screens.dophinapp.HomeScreen;
import com.hust.screens.dophinapp.SearchInPageScreen;
import com.hust.screens.dophinapp.SearchScreen;
import org.testng.annotations.Test;

import static com.hust.keywords.MobileUI.sleep;

public class SearchInPageTest extends BaseTest {

    @Test(priority = 0)
    public void TC_searchInPageWithExistKeyword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchInPageScreen().
                searchInPage("ha noi").
                verifySearchText("ha noi");
    }

    @Test(priority = 1)
    public void TC_searchInPageWithNOTExistKeyword() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchInPageScreen().
                searchInPage("qwert").
                verifySearchText("qwert");
    }

    @Test(priority = 2)
    public void TC_searchInPageWithButtonDown() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchInPageScreen().
                searchInPage("ha noi").
                verifySearchText("ha noi").
                clickDownArrow();
    }

    @Test(priority = 3)
    public void TC_searchInPageWithButtonDownUp() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchInPageScreen().
                searchInPage("ha noi").
                verifySearchText("ha noi").
                clickDownArrow().
                clickUpArrow();
    }

    @Test(priority = 4)
    public void TC_searchInPageAndClickButtonCancel() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchInPageScreen().
                searchInPage("ha noi").
                verifySearchText("ha noi").
                clickIconCancel().
                waitForElementNotVisible();
    }

}
