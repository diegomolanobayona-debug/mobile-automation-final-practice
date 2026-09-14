package com.dmolano.mobile.tests;

import com.dmolano.mobile.screens.*;
import org.testng.annotations.Test;

/**
 * Scenario 1: verifies the bottom navigation bar. Preconditions: the app
 * starts on Home. Navigates to every section and asserts each screen's
 * distinctive content is visible (and, for Forms, that a key element is
 * also enabled — a property check beyond mere visibility).
 */
public class BottomBarNavigationTest extends BaseTest {

    @Test
    public void navigateAllBottomBarSections() {
        HomeScreen homeScreen = new HomeScreen(driver);

        softAssert.assertTrue(homeScreen.isHomeTabDisplayed(), "Home tab should be visible on start");

        homeScreen.tapWebTab();
        WebScreen webScreen = new WebScreen(driver);
        softAssert.assertTrue(webScreen.isDisplayed(), "Web screen should display its content");

        homeScreen.tapLoginTab();
        LoginScreen loginScreen = new LoginScreen(driver);
        softAssert.assertTrue(loginScreen.isDisplayed(), "Login screen should display its content");

        homeScreen.tapFormsTab();
        FormsScreen formsScreen = new FormsScreen(driver);
        softAssert.assertTrue(formsScreen.isDisplayed(), "Forms screen should display its content");
        softAssert.assertTrue(formsScreen.isInputFieldEnabled(), "Forms input field should be enabled");

        homeScreen.tapSwipeTab();
        SwipeScreen swipeScreen = new SwipeScreen(driver);
        softAssert.assertTrue(swipeScreen.isDisplayed(), "Swipe screen should display its content");

        homeScreen.tapDragTab();
        DragScreen dragScreen = new DragScreen(driver);
        softAssert.assertTrue(dragScreen.isDisplayed(), "Drag screen should display its content");

        homeScreen.tapMenuTab();
        MenuScreen menuScreen = new MenuScreen(driver);
        softAssert.assertTrue(menuScreen.isDisplayed(), "Menu screen should display its content");
    }
}