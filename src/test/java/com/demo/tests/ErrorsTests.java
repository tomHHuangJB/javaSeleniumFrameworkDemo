package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.ErrorsPage;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorsTests extends BaseTest {

    @Test
    void errorStatesAndSecurityLabs() {
        openPath("/errors");
        ErrorsPage errors = new ErrorsPage(driver);

        errors.triggerNetworkFail();
        errors.triggerTimeouts();
        assertTrue(errors.partialGoodVisible());
        assertTrue(errors.partialFailVisible());

        errors.startLeak();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.getPageSource().contains("Leak size:"));

        // Demo-only: security lab triggers exercise Selenium flows.
        // In production, these behaviors must be blocked and validated by app controls.
        errors.runSecurityLabs();
        assertTrue(errors.auditLogText().contains("Audit log"));
    }
}
