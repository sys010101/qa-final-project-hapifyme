package com.qaschool.hapifyme.pages;

import com.qaschool.hapifyme.model.TestUser;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

// Page Object for the HapifyMe login and registration page.
public class LoginPage {
    private final By signUpLink = By.id("signup");
    private final By signInLink = By.id("signin");
    private final By loginEmailInput = By.name("log_email");
    private final By loginPasswordInput = By.name("log_password");
    private final By loginButton = By.name("login_button");
    private final By firstNameInput = By.name("reg_fname");
    private final By lastNameInput = By.name("reg_lname");
    private final By registerEmailInput = By.name("reg_email");
    private final By confirmEmailInput = By.name("reg_email2");
    private final By registerPasswordInput = By.name("reg_password");
    private final By confirmPasswordInput = By.name("reg_password2");
    private final By registerButton = By.name("register_button");

    // Opens the login and registration page.
    public void openPage() {
        open("/login_register.php");
    }

    // Switches the page to the registration form.
    public void openRegistrationForm() {
        $(signUpLink).shouldBe(visible).click();
    }

    // Switches the page to the login form.
    public void openLoginForm() {
        $(signInLink).click();
    }

    // Fills in and submits the registration form for the provided test user.
    public void register(TestUser user) {
        $(firstNameInput).setValue(user.getFirstName());
        $(lastNameInput).setValue(user.getLastName());
        $(registerEmailInput).setValue(user.getEmail());
        $(confirmEmailInput).setValue(user.getEmail());
        $(registerPasswordInput).setValue(user.getPassword());
        $(confirmPasswordInput).setValue(user.getPassword());
        $(registerButton).click();
    }

    // Fills in and submits the login form.
    public void login(String email, String password) {
        $(loginEmailInput).shouldBe(visible).setValue(email);
        $(loginPasswordInput).setValue(password);
        $(loginButton).click();
    }

    // Verifies that the registration flow completed successfully.
    public void verifyRegistrationSuccess() {
        $("body").shouldHave(text("You're all set"));
    }

    // Verifies that the expected login error message is shown.
    public void verifyLoginError(String expectedMessage) {
        $("body").shouldHave(text(expectedMessage));
    }
}
