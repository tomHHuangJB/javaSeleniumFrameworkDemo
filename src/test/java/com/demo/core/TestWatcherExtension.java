package com.demo.core;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestWatcherExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            return;
        }
        try {
            Path screenshotsDir = Path.of("target", "screenshots");
            Files.createDirectories(screenshotsDir);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path target = screenshotsDir.resolve(context.getDisplayName().replaceAll("[^a-zA-Z0-9-_]", "_") + ".png");
            Files.copy(screenshot.toPath(), target);
        } catch (Exception ignored) {
        }

        // Demo-only: optional Jira issue creation for failures.
        // In production, ensure failures are deduplicated and sensitive data is scrubbed.
        String testName = context.getDisplayName();
        String className = context.getTestClass().map(Class::getSimpleName).orElse("Unknown");
        String summary = "UI Test Failure: " + className + "." + testName;
        String details = "Failure in " + className + "." + testName + "\n"
            + "Base URL: " + Config.baseUrl() + "\n"
            + "Browser: " + Config.browser() + "\n"
            + "Reason: " + (cause != null ? cause.getMessage() : "Unknown");
        new JiraClient().createIssue(summary, details);
    }
}
