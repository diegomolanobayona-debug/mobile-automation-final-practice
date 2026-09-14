package com.dmolano.mobile.tests;

import com.dmolano.mobile.screens.HomeScreen;
import com.dmolano.mobile.screens.LoginScreen;
import com.dmolano.mobile.utils.RandomDataGenerator;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin() {
        HomeScreen homeScreen = new HomeScreen(driver);
        homeScreen.tapLoginTab();

        LoginScreen loginScreen = new LoginScreen(driver);

        String email = RandomDataGenerator.randomEmail();
        String password = RandomDataGenerator.password();

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