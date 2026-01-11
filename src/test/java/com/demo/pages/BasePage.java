package com.demo.pages;

import com.demo.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.TimeoutException;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final Waits waits;
    protected final Actions actions;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
        this.actions = new Actions(driver);
    }

    protected By testId(String id) {
        return By.cssSelector("[data-testid='" + id + "']");
    }

    protected WebElement get(By locator) {
        return waits.visible(locator);
    }

    protected void click(By locator) {
        WebElement element = waits.visible(locator);
        scrollIntoView(element);
        try {
            waits.clickable(locator).click();
        } catch (ElementClickInterceptedException | TimeoutException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String text) {
        WebElement element = waits.visible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void selectByValue(By locator, String value) {
        Select select = new Select(waits.visible(locator));
        select.selectByValue(value);
    }

    protected void selectByVisibleText(By locator, String text) {
        Select select = new Select(waits.visible(locator));
        select.selectByVisibleText(text);
    }

    protected WebElement shadowInput(By hostLocator, String shadowTestId) {
        WebElement host = waits.visible(hostLocator);
        SearchContext root = host.getShadowRoot();
        return root.findElement(By.cssSelector("[data-testid='" + shadowTestId + "']"));
    }

    protected void jsClick(By locator) {
        WebElement element = waits.visible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center', inline:'center'});",
            element
        );
    }
}
