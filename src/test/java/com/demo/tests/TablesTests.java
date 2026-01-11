package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.TablesPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TablesTests extends BaseTest {

    @Test
    void tableOperations() {
        openPath("/tables");
        TablesPage tables = new TablesPage(driver);

        assertTrue(tables.gridVisible());
        tables.selectRow(1);
        tables.updateRowName(2, "Row 2 Updated");
        tables.updateRowStatus(3, "Archived");
        tables.sortAscending();
        tables.filterActive();
        tables.nextCursorPage();
        tables.nextOffsetPage();
        tables.exportCsv();
    }
}
