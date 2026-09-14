package com.dmolano.mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BaseScreen {

    protected static final Logger logger = LoggerFactory.getLogger(BaseScreen.class);
    protected final AndroidDriver driver;

    protected BaseScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    // Nunca se guarda el WebElement como campo: se busca en el momento exacto de usarlo
    protected WebElement getWebElement(By locator) {
        return driver.findElement(locator);
    }

    protected void click(By locator, String actionDescription) {
        logger.info("Clicking on: {}", actionDescription);
        getWebElement(locator).click();
    }

    protected void sendKeys(By locator, String text, String actionDescription) {
        logger.info("Typing into '{}'", actionDescription);
        WebElement element = getWebElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator, String actionDescription) {
        logger.info("Reading text from: {}", actionDescription);
        return getWebElement(locator).getText();
    }

    // Espera explícita controlada: nunca lanza excepción hacia afuera, devuelve boolean
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
}