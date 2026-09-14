package com.dmolano.mobile.tests;

import com.dmolano.mobile.screens.HomeScreen;
import com.dmolano.mobile.screens.SwipeScreen;
import org.testng.annotations.Test;

/**
 * Scenario 4: verifies the card carousel and hidden-message swipe.
 * Precondition: navigates to the Swipe section. Swipes horizontally through
 * all cards until the last one ("COMPATIBLE") is fully visible, then swipes
 * vertically until the hidden "You found me!!!" message appears.
 */
public class SwipeTest extends BaseTest {

    @Test
    public void swipeCardsAndFindHiddenMessage() {
        HomeScreen homeScreen = new HomeScreen(driver);
        homeScreen.tapSwipeTab();

        SwipeScreen swipeScreen = new SwipeScreen(driver);
        softAssert.assertTrue(swipeScreen.isDisplayed(), "Swipe screen should be displayed");

        softAssert.assertFalse(swipeScreen.isLastCardDisplayed(),
                "Last card should not be visible at the start");

        softAssert.assertTrue(swipeScreen.swipeUntilLastCardVisible(),
                "Should be able to swipe through all cards and reach the last one");

        softAssert.assertTrue(swipeScreen.swipeUpUntilFoundMe(),
                "Hidden message should appear after vertical swipes");
        softAssert.assertEquals(swipeScreen.getFoundMeText(), "You found me!!!",
                "Hidden message text should match");
    }
}