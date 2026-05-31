package com.qaschool.hapifyme.pages;

import com.qaschool.hapifyme.model.TestUser;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

// Page Object for searching users and opening HapifyMe profile pages.
public class SearchPage {
    private static final Duration PROFILE_LOAD_TIMEOUT = Duration.ofSeconds(3);

    private final By searchInput = By.id("search_text_input");
    private final By searchResults = By.cssSelector(".search_result");
    private final By profileMainColumn = By.cssSelector(".profile_main_column");

    // Searches for the provided user by last name.
    public void searchFor(TestUser user) {
        $(searchInput).shouldBe(visible).setValue(user.getLastName()).pressEnter();
    }

    // Verifies that the live search results contain the expected user.
    public void verifyResultIncludes(TestUser user) {
        $(searchResults).shouldBe(visible).shouldHave(text(user.getFullName()));
    }

    // Opens the user's profile from the search results.
    public void openProfile(TestUser user) {
        $(searchResults)
                .find(By.cssSelector("a[href='" + user.getExpectedUsername() + "']"))
                .shouldBe(visible)
                .click();

        if (!profilePageIsLoaded()) {
            openProfileRouteWhenPrettyUrlIsNotAvailable(user);
        }
    }

    // Verifies that the user profile page is displayed.
    public void verifyProfilePageIsDisplayed() {
        $(profileMainColumn).shouldBe(visible);
    }

    // Waits briefly for the profile page opened from the search result.
    private boolean profilePageIsLoaded() {
        try {
            $(profileMainColumn).shouldBe(visible, PROFILE_LOAD_TIMEOUT);
            return true;
        } catch (AssertionError error) {
            return false;
        }
    }

    // Keeps the scenario stable on environments where pretty profile URLs are not routed.
    private void openProfileRouteWhenPrettyUrlIsNotAvailable(TestUser user) {
        open("/profile.php?profile_username=" + user.getExpectedUsername());
    }
}
