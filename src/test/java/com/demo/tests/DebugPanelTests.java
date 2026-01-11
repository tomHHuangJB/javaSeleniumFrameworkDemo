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
        DebugPanelPage debug = new DebugPanelPage(driver);

        debug.openPanel();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> debug.isOpen());

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
