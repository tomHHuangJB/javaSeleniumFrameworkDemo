package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.PerformancePage;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerformanceTests extends BaseTest {

    @Test
    void performanceSignalsVisible() {
        openPath("/performance");
        PerformancePage performance = new PerformancePage(driver);

        assertTrue(performance.largeDomCount() > 100);
        performance.blockMainThread();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> performance.workerResultText().contains("Result:"));

        assertTrue(performance.cpuIndicatorText().contains("CPU"));
    }
}
