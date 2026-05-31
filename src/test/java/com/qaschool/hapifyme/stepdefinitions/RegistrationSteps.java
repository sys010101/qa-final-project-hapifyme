package com.qaschool.hapifyme.stepdefinitions;

import com.qaschool.hapifyme.context.TestContext;
import com.qaschool.hapifyme.model.TestUser;
import com.qaschool.hapifyme.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Step definitions for the HapifyMe registration scenario.
public class RegistrationSteps {
    private final LoginPage loginPage = new LoginPage();

    // Opens the registration form from the login/register page.
    @Given("the user opens the registration form")
    public void theUserOpensTheRegistrationForm() {
        loginPage.openPage();
        loginPage.openRegistrationForm();
    }

    // Registers a unique user by combining Data Table values with generated test data.
    @When("the user registers with the following data:")
    public void theUserRegistersWithTheFollowingData(DataTable dataTable) {
        TestUser user = TestUser.fromDataTable(dataTable.asMap());
        TestContext.setRegisteredUser(user);
        loginPage.register(user);
    }

    // Verifies that the registration success message is displayed.
    @Then("a registration success message is displayed")
    public void aRegistrationSuccessMessageIsDisplayed() {
        loginPage.verifyRegistrationSuccess();
    }
}
