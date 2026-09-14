package com.dmolano.mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class WebScreen extends BaseScreen {

    private final By titleText = By.xpath(
            "//android.widget.TextView[@text=\"Next-gen browser and mobile automation test framework for Node.js\"]");

    public WebScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        // WebView tarda más en cargar que las pantallas nativas: timeout extendido
        return isElementDisplayed(titleText, 20);
    }
}