package com.hust.reports;

//import com.github.automatedowl.tools.AllureEnvironmentWriter;

import com.hust.driver.DriverManager;
import com.hust.helpers.CaptureHelper;
import com.hust.helpers.JsonHelper;
import com.hust.helpers.PropertiesHelper;
import com.hust.utils.logs.LogUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    static int count_totalTCs;
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;


    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Before every method in the Test Class
        //System.out.println(method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // After every method in the Test Class
        //System.out.println(method.getTestMethod().getMethodName());
    }

    @Override
    public void onStart(ISuite iSuite) {
        System.out.println("========= INSTALLING CONFIGURATION DATA =========");
//        try {
//            FileUtils.deleteDirectory(new File("target/allure-results"));
//            System.out.println("Deleted Directory target/allure-results");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        PropertiesHelper.loadAllFiles();

        System.out.println("========= LAUNCHING APPLICATION=========");
        System.out.println("");
        LogUtils.info("Starting Suite: " + iSuite.getName());
    }

    @Override
    public void onFinish(ISuite iSuite) {
        LogUtils.info("End Suite: " + iSuite.getName());
        LogUtils.info("========================================");
//        MobileUI.stopSoftAssertAll();

        //FileHelpers.copyFile("src/test/resources/config/allure/environment.xml", "target/allure-results/environment.xml");

//        FileHelpers.copyFile("src/test/resources/config/allure/categories.json", "target/allure-results/categories.json");
//        FileHelpers.copyFile("src/test/resources/config/allure/executor.json", "target/allure-results/executor.json");

    }


    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info("Test case: " + getTestName(iTestResult) + " is starting...");
        count_totalTCs = count_totalTCs + 1;

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("Test case: " + getTestName(iTestResult) + " is passed.");
        count_passedTCs = count_passedTCs + 1;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String filePath = "src/test/resources/testdata/backupdata.json";

        LogUtils.error("Test case: " + getTestName(iTestResult) + " is failed.");
        count_failedTCs = count_failedTCs + 1;

        CaptureHelper.captureScreenshot(DriverManager.getDriver(),getTestName(iTestResult));


        //Allure report screenshot file and log
        LogUtils.error("FAILED !! Screenshot for test case: " + getTestName(iTestResult));
        LogUtils.error(iTestResult.getThrowable());

        AllureManager.takeScreenshotStep();
        AllureManager.takeScreenshotToAttachOnAllureReport();
        AllureManager.saveTextLog(iTestResult.getThrowable().toString());

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn("Test case: " + getTestName(iTestResult) + " is skipped.");
        count_skippedTCs = count_skippedTCs + 1;

//        if (SCREENSHOT_SKIPPED_STEPS.equals(YES)) {
//            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
//        }
//
//        ExtentReportManager.logMessage(Status.SKIP, "Test case: " + getTestName(iTestResult) + " is skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LogUtils.error("Test failed but it is in defined success ratio: " + getTestName(iTestResult));
//        ExtentReportManager.logMessage("Test failed but it is in defined success ratio: " + getTestName(iTestResult));
    }

}
