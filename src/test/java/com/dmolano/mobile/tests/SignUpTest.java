package com.dmolano.mobile.tests;

import com.dmolano.mobile.screens.HomeScreen;
import com.dmolano.mobile.screens.LoginScreen;
import com.dmolano.mobile.utils.RandomDataGenerator;
import org.testng.annotations.Test;

/**
 * Scenario 2: verifies a successful Sign Up. Precondition: navigates to the
 * Login section first, then switches to the Sign Up form. Uses a
 * dynamically generated email on every run so the test can be executed
 * repeatedly without colliding on an already-registered address.
 */
public class SignUpTest extends BaseTest {

    @Test
    public void successfulSignUp() {
        HomeScreen homeScreen = new HomeScreen(driver);
        homeScreen.tapLoginTab();

        LoginScreen loginScreen = new LoginScreen(driver);

        String email = RandomDataGenerator.randomEmail();
        String password = RandomDataGenerator.password();

        loginScreen.goToSignUpTab();
        loginScreen.fillSignUpForm(email, password);

        softAssert.assertEquals(loginScreen.getAlertTitle(), "Signed Up!",
                "Alert title should confirm sign up");
        softAssert.assertEquals(loginScreen.getAlertMessage(), "You successfully signed up!",
                "Alert message should confirm sign up");

        loginScreen.confirmAlert();
    }
}