package com.dmolano.mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * Page Object for the "Menu" section, which opens a side drawer with
 * navigation shortcuts and app settings. Only used for Scenario 1's
 * navigation/visibility check.
 */
public class MenuScreen extends BaseScreen {

    private final By titleText = By.xpath("//android.widget.TextView[@text=\"Menu\"]");

    public MenuScreen(AndroidDriver driver) {
        super(driver);
    }

    /** @return true if the "Menu" title is visible on screen */
    public boolean isDisplayed() {
        return isElementDisplayed(titleText, 5);
    }
}