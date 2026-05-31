package com.qaschool.hapifyme.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

// Page Object for the HapifyMe feed page.
public class FeedPage {
    private final By postTextArea = By.name("post_text");
    private final By postButton = By.name("post");
    private final By postsArea = By.cssSelector(".posts_area");

    // Verifies that the feed page is ready for posting.
    public void verifyLoaded() {
        $(postTextArea).shouldBe(visible);
        $(postsArea).shouldBe(visible);
    }

    // Creates a new feed post with the provided text.
    public void createPost(String postText) {
        $(postTextArea).shouldBe(visible).setValue(postText);
        $(postButton).click();
    }

    // Verifies that the feed contains the expected post text.
    public void verifyPostVisible(String postText) {
        $(postsArea).shouldBe(visible).shouldHave(text(postText));
    }
}
