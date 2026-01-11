package com.demo.core;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JiraClient {
    private final HttpClient client = HttpClient.newHttpClient();

    public void createIssue(String summary, String description) {
        if (!JiraConfig.enabled()) {
            return;
        }

        String payload = "{" +
            "\"fields\":{" +
            "\"project\":{\"key\":\"" + escape(JiraConfig.projectKey()) + "\"}," +
            "\"summary\":\"" + escape(summary) + "\"," +
            "\"description\":\"" + escape(description) + "\"," +
            "\"issuetype\":{\"name\":\"" + escape(JiraConfig.issueType()) + "\"}" +
            "}}";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(JiraConfig.baseUrl() + "/rest/api/2/issue"))
            .header("Content-Type", "application/json")
            .header("Authorization", basicAuth())
            .POST(HttpRequest.BodyPublishers.ofString(payload))
            .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.discarding());
    }

    private String basicAuth() {
        String user = JiraConfig.user();
        String token = JiraConfig.token();
        if (user.isBlank() || token.isBlank()) {
            return "";
        }
        String raw = user + ":" + token;
        String encoded = Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
        return "Basic " + encoded;
    }

    private String escape(String value) {
        return value.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r");
    }
}
