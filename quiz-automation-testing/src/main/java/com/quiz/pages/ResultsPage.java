package com.quiz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Results Page Object
 * Contains elements and actions for the quiz results page
 */
public class ResultsPage extends BasePage {

    // Page Elements
    @FindBy(css = ".results-header h1")
    private WebElement resultsHeader;

    @FindBy(id = "scoreValue")
    private WebElement scoreValue;

    @FindBy(css = ".score-label")
    private WebElement scoreLabel;

    @FindBy(id = "correctCount")
    private WebElement correctCount;

    @FindBy(id = "incorrectCount")
    private WebElement incorrectCount;

    @FindBy(id = "totalCount")
    private WebElement totalCount;

    @FindBy(id = "totalTime")
    private WebElement totalTime;

    @FindBy(id = "performanceSummary")
    private WebElement performanceSummary;

    @FindBy(id = "answerChart")
    private WebElement answerChart;

    @FindBy(id = "timeChart")
    private WebElement timeChart;

    @FindBy(css = ".breakdown-item")
    private List<WebElement> breakdownItems;

    @FindBy(css = "button[onclick='restartQuiz()']")
    private WebElement restartQuizButton;

    @FindBy(css = ".breakdown-item.correct")
    private List<WebElement> correctAnswers;

    @FindBy(css = ".breakdown-item.incorrect")
    private List<WebElement> incorrectAnswers;

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions

    public boolean isResultsPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOf(resultsHeader)).isDisplayed();
    }

    public String getResultsHeader() {
        return resultsHeader.getText();
    }

    public String getScoreValue() {
        return scoreValue.getText();
    }

    public int getScorePercentage() {
        String score = getScoreValue().replace("%", "");
        return Integer.parseInt(score);
    }

    public String getScoreLabel() {
        return scoreLabel.getText();
    }

    public int getCorrectCount() {
        return Integer.parseInt(correctCount.getText());
    }

    public int getIncorrectCount() {
        return Integer.parseInt(incorrectCount.getText());
    }

    public int getTotalCount() {
        return Integer.parseInt(totalCount.getText());
    }

    public String getTotalTime() {
        return totalTime.getText();
    }

    public String getPerformanceSummary() {
        return performanceSummary.getText();
    }

    public boolean isAnswerChartDisplayed() {
        return answerChart.isDisplayed();
    }

    public boolean isTimeChartDisplayed() {
        return timeChart.isDisplayed();
    }

    public int getBreakdownItemsCount() {
        return breakdownItems.size();
    }

    public List<WebElement> getBreakdownItems() {
        return breakdownItems;
    }

    public int getCorrectAnswersCount() {
        return correctAnswers.size();
    }

    public int getIncorrectAnswersCount() {
        return incorrectAnswers.size();
    }

    public String getBreakdownItemText(int index) {
        if (index < breakdownItems.size()) {
            return breakdownItems.get(index).getText();
        }
        return "";
    }

    public boolean isRestartQuizButtonDisplayed() {
        return restartQuizButton.isDisplayed();
    }

    public void clickRestartQuiz() {
        wait.until(ExpectedConditions.elementToBeClickable(restartQuizButton));
        restartQuizButton.click();
    }

    /**
     * Verify score calculation is correct
     */
    public boolean verifyScoreCalculation() {
        int correct = getCorrectCount();
        int total = getTotalCount();
        int expectedScore = (int) Math.round((double) correct / total * 100);
        int actualScore = getScorePercentage();
        
        return expectedScore == actualScore;
    }

    /**
     * Verify breakdown matches score
     */
    public boolean verifyBreakdownMatchesScore() {
        int correctFromScore = getCorrectCount();
        int incorrectFromScore = getIncorrectCount();
        int correctFromBreakdown = getCorrectAnswersCount();
        int incorrectFromBreakdown = getIncorrectAnswersCount();
        
        return (correctFromScore == correctFromBreakdown) && 
               (incorrectFromScore == incorrectFromBreakdown);
    }
}
