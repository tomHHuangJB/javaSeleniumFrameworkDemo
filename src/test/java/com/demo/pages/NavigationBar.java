package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar extends BasePage {
    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    public void goTo(String label) {
        String normalized = label.toLowerCase();
        By nav = testId("nav-" + normalized);
        click(nav);
    }
}
