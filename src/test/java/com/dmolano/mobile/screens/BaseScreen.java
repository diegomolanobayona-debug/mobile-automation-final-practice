package com.dmolano.mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Base class for all Page Objects (screens) in the app.
 * Provides shared interaction and wait utilities so that individual screen
 * classes never talk to the driver directly and never store WebElements as
 * static fields (locators are resolved fresh on every interaction).
 */
public abstract class BaseScreen {

    protected static final Logger logger = LoggerFactory.getLogger(BaseScreen.class);
    protected final AndroidDriver driver;

    protected BaseScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * Resolves a locator into a live WebElement at the moment it's needed,
     * instead of caching it. Avoids stale element references and Page
     * Factory's lazy-proxy overhead.
     *
     * @param locator the locator strategy to resolve
     * @return the matching WebElement
     */
    protected WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Taps the element matched by the given locator, logging the action.
     *
     * @param locator           locator of the element to click
     * @param actionDescription human-readable description for logging
     */
    protected void click(By locator, String actionDescription) {
        logger.info("Clicking on: {}", actionDescription);
        getWebElement(locator).click();
    }

    /**
     * Clears and types text into the element matched by the given locator.
     *
     * @param locator           locator of the input field
     * @param text              text to type
     * @param actionDescription human-readable description for logging
     */
    protected void sendKeys(By locator, String text, String actionDescription) {
        logger.info("Typing into '{}'", actionDescription);
        WebElement element = getWebElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Reads the visible text of the element matched by the given locator.
     *
     * @param locator           locator of the element
     * @param actionDescription human-readable description for logging
     * @return the element's text
     */
    protected String getText(By locator, String actionDescription) {
        logger.info("Reading text from: {}", actionDescription);
        return getWebElement(locator).getText();
    }

    /**
     * Waits explicitly (no fixed sleeps) for an element to become visible.
     * Temporarily disables the implicit wait to fail fast, and always
     * restores it afterward. Never throws: any failure (timeout, no such
     * element) resolves to {@code false}.
     *
     * @param locator        locator of the element to wait for
     * @param timeoutSeconds max time to wait, in seconds
     * @return true if the element became visible within the timeout, false otherwise
     */
    protected boolean isElementDisplayed(By locator, int timeoutSeconds) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ZERO);
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            logger.warn("Element not displayed within {}s: {}", timeoutSeconds, locator);
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
    }

    /**
     * Checks whether the element matched by the given locator is enabled
     * (interactable), as opposed to merely present or visible. Used to
     * verify element *properties*, not just their presence on screen.
     *
     * @param locator locator of the element
     * @return true if the element is enabled, false if not found or disabled
     */
    protected boolean isElementEnabled(By locator) {
        try {
            return getWebElement(locator).isEnabled();
        } catch (Exception e) {
            logger.warn("Element not found to check enabled state: {}", locator);
            return false;
        }
    }
}