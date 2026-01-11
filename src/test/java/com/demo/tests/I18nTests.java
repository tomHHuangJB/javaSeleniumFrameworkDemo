package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.I18nPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class I18nTests extends BaseTest {

    @Test
    void localeAndTimezoneSwitching() {
        openPath("/i18n");
        I18nPage i18n = new I18nPage(driver);

        i18n.selectLocale("ar");
        assertEquals("rtl", i18n.pageDirAttribute());

        i18n.selectTimezone("Asia/Tokyo");
        assertTrue(i18n.timezoneText().contains("Asia/Tokyo"));
    }
}
