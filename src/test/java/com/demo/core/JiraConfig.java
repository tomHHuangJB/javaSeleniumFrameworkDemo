package com.demo.core;

public final class JiraConfig {
    private JiraConfig() {
    }

    public static String baseUrl() {
        return get("JIRA_BASE_URL", "");
    }

    public static String user() {
        return get("JIRA_USER", "");
    }

    public static String token() {
        return get("JIRA_TOKEN", "");
    }

    public static String projectKey() {
        return get("JIRA_PROJECT_KEY", "");
    }

    public static String issueType() {
        return get("JIRA_ISSUE_TYPE", "Bug");
    }

    public static boolean enabled() {
        return !baseUrl().isBlank() && !projectKey().isBlank();
    }

    private static String get(String key, String fallback) {
        String env = System.getenv(key);
        if (env != null && !env.isBlank()) {
            return env;
        }
        return fallback;
    }
}
