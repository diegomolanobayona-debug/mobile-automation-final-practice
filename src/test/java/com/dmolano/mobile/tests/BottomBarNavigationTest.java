package com.dmolano.mobile.tests;

import com.dmolano.mobile.screens.*;
import org.testng.annotations.Test;

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