package com.demo.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicPage extends BasePage {
    private final By optimisticCount = testId("optimistic-count");
    private final By optimisticBtn = testId("optimistic-btn");
    private final By optimisticStatus = testId("optimistic-status");
    private final By raceTrigger = testId("race-trigger");
    private final By dedupTrigger = testId("dedup-trigger");
    private final By partialTrigger = testId("partial-trigger");
    private final By cacheToggle = testId("cache-toggle");
    private final By wsDisconnect = testId("ws-disconnect");
    private final By swRegister = testId("sw-register");
    private final By swUnregister = testId("sw-unregister");
    private final By skeletonCard = testId("skeleton-card");
    private final By partialFailure = testId("partial-failure");
    private final By dynamicLog = testId("dynamic-log");

    public DynamicPage(WebDriver driver) {
        super(driver);
    }

    public int count() {
        return Integer.parseInt(get(optimisticCount).getText());
    }

    public void clickOptimistic() {
        click(optimisticBtn);
    }

    public String statusText() {
        return get(optimisticStatus).getText();
    }

    public void triggerRace() {
        click(raceTrigger);
    }

    public void triggerDedup() {
        click(dedupTrigger);
    }

    public void triggerPartial() {
        click(partialTrigger);
    }

    public void toggleCache() {
        click(cacheToggle);
    }

    public void simulateDisconnect() {
        click(wsDisconnect);
    }

    public void registerServiceWorker() {
        click(swRegister);
    }

    public void unregisterServiceWorker() {
        click(swUnregister);
    }

    public boolean skeletonVisible() {
        return get(skeletonCard).isDisplayed();
    }

    public boolean partialFailureVisible() {
        return get(partialFailure).isDisplayed();
    }

    public List<WebElement> logItems() {
        return get(dynamicLog).findElements(By.tagName("li"));
    }
}
