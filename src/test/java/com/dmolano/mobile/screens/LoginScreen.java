package com.dmolano.mobile.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * Page Object for the combined Login / Sign Up screen. Both forms live on
 * the same screen behind a toggle, so this single Page Object exposes
 * actions for both flows (Scenarios 2 and 3).
 */
public class LoginScreen extends BaseScreen {

    private final By titleText = By.xpath("//android.widget.TextView[@text=\"Login / Sign up Form\"]");

    private final By signUpTabToggle = AppiumBy.accessibilityId("button-sign-up-container");
    private final By loginTabToggle = AppiumBy.accessibilityId("button-login-container");

    private final By emailInput = AppiumBy.accessibilityId("input-email");
    private final By passwordInput = AppiumBy.accessibilityId("input-password");
    private final By repeatPasswordInput = AppiumBy.accessibilityId("input-repeat-password");

    private final By signUpSubmitButton = AppiumBy.accessibilityId("button-SIGN UP");
    private final By loginSubmitButton = AppiumBy.accessibilityId("button-LOGIN");

    private final By alertTitle = By.id("com.wdiodemoapp:id/alert_title");
    private final By alertMessage = By.id("android:id/message");
    private final By alertOkButton = By.id("android:id/button1");

    public LoginScreen(AndroidDriver driver) {
        super(driver);
    }

    /** @return true if this screen's title ("Login / Sign up Form") is visible */
    public boolean isDisplayed() {
        return isElementDisplayed(titleText, 5);
    }

    /** Switches the screen to the Sign Up form. */
    public void goToSignUpTab() {
        click(signUpTabToggle, "Sign Up tab toggle");
    }

    /** Switches the screen to the Login form. */
    public void goToLoginTab() {
        click(loginTabToggle, "Login tab toggle");
    }

    /**
     * Fills and submits the Sign Up form.
     *
     * @param email    email address to register
     * @param password password to use (also used to fill the "repeat password" field)
     */
    public void fillSignUpForm(String email, String password) {
        sendKeys(emailInput, email, "Sign Up email field");
        sendKeys(passwordInput, password, "Sign Up password field");
        sendKeys(repeatPasswordInput, password, "Sign Up repeat password field");
        click(signUpSubmitButton, "Sign Up submit button");
    }

    /**
     * Fills and submits the Login form.
     *
     * @param email    email address to log in with
     * @param password password to log in with
     */
    public void fillLoginForm(String email, String password) {
        sendKeys(emailInput, email, "Login email field");
        sendKeys(passwordInput, password, "Login password field");
        click(loginSubmitButton, "Login submit button");
    }

    /** @return the bold title of the confirmation alert (e.g. "Success", "Signed Up!") */
    public String getAlertTitle() {
        return getText(alertTitle, "Alert title");
    }

    /** @return the message body of the confirmation alert */
    public String getAlertMessage() {
        return getText(alertMessage, "Alert message");
    }

    /** Dismisses the confirmation alert by tapping its OK button. */
    public void confirmAlert() {
        click(alertOkButton, "Alert OK button");
    }
}