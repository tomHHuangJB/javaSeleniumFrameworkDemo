package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.HomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeTests extends BaseTest {

    @Test
    void dashboardWidgetsAndLandmarks() {
        openPath("/");
        HomePage home = new HomePage(driver);

        assertTrue(home.sessionStateVisible());
        assertTrue(home.notificationLogVisible());
        assertFalse(home.websocketStatus().isBlank());

        assertTrue(driver.findElements(By.cssSelector("[role='navigation']")).size() > 0);
        assertTrue(driver.findElements(By.cssSelector("[role='complementary']")).size() > 0);
    }
}
