package com.hust.testcases;

import com.hust.common.BaseTest;
import com.hust.keywords.MobileUI;
import com.hust.screens.dophinapp.HomeScreen;
import com.hust.screens.dophinapp.SearchGesturesScreen;
import com.hust.screens.dophinapp.SearchInPageScreen;
import org.testng.annotations.Test;

import static com.hust.keywords.MobileUI.verifyToastMessage;

public class SearchGesturesTest extends BaseTest {

    @Test(priority = 0)
    public void TC_searchByValidLetter_Y() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchGesturesScreen().
                dragGenestureScreen().
                drawLetterInScreen('Y').
                accessWebsite(SearchGesturesScreen.logoYoutube);
    }

    @Test(priority = 1)
    public void TC_searchByValidLetter_F() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchGesturesScreen().
                dragGenestureScreen().
                drawLetterInScreen('F').
                accessWebsite(SearchGesturesScreen.logoFaceBook);
    }

    @Test(priority = 2)
    public void TC_searchByINVALIDLetter_M() {
        new HomeScreen().
                clickOnButtonAgreeAndEnter();
        new SearchGesturesScreen().
                dragGenestureScreen().
                drawLetterInScreen('M').
                verifyMessageGesture("Did you mean any of these?");
    }

}
