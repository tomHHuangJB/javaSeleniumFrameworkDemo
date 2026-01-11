package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormsPage extends BasePage {
    private final By toggleExtra = testId("toggle-extra");
    private final By conditionalInput = testId("conditional-input");
    private final By wizardPrev = testId("wizard-prev");
    private final By wizardNext = testId("wizard-next");
    private final By wizardStep = testId("wizard-step");
    private final By arrayAdd = testId("array-add");
    private final By richTextIframe = testId("rich-text-iframe");
    private final By dragDropZone = testId("drag-drop-zone");
    private final By colorPicker = testId("color-picker");
    private final By rangeMin = testId("range-min");
    private final By rangeMax = testId("range-max");
    private final By datetimePicker = testId("datetime-picker");
    private final By shadowHost = testId("shadow-host");

    public FormsPage(WebDriver driver) {
        super(driver);
    }

    public void toggleExtraField() {
        click(toggleExtra);
    }

    public boolean isConditionalFieldVisible() {
        try {
            return get(conditionalInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void wizardNext() {
        click(wizardNext);
    }

    public void wizardPrev() {
        click(wizardPrev);
    }

    public String wizardStepText() {
        return get(wizardStep).getText();
    }

    public void addArrayItem() {
        click(arrayAdd);
    }

    public void removeArrayItem(int index) {
        click(testId("array-remove-" + index));
    }

    public void enterRichText(String text) {
        WebElement iframe = get(richTextIframe);
        driver.switchTo().frame(iframe);
        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        body.sendKeys(text);
        driver.switchTo().defaultContent();
    }

    public boolean dragDropZoneVisible() {
        return get(dragDropZone).isDisplayed();
    }

    public void pickColor(String hex) {
        type(colorPicker, hex);
    }

    public void setRange(int min, int max) {
        type(rangeMin, String.valueOf(min));
        type(rangeMax, String.valueOf(max));
    }

    public void setDateTime(String value) {
        type(datetimePicker, value);
    }

    public void fillShadowInput(String text) {
        WebElement input = shadowInput(shadowHost, "shadow-input");
        input.clear();
        input.sendKeys(text);
    }
}
