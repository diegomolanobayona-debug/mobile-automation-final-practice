package com.dmolano.mobile.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * Page Object for the bottom navigation bar, present on every screen of the
 * app. Provides taps into each of the 7 sections (Home, Web, Login, Forms,
 * Swipe, Drag, Menu) and visibility checks for each tab icon.
 */
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

    /** @return true if the Home tab icon is visible in the bottom bar */
    public boolean isHomeTabDisplayed() {
        return isElementDisplayed(homeTab, 5);
    }

    /** @return true if the Web tab icon is visible in the bottom bar */
    public boolean isWebTabDisplayed() {
        return isElementDisplayed(webTab, 5);
    }

    /** @return true if the Login tab icon is visible in the bottom bar */
    public boolean isLoginTabDisplayed() {
        return isElementDisplayed(loginTab, 5);
    }

    /** @return true if the Forms tab icon is visible in the bottom bar */
    public boolean isFormsTabDisplayed() {
        return isElementDisplayed(formsTab, 5);
    }

    /** @return true if the Swipe tab icon is visible in the bottom bar */
    public boolean isSwipeTabDisplayed() {
        return isElementDisplayed(swipeTab, 5);
    }

    /** @return true if the Drag tab icon is visible in the bottom bar */
    public boolean isDragTabDisplayed() {
        return isElementDisplayed(dragTab, 5);
    }

    /** @return true if the Menu tab icon is visible in the bottom bar */
    public boolean isMenuTabDisplayed() {
        return isElementDisplayed(menuTab, 5);
    }

    /** Taps the Web icon in the bottom bar. */
    public void tapWebTab() {
        click(webTab, "Web tab");
    }

    /** Taps the Login icon in the bottom bar. */
    public void tapLoginTab() {
        click(loginTab, "Login tab");
    }

    /** Taps the Forms icon in the bottom bar. */
    public void tapFormsTab() {
        click(formsTab, "Forms tab");
    }

    /** Taps the Swipe icon in the bottom bar. */
    public void tapSwipeTab() {
        click(swipeTab, "Swipe tab");
    }

    /** Taps the Drag icon in the bottom bar. */
    public void tapDragTab() {
        click(dragTab, "Drag tab");
    }

    /** Taps the Menu icon in the bottom bar. */
    public void tapMenuTab() {
        click(menuTab, "Menu tab");
    }
}