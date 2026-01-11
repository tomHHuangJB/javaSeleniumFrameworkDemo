package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

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
        waits.pageReady();
        WebElement body = driver.findElement(By.tagName("body"));
        body.click();
        body.sendKeys(Keys.chord(Keys.ALT, Keys.SHIFT, "d"));
        if (!isOpen()) {
            actions.keyDown(Keys.ALT).keyDown(Keys.SHIFT).sendKeys("d").keyUp(Keys.SHIFT).keyUp(Keys.ALT).perform();
        }
        if (!isOpen()) {
            ((JavascriptExecutor) driver).executeScript(
                "const evt = (type) => new KeyboardEvent(type, {key:'D', code:'KeyD', keyCode:68, which:68, altKey:true, shiftKey:true, bubbles:true, cancelable:true});"
                    + "document.dispatchEvent(evt('keydown'));"
                    + "document.dispatchEvent(evt('keyup'));"
                    + "window.dispatchEvent(evt('keydown'));"
                    + "window.dispatchEvent(evt('keyup'));"
            );
        }
        if (!isOpen() && driver instanceof ChromeDriver) {
            // Linux can intercept Alt+Shift; CDP dispatch is a reliable fallback in headless CI.
            dispatchCdpShortcut((ChromeDriver) driver);
        }
    }

    private void dispatchCdpShortcut(ChromeDriver chrome) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", "keyDown");
        params.put("key", "Alt");
        params.put("code", "AltLeft");
        params.put("windowsVirtualKeyCode", 18);
        params.put("modifiers", 1);
        chrome.executeCdpCommand("Input.dispatchKeyEvent", params);

        params.put("key", "Shift");
        params.put("code", "ShiftLeft");
        params.put("windowsVirtualKeyCode", 16);
        params.put("modifiers", 9);
        chrome.executeCdpCommand("Input.dispatchKeyEvent", params);

        params.put("key", "D");
        params.put("code", "KeyD");
        params.put("keyCode", 68);
        params.put("windowsVirtualKeyCode", 68);
        chrome.executeCdpCommand("Input.dispatchKeyEvent", params);
        params.put("type", "keyUp");
        chrome.executeCdpCommand("Input.dispatchKeyEvent", params);

        params.put("key", "Shift");
        params.put("code", "ShiftLeft");
        params.put("keyCode", 16);
        params.put("windowsVirtualKeyCode", 16);
        params.put("modifiers", 1);
        chrome.executeCdpCommand("Input.dispatchKeyEvent", params);

        params.put("key", "Alt");
        params.put("code", "AltLeft");
        params.put("keyCode", 18);
        params.put("windowsVirtualKeyCode", 18);
        params.put("modifiers", 0);
        chrome.executeCdpCommand("Input.dispatchKeyEvent", params);
    }

    public boolean isOpen() {
        return driver.findElements(closeBtn).stream().anyMatch(WebElement::isDisplayed);
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
