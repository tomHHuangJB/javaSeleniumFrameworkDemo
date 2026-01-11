package com.demo.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

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
}
