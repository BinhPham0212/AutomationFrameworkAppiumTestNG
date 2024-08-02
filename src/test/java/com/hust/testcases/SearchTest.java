package com.hust.testcases;

import com.hust.common.BaseTest;
import com.hust.screens.dophinapp.HomeScreen;
import com.hust.screens.dophinapp.SearchScreen;
import com.hust.screens.dophinapp.SignInScreen;
import com.hust.screens.dophinapp.SignUpScreen;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(priority = 0)
    public void TC_searchExistURL() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchScreen().
                searchURL("https://hust.edu.vn");
    }

    @Test(priority = 1)
    public void TC_searchKeywordInURL() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchScreen().
                searchNotExistsURL();
    }

    @Test(priority = 2)
    public void TC_searchKeywordSuggestion() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchScreen().
                searchKeywordSuggest("f");
    }

    @Test(priority = 3)
    public void TC_searchKeywordSuggestion2() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchScreen().
                searchKeywordSuggest("Appium");
    }




}
