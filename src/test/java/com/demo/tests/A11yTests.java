package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.A11yPage;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class A11yTests extends BaseTest {

    @Test
    void accessibilityInteractions() {
        openPath("/a11y");
        A11yPage a11y = new A11yPage(driver);

        a11y.announceUpdate();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> !a11y.ariaLiveText().equals("Ready"));

        driver.findElement(By.xpath("//button[contains(.,'Open modal')]")).click();
        assertTrue(a11y.modalVisible());

        driver.findElement(By.cssSelector("[data-testid='high-contrast']")).click();
        driver.findElement(By.cssSelector("[data-testid='reduced-motion']")).click();
    }
}
