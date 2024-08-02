package com.hust.common;

import com.hust.driver.DriverManager;
import com.hust.helpers.SystemsHelper;
import com.hust.reports.TestListener;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.MalformedURLException;
import java.net.URL;

import static com.hust.keywords.MobileUI.sleep;

@Listeners({TestListener.class})
public class BaseTest {

//    public static AndroidDriver driver;
    public DriverManager driver;
    private AppiumDriverLocalService service;
    private UiAutomator2Options option;



    @BeforeSuite
    public void RunServer() {
        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1").usingPort(4723);

//        Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();

    }

//    @BeforeClass
    @BeforeTest
    public void RunApplication() {
        option = new UiAutomator2Options();
        option.setDeviceName(System.getProperty("deviceName"));
        option.setPlatformName(System.getProperty("platformName"));
        option.setAutomationName(System.getProperty("androidAutomationName"));
        option.setAppPackage(System.getProperty("androidAppPackage"));
        option.autoGrantPermissions();    //Auto permission for location and library
        option.setAppActivity(System.getProperty("androidAppActivity"));
        option.setApp(SystemsHelper.getCurrentDir() + "\\src\\test\\resources\\app\\DolphinBrowser.apk");
    }

    @BeforeMethod
    public void createAndroidDriver() throws MalformedURLException {
        DriverManager.setDriver(new AndroidDriver(new URL("http://127.0.0.1:4723"), option));

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void quitAndroidDriver() {
        DriverManager.quit();
    }

    @AfterSuite
    public void stopServer() {
        service.stop();
    }

}
