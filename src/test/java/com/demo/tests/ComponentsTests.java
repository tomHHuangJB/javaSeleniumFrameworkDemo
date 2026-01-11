package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.ComponentsPage;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComponentsTests extends BaseTest {

    @Test
    void componentsCoverage() {
        openPath("/components");
        ComponentsPage components = new ComponentsPage(driver);

        assertTrue(components.virtualListVisible());
        int before = components.infiniteItemsCount();
        components.loadMoreItems();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> components.infiniteItemsCount() > before);

        assertTrue(components.svgVisible());
        assertTrue(components.canvasVisible());

        components.openContextMenu();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        components.triggerToast();
        wait.until(d -> components.toastCount() > 0);
        assertTrue(components.toastCount() > 0);
    }
}
