package com.quiz.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ExtentReports Manager
 * Manages ExtentReports configuration and test reporting
 */
public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static String reportPath;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportName = ConfigReader.getReportName() + "_" + timestamp + ".html";
        reportPath = ConfigReader.getReportPath() + "/" + reportName;

        // Create reports directory if not exists
        File reportDir = new File(ConfigReader.getReportPath());
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        
        // Configuration
        sparkReporter.config().setDocumentTitle("Quiz Application - Test Report");
        sparkReporter.config().setReportName("Selenium Automation Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("UTF-8");
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // System Information
        extent.setSystemInfo("Application", "Quiz Master");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("Browser", ConfigReader.getBrowser());
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Tester", "Automation Team");
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static void removeTest() {
        extentTest.remove();
    }

    public static String getReportPath() {
        return reportPath;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
            System.out.println("\n==============================================");
            System.out.println("Test Report Generated: " + reportPath);
            System.out.println("==============================================\n");
        }
    }
}
