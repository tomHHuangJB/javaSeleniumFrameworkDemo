package com.demo.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

public class DriverFactory {

    public WebDriver createDriver() {
        String browser = Config.browser().toLowerCase();
        String remoteUrl = Config.remoteUrl();

        if (!remoteUrl.isBlank()) {
            return createRemoteDriver(browser, remoteUrl);
        }

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(firefoxOptions());
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver(edgeOptions());
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(chromeOptions());
        }
    }

    private WebDriver createRemoteDriver(String browser, String remoteUrl) {
        try {
            MutableCapabilities options;
            switch (browser) {
                case "firefox":
                    options = firefoxOptions();
                    break;
                case "edge":
                    options = edgeOptions();
                    break;
                case "chrome":
                default:
                    options = chromeOptions();
                    break;
            }
            return new RemoteWebDriver(new URL(remoteUrl), options);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid remote.url", e);
        }
    }

    private ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (Config.headless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1400,900");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        Map<String, Object> prefs = new HashMap<>();
        try {
            Files.createDirectories(Config.downloadDir());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create download dir", e);
        }
        prefs.put("download.default_directory", Config.downloadDir().toString());
        prefs.put("download.prompt_for_download", false);
        prefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", prefs);

        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        return options;
    }

    private FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (Config.headless()) {
            options.addArguments("-headless");
        }
        options.addPreference("browser.download.dir", Config.downloadDir().toString());
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/pdf");
        return options;
    }

    private EdgeOptions edgeOptions() {
        EdgeOptions options = new EdgeOptions();
        if (Config.headless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1400,900");
        return options;
    }
}
