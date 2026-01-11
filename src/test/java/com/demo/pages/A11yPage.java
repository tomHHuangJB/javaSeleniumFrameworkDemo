package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class A11yPage extends BasePage {
    private final By announceBtn = testId("announce-btn");
    private final By ariaLive = testId("aria-live");
    private final By focusModal = testId("focus-modal");

    public A11yPage(WebDriver driver) {
        super(driver);
    }

    public void announceUpdate() {
        click(announceBtn);
    }

    public String ariaLiveText() {
        return get(ariaLive).getText();
    }

    public boolean modalVisible() {
        return get(focusModal).isDisplayed();
    }
}
