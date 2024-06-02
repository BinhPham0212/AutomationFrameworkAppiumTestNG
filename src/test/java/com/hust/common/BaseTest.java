package com.hust.common;

import com.hust.driver.DriverManager;
import com.hust.helpers.SystemsHelper;
import com.hust.reports.TestListener;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

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

//        service = AppiumDriverLocalService.buildDefaultService();
////        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\votha\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).withTimeout(Duration.ofSeconds(120))
//        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\binhp\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).withTimeout(Duration.ofSeconds(120))
//                .withIPAddress("127.0.0.1").usingPort(4723).build();
//        service.start();
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

//        option.setApp("D://BinhAT//Appium Automation//src//test//resources//app//DolphinBrowser.apk");
//        option.setApp("D:\\BinhAT\\Appium Automation\\src\\test\\resources\\app\\DolphinBrowser.apk");
//        mobi.mgeek.TunnyBrowser/mobi.mgeek.TunnyBrowser.BrowserActivity

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
