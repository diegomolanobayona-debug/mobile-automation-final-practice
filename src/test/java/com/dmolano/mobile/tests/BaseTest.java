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

public abstract class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected AndroidDriver driver;
    protected SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = createDriver();
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
        softAssert.assertAll();
        if (driver != null) {
            logger.info("Quitting driver session");
            driver.quit();
        }
    }

    private AndroidDriver createDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setApp("C:/Users/d.molano/Downloads/android.wdio.native.app.v2.2.0.apk"); // ajustá tu ruta real
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