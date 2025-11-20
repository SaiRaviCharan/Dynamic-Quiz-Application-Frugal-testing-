package com.quiz.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration Reader Utility
 * Reads configuration from config.properties file
 */
public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getAppUrl() {
        return getProperty("app.url");
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout"));
    }

    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getProperty("screenshot.on.failure"));
    }

    public static boolean isScreenshotOnSuccess() {
        return Boolean.parseBoolean(getProperty("screenshot.on.success"));
    }

    public static String getScreenshotPath() {
        return getProperty("screenshot.path");
    }

    public static String getReportPath() {
        return getProperty("report.path");
    }

    public static String getReportName() {
        return getProperty("report.name");
    }

    public static String getQuizCategory() {
        return getProperty("quiz.category");
    }

    public static String getQuizDifficulty() {
        return getProperty("quiz.difficulty");
    }

    public static int getExpectedQuestionCount() {
        return Integer.parseInt(getProperty("expected.question.count"));
    }

    public static int getExpectedTimerDuration() {
        return Integer.parseInt(getProperty("expected.timer.duration"));
    }
}
