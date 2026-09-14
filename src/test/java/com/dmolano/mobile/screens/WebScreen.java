package com.dmolano.mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * Page Object for the "Web" section, a WebView embedding a small
 * informational site about WebdriverIO. Loading is noticeably slower than
 * the native screens, hence the extended timeout.
 */
public class WebScreen extends BaseScreen {

    private final By titleText = By.xpath(
            "//android.widget.TextView[@text=\"Next-gen browser and mobile automation test framework for Node.js\"]");

    public WebScreen(AndroidDriver driver) {
        super(driver);
    }

    /** @return true if the WebView content has finished loading and is visible */
    public boolean isDisplayed() {
        return isElementDisplayed(titleText, 20);
    }
}