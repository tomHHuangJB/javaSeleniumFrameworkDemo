package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DebugPanelPage extends BasePage {
    private final By closeBtn = testId("debug-close");
    private final By showTestIds = testId("debug-testids");
    private final By simulateOffline = testId("debug-offline");
    private final By networkProfile = testId("debug-network");
    private final By permissionOverride = testId("debug-permission");
    private final By timeSkew = testId("debug-time-skew");
    private final By stateViewer = testId("state-viewer");

    public DebugPanelPage(WebDriver driver) {
        super(driver);
    }

    public void openPanel() {
        actions.keyDown(Keys.ALT).keyDown(Keys.SHIFT).sendKeys("d").keyUp(Keys.SHIFT).keyUp(Keys.ALT).perform();
    }

    public boolean isOpen() {
        return driver.findElements(closeBtn).size() > 0;
    }

    public void toggleShowTestIds() {
        // CI runs can have overlay timing issues; JS click avoids intercepted clicks.
        jsClick(showTestIds);
    }

    public void toggleOffline() {
        // CI runs can have overlay timing issues; JS click avoids intercepted clicks.
        jsClick(simulateOffline);
    }

    public void selectNetworkProfile(String value) {
        selectByValue(networkProfile, value);
    }

    public void selectPermissionOverride(String value) {
        selectByValue(permissionOverride, value);
    }

    public void setTimeSkew(String value) {
        WebElement input = get(timeSkew);
        input.clear();
        input.sendKeys(value);
    }

    public String stateViewerText() {
        return get(stateViewer).getText();
    }

    public String testIdVisibilityAttr() {
        return (String) ((org.openqa.selenium.JavascriptExecutor) driver)
            .executeScript("return document.documentElement.getAttribute('data-testid-visible');");
    }
}
