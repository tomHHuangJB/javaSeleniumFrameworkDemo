package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.DebugPanelPage;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DebugPanelTests extends BaseTest {

    @Test
    void debugPanelToggles() {
        openPath("/");
        waitForAppReady();
        DebugPanelPage debug = new DebugPanelPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(d -> {
            if (!debug.isOpen()) {
                debug.openPanel();
            }
            return debug.isOpen();
        });

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
