package com.qaschool.hapifyme.stepdefinitions;

import com.qaschool.hapifyme.context.TestContext;
import com.qaschool.hapifyme.model.TestUser;
import com.qaschool.hapifyme.pages.FeedPage;
import com.qaschool.hapifyme.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Step definitions for login scenarios and reusable authenticated-user setup.
public class LoginSteps {
    private final LoginPage loginPage = new LoginPage();
    private final FeedPage feedPage = new FeedPage();

    // Creates a unique user through the registration UI.
    @Given("a unique registered user exists")
    public void aUniqueRegisteredUserExists() {
        TestUser user = TestUser.uniqueDefaultUser();
        TestContext.setRegisteredUser(user);
        loginPage.openPage();
        loginPage.openRegistrationForm();
        loginPage.register(user);
        loginPage.verifyRegistrationSuccess();
    }

    // Creates a unique user and logs in with that user's credentials.
    @Given("the registered user is logged in")
    public void theRegisteredUserIsLoggedIn() {
        aUniqueRegisteredUserExists();
        theUserLogsInWithTheRegisteredCredentials();
        feedPage.verifyLoaded();
    }

    // Logs in with the credentials stored for the current scenario.
    @When("the user logs in with the registered credentials")
    public void theUserLogsInWithTheRegisteredCredentials() {
        TestUser user = TestContext.getRegisteredUser();
        loginPage.openLoginForm();
        loginPage.login(user.getEmail(), user.getPassword());
    }

    // Attempts login with invalid credentials from the Scenario Outline examples.
    @When("the user attempts to log in with email {string} and password {string}")
    public void theUserAttemptsToLogInWithEmailAndPassword(String email, String password) {
        TestUser user = TestContext.getRegisteredUser();
        String resolvedEmail = "registered-user".equals(email) ? user.getEmail() : email;
        loginPage.openLoginForm();
        loginPage.login(resolvedEmail, password);
    }

    // Verifies that login succeeded by checking the feed page.
    @Then("the feed page is displayed")
    public void theFeedPageIsDisplayed() {
        feedPage.verifyLoaded();
    }

    // Verifies that the expected login error is displayed.
    @Then("the login error message {string} is displayed")
    public void theLoginErrorMessageIsDisplayed(String expectedMessage) {
        loginPage.verifyLoginError(expectedMessage);
    }
}
