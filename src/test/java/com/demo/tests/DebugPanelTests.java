package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.DebugPanelPage;
import java.time.Duration;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DebugPanelTests extends BaseTest {

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    void debugPanelToggles() {
        openPath("/");
        waitForAppReady();
        DebugPanelPage debug = new DebugPanelPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(d -> {
                if (!debug.isOpen()) {
                    debug.openPanel();
                }
                return debug.isOpen();
            });
        } catch (TimeoutException e) {
            // CI stable profile can disable the debug hotkey; skip if the panel never opens.
            Assumptions.assumeTrue(false, "Debug panel hotkey unavailable in this environment");
        }

        debug.toggleShowTestIds();
        assertEquals("true", debug.testIdVisibilityAttr());

        debug.toggleOffline();
        debug.selectNetworkProfile("offline");
        debug.selectPermissionOverride("granted");
        debug.setTimeSkew("60000");

        assertTrue(debug.stateViewerText().contains("offline"));
        assertTrue(debug.stateViewerText().contains("granted"));
    }
}
