package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.ExperimentsPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExperimentsTests extends BaseTest {

    @Test
    void experimentsFlagsAndRoles() {
        openPath("/experiments");
        ExperimentsPage experiments = new ExperimentsPage(driver);

        experiments.chooseVariantB();
        assertTrue(experiments.activeVariantText().contains("B"));

        experiments.applyFlagOverride();
        experiments.selectRole("admin");
        assertTrue(experiments.flagEnabledText().contains("true"));
    }
}
