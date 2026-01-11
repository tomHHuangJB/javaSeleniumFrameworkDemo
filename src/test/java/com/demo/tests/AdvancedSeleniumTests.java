package com.demo.tests;

import com.demo.core.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdvancedSeleniumTests extends BaseTest {

    @Test
    void usesActionsJavaScriptAndRelativeLocators() {
        openPath("/components");

        WebElement contextZone = driver.findElement(By.cssSelector("[data-testid='context-zone']"));
        new Actions(driver).contextClick(contextZone).perform();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement toastBtn = driver.findElement(By.cssSelector("[data-testid='toast-btn']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", toastBtn);
        toastBtn.click();
        assertTrue(driver.findElements(By.cssSelector("[data-testid='toast-item']")).size() > 0);

        openPath("/auth");
        WebElement usernameLabel = driver.findElement(By.xpath("//label[contains(.,'Username')]") );
        WebElement usernameInput = driver.findElement(RelativeLocator.with(By.tagName("input")).below(usernameLabel));
        usernameInput.sendKeys("relative-locator");
    }
}
