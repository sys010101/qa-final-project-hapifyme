package com.qaschool.hapifyme.context;

import com.qaschool.hapifyme.model.TestUser;

// Stores scenario-level test data shared between Cucumber step definition classes.
public final class TestContext {
    private static TestUser registeredUser;
    private static String latestPostText;

    private TestContext() {
    }

    // Clears the shared data before each scenario starts.
    public static void reset() {
        registeredUser = null;
        latestPostText = null;
    }

    // Returns the user created during the current scenario.
    public static TestUser getRegisteredUser() {
        return registeredUser;
    }

    // Saves the user created during the current scenario.
    public static void setRegisteredUser(TestUser user) {
        registeredUser = user;
    }

    // Returns the latest feed post text created during the current scenario.
    public static String getLatestPostText() {
        return latestPostText;
    }

    // Saves the latest feed post text created during the current scenario.
    public static void setLatestPostText(String postText) {
        latestPostText = postText;
    }
}
