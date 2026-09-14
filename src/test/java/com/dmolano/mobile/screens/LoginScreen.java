package com.dmolano.mobile.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginScreen extends BaseScreen {

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

    public void goToSignUpTab() {
        click(signUpTabToggle, "Sign Up tab toggle");
    }

    public void goToLoginTab() {
        click(loginTabToggle, "Login tab toggle");
    }

    public void fillSignUpForm(String email, String password) {
        sendKeys(emailInput, email, "Sign Up email field");
        sendKeys(passwordInput, password, "Sign Up password field");
        sendKeys(repeatPasswordInput, password, "Sign Up repeat password field");
        click(signUpSubmitButton, "Sign Up submit button");
    }

    public void fillLoginForm(String email, String password) {
        sendKeys(emailInput, email, "Login email field");
        sendKeys(passwordInput, password, "Login password field");
        click(loginSubmitButton, "Login submit button");
    }

    public String getAlertTitle() {
        return getText(alertTitle, "Alert title");
    }

    public String getAlertMessage() {
        return getText(alertMessage, "Alert message");
    }

    public void confirmAlert() {
        click(alertOkButton, "Alert OK button");
    }
}