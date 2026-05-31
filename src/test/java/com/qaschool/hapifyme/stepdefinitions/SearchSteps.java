package com.qaschool.hapifyme.stepdefinitions;

import com.qaschool.hapifyme.context.TestContext;
import com.qaschool.hapifyme.pages.SearchPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Step definitions for searching users and opening their profiles.
public class SearchSteps {
    private final SearchPage searchPage = new SearchPage();

    // Searches for the user registered during the current scenario.
    @When("the user searches for the registered user")
    public void theUserSearchesForTheRegisteredUser() {
        searchPage.searchFor(TestContext.getRegisteredUser());
    }

    // Verifies that the registered user appears in the search results.
    @Then("the search results include the registered user")
    public void theSearchResultsIncludeTheRegisteredUser() {
        searchPage.verifyResultIncludes(TestContext.getRegisteredUser());
    }

    // Opens the registered user's profile from the search result list.
    @When("the user opens the registered user's profile from the search results")
    public void theUserOpensTheRegisteredUsersProfileFromTheSearchResults() {
        searchPage.openProfile(TestContext.getRegisteredUser());
    }

    // Verifies that the profile page is visible.
    @Then("the registered user's profile page is displayed")
    public void theRegisteredUsersProfilePageIsDisplayed() {
        searchPage.verifyProfilePageIsDisplayed();
    }
}
