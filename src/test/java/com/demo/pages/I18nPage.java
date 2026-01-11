package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class I18nPage extends BasePage {
    private final By localeSelect = testId("locale-select");
    private final By timezoneSelect = testId("timezone-select");

    public I18nPage(WebDriver driver) {
        super(driver);
    }

    public void selectLocale(String locale) {
        selectByValue(localeSelect, locale);
    }

    public void selectTimezone(String timezone) {
        selectByValue(timezoneSelect, timezone);
    }

    public String pageDirAttribute() {
        WebElement select = get(localeSelect);
        return (String) ((org.openqa.selenium.JavascriptExecutor) driver)
            .executeScript("return arguments[0].closest('div[dir]').getAttribute('dir');", select);
    }

    public String timezoneText() {
        return driver.findElement(By.xpath("//*[contains(text(),'Selected TZ:')]")).getText();
    }
}
