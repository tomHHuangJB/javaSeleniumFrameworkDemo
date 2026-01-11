package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IntegrationsPage extends BasePage {
    private final By paymentIframe = testId("payment-iframe");
    private final By iframeMessage = testId("iframe-message");

    public IntegrationsPage(WebDriver driver) {
        super(driver);
    }

    public void approvePaymentInIframe() {
        WebElement iframe = get(paymentIframe);
        driver.switchTo().frame(iframe);
        driver.findElement(By.tagName("button")).click();
        driver.switchTo().defaultContent();
    }

    public String messageText() {
        return get(iframeMessage).getText();
    }
}
