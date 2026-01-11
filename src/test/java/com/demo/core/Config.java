package com.demo.core;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Config {
    private Config() {
    }

    public static String baseUrl() {
        return get("base.url", "http://localhost:5173");
    }

    public static String browser() {
        return get("browser", "chrome");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(get("headless", "false"));
    }

    public static String remoteUrl() {
        return get("remote.url", "");
    }

    public static Path downloadDir() {
        return Paths.get(System.getProperty("user.dir"), "target", "downloads");
    }

    private static String get(String key, String fallback) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) {
            return sys;
        }
        String envKey = key.toUpperCase().replace('.', '_');
        String env = System.getenv(envKey);
        if (env != null && !env.isBlank()) {
            return env;
        }
        return fallback;
    }
}
