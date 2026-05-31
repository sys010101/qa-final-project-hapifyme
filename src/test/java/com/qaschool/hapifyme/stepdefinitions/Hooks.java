package com.qaschool.hapifyme.stepdefinitions;

import com.codeborne.selenide.Configuration;
import com.qaschool.hapifyme.context.TestContext;
import com.qaschool.hapifyme.support.EnvironmentChecks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.Assume;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.screenshot;

// Cucumber hooks that prepare and clean up the browser for each scenario.
public class Hooks {
    // Resets shared scenario data and configures Selenide before each scenario.
    @Before
    public void setUp() {
        TestContext.reset();
        Configuration.browser = "chrome";
        Configuration.baseUrl = System.getProperty("hapifyme.baseUrl", "https://test.hapifyme.com");
        Assume.assumeTrue(
                "Skipping UI scenario because the HapifyMe host cannot be resolved: " + Configuration.baseUrl,
                EnvironmentChecks.baseUrlHostCanResolve(Configuration.baseUrl)
        );
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10_000;
        Configuration.reportsFolder = "target/selenide-reports";
        Configuration.headless = Boolean.parseBoolean(System.getProperty("selenide.headless", "true"));
    }

    // Captures a screenshot on failure and closes the browser after each scenario.
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            screenshot(scenario.getName().replaceAll("[^a-zA-Z0-9]", "_"));
        }
        closeWebDriver();
    }
}