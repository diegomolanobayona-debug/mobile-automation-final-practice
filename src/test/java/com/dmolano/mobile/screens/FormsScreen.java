package com.dmolano.mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * Page Object for the "Forms" section, reachable from the bottom bar.
 * Displays a set of interactive form components (input, switch, dropdown,
 * buttons) used to verify the screen's elements and properties.
 */
public class FormsScreen extends BaseScreen {

    private final By titleText = By.xpath("//android.widget.TextView[@text=\"Form components\"]");
    private final By inputField = By.xpath("//android.widget.EditText");

    public FormsScreen(AndroidDriver driver) {
        super(driver);
    }

    /**
     * @return true if the "Form components" title is visible on screen
     */
    public boolean isDisplayed() {
        return isElementDisplayed(titleText, 5);
    }

    /**
     * Verifies a property (not just visibility) of an element on this
     * screen: whether the free-text input field is enabled/interactable.
     *
     * @return true if the input field is enabled
     */
    public boolean isInputFieldEnabled() {
        return isElementEnabled(inputField);
    }
}