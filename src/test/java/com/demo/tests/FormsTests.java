package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.FormsPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormsTests extends BaseTest {

    @Test
    void handlesComplexInputs() {
        openPath("/forms");
        FormsPage forms = new FormsPage(driver);

        forms.toggleExtraField();
        assertTrue(forms.isConditionalFieldVisible());

        forms.wizardNext();
        assertTrue(forms.wizardStepText().contains("Step 2"));

        forms.addArrayItem();
        forms.removeArrayItem(0);

        forms.enterRichText("Senior automation input");
        forms.fillShadowInput("Shadow DOM value");
        forms.pickColor("#ff0000");
        forms.setRange(20, 80);
        forms.setDateTime("2024-01-10T10:30");
        assertTrue(forms.dragDropZoneVisible());
    }
}
