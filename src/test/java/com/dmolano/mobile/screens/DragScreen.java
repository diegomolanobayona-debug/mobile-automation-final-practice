package com.dmolano.mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * Page Object for the "Drag" section, a drag-and-drop puzzle game.
 * Only used for Scenario 1's navigation/visibility check.
 */
public class DragScreen extends BaseScreen {

    private final By titleText = By.xpath("//android.widget.TextView[@text=\"Drag and Drop\"]");

    public DragScreen(AndroidDriver driver) {
        super(driver);
    }

    /** @return true if the "Drag and Drop" title is visible on screen */
    public boolean isDisplayed() {
        return isElementDisplayed(titleText, 5);
    }
}