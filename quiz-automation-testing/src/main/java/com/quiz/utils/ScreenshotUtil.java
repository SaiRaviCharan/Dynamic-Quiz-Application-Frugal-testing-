package com.quiz.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Screenshot Utility
 * Captures and saves screenshots during test execution
 */
public class ScreenshotUtil {
    
    /**
     * Capture screenshot with custom name
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String screenshotPath = ConfigReader.getScreenshotPath() + "/" + fileName;

        try {
            // Create screenshots directory if not exists
            File screenshotDir = new File(ConfigReader.getScreenshotPath());
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            // Capture screenshot
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath);
            
            FileUtils.copyFile(sourceFile, destinationFile);
            
            System.out.println("Screenshot captured: " + screenshotPath);
            return screenshotPath;
            
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Capture screenshot for failed test
     */
    public static String captureFailureScreenshot(WebDriver driver, String testName) {
        return captureScreenshot(driver, "FAIL_" + testName);
    }

    /**
     * Capture screenshot for passed test
     */
    public static String captureSuccessScreenshot(WebDriver driver, String testName) {
        return captureScreenshot(driver, "PASS_" + testName);
    }
}
