package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By wsStatus = testId("ws-status");
    private final By notificationLog = testId("notification-log");
    private final By sessionState = testId("session-state");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String websocketStatus() {
        return get(wsStatus).getText();
    }

    public boolean notificationLogVisible() {
        return get(notificationLog).isDisplayed();
    }

    public boolean sessionStateVisible() {
        return get(sessionState).isDisplayed();
    }
}
