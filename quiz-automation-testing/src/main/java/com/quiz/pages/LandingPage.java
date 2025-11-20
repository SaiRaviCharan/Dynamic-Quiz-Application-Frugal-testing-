package com.quiz.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Landing Page Object
 * Contains elements and actions for the quiz landing page
 */
public class LandingPage extends BasePage {

    // Page Elements
    @FindBy(className = "brand-icon")
    private WebElement brandIcon;

    @FindBy(css = ".brand-copy h1")
    private WebElement brandTitle;

    @FindBy(id = "statusPill")
    private WebElement statusPill;

    @FindBy(css = ".hero-title")
    private WebElement heroTitle;

    @FindBy(css = ".hero-subtitle")
    private WebElement heroSubtitle;

    @FindBy(id = "categorySelect")
    private WebElement categoryDropdown;

    @FindBy(id = "difficultySelect")
    private WebElement difficultyDropdown;

    @FindBy(id = "startQuizBtn")
    private WebElement startQuizButton;

    @FindBy(css = ".hero-primary")
    private WebElement browseTracksButton;

    @FindBy(css = ".hero-secondary")
    private WebElement quickLaunchButton;

    @FindBy(css = ".highlight-value")
    private WebElement highlightValues;

    @FindBy(id = "feedbackBanner")
    private WebElement feedbackBanner;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOf(heroTitle)).isDisplayed();
    }

    public String getHeroTitle() {
        return heroTitle.getText();
    }

    public String getHeroSubtitle() {
        return heroSubtitle.getText();
    }

    public String getBrandTitle() {
        return brandTitle.getText();
    }

    public String getStatusPillText() {
        return statusPill.getText();
    }

    public boolean isCategoryDropdownDisplayed() {
        return categoryDropdown.isDisplayed();
    }

    public boolean isDifficultyDropdownDisplayed() {
        return difficultyDropdown.isDisplayed();
    }

    public boolean isStartQuizButtonDisplayed() {
        return startQuizButton.isDisplayed();
    }

    public void selectCategory(String category) {
        wait.until(ExpectedConditions.elementToBeClickable(categoryDropdown));
        Select select = new Select(categoryDropdown);
        select.selectByValue(category);
    }

    public void selectDifficulty(String difficulty) {
        wait.until(ExpectedConditions.elementToBeClickable(difficultyDropdown));
        Select select = new Select(difficultyDropdown);
        select.selectByValue(difficulty);
    }

    public void clickStartQuiz() {
        wait.until(ExpectedConditions.elementToBeClickable(startQuizButton));
        startQuizButton.click();
    }

    public void clickBrowseTracks() {
        wait.until(ExpectedConditions.elementToBeClickable(browseTracksButton));
        browseTracksButton.click();
    }

    public void clickQuickLaunch() {
        wait.until(ExpectedConditions.elementToBeClickable(quickLaunchButton));
        quickLaunchButton.click();
    }

    public String getSelectedCategory() {
        Select select = new Select(categoryDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedDifficulty() {
        Select select = new Select(difficultyDropdown);
        return select.getFirstSelectedOption().getText();
    }

    public boolean isFeedbackBannerDisplayed() {
        try {
            return feedbackBanner.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFeedbackMessage() {
        if (isFeedbackBannerDisplayed()) {
            return feedbackBanner.getText();
        }
        return "";
    }

    /**
     * Start quiz with given category and difficulty
     */
    public void startQuiz(String category, String difficulty) {
        selectCategory(category);
        selectDifficulty(difficulty);
        clickStartQuiz();
    }
}
