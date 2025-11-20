package com.quiz.tests;

import com.quiz.utils.ConfigReader;
import com.quiz.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Quiz Application Automation Test
 * Comprehensive test suite covering all test scenarios
 */
public class QuizAutomationTest extends BaseTest {

    @Test(priority = 1, description = "Verify Landing Page - URL, Title, and UI Elements")
    public void testLandingPageVerification() {
        test = ExtentManager.getInstance().createTest(
            "Test 1: Landing Page Verification",
            "Verify landing page loads correctly with all elements"
        );
        ExtentManager.setTest(test);

        logStep("Navigating to Quiz Application URL");
        String currentUrl = driver.getCurrentUrl();
        logStep("Current URL: " + currentUrl);
        
        String pageTitle = driver.getTitle();
        logStep("Page Title: " + pageTitle);
        
        // Verify page loaded
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page should be loaded");
        logStep("✓ Landing page loaded successfully");
        captureScreenshot("LandingPage_Loaded");

        // Verify brand title
        String brandTitle = landingPage.getBrandTitle();
        Assert.assertEquals(brandTitle, "Quiz Master", "Brand title should be 'Quiz Master'");
        logStep("✓ Brand Title verified: " + brandTitle);

        // Verify hero title is displayed
        String heroTitle = landingPage.getHeroTitle();
        Assert.assertFalse(heroTitle.isEmpty(), "Hero title should not be empty");
        logStep("✓ Hero Title displayed: " + heroTitle);

        // Verify status pill
        String statusText = landingPage.getStatusPillText();
        logStep("✓ Status Pill: " + statusText);

        // Verify UI elements
        Assert.assertTrue(landingPage.isCategoryDropdownDisplayed(), 
            "Category dropdown should be displayed");
        logStep("✓ Category dropdown is displayed");

        Assert.assertTrue(landingPage.isDifficultyDropdownDisplayed(), 
            "Difficulty dropdown should be displayed");
        logStep("✓ Difficulty dropdown is displayed");

        Assert.assertTrue(landingPage.isStartQuizButtonDisplayed(), 
            "Start Quiz button should be displayed");
        logStep("✓ Start Quiz button is displayed");

        captureScreenshot("LandingPage_ElementsVerified");
        test.pass("Landing page verification completed successfully");
    }

    @Test(priority = 2, description = "Start Quiz and Verify First Question Display")
    public void testStartQuizAndFirstQuestion() {
        test = ExtentManager.getInstance().createTest(
            "Test 2: Start Quiz",
            "Click Start Quiz button and verify first question is displayed"
        );
        ExtentManager.setTest(test);

        logStep("Selecting quiz category: " + ConfigReader.getQuizCategory());
        landingPage.selectCategory(ConfigReader.getQuizCategory());
        captureScreenshot("Category_Selected");

        logStep("Selecting quiz difficulty: " + ConfigReader.getQuizDifficulty());
        landingPage.selectDifficulty(ConfigReader.getQuizDifficulty());
        captureScreenshot("Difficulty_Selected");

        logStep("Clicking Start Quiz button");
        landingPage.clickStartQuiz();
        
        // Wait for quiz page to load
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify quiz page loaded
        Assert.assertTrue(quizPage.isQuizPageLoaded(), "Quiz page should be loaded");
        logStep("✓ Quiz page loaded successfully");
        captureScreenshot("QuizPage_Loaded");

        // Verify first question is displayed
        String questionNumber = quizPage.getQuestionNumber();
        Assert.assertTrue(questionNumber.contains("Question 1"), 
            "Should display Question 1");
        logStep("✓ " + questionNumber + " displayed");

        String questionText = quizPage.getQuestionText();
        Assert.assertFalse(questionText.isEmpty(), "Question text should not be empty");
        logStep("✓ Question Text: " + questionText);

        // Verify category and difficulty badges
        String categoryBadge = quizPage.getCategoryBadge();
        String difficultyBadge = quizPage.getDifficultyBadge();
        logStep("✓ Category Badge: " + categoryBadge);
        logStep("✓ Difficulty Badge: " + difficultyBadge);

        captureScreenshot("FirstQuestion_Displayed");
        test.pass("Quiz started successfully and first question displayed");
    }

    @Test(priority = 3, description = "Question Navigation and Answer Selection")
    public void testQuestionNavigationAndAnswerSelection() {
        test = ExtentManager.getInstance().createTest(
            "Test 3: Question Navigation & Answer Selection",
            "Navigate through questions, verify text and options, select answers"
        );
        ExtentManager.setTest(test);

        // Start quiz first
        logStep("Starting quiz with category: " + ConfigReader.getQuizCategory() + 
                ", difficulty: " + ConfigReader.getQuizDifficulty());
        landingPage.startQuiz(ConfigReader.getQuizCategory(), ConfigReader.getQuizDifficulty());
        
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Define answer indices for each question (0-based)
        // Example: selecting answer option index 2 for Q1, 1 for Q2, etc.
        int[] answerIndices = {2, 1, 3, 0, 2}; // Adjust based on your quiz
        int expectedQuestionCount = ConfigReader.getExpectedQuestionCount();

        for (int i = 0; i < expectedQuestionCount; i++) {
            // Verify question number
            String questionNumber = quizPage.getQuestionNumber();
            Assert.assertTrue(questionNumber.contains("Question " + (i + 1)), 
                "Should display Question " + (i + 1));
            logStep("✓ " + questionNumber);

            // Verify question text
            String questionText = quizPage.getQuestionText();
            Assert.assertFalse(questionText.isEmpty(), "Question text should not be empty");
            logStep("  Question: " + questionText);

            // Verify answer options
            int optionsCount = quizPage.getAnswerOptionsCount();
            Assert.assertTrue(optionsCount >= 2, "Should have at least 2 answer options");
            logStep("  Answer options count: " + optionsCount);

            // Log all answer options
            for (int j = 0; j < optionsCount; j++) {
                String optionText = quizPage.getAnswerOptionText(j);
                logStep("    Option " + (j + 1) + ": " + optionText);
            }

            // Verify timer is displayed
            Assert.assertTrue(quizPage.isTimerDisplayed(), "Timer should be displayed");
            String timerValue = quizPage.getTimerValue();
            logStep("  Timer: " + timerValue + " seconds");
            captureScreenshot("Question_" + (i + 1) + "_Display");

            // Select answer
            int answerIndex = answerIndices[i];
            logStep("  Selecting answer option " + (answerIndex + 1));
            quizPage.selectAnswerByIndex(answerIndex);
            
            // Verify answer is selected
            Assert.assertTrue(quizPage.isAnswerSelected(answerIndex), 
                "Answer should be selected");
            logStep("  ✓ Answer option " + (answerIndex + 1) + " selected");
            captureScreenshot("Question_" + (i + 1) + "_Answered");

            // Navigate to next question or submit
            if (i < expectedQuestionCount - 1) {
                if (quizPage.isNextButtonVisible()) {
                    logStep("  Clicking Next button");
                    quizPage.clickNextButton();
                    
                    // Wait for next question to load
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Check navigation status
                    String navStatus = quizPage.getNavigationStatus();
                    if (!navStatus.isEmpty()) {
                        logStep("  Navigation Status: " + navStatus);
                    }
                }
            } else {
                // Last question - verify submit button
                Assert.assertTrue(quizPage.isSubmitButtonVisible(), 
                    "Submit button should be visible on last question");
                logStep("  ✓ Submit button is visible");
            }

            logStep("  ─────────────────────────────────────");
        }

        test.pass("Successfully navigated through all questions and selected answers");
    }

    @Test(priority = 4, description = "Submit Quiz and Navigate to Results")
    public void testSubmitQuiz() {
        test = ExtentManager.getInstance().createTest(
            "Test 4: Submit Quiz",
            "Submit quiz after answering all questions and verify results page loads"
        );
        ExtentManager.setTest(test);

        // Start quiz and answer all questions
        logStep("Starting quiz");
        landingPage.startQuiz(ConfigReader.getQuizCategory(), ConfigReader.getQuizDifficulty());
        
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Answer all questions
        int[] answerIndices = {2, 1, 3, 0, 2};
        logStep("Answering all questions");
        
        for (int i = 0; i < answerIndices.length; i++) {
            quizPage.selectAnswerByIndex(answerIndices[i]);
            logStep("  ✓ Question " + (i + 1) + " answered");
            
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (i < answerIndices.length - 1) {
                if (quizPage.isNextButtonVisible()) {
                    quizPage.clickNextButton();
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Capture screenshot before submit
        captureScreenshot("BeforeSubmit_LastQuestion");

        // Click Submit button
        logStep("Clicking Submit button");
        Assert.assertTrue(quizPage.isSubmitButtonVisible(), "Submit button should be visible");
        quizPage.clickSubmitButton();

        // Wait for results page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify results page loaded
        Assert.assertTrue(resultsPage.isResultsPageLoaded(), "Results page should be loaded");
        logStep("✓ Results page loaded successfully");
        captureScreenshot("ResultsPage_Loaded");

        test.pass("Quiz submitted successfully and results page displayed");
    }

    @Test(priority = 5, description = "Score Calculation and Result Analysis")
    public void testScoreCalculationAndResultAnalysis() {
        test = ExtentManager.getInstance().createTest(
            "Test 5: Score Calculation & Result Analysis",
            "Verify score calculation, correct/incorrect answers, and result breakdown"
        );
        ExtentManager.setTest(test);

        // Start quiz and complete it
        logStep("Starting and completing quiz");
        landingPage.startQuiz(ConfigReader.getQuizCategory(), ConfigReader.getQuizDifficulty());
        
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Answer all questions
        int[] answerIndices = {2, 1, 3, 0, 2};
        quizPage.answerAllQuestions(answerIndices);
        
        // Wait for results to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify results page loaded
        Assert.assertTrue(resultsPage.isResultsPageLoaded(), "Results page should be loaded");
        captureScreenshot("Results_FullView");

        // Get score details
        String scoreValue = resultsPage.getScoreValue();
        int correctCount = resultsPage.getCorrectCount();
        int incorrectCount = resultsPage.getIncorrectCount();
        int totalCount = resultsPage.getTotalCount();
        String totalTime = resultsPage.getTotalTime();

        logStep("Score Details:");
        logStep("  Overall Score: " + scoreValue);
        logStep("  Correct Answers: " + correctCount);
        logStep("  Incorrect Answers: " + incorrectCount);
        logStep("  Total Questions: " + totalCount);
        logStep("  Total Time: " + totalTime);

        // Verify total count matches expected
        Assert.assertEquals(totalCount, ConfigReader.getExpectedQuestionCount(), 
            "Total question count should match");
        logStep("✓ Total question count verified");

        // Verify correct + incorrect = total
        Assert.assertEquals(correctCount + incorrectCount, totalCount, 
            "Correct + Incorrect should equal Total");
        logStep("✓ Answer count calculation verified");

        // Verify score calculation
        Assert.assertTrue(resultsPage.verifyScoreCalculation(), 
            "Score calculation should be correct");
        logStep("✓ Score calculation verified");

        // Verify breakdown matches score
        Assert.assertTrue(resultsPage.verifyBreakdownMatchesScore(), 
            "Breakdown should match score summary");
        logStep("✓ Breakdown matches score summary");

        // Verify charts are displayed
        Assert.assertTrue(resultsPage.isAnswerChartDisplayed(), 
            "Answer distribution chart should be displayed");
        logStep("✓ Answer distribution chart displayed");
        captureScreenshot("Results_AnswerChart");

        Assert.assertTrue(resultsPage.isTimeChartDisplayed(), 
            "Time chart should be displayed");
        logStep("✓ Time per question chart displayed");
        captureScreenshot("Results_TimeChart");

        // Verify performance summary
        String performanceSummary = resultsPage.getPerformanceSummary();
        Assert.assertFalse(performanceSummary.isEmpty(), 
            "Performance summary should be displayed");
        logStep("✓ Performance Summary: " + performanceSummary);

        // Verify breakdown items
        int breakdownCount = resultsPage.getBreakdownItemsCount();
        Assert.assertEquals(breakdownCount, totalCount, 
            "Breakdown should show all questions");
        logStep("✓ Question breakdown displayed for all " + breakdownCount + " questions");
        captureScreenshot("Results_Breakdown");

        // Log breakdown details
        logStep("Question Breakdown:");
        for (int i = 0; i < breakdownCount && i < 3; i++) {
            String breakdownText = resultsPage.getBreakdownItemText(i);
            logStep("  Q" + (i + 1) + ": " + breakdownText.substring(0, Math.min(80, breakdownText.length())) + "...");
        }

        test.pass("Score calculation and result analysis verified successfully");
    }

    @Test(priority = 6, description = "Complete End-to-End Quiz Flow")
    public void testCompleteQuizFlow() {
        test = ExtentManager.getInstance().createTest(
            "Test 6: Complete End-to-End Flow",
            "Comprehensive test covering entire quiz workflow from landing to results"
        );
        ExtentManager.setTest(test);

        // 1. Landing Page
        logStep("STEP 1: Landing Page Verification");
        Assert.assertTrue(landingPage.isPageLoaded(), "Landing page should be loaded");
        logStep("✓ Landing page verified");
        captureScreenshot("E2E_01_Landing");

        // 2. Select Quiz Configuration
        logStep("STEP 2: Select Quiz Configuration");
        landingPage.selectCategory(ConfigReader.getQuizCategory());
        landingPage.selectDifficulty(ConfigReader.getQuizDifficulty());
        logStep("✓ Category: " + landingPage.getSelectedCategory());
        logStep("✓ Difficulty: " + landingPage.getSelectedDifficulty());
        captureScreenshot("E2E_02_Configuration");

        // 3. Start Quiz
        logStep("STEP 3: Start Quiz");
        landingPage.clickStartQuiz();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(quizPage.isQuizPageLoaded(), "Quiz should start");
        logStep("✓ Quiz started successfully");
        captureScreenshot("E2E_03_QuizStarted");

        // 4. Answer Questions
        logStep("STEP 4: Answer All Questions");
        int[] answerIndices = {2, 1, 3, 0, 2};
        
        for (int i = 0; i < answerIndices.length; i++) {
            quizPage.selectAnswerByIndex(answerIndices[i]);
            logStep("  ✓ Answered Question " + (i + 1));
            
            if (i < answerIndices.length - 1) {
                if (quizPage.isNextButtonVisible()) {
                    quizPage.clickNextButton();
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        captureScreenshot("E2E_04_QuestionsAnswered");

        // 5. Submit Quiz
        logStep("STEP 5: Submit Quiz");
        quizPage.clickSubmitButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(resultsPage.isResultsPageLoaded(), "Results should be displayed");
        logStep("✓ Quiz submitted successfully");
        captureScreenshot("E2E_05_Results");

        // 6. Verify Results
        logStep("STEP 6: Verify Results");
        String scoreValue = resultsPage.getScoreValue();
        int correctCount = resultsPage.getCorrectCount();
        int incorrectCount = resultsPage.getIncorrectCount();
        
        logStep("  Score: " + scoreValue);
        logStep("  Correct: " + correctCount);
        logStep("  Incorrect: " + incorrectCount);
        
        Assert.assertTrue(resultsPage.verifyScoreCalculation(), "Score should be calculated correctly");
        logStep("✓ Results verified successfully");
        captureScreenshot("E2E_06_ResultsVerified");

        test.pass("Complete end-to-end quiz flow executed successfully");
    }
}
