package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthPage extends BasePage {
    private final By username = testId("login-username");
    private final By password = testId("login-password");
    private final By remember = testId("login-remember");
    private final By submit = testId("login-submit");
    private final By mfaCode = testId("mfa-code");
    private final By mfaVerify = testId("mfa-verify");

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public void login(String user, String pass, boolean rememberMe) {
        type(username, user);
        type(password, pass);
        if (rememberMe) {
            click(remember);
        }
        click(submit);
    }

    public void submitMfa(String code) {
        type(mfaCode, code);
        click(mfaVerify);
    }
}
