package com.dmolano.mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MenuScreen extends BaseScreen {

    private final By titleText = By.xpath("//android.widget.TextView[@text=\"Menu\"]");

    public MenuScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isElementDisplayed(titleText, 5);
    }
}