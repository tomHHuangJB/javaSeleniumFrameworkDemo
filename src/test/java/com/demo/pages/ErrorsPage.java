package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorsPage extends BasePage {
    private final By networkFail = testId("network-fail");
    private final By timeout1s = testId("timeout-1s");
    private final By timeout5s = testId("timeout-5s");
    private final By timeout30s = testId("timeout-30s");
    private final By partialGood = testId("partial-good");
    private final By partialFail = testId("partial-fail");
    private final By leakStart = testId("leak-start");
    private final By auditLog = testId("audit-log");

    private final By securityInjection = testId("security-injection");
    private final By securityAccess = testId("security-access");
    private final By securityXss = testId("security-xss");
    private final By securityVuln = testId("security-vuln");
    private final By securitySsrf = testId("security-ssrf");
    private final By securityCrypto = testId("security-crypto");
    private final By securityLogging = testId("security-logging");

    public ErrorsPage(WebDriver driver) {
        super(driver);
    }

    public void triggerNetworkFail() {
        click(networkFail);
    }

    public void triggerTimeouts() {
        click(timeout1s);
        click(timeout5s);
        click(timeout30s);
    }

    public boolean partialGoodVisible() {
        return get(partialGood).isDisplayed();
    }

    public boolean partialFailVisible() {
        return get(partialFail).isDisplayed();
    }

    public void startLeak() {
        click(leakStart);
    }

    public String auditLogText() {
        return get(auditLog).getText();
    }

    public void runSecurityLabs() {
        click(securityInjection);
        click(securityAccess);
        click(securityXss);
        click(securityVuln);
        click(securitySsrf);
        click(securityCrypto);
        click(securityLogging);
    }
}
