package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TablesPage extends BasePage {
    private final By dataGrid = testId("data-grid");
    private final By cursorNext = testId("cursor-next");
    private final By offsetNext = testId("offset-next");
    private final By bulkExport = testId("bulk-export");
    private final By sortAsc = testId("sort-asc");
    private final By filterActive = testId("filter-active");

    public TablesPage(WebDriver driver) {
        super(driver);
    }

    public boolean gridVisible() {
        return get(dataGrid).isDisplayed();
    }

    public void selectRow(int id) {
        click(testId("row-select-" + id));
    }

    public void updateRowName(int id, String name) {
        type(testId("row-name-" + id), name);
    }

    public void updateRowStatus(int id, String status) {
        selectByVisibleText(testId("row-status-" + id), status);
    }

    public void nextCursorPage() {
        // CI runs can have overlay timing issues; JS click avoids intercepted clicks.
        jsClick(cursorNext);
    }

    public void nextOffsetPage() {
        click(offsetNext);
    }

    public void exportCsv() {
        click(bulkExport);
    }

    public void sortAscending() {
        click(sortAsc);
    }

    public void filterActive() {
        click(filterActive);
    }
}
