package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.DynamicPage;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicTests extends BaseTest {

    @Test
    void dynamicStateAndRecovery() {
        openPath("/dynamic");
        DynamicPage dynamic = new DynamicPage(driver);

        int before = dynamic.count();
        dynamic.clickOptimistic();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> !dynamic.statusText().equals("saving"));
        assertTrue(dynamic.count() >= before);

        dynamic.triggerRace();
        dynamic.triggerDedup();
        dynamic.triggerPartial();
        dynamic.toggleCache();
        dynamic.simulateDisconnect();
        dynamic.registerServiceWorker();
        dynamic.unregisterServiceWorker();

        assertTrue(dynamic.skeletonVisible());
        assertTrue(dynamic.partialFailureVisible());
        assertTrue(dynamic.logItems().size() > 0);
    }
}
