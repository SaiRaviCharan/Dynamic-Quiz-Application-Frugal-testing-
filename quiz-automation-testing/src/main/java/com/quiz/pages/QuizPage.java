package com.quiz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Quiz Page Object
 * Contains elements and actions for the quiz question page
 */
public class QuizPage extends BasePage {

    // Page Elements
    @FindBy(id = "categoryBadge")
    private WebElement categoryBadge;

    @FindBy(id = "difficultyBadge")
    private WebElement difficultyBadge;

    @FindBy(id = "questionNumber")
    private WebElement questionNumber;

    @FindBy(id = "questionText")
    private WebElement questionText;

    @FindBy(id = "progressFill")
    private WebElement progressFill;

    @FindBy(css = ".option")
    private List<WebElement> answerOptions;

    @FindBy(id = "prevBtn")
    private WebElement previousButton;

    @FindBy(id = "nextBtn")
    private WebElement nextButton;

    @FindBy(id = "submitBtn")
    private WebElement submitButton;

    @FindBy(id = "timerDisplay")
    private WebElement timerDisplay;

    @FindBy(css = ".timer-section")
    private WebElement timerSection;

    @FindBy(id = "navigationStatus")
    private WebElement navigationStatus;

    public QuizPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions

    public boolean isQuizPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOf(questionText)).isDisplayed();
    }

    public String getCategoryBadge() {
        return categoryBadge.getText();
    }

    public String getDifficultyBadge() {
        return difficultyBadge.getText();
    }

    public String getQuestionNumber() {
        return questionNumber.getText();
    }

    public String getQuestionText() {
        return questionText.getText();
    }

    public int getAnswerOptionsCount() {
        return answerOptions.size();
    }

    public List<WebElement> getAnswerOptions() {
        return answerOptions;
    }

    public String getAnswerOptionText(int index) {
        if (index < answerOptions.size()) {
            return answerOptions.get(index).getText();
        }
        return "";
    }

    public void selectAnswerByIndex(int index) {
        if (index < answerOptions.size()) {
            wait.until(ExpectedConditions.elementToBeClickable(answerOptions.get(index)));
            answerOptions.get(index).click();
        }
    }

    public void selectAnswerByText(String answerText) {
        for (WebElement option : answerOptions) {
            if (option.getText().contains(answerText)) {
                wait.until(ExpectedConditions.elementToBeClickable(option));
                option.click();
                break;
            }
        }
    }

    public boolean isAnswerSelected(int index) {
        if (index < answerOptions.size()) {
            return answerOptions.get(index).getAttribute("class").contains("selected");
        }
        return false;
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
    }

    public void clickPreviousButton() {
        wait.until(ExpectedConditions.elementToBeClickable(previousButton));
        previousButton.click();
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public boolean isNextButtonVisible() {
        try {
            return nextButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPreviousButtonVisible() {
        try {
            return previousButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubmitButtonVisible() {
        try {
            return submitButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTimerValue() {
        return timerDisplay.getText();
    }

    public boolean isTimerDisplayed() {
        return timerSection.isDisplayed();
    }

    public String getNavigationStatus() {
        try {
            if (navigationStatus.isDisplayed()) {
                return navigationStatus.getText();
            }
        } catch (Exception e) {
            // Navigation status not visible
        }
        return "";
    }

    public String getProgressBarWidth() {
        return progressFill.getCssValue("width");
    }

    /**
     * Answer all questions with specified answer indices
     * @param answerIndices Array of answer indices (0-based) for each question
     */
    public void answerAllQuestions(int[] answerIndices) {
        for (int i = 0; i < answerIndices.length; i++) {
            // Wait for question to load
            wait.until(ExpectedConditions.visibilityOf(questionText));
            
            // Select answer
            selectAnswerByIndex(answerIndices[i]);
            
            // Add small delay to ensure selection is registered
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // Navigate to next question or submit
            if (i < answerIndices.length - 1) {
                if (isNextButtonVisible()) {
                    clickNextButton();
                }
            } else {
                if (isSubmitButtonVisible()) {
                    clickSubmitButton();
                }
            }
            
            // Wait for navigation to complete
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
