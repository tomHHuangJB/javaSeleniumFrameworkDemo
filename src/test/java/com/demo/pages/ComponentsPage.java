package com.demo.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ComponentsPage extends BasePage {
    private final By virtualList = testId("virtual-list");
    private final By infiniteScroll = testId("infinite-scroll");
    private final By loadMore = testId("load-more");
    private final By svgChart = testId("svg-chart");
    private final By canvas = testId("canvas");
    private final By contextZone = testId("context-zone");
    private final By toastBtn = testId("toast-btn");
    private final By toastItem = testId("toast-item");

    public ComponentsPage(WebDriver driver) {
        super(driver);
    }

    public boolean virtualListVisible() {
        return get(virtualList).isDisplayed();
    }

    public int infiniteItemsCount() {
        WebElement container = get(infiniteScroll);
        List<WebElement> items = container.findElements(By.cssSelector("div.rounded"));
        return items.size();
    }

    public void loadMoreItems() {
        click(loadMore);
    }

    public boolean svgVisible() {
        return get(svgChart).isDisplayed();
    }

    public boolean canvasVisible() {
        return get(canvas).isDisplayed();
    }

    public void openContextMenu() {
        WebElement zone = get(contextZone);
        actions.contextClick(zone).perform();
    }

    public void triggerToast() {
        click(toastBtn);
    }

    public int toastCount() {
        return driver.findElements(toastItem).size();
    }
}
