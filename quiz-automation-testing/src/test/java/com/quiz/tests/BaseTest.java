package com.quiz.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.quiz.pages.LandingPage;
import com.quiz.pages.QuizPage;
import com.quiz.pages.ResultsPage;
import com.quiz.utils.ConfigReader;
import com.quiz.utils.DriverManager;
import com.quiz.utils.ExtentManager;
import com.quiz.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * Base Test Class
 * Contains setup, teardown, and common test utilities
 */
public class BaseTest {
    protected WebDriver driver;
    protected LandingPage landingPage;
    protected QuizPage quizPage;
    protected ResultsPage resultsPage;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupSuite() {
        extent = ExtentManager.getInstance();
        System.out.println("\n==============================================");
        System.out.println("Quiz Application Test Suite Starting...");
        System.out.println("==============================================\n");
    }

    @BeforeMethod
    public void setup() {
        // Initialize WebDriver
        DriverManager.setDriver(ConfigReader.getBrowser());
        driver = DriverManager.getDriver();

        // Initialize Page Objects
        landingPage = new LandingPage(driver);
        quizPage = new QuizPage(driver);
        resultsPage = new ResultsPage(driver);

        // Navigate to application
        driver.get(ConfigReader.getAppUrl());
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        // Handle test result
        if (result.getStatus() == ITestResult.FAILURE) {
            // Capture screenshot on failure
            String screenshotPath = ScreenshotUtil.captureFailureScreenshot(
                driver, 
                result.getMethod().getMethodName()
            );
            
            if (test != null && screenshotPath != null) {
                test.fail("Test Failed: " + result.getThrowable().getMessage());
                test.addScreenshotCaptureFromPath(screenshotPath);
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            if (ConfigReader.isScreenshotOnSuccess()) {
                String screenshotPath = ScreenshotUtil.captureSuccessScreenshot(
                    driver, 
                    result.getMethod().getMethodName()
                );
                
                if (test != null && screenshotPath != null) {
                    test.pass("Test Passed Successfully");
                    test.addScreenshotCaptureFromPath(screenshotPath);
                }
            }
        }

        // Quit driver
        DriverManager.quitDriver();
        
        // Remove test from ThreadLocal
        ExtentManager.removeTest();
    }

    @AfterSuite
    public void teardownSuite() {
        ExtentManager.flush();
        System.out.println("\n==============================================");
        System.out.println("Quiz Application Test Suite Completed!");
        System.out.println("==============================================\n");
    }

    /**
     * Helper method to log test steps
     */
    protected void logStep(String message) {
        if (test != null) {
            test.info(message);
        }
        System.out.println("  ✓ " + message);
    }

    /**
     * Helper method to capture screenshot
     */
    protected void captureScreenshot(String stepName) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, stepName);
        if (test != null && screenshotPath != null) {
            try {
                test.addScreenshotCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                System.err.println("Failed to add screenshot to report: " + e.getMessage());
            }
        }
    }
}
