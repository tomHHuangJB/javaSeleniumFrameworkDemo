package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SystemPage extends BasePage {
    private final By alertButton = testId("dialog-alert");
    private final By confirmButton = testId("dialog-confirm");
    private final By promptButton = testId("dialog-prompt");
    private final By windowOpen = testId("window-open");
    private final By storageWrite = testId("storage-write");
    private final By storageEvent = testId("storage-event");
    private final By roleSelect = testId("role-access-select");

    public SystemPage(WebDriver driver) {
        super(driver);
    }

    public void openAlert() {
        click(alertButton);
    }

    public void openConfirm() {
        click(confirmButton);
    }

    public void openPrompt() {
        click(promptButton);
    }

    public void openNewWindow() {
        click(windowOpen);
    }

    public void writeStorage() {
        click(storageWrite);
    }

    public String storageEventText() {
        return get(storageEvent).getText();
    }

    public void selectRole(String roleValue) {
        selectByValue(roleSelect, roleValue);
    }
}
