package com.demo.core;

import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> THREAD_DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        WebDriver driver = THREAD_DRIVER.get();
        if (driver == null) {
            driver = new DriverFactory().createDriver();
            THREAD_DRIVER.set(driver);
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = THREAD_DRIVER.get();
        if (driver != null) {
            driver.quit();
            THREAD_DRIVER.remove();
        }
    }
}
