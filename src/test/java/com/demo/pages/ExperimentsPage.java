package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExperimentsPage extends BasePage {
    private final By variantA = testId("variant-a");
    private final By variantB = testId("variant-b");
    private final By flagOverride = testId("flag-override");
    private final By roleSelect = testId("role-select");

    public ExperimentsPage(WebDriver driver) {
        super(driver);
    }

    public void chooseVariantA() {
        click(variantA);
    }

    public void chooseVariantB() {
        click(variantB);
    }

    public String activeVariantText() {
        return driver.findElement(By.xpath("//*[contains(text(),'Active variant:')]")).getText();
    }

    public void applyFlagOverride() {
        click(flagOverride);
    }

    public void selectRole(String role) {
        selectByValue(roleSelect, role);
    }

    public String flagEnabledText() {
        return driver.findElement(By.xpath("//*[contains(text(),'Flag enabled:')]")).getText();
    }
}
