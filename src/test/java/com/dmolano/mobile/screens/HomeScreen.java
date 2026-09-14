package com.dmolano.mobile.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HomeScreen extends BaseScreen {

    private final By homeTab = AppiumBy.accessibilityId("Home");
    private final By webTab = AppiumBy.accessibilityId("Webview");
    private final By loginTab = AppiumBy.accessibilityId("Login");
    private final By formsTab = AppiumBy.accessibilityId("Forms");
    private final By swipeTab = AppiumBy.accessibilityId("Swipe");
    private final By dragTab = AppiumBy.accessibilityId("Drag");
    private final By menuTab = AppiumBy.accessibilityId("Menu");

    public HomeScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isHomeTabDisplayed() {
        return isElementDisplayed(homeTab, 5);
    }

    public boolean isWebTabDisplayed() {
        return isElementDisplayed(webTab, 5);
    }

    public boolean isLoginTabDisplayed() {
        return isElementDisplayed(loginTab, 5);
    }

    public boolean isFormsTabDisplayed() {
        return isElementDisplayed(formsTab, 5);
    }

    public boolean isSwipeTabDisplayed() {
        return isElementDisplayed(swipeTab, 5);
    }

    public boolean isDragTabDisplayed() {
        return isElementDisplayed(dragTab, 5);
    }

    public boolean isMenuTabDisplayed() {
        return isElementDisplayed(menuTab, 5);
    }

    public void tapWebTab() {
        click(webTab, "Web tab");
    }

    public void tapLoginTab() {
        click(loginTab, "Login tab");
    }

    public void tapFormsTab() {
        click(formsTab, "Forms tab");
    }

    public void tapSwipeTab() {
        click(swipeTab, "Swipe tab");
    }

    public void tapDragTab() {
        click(dragTab, "Drag tab");
    }

    public void tapMenuTab() {
        click(menuTab, "Menu tab");
    }
}