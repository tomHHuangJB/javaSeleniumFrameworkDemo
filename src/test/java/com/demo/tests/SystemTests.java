package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.core.Config;
import com.demo.pages.SystemPage;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SystemTests extends BaseTest {

    @Test
    void handlesDialogsAndWindows() {
        openPath("/system");
        SystemPage system = new SystemPage(driver);

        system.openAlert();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        system.openConfirm();
        alert = driver.switchTo().alert();
        alert.dismiss();

        system.openPrompt();
        alert = driver.switchTo().alert();
        alert.sendKeys("Selenium");
        alert.accept();

        String original = driver.getWindowHandle();
        system.openNewWindow();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getWindowHandles().size() > 1);

        List<String> handles = new ArrayList<>(driver.getWindowHandles());
        String newWindow = handles.stream().filter(h -> !h.equals(original)).findFirst().orElse(original);
        driver.switchTo().window(newWindow);
        driver.get(Config.baseUrl() + "/system");
        driver.findElement(By.cssSelector("[data-testid='storage-write']")).click();
        driver.close();
        driver.switchTo().window(original);

        system.selectRole("admin");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
            By.cssSelector("[data-testid='storage-event']"), "Storage event"));
        assertTrue(system.storageEventText().contains("Storage event"));
    }
}
