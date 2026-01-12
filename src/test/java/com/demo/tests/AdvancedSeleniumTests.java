package com.demo.tests;

import com.demo.core.BaseTest;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdvancedSeleniumTests extends BaseTest {

    @Test
    void usesActionsJavaScriptAndRelativeLocators() {
        openPath("/components");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contextZone = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='context-zone']"))
        );
        new Actions(driver).contextClick(contextZone).perform();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement toastBtn = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='toast-btn']"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", toastBtn);
        toastBtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='toast-item']")));
        assertTrue(driver.findElements(By.cssSelector("[data-testid='toast-item']")).size() > 0);

        openPath("/auth");
        WebElement usernameLabel = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(.,'Username')]"))
        );
        WebElement usernameInput = driver.findElement(RelativeLocator.with(By.tagName("input")).below(usernameLabel));
        usernameInput.sendKeys("relative-locator");
    }
}
