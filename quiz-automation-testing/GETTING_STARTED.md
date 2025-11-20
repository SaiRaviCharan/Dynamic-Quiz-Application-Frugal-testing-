# Quiz Application - Selenium Automation Testing

## Quick Start Guide

### Step-by-Step Installation (For Beginners)

#### 1. Install Java JDK
1. Go to https://www.oracle.com/java/technologies/downloads/
2. Download JDK 11 or higher for Windows
3. Run the installer (keep default settings)
4. After installation, set JAVA_HOME:
   - Press `Windows + Pause` → Advanced System Settings
   - Click "Environment Variables"
   - Under System Variables, click "New"
   - Variable Name: `JAVA_HOME`
   - Variable Value: `C:\Program Files\Java\jdk-11` (or your JDK path)
   - Find "Path" variable → Edit → New → Add `%JAVA_HOME%\bin`
5. Verify: Open Command Prompt and type `java -version`

#### 2. Install Eclipse IDE
1. Go to https://www.eclipse.org/downloads/
2. Download "Eclipse IDE for Java Developers"
3. Run installer and follow wizard
4. Launch Eclipse and create/select workspace

#### 3. Import This Project
1. Open Eclipse
2. File → Import → Maven → Existing Maven Projects
3. Browse to `quiz-automation-testing` folder
4. Click Finish
5. Wait for Maven to download dependencies (5-10 minutes first time)
6. Look for "Building workspace" in bottom-right corner

#### 4. Configure Quiz URL
1. Open `config.properties` file in project root
2. Update this line:
   ```
   app.url=file:///S:/Quiz%20Application%20Frugal%20Task/quiz-app/index.html
   ```
3. Replace with YOUR correct path to index.html
4. Use forward slashes (/) even on Windows
5. Replace spaces with `%20`
6. Save file

#### 5. Run Tests
**Option A: From Eclipse (Easiest)**
1. Right-click on `QuizAutomationTest.java`
2. Run As → TestNG Test
3. Watch browser open and tests execute
4. View results in Console and TestNG Results tab

**Option B: From Command Line**
1. Open Command Prompt
2. Navigate to project folder:
   ```
   cd "S:\Quiz Application Frugal Task\quiz-automation-testing"
   ```
3. Run:
   ```
   mvn clean test
   ```

**Option C: Double-click**
- Just double-click `run-tests.bat` file

#### 6. View Results
- **HTML Report**: Opens automatically in browser
- **Or manually open**: `test-output/reports/QuizAutomationReport_[timestamp].html`
- **Screenshots**: Check `test-output/screenshots/` folder
- **Console Logs**: See Eclipse Console or Command Prompt

---

## Troubleshooting Common Issues

### "Maven dependencies not downloading"
**Solution:**
1. Right-click project → Maven → Update Project
2. Check "Force Update of Snapshots/Releases"
3. Click OK
4. Wait for download to complete

### "Cannot find symbol" errors
**Solution:**
1. Right-click project → Maven → Update Project
2. Project → Clean → Clean all projects
3. Project → Build Automatically (ensure checked)

### "WebDriver not found"
**Solution:**
- This project uses WebDriverManager - no manual driver needed
- Just ensure you have internet connection
- Chrome browser must be installed

### "Quiz application not found"
**Solution:**
1. Update `app.url` in `config.properties`
2. Use full file path like: `file:///C:/Projects/quiz-app/index.html`
3. Replace spaces with `%20`
4. Use forward slashes `/` not backslashes `\`

### Tests fail with "element not found"
**Solution:**
1. Increase wait times in `config.properties`:
   ```
   implicit.wait=15
   explicit.wait=20
   ```
2. Ensure quiz application is working when opened manually

---

## What Each Test Does

### Test 1: Landing Page Verification
- Opens quiz URL
- Prints URL and title to console
- Verifies all UI elements are present
- Takes screenshot of landing page

### Test 2: Start Quiz
- Selects category (AI & Machine Learning)
- Selects difficulty (Foundation/Easy)
- Clicks Start Quiz button
- Verifies first question displays

### Test 3: Question Navigation
- Goes through all 5 questions
- Verifies question text and options
- Selects answer for each question
- Takes screenshot of each question
- Checks timer display

### Test 4: Submit Quiz
- Completes all questions
- Clicks Submit button
- Verifies results page loads

### Test 5: Score Calculation
- Verifies correct/incorrect counts
- Checks total score calculation
- Validates charts are displayed
- Reviews question breakdown

### Test 6: End-to-End Flow
- Complete workflow from start to finish
- All steps in one comprehensive test

---

## Screen Recording Instructions

### Using Windows Game Bar (Built-in)
1. Press `Windows + G` to open Game Bar
2. Click the Record button (circle icon)
3. Or press `Windows + Alt + R` to start/stop
4. Recording saved to: `Videos/Captures` folder

### Using OBS Studio (Better Quality)
1. Download from https://obsproject.com/
2. Install and open OBS
3. Click "+" under Sources → Display Capture
4. Click "Start Recording"
5. Run your tests
6. Click "Stop Recording"
7. Videos saved to: Videos folder (configurable)

---

## Submission Checklist

Before submitting, ensure you have:

- [ ] Screen recording of complete test execution (MP4/AVI format)
- [ ] ExtentReport HTML file from `test-output/reports/`
- [ ] All screenshots from `test-output/screenshots/`
- [ ] Test execution logs (Console output or log files)
- [ ] GitHub repository with complete code
- [ ] README.md with setup instructions
- [ ] Test summary document (optional but recommended)

### Upload Screen Recording
1. Upload video to Google Drive or OneDrive
2. Set sharing to "Anyone with link can view"
3. Copy the share link
4. Include link in submission

---

## Project Structure Explained

```
quiz-automation-testing/
│
├── src/main/java/com/quiz/
│   ├── pages/              ← Page Object Model classes
│   │   ├── BasePage.java   ← Common methods for all pages
│   │   ├── LandingPage.java ← Landing page elements & actions
│   │   ├── QuizPage.java   ← Quiz page elements & actions
│   │   └── ResultsPage.java ← Results page elements & actions
│   │
│   └── utils/              ← Utility classes
│       ├── ConfigReader.java ← Read config.properties
│       ├── DriverManager.java ← WebDriver setup
│       ├── ScreenshotUtil.java ← Screenshot capture
│       └── ExtentManager.java ← Report generation
│
├── src/test/java/com/quiz/tests/
│   ├── BaseTest.java       ← Setup and teardown
│   └── QuizAutomationTest.java ← All test cases
│
├── test-output/            ← Generated during test run
│   ├── screenshots/        ← All screenshots here
│   ├── reports/            ← HTML test reports here
│   └── logs/               ← Execution logs here
│
├── config.properties       ← Configuration settings
├── pom.xml                 ← Maven dependencies
├── testng.xml              ← TestNG configuration
├── run-tests.bat           ← Windows run script
└── README.md               ← This file
```

---

## Dependencies Used

- **Selenium WebDriver 4.15.0** - Browser automation
- **TestNG 7.8.0** - Test framework
- **ExtentReports 5.1.1** - HTML reporting
- **WebDriverManager 5.6.2** - Auto-manage browser drivers
- **Log4j2 2.21.1** - Logging framework
- **Apache Commons IO 2.15.0** - File operations

All dependencies are automatically downloaded by Maven.

---

## Support

If you're stuck:
1. Check "Troubleshooting" section above
2. Verify Java and Eclipse are installed correctly
3. Ensure quiz application path in config.properties is correct
4. Try running tests from Eclipse IDE first
5. Check console output for error messages

---

## Test Report Sample

After running tests, your ExtentReport will show:
- ✅ Total tests passed/failed
- ⏱️ Execution time for each test
- 📸 Screenshots at each step
- 📊 Dashboard with statistics
- 📝 Detailed logs of all actions

---

**Good Luck with Your Testing! 🚀**
