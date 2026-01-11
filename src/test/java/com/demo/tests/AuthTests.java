package com.demo.tests;

import com.demo.core.BaseTest;
import com.demo.pages.AuthPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AuthTests extends BaseTest {

    @Test
    void loginAndMfaFlow() {
        openPath("/auth");
        AuthPage auth = new AuthPage(driver);
        assertDoesNotThrow(() -> auth.login("principal.engineer", "demo", true));
        assertDoesNotThrow(() -> auth.submitMfa("123456"));
    }
}
