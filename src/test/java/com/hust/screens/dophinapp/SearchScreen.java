package com.hust.screens.dophinapp;

import com.hust.driver.DriverManager;
import com.hust.screens.CommonDolphin;
import com.hust.utils.logs.LogUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import static com.hust.keywords.MobileUI.*;

public class SearchScreen extends CommonDolphin {
    /*
     * Locator Search In Screen
     */
    //--ID
    public static String searchBox = "mobi.mgeek.TunnyBrowser:id/search_frame";
    public static String inputSearch = "mobi.mgeek.TunnyBrowser:id/search_input";
    public static String iconSearch = "mobi.mgeek.TunnyBrowser:id/go_group";
    //--XPATH

    //*[contains(@text,"f")]

    /*
     * Method Search In Screen
     */
    @Step("Search exists URL")
    public SearchScreen searchURL(String URL) {
        String verifyURL = "//android.widget.TextView[@text=\"Đại học Bách khoa Hà Nội\"]";
        clickElement("id", searchBox);
        setText("id", inputSearch,URL);
        sleep(3);
        clickElement("id",iconSearch);
        sleep(2);
        isElementDisplayed("xpath",verifyURL);
        return this;
    }

    @Step("Search NOT exists URL")
    public SearchScreen searchNotExistsURL() {
        clickElement("id", searchBox);
        setText("id", inputSearch,"https://test");
        clickElement("id",iconSearch);
        String webPageNotAvaible = "//android.widget.TextView[@text=\"Webpage not available\"]";
        verifyEquals(getWebElement("xpath",webPageNotAvaible),"Webpage not available", "URL exists");
        return this;
    }

    @Step("Search key word suggestions {1}")
    public SearchScreen searchKeywordSuggest(String keywordSearch) {
        String keySuggestion = "//*[contains(@text,\""+keywordSearch+"\")]";
        clickElement("id", searchBox);
        setText("id", inputSearch,keywordSearch);
        waitForElementVisible("xpath",keySuggestion);
        WebElement element = getWebElement("xpath",keySuggestion);
        String textAttribute = element.getAttribute("text");
        sleep(2);
//        String webPageNotAvaible = "//android.widget.TextView[@text=\"Webpage not available\"]";
        verifyEquals(textAttribute,keywordSearch, "Suggest keyword is NOT displayed");
        sleep(2);
        return this;
    }


}
