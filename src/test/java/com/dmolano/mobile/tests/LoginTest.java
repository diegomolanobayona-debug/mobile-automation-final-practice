package com.dmolano.mobile.tests;

import com.dmolano.mobile.screens.HomeScreen;
import com.dmolano.mobile.screens.LoginScreen;
import com.dmolano.mobile.utils.RandomDataGenerator;
import org.testng.annotations.Test;

/**
 * Scenario 3: verifies a successful Login. Precondition: a previously
 * created user at the Login section. This test is fully independent from
 * {@link SignUpTest} — it reuses the sign-up flow internally to create its
 * own user rather than depending on SignUpTest having run first.
 */
public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin() {
        HomeScreen homeScreen = new HomeScreen(driver);
        homeScreen.tapLoginTab();

        LoginScreen loginScreen = new LoginScreen(driver);

        String email = RandomDataGenerator.randomEmail();
        String password = RandomDataGenerator.password();

        // Reuses the Sign Up flow to satisfy this scenario's precondition
        // ("a previously created user") without depending on SignUpTest.
        loginScreen.goToSignUpTab();
        loginScreen.fillSignUpForm(email, password);
        loginScreen.confirmAlert();

        loginScreen.goToLoginTab();
        loginScreen.fillLoginForm(email, password);

        softAssert.assertEquals(loginScreen.getAlertTitle(), "Success",
                "Alert title should confirm success");
        softAssert.assertEquals(loginScreen.getAlertMessage(), "You are logged in!",
                "Alert message should confirm login");

        loginScreen.confirmAlert();
    }
}