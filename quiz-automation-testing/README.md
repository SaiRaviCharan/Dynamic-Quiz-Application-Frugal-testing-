# Quiz Application - Selenium Automation Testing

## 📋 Overview
Complete automation testing suite for the Quiz Application using Selenium WebDriver, TestNG, and ExtentReports.

---

## 🎯 Test Coverage
1. **Landing Page Verification** - URL, title, and UI elements
2. **Quiz Start Functionality** - Start button and first question display
3. **Question Navigation** - Forward/backward navigation and answer selection
4. **Answer Selection** - Direct answer selection for all questions
5. **Quiz Submission** - Submit button and results page navigation
6. **Score Validation** - Correct/incorrect answers and total score verification

---

## 🛠️ Prerequisites

### 1. Install Java Development Kit (JDK)
- **Download**: [Java JDK 11 or higher](https://www.oracle.com/java/technologies/downloads/)
- **Installation Steps**:
  1. Download JDK installer for Windows
  2. Run installer and follow wizard (default settings work fine)
  3. Set JAVA_HOME environment variable:
     - Right-click "This PC" → Properties → Advanced System Settings
     - Environment Variables → New (System Variable)
     - Variable Name: `JAVA_HOME`
     - Variable Value: `C:\Program Files\Java\jdk-11` (adjust to your JDK path)
  4. Add to PATH: `%JAVA_HOME%\bin`
  5. Verify: Open CMD and run `java -version`

### 2. Install Eclipse IDE
- **Download**: [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/)
- **Installation Steps**:
  1. Download Eclipse Installer
  2. Run installer and select "Eclipse IDE for Java Developers"
  3. Choose installation directory
  4. Launch Eclipse and select workspace location

### 3. Install Maven (Optional - Eclipse has built-in Maven)
- Eclipse includes Maven (m2e plugin) by default
- Verify in Eclipse: Help → About Eclipse IDE → Installation Details → Plugins (search for m2e)

### 4. Install Git (for version control)
- **Download**: [Git for Windows](https://git-scm.com/downloads)
- Install with default settings

### 5. Browser Drivers
**No manual download needed!** - This project uses WebDriverManager which automatically downloads and manages browser drivers.

---

## 🚀 Setup Instructions

### Method 1: Import Existing Project (Recommended)

1. **Open Eclipse**
   - Launch Eclipse IDE
   - Select your workspace

2. **Import Maven Project**
   ```
   File → Import → Maven → Existing Maven Projects → Next
   Root Directory: Browse to "quiz-automation-testing" folder
   Click Finish
   ```

3. **Update Maven Project**
   - Right-click on project → Maven → Update Project
   - Check "Force Update of Snapshots/Releases"
   - Click OK

4. **Wait for Dependencies**
   - Maven will automatically download all dependencies (Selenium, TestNG, etc.)
   - This may take 2-5 minutes depending on internet speed
   - Check progress in bottom-right corner of Eclipse

### Method 2: Create New Project from Scratch

1. **Create Maven Project**
   ```
   File → New → Maven Project
   Check "Create a simple project"
   Group Id: com.quiz
   Artifact Id: quiz-automation-testing
   Finish
   ```

2. **Copy Files**
   - Copy `pom.xml` to project root
   - Copy `src` folder structure
   - Right-click project → Maven → Update Project

---

## 📁 Project Structure
```
quiz-automation-testing/
│
├── src/
│   ├── main/java/com/quiz/
│   │   ├── pages/
│   │   │   ├── BasePage.java           # Base page with common methods
│   │   │   ├── LandingPage.java        # Landing page elements & actions
│   │   │   ├── QuizPage.java           # Quiz page elements & actions
│   │   │   └── ResultsPage.java        # Results page elements & actions
│   │   │
│   │   └── utils/
│   │       ├── DriverManager.java      # WebDriver initialization
│   │       ├── ScreenshotUtil.java     # Screenshot capture utility
│   │       ├── ConfigReader.java       # Configuration reader
│   │       └── ExtentManager.java      # ExtentReports manager
│   │
│   └── test/java/com/quiz/tests/
│       ├── BaseTest.java               # Base test with setup/teardown
│       └── QuizAutomationTest.java     # Main test suite
│
├── test-output/
│   ├── screenshots/                    # Test screenshots
│   ├── reports/                        # HTML test reports
│   └── logs/                           # Test execution logs
│
├── pom.xml                             # Maven dependencies
├── testng.xml                          # TestNG configuration
├── config.properties                   # Test configuration
└── README.md                           # This file
```

---

## ⚙️ Configuration

### config.properties
```properties
# Application URL (update this to your quiz app URL)
app.url=file:///S:/Quiz%20Application%20Frugal%20Task/quiz-app/index.html

# Browser (chrome, firefox, edge)
browser=chrome

# Timeouts (in seconds)
implicit.wait=10
explicit.wait=15
page.load.timeout=30

# Screenshots
screenshot.on.failure=true
screenshot.on.success=true

# Test Data
quiz.category=ai
quiz.difficulty=easy
```

---

## ▶️ Running Tests

### Option 1: Run from Eclipse (Easiest)

1. **Right-click on `QuizAutomationTest.java`**
   - Run As → TestNG Test

2. **Or right-click on `testng.xml`**
   - Run As → TestNG Suite

3. **View Results**
   - TestNG Results tab appears at bottom
   - Green = Pass, Red = Fail
   - HTML report generated in `test-output/reports/`

### Option 2: Run from Command Line

1. **Open Command Prompt**
   ```cmd
   cd "S:\Quiz Application Frugal Task\quiz-automation-testing"
   ```

2. **Execute Tests**
   ```cmd
   mvn clean test
   ```

3. **View Reports**
   - Open `test-output/reports/ExtentReport.html` in browser

### Option 3: Run with Batch Script

1. **Double-click** `run-tests.bat` (in project root)
2. Tests will execute and report will open automatically

---

## 📊 Test Reports

### 1. ExtentReports (HTML)
- **Location**: `test-output/reports/ExtentReport_[timestamp].html`
- **Features**:
  - Dashboard with pass/fail statistics
  - Step-by-step test execution details
  - Embedded screenshots
  - Execution time and timestamps
  - Browser and environment details

### 2. TestNG Reports
- **Location**: `test-output/index.html`
- **Features**:
  - Test suite summary
  - Failed test details
  - Execution timeline

### 3. Screenshots
- **Location**: `test-output/screenshots/`
- **Naming**: `[TestName]_[Step]_[Timestamp].png`
- **Captured At**:
  - Landing page load
  - Quiz start
  - Each question display
  - Timer display
  - Results page
  - Any test failure

### 4. Logs
- **Location**: `test-output/logs/test-execution.log`
- **Contains**:
  - All Selenium interactions
  - Test execution flow
  - Error messages and stack traces
  - WebDriver commands

---

## 🎥 Screen Recording

### Windows Built-in (Xbox Game Bar)
1. **Enable**: Windows + G
2. **Record**: Windows + Alt + R (start/stop)
3. **Location**: Videos → Captures folder

### Alternative Tools
- **OBS Studio** (Free): https://obsproject.com/
- **ShareX** (Free): https://getsharex.com/
- **Camtasia** (Paid): https://www.techsmith.com/video-editor.html

---

## 🐛 Troubleshooting

### Issue: Maven dependencies not downloading
**Solution**:
```
Right-click project → Maven → Update Project → Force Update
```
Or delete `.m2/repository` folder and update again

### Issue: WebDriver not found
**Solution**:
WebDriverManager handles this automatically. Ensure internet connection is active.

### Issue: Cannot find quiz application
**Solution**:
Update `app.url` in `config.properties` to correct path or URL

### Issue: Tests running too fast
**Solution**:
Increase wait times in `config.properties` or add explicit waits in test code

### Issue: Browser not opening
**Solution**:
1. Check if Chrome is installed
2. Try different browser in config: `browser=firefox`
3. Check Windows Firewall settings

### Issue: ClassNotFoundException
**Solution**:
```
Right-click project → Properties → Java Build Path → Libraries
Ensure JRE System Library is present
```

---

## 📝 Test Execution Checklist

- [ ] Java JDK installed and JAVA_HOME set
- [ ] Eclipse IDE installed
- [ ] Project imported and Maven dependencies downloaded
- [ ] config.properties updated with correct quiz URL
- [ ] Chrome browser installed
- [ ] Quiz application running/accessible
- [ ] Screen recording tool ready
- [ ] Execute tests from Eclipse
- [ ] Verify all test steps pass
- [ ] Check ExtentReport.html for detailed results
- [ ] Collect screenshots from test-output/screenshots
- [ ] Save screen recording
- [ ] Review logs for any issues

---

## 📤 Submission Package

Your submission should include:

1. **Screen Recording** (upload to Google Drive/OneDrive)
   - Full test execution from start to finish
   - Clear visibility of browser actions

2. **Test Report** (HTML file)
   - `test-output/reports/ExtentReport_[timestamp].html`

3. **Screenshots Folder**
   - All screenshots from `test-output/screenshots/`

4. **Logs**
   - `test-output/logs/test-execution.log`

5. **GitHub Repository**
   - Complete `quiz-automation-testing` folder
   - Include README.md with setup instructions

6. **Test Summary Document** (Optional but recommended)
   - Overview of test scenarios
   - Pass/Fail summary
   - Known issues or observations

---

## 🔗 Additional Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Tutorial](https://testng.org/doc/documentation-main.html)
- [Eclipse Maven Plugin](https://www.eclipse.org/m2e/)
- [ExtentReports Documentation](https://www.extentreports.com/)

---

## 👨‍💻 Support

If you encounter any issues:
1. Check the Troubleshooting section above
2. Review logs in `test-output/logs/`
3. Verify all prerequisites are installed correctly
4. Ensure quiz application is accessible at configured URL

---

## 📄 License

This automation framework is created for testing the Quiz Application.

---

**Happy Testing! 🚀**
