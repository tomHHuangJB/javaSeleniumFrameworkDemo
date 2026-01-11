package com.demo.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    @RegisterExtension
    static TestWatcherExtension watcher = new TestWatcherExtension();

    @BeforeEach
    void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(Config.baseUrl());
    }

    @AfterEach
    void tearDown() {
        DriverManager.quitDriver();
    }

    protected void openPath(String path) {
        String base = Config.baseUrl();
        if (path.startsWith("/")) {
            driver.get(base + path);
        } else {
            driver.get(base + "/" + path);
        }
    }

    protected void waitForAppReady() {
        // CI can load the app shell late; wait for a stable "ready" marker before global hotkeys.
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='session-state']")));
    }
}
