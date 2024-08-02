package com.hust.keywords;

import com.google.common.collect.ImmutableMap;
import com.hust.driver.DriverManager;
import com.hust.reports.AllureManager;
import com.hust.utils.logs.LogUtils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.Driver;
import java.time.Duration;
import java.util.*;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class MobileUI {
    //Mobile UI Object
//    private AndroidDriver driver;
//    private WebDriverWait wait;

    //    //Method Mobile UI
//    public MobileUI(AndroidDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver,30);
//    }
    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * appium-wait-plugin
     * Installation: appium plugin install --source=npm appium-wait-plugin
     * Activation: appium --use-plugins=element-wait
     *
     * @param driver        the driver want to set wait
     * @param timeoutMillis the millisecond
     */
//    public static AppiumDriver waitElement(AppiumDriver driver, int timeoutMillis) {
//        driver.executeScript("plugin: setWaitPluginProperties", ImmutableMap.of("timeout", timeoutMillis, "intervalBetweenAttempts", 500));
//        return driver;
//    }
    @Step("Get element with locatorType {0} and locatorValue {1}")
    public static By getLocator(String locatorType, String locatorValue) {
        LogUtils.info("Getting locator of type '{" + locatorType + "}' with value '{" + locatorValue + "}'");
        switch (locatorType.toLowerCase()) {
            case "id":
                return AppiumBy.id(locatorValue);
            case "accessibilityid":
                return AppiumBy.accessibilityId(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);
            case "classname":
            case "class":
                return By.className(locatorValue);
//            case "text":
//                return By.(locatorValue);
            case "uiautomator":
                return AppiumBy.androidUIAutomator(locatorValue);
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
        }
    }

    @Step("Wait for element VISIBLE with locatorType {0} and locatorValue {1} in time {2} ")
    public static WebElement waitForElementVisible(String locatorType, String locatorValue, int waitTime) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), ofSeconds(waitTime), ofMillis(500));
        LogUtils.info("Waiting for element to be visible. Locator type: '{" + locatorType + "}', value: '{" + locatorValue + "}', timeout: {" + waitTime + "} seconds");
        By locator = getLocator(locatorType, locatorValue);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Step("Wait for element VISIBLE with locatorType {0} and locatorValue {1} in time {2} ")
    public static Boolean waitForElementNOTVisible(String locatorType, String locatorValue) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(6), Duration.ofMillis(500));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(getLocator(locatorType, locatorValue)));
            return true;
        } catch (Exception e) {
            LogUtils.error("FAILED. The element is visible " + getLocator(locatorType, locatorValue));
            return false;
        }
    }

    @Step("Wait for element VISIBLE with locatorType {0} and locatorValue {1} in time 5s} ")
    public static WebElement waitForElementVisible(String locatorType, String locatorValue) {
        return waitForElementVisible(locatorType, locatorValue, 5);
    }

    @Step("Click element with locatorType {0} and locatorValue {1}")
    public static void clickElement(String locatorType, String locatorValue) {
        LogUtils.info("Clicking on element. Locator type '{" + locatorType + "}' with value '{" + locatorValue + "}'");
        WebElement element = waitForElementVisible(locatorType, locatorValue);
        element.click();
    }

    @Step("Set text={3} in element with locatorType {0} and locatorValue {1}")
    public static void setText(String locatorType, String locatorValue, String text) {
        LogUtils.info("Entering text into element. type '{" + locatorType + "}' with value '{" + locatorValue + "}' text: '{" + text + "}'");
        WebElement element = waitForElementVisible(locatorType, locatorValue);
        element.clear();
        element.sendKeys(text);
    }

    @Step("Set text={3} in element with locatorType {0} and locatorValue {1}")
    public static void setTextNotClear(String locatorType, String locatorValue, String text) {
        LogUtils.info("Entering text into element. type '{" + locatorType + "}' with value '{" + locatorValue + "}' text: '{" + text + "}'");
        WebElement element = waitForElementVisible(locatorType, locatorValue);
        element.sendKeys(text);
    }

    @Step("Get text from element with locatorType {0} and locatorValue {1}")
    public static String getText(String locatorType, String locatorValue) {
        LogUtils.info("Getting text from element. Locator type: '{" + locatorType + "}', value: '{" + locatorValue + "}'");
        WebElement element = waitForElementVisible(locatorType, locatorValue);
        return element.getText();
    }

    public static boolean isElementDisplayed(String locatorType, String locatorValue) {
        LogUtils.info("Checking if element is displayed. Locator type: '{" + locatorType + "}', value: '{" + locatorValue + "}'");
        try {
            WebElement element = waitForElementVisible(locatorType, locatorValue, 10);
            return true;
        } catch (Exception e) {
            LogUtils.error("Element not displayed. Locator type: '{" + locatorType + "}', value: '{" + locatorValue + "}'" + e);
            return false;
        }
    }

    public static void pressBack() {
        LogUtils.info("Pressing back button");
        DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public static void pressHome() {
        LogUtils.info("Pressing home button");
        DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.HOME));
    }

    @Step("Press key : {0}")
    public static void pressKEY(AndroidKey key) {
        LogUtils.info("Pressing enter button");

        try {
            DriverManager.getDriver().pressKey(new KeyEvent(key));
            System.out.println("Pressed key: " + key);
        } catch (Exception e) {
            System.err.println("Failed to press key: " + key);
            e.printStackTrace();
        }
    }

    @Step("Swipe with Direction : {0}")
    public static void swipeAction(String direction) {
        // [LEFT, RIGHT, UP, DOWN]
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("mobile: swipeGesture", ImmutableMap.of(

                "left", 100, "top", 100, "width", 200, "height", 200,
//                "elementId", ((RemoteWebElement)element).getId(),
                "direction", direction,
                "percent", 0.75
        ));
        LogUtils.info("Swipe " + direction);
        sleep(3);
    }

    public static void longPressAction(String locatorType, String locatorValue) {
        WebElement element = waitForElementVisible(locatorType, locatorValue);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("mobile: longClickGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "duration", 3000));
        sleep(2);
    }

    public static void longPressAndSwipeAction(String locatorType, String locatorValue) {
        WebElement element = waitForElementVisible(locatorType, locatorValue);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("mobile: longClickGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "duration", 3000));

        // Lấy kích thước của phần tử
        int elementWidth = element.getSize().getWidth();
        int elementHeight = element.getSize().getHeight();

        // Tính tọa độ để vuốt lên góc trái 20px
        int startX = element.getLocation().getX() + elementWidth / 2;
        int startY = element.getLocation().getY() + elementHeight / 2;
        int endX = startX - 500; // Vuốt về bên trái 20px
        int endY = startY - 500; // Vuốt lên trên 20px

        // Thực hiện thao tác vuốt lên góc trái 20px
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("mobile: dragGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "endX", 298,
                        "endY", 1385,
                        "duration", 3000  // Thời gian vuốt 1 giây

                ));  // Thời gian vuốt 1 giây

        LogUtils.info("Swipe to left top");
        sleep(2);
    }

    @Step("Long Press and Drag : {0} ---AND--- {1}")
    public static void longPressAndDrag(String locatorType, String  locatorValue, int endX, int endY) {
        WebElement element = getWebElement(locatorType, locatorValue);

        TouchAction touchAction = new TouchAction(DriverManager.getDriver());
        touchAction
                .longPress(ElementOption.element(element))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    public static void dragAction(String locatorType, String locatorValue, int endX, int endY) {
        WebElement element = waitForElementVisible(locatorType, locatorValue);
        // Lấy kích thước của phần tử
        int elementWidth = element.getSize().getWidth();
        int elementHeight = element.getSize().getHeight();

        // Tính tọa độ để vuốt lên góc trái 20px
        int startX = element.getLocation().getX() + elementWidth / 2;
        int startY = element.getLocation().getY() + elementHeight / 2;
//        int endX = startX - 500; // Vuốt về bên trái 20px
//        int endY = startY - 500; // Vuốt lên trên 20px

        // Thực hiện thao tác vuốt lên góc trái 20px
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("mobile: dragGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "endX", endX,
                        "endY", endY,
                        "duration", 3000  // Thời gian vuốt 1 giây

                ));  // Thời gian vuốt 1 giây

        LogUtils.info("Swipe to left top");
        sleep(2);
    }

    public static void swipeToElement(String locatorType, String locatorValue, String direction) {
        // Java
        WebElement element = waitForElementVisible(locatorType, locatorValue);
        try {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("mobile: swipeGesture",
                    ImmutableMap.of(
                            "elementId", ((RemoteWebElement) element).getId(),
                            "direction", direction,
                            "percent", 0.75
                    ));
            LogUtils.info("Swipe to Element" + element);
            sleep(3);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Swipe from to
    public static void swipe(int fromX, int fromY, int toX, int toY) {

        TouchAction action = new TouchAction(DriverManager.getDriver());
        action.press(PointOption.point(fromX, fromY))
                .waitAction(new WaitOptions().withDuration(ofMillis(3000))) //you can change wait durations as per your requirement
                .moveTo(PointOption.point(toX, toY))
                .release()
                .perform();
    }


    /**
     * Verify Element Visible, Equal
     */

    @Step("Verify Equals: {0} ---AND--- {1}")
    public static boolean verifyEquals(Object value1, Object value2) {
        boolean result = value1.equals(value2);
        if (result == true) {
        } else {
            LogUtils.info("Verify Equals: " + value1 + " != " + value2);
//            if (ExtentTestManager.getExtentTest() != null) {
//                ExtentReportManager.fail("Verify Equals: " + value1 + " != " + value2);
//            }
            AllureManager.saveTextLog("Verify Equals: " + value1 + " != " + value2);
            Assert.assertEquals(value1, value2, value1 + " != " + value2);
        }
        return result;
    }

    @Step("Verify Equals: {0} ---AND--- {1}")
    public static boolean verifyEquals(Object value1, Object value2, String message) {
        boolean result = value1.equals(value2);
        if (result == true) {
        } else {
            LogUtils.info("Verify Equals: " + value1 + " != " + value2);
//            if (ExtentTestManager.getExtentTest() != null) {
//                ExtentReportManager.fail("Verify Equals: " + value1 + " != " + value2);
//            }
            AllureManager.saveTextLog("Verify Equals: " + value1 + " != " + value2);
            Assert.assertEquals(value1, value2, message);
        }
        return result;
    }

    @Step("Verify Equals: {0} ---AND--- {1}")
    public static void verifyToastMessage(Object expectedMessage, String message) {

        String toastMessage = DriverManager.getDriver().findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        System.out.println(toastMessage);
        verifyEquals(toastMessage, expectedMessage, message);

    }


    public static WebElement getWebElement(String locatorType, String locatorValue) {
        return DriverManager.getDriver().findElement(getLocator(locatorType, locatorValue));
    }

    @Step("Verify element exists {0} and {1}")
    public static boolean verifyElementExists(String locatorType, String locatorValue) {

        boolean res;
        List<WebElement> elementList = Collections.singletonList(getWebElement(locatorType, locatorValue));
        if (elementList.size() > 0) {
            res = true;
            LogUtils.info("Element existing");
        } else {
            res = false;
            LogUtils.error("Element not exists");

        }
        return res;
    }

    /*Try with Action
     *
     */
    public static void tapAtPosition(int x, int y) {
        new TouchAction<>(DriverManager.getDriver())
                .tap(PointOption.point(x, y))
                .waitAction(waitOptions(ofMillis(250)))
                .release()
                .perform();
    }

    //Press by element
    public static void tapByElement(String locator, String locatorValue) {
        new TouchAction(DriverManager.getDriver())
                .tap(tapOptions().withElement(element(getWebElement(locator, locatorValue))))
                .waitAction(waitOptions(ofMillis(250))).perform();
    }

    public static void clickAtPosition(int x, int y) {
        try {
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Actions actions = new Actions(DriverManager.getDriver());
            actions.tick(finger.createPointerMove(Duration.ofMillis(1), PointerInput.Origin.viewport(), x, y))
                    .tick(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .tick(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                    .perform();
            LogUtils.info("Clicked at position: (" + x + ", " + y + ")");
        } catch (Exception e) {
            LogUtils.error("Failed to click at position: (" + x + ", " + y + ")");
            e.printStackTrace();
        }
    }

    public static void copyToClipboard(String text) {
        DriverManager.getDriver().setClipboardText(text);
        System.out.println("Copied text to clipboard: " + text);
    }

    public static void pasteFromClipboard(String locatorType, String locatorValue) {
        try {
            // Tìm hộp văn bản bằng id và dán văn bản từ clipboard
            WebElement textBox = DriverManager.getDriver().findElement(getLocator(locatorType, locatorValue));
            textBox.click(); // Đảm bảo hộp văn bản được chọn

            // Sử dụng phím tắt hoặc hành động để dán văn bản vào hộp văn bản
            textBox.sendKeys(DriverManager.getDriver().getClipboardText());
            System.out.println("Pasted text from clipboard: " + DriverManager.getDriver().getClipboardText());
        } catch (Exception e) {
            System.out.println("Failed to paste text from clipboard into text box with id: " + getLocator(locatorType, locatorValue));
            e.printStackTrace();
        }
    }

    // Hàm keyboard
    public static void typeTextFromKeyboard(String text) {
        LogUtils.info("Type text " + text);
        for (char c : text.toCharArray()) {
            AndroidKey key = getKey(c);
            LogUtils.info("Type key " + key);
            if (key != null) {
                DriverManager.getDriver().pressKey(new KeyEvent(key));
            }
        }
    }

    public static AndroidKey getKey(char c) {
        switch (c) {
            case 'a':
                return AndroidKey.A;
            case 'b':
                return AndroidKey.B;
            case 'c':
                return AndroidKey.C;
            case 'd':
                return AndroidKey.D;
            case 'e':
                return AndroidKey.E;
            case 'f':
                return AndroidKey.F;
            case 'g':
                return AndroidKey.G;
            case 'h':
                return AndroidKey.H;
            case 'i':
                return AndroidKey.I;
            case 'j':
                return AndroidKey.J;
            case 'k':
                return AndroidKey.K;
            case 'l':
                return AndroidKey.L;
            case 'm':
                return AndroidKey.M;
            case 'n':
                return AndroidKey.N;
            case 'o':
                return AndroidKey.O;
            case 'p':
                return AndroidKey.P;
            case 'q':
                return AndroidKey.Q;
            case 'r':
                return AndroidKey.R;
            case 's':
                return AndroidKey.S;
            case 't':
                return AndroidKey.T;
            case 'u':
                return AndroidKey.U;
            case 'v':
                return AndroidKey.V;
            case 'w':
                return AndroidKey.W;
            case 'x':
                return AndroidKey.X;
            case 'y':
                return AndroidKey.Y;
            case 'z':
                return AndroidKey.Z;
            case '0':
                return AndroidKey.DIGIT_0;
            case '1':
                return AndroidKey.DIGIT_1;
            case '2':
                return AndroidKey.DIGIT_2;
            case '3':
                return AndroidKey.DIGIT_3;
            case '4':
                return AndroidKey.DIGIT_4;
            case '5':
                return AndroidKey.DIGIT_5;
            case '6':
                return AndroidKey.DIGIT_6;
            case '7':
                return AndroidKey.DIGIT_7;
            case '8':
                return AndroidKey.DIGIT_8;
            case '9':
                return AndroidKey.DIGIT_9;
            case ' ':
                return AndroidKey.SPACE;
            default:
                return null;
        }
    }

    /*
    * Draw Letter
     */
    @Step("Draw the letter_{0} on {1}")
    public static void drawLetter(WebElement signaturePad, char letter) {

        int width = signaturePad.getSize().getWidth()/2;
        int height = signaturePad.getSize().getHeight()/2;
        int startX = signaturePad.getLocation().getX() + width/2;
        int startY = signaturePad.getLocation().getY() + height/2;

        Map<Character, List<int[]>> letterPoints = getLetterPoints(startX, startY, width, height);

        if (letterPoints.containsKey(letter)) {
            LogUtils.info("Drawing " + letter);

            List<int[]> points = letterPoints.get(letter);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence sequence = new Sequence(finger, 1);

            sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), points.get(0)[0], points.get(0)[1]));
            sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

            for (int i = 1; i < points.size(); i++) {
                sequence.addAction(finger.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), points.get(i)[0], points.get(i)[1]));
                LogUtils.info("Draw with X " + points.get(i)[0] +" and Y " + points.get(i)[1]);
            }

            sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            DriverManager.getDriver().perform(Collections.singletonList(sequence));
        } else {
            LogUtils.error("Letter " + letter + " not supported");
        }
    }

    public static Map<Character, List<int[]>> getLetterPoints(int startX, int startY, int width, int height) {
        Map<Character, List<int[]>> letterPoints = new HashMap<>();

        // Define points for letter "A"
        letterPoints.put('A', Arrays.asList(
                new int[]{startX + width / 2, startY},          // Top-center
                new int[]{startX, startY + height},             // Bottom-left
                new int[]{startX + width, startY + height},     // Bottom-right
                new int[]{startX + width / 4, startY + height / 2}, // Middle-left
                new int[]{startX + 3 * width / 4, startY + height / 2}  // Middle-right
        ));

        // Define points for letter "B"
        letterPoints.put('B', Arrays.asList(
                new int[]{startX, startY},                   // Top-left start
                new int[]{startX, startY + height},          // Bottom-left
                new int[]{startX + width / 2, startY + height / 4}, // First curve
                new int[]{startX, startY + height / 2},      // Middle
                new int[]{startX + width / 2, startY + 3 * height / 4}, // Second curve
                new int[]{startX, startY + height}           // Bottom-left end
        ));

        // Define points for letter "Y"
        letterPoints.put('Y', Arrays.asList(
                new int[]{startX, startY},                        // Top-left start
                new int[]{startX + width / 2, startY + height / 2}, // Center meeting point
                new int[]{startX + width, startY},                 // Top-right
                new int[]{startX + width / 2, startY + height / 2}, // Revisited center
                new int[]{startX + width / 2, startY + height}      // Bottom center
        ));


// Define points for letter "F"
        letterPoints.put('F', Arrays.asList(
            new int[]{startX + width / 2, startY},
            new int[]{startX + width / 2 - 20, startY + 10},
            new int[]{startX + width / 2 - 40, startY + 20},
            new int[]{startX + width / 2 - 60, startY + 30},
            new int[]{startX + width / 2 - 80, startY + 40},
            new int[]{startX + width / 3 - 10, startY + 50},
            new int[]{startX + width / 3 - 20, startY + 60},
            new int[]{startX + width / 3 - 30, startY + 70},
            new int[]{startX + width / 3 - 40 ,startY + 80},
            new int[]{startX + width / 3 - width / 5, startY + height},
            new int[]{startX + width / 3 - width / 5 - 10, startY + height + 10},
            new int[]{startX + width / 3 - width / 5 - 20, startY + height + 20},
            new int[]{startX + width / 3 - width / 5 - 40, startY + height + 40},
            new int[]{startX + width / 3 - width / 5 - 60, startY + height + 60},
            new int[]{startX + width / 3 - width / 5 - 80, startY + height + 80}
        ));

        // Define points for letter "M"
        letterPoints.put('M', Arrays.asList(
                new int[]{startX, startY + height},                // Bottom-left
                new int[]{startX, startY},                         // Top-left
                new int[]{startX + width / 2, startY + height},    // Middle-bottom
                new int[]{startX + width, startY},                 // Top-right
                new int[]{startX + width, startY + height}         // Bottom-right
        ));

        return letterPoints;
    }

}

