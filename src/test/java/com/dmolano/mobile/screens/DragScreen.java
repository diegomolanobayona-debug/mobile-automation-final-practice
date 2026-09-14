package com.dmolano.mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class DragScreen extends BaseScreen {

    private final By titleText = By.xpath("//android.widget.TextView[@text=\"Drag and Drop\"]");

    public DragScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isElementDisplayed(titleText, 5);
    }
}