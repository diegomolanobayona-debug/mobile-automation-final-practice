package com.dmolano.mobile.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Base class for all test classes. Owns the Appium driver's lifecycle
 * (creation and teardown) so that no test or Page Object ever instantiates
 * the driver directly, and exposes a shared {@link SoftAssert} instance so
 * every failed assertion in a test method is collected and reported together.
 */
public abstract class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected AndroidDriver driver;
    protected SoftAssert softAssert;

    /**
     * Creates a fresh driver session and a fresh SoftAssert before every
     * test method, so tests never share state or depend on execution order.
     */
    @BeforeMethod
    public void setUp() {
        driver = createDriver();
        softAssert = new SoftAssert();
    }

    /**
     * Flushes all collected soft assertions (failing the test if any
     * assertion failed) and closes the driver session, regardless of the
     * test outcome.
     */
    @AfterMethod
    public void tearDown() {
        softAssert.assertAll();
        if (driver != null) {
            logger.info("Quitting driver session");
            driver.quit();
        }
    }

    /**
     * Builds and starts a new Appium/UiAutomator2 session against the
     * WDIO demo app.
     *
     * @return a ready-to-use AndroidDriver instance
     */
    private AndroidDriver createDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setApp("C:/Users/d.molano/Downloads/android.wdio.native.app.v2.2.0.apk");
            options.setAppPackage("com.wdiodemoapp");
            options.setNoReset(false);

            URL url = new URL("http://127.0.0.1:4723/");
            AndroidDriver appiumDriver = new AndroidDriver(url, options);
            appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            return appiumDriver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}