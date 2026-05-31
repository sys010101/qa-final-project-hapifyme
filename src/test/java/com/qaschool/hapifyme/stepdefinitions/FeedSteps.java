package com.qaschool.hapifyme.stepdefinitions;

import com.qaschool.hapifyme.context.TestContext;
import com.qaschool.hapifyme.pages.FeedPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Step definitions for creating and verifying feed posts.
public class FeedSteps {
    private final FeedPage feedPage = new FeedPage();

    // Creates a unique feed post so repeated test runs do not reuse the same text.
    @When("the user creates a feed post with text {string}")
    public void theUserCreatesAFeedPostWithText(String postText) {
        String uniquePostText = postText + " " + System.currentTimeMillis();
        TestContext.setLatestPostText(uniquePostText);
        feedPage.createPost(uniquePostText);
    }

    // Verifies that the post created in the current scenario appears on the feed.
    @Then("the feed contains the new post")
    public void theFeedContainsTheNewPost() {
        feedPage.verifyPostVisible(TestContext.getLatestPostText());
    }
}
