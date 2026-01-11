package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.IntegrationsPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationsTests extends BaseTest {

    @Test
    void iframePostMessageFlow() {
        openPath("/integrations");
        IntegrationsPage integrations = new IntegrationsPage(driver);
        integrations.approvePaymentInIframe();
        integrations.waitForMessageContains("payment-approved");
        assertTrue(integrations.messageText().contains("payment-approved"));
    }
}
