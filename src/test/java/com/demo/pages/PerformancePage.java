package com.demo.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PerformancePage extends BasePage {
    private final By largeDom = testId("large-dom");
    private final By blockMainThread = testId("block-main-thread");
    private final By workerResult = testId("worker-result");
    private final By cpuIndicator = testId("cpu-indicator");

    public PerformancePage(WebDriver driver) {
        super(driver);
    }

    public int largeDomCount() {
        List<WebElement> spans = get(largeDom).findElements(By.tagName("span"));
        return spans.size();
    }

    public void blockMainThread() {
        click(blockMainThread);
    }

    public String workerResultText() {
        return get(workerResult).getText();
    }

    public String cpuIndicatorText() {
        return get(cpuIndicator).getText();
    }
}
