package com.dmolano.mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Page Object for the "Swipe" section: a horizontal card carousel plus a
 * vertical scroll that reveals a hidden message. Swipes are performed via
 * Appium's native {@code mobile: swipeGesture} command (more reliable than
 * raw W3C pointer actions for this React Native carousel), and every swipe
 * is followed by an explicit wait so the test never advances on a gesture
 * the app itself failed to register (rubber-banding).
 */
public class SwipeScreen extends BaseScreen {

    private static final int SCREEN_WIDTH = 1440;
    private static final int MAX_SWIPE_ATTEMPTS = 10;
    private static final int MAX_VERTICAL_SWIPE_ATTEMPTS = 4;

    private final By titleText = By.xpath("//android.widget.TextView[@text=\"Swipe horizontal\"]");
    private final By foundMeText = By.xpath("//android.widget.TextView[@text=\"You found me!!!\"]");
    private final By lastCardText = By.xpath("//android.widget.TextView[@text=\"COMPATIBLE\"]");

    public SwipeScreen(AndroidDriver driver) {
        super(driver);
    }

    /** @return true if the "Swipe horizontal" title is visible on screen */
    public boolean isDisplayed() {
        return isElementDisplayed(titleText, 5);
    }

    /**
     * Checks whether the last card's distinctive text ("COMPATIBLE") is
     * fully inside the screen bounds, not merely peeking from the edge as
     * the carousel's partial-preview effect would otherwise report via a
     * plain {@code isDisplayed()} check.
     *
     * @return true if the last card is fully visible
     */
    public boolean isLastCardDisplayed() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ZERO);
            return new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(d -> {
                        List<WebElement> found = d.findElements(lastCardText);
                        if (found.isEmpty()) {
                            return false;
                        }
                        Rectangle r = found.get(0).getRect();
                        return r.getX() >= 0 && (r.getX() + r.getWidth()) <= SCREEN_WIDTH;
                    });
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

    /**
     * Swipes left repeatedly until the last card ("COMPATIBLE") is fully
     * visible, or gives up after {@link #MAX_SWIPE_ATTEMPTS} tries. Each
     * attempt re-checks the condition before swiping again, so an
     * occasional rubber-banded gesture is simply retried instead of
     * derailing the rest of the test.
     *
     * @return true if the last card ended up fully visible
     */
    public boolean swipeUntilLastCardVisible() {
        for (int attempt = 1; attempt <= MAX_SWIPE_ATTEMPTS; attempt++) {
            if (isLastCardDisplayed()) {
                logger.info("Last card ('COMPATIBLE') fully visible after {} swipe(s)", attempt - 1);
                return true;
            }
            logger.info("Swipe attempt {}", attempt);
            performSwipeLeft();
        }
        return isLastCardDisplayed();
    }

    private void performSwipeLeft() {
        Map<String, Object> params = new HashMap<>();
        params.put("left", 150);
        params.put("top", 1500);
        params.put("width", 1140);
        params.put("height", 880);
        params.put("direction", "left");
        params.put("percent", 1.0);
        params.put("speed", 4500);
        driver.executeScript("mobile: swipeGesture", params);
    }

    private void swipeUp() {
        Map<String, Object> params = new HashMap<>();
        params.put("left", 150);
        params.put("top", 900);
        params.put("width", 1140);
        params.put("height", 1700);
        params.put("direction", "up");
        params.put("percent", 0.85);
        driver.executeScript("mobile: swipeGesture", params);
    }

    /**
     * Swipes up repeatedly until the hidden "You found me!!!" message
     * becomes visible, or gives up after {@link #MAX_VERTICAL_SWIPE_ATTEMPTS} tries.
     *
     * @return true if the hidden message was found
     */
    public boolean swipeUpUntilFoundMe() {
        for (int attempt = 1; attempt <= MAX_VERTICAL_SWIPE_ATTEMPTS; attempt++) {
            logger.info("Vertical swipe attempt {} looking for hidden message", attempt);
            swipeUp();
            if (isElementDisplayed(foundMeText, 3)) {
                return true;
            }
        }
        return false;
    }

    /** @return the exact text of the hidden message once it's visible */
    public String getFoundMeText() {
        return getText(foundMeText, "Hidden message");
    }
}