# Complete Installation Guide for Quiz Automation Testing

## 📥 Step-by-Step Installation Process

### Step 1: Install Java JDK (Required)

#### Option A: Eclipse Temurin (Recommended - Free & Open Source)
1. **Download Link:** https://adoptium.net/temurin/releases/
2. **Select:**
   - Operating System: **Windows**
   - Architecture: **x64**
   - Package Type: **JDK**
   - Version: **17 (LTS)** or **11 (LTS)**
3. **Download:** Click the `.msi` installer
4. **Install:**
   - Run the downloaded `.msi` file
   - ✅ Check "Set JAVA_HOME variable"
   - ✅ Check "Add to PATH"
   - Click Next → Install
5. **Verify Installation:**
   ```cmd
   java -version
   ```
   Should show: `openjdk version "17.x.x"` or `"11.x.x"`

#### Option B: Oracle JDK (Alternative)
1. **Download:** https://www.oracle.com/java/technologies/downloads/
2. Select **Java 17** or **Java 11**
3. Download Windows installer
4. Run and install with default settings
5. Manually set JAVA_HOME:
   - Press `Windows + Pause` → Advanced System Settings
   - Environment Variables → New (System Variables)
   - Variable Name: `JAVA_HOME`
   - Variable Value: `C:\Program Files\Java\jdk-17` (or your path)
   - Edit Path → New → Add `%JAVA_HOME%\bin`

---

### Step 2: Install Eclipse IDE (Required)

#### Download Eclipse IDE for Java Developers
1. **Download Link:** https://www.eclipse.org/downloads/
2. Click **"Download x86_64"** button
3. Run **eclipse-inst-jre-win64.exe**
4. **Select:** "Eclipse IDE for Java Developers"
5. **Installation Folder:** Default is fine (e.g., `C:\Users\YourName\eclipse\java-2023-12`)
6. Click **Install**
7. Accept licenses
8. **Launch Eclipse**
9. Select workspace location (e.g., `C:\Users\YourName\eclipse-workspace`)

#### Configure Eclipse
1. Open Eclipse
2. **Help → Eclipse Marketplace**
3. Search and Install (if not already installed):
   - ✅ **TestNG for Eclipse** (Important!)
   - ✅ Maven Integration (usually pre-installed)

---

### Step 3: Install Git (Recommended for GitHub)

1. **Download:** https://git-scm.com/download/win
2. Run installer
3. Use **default settings** (or customize if you know what you're doing)
4. Install
5. **Verify:**
   ```cmd
   git --version
   ```

---

### Step 4: Install Chrome Browser (Required for Testing)

1. **Download:** https://www.google.com/chrome/
2. Install normally
3. Chrome is needed because Selenium will automate it

---

### Step 5: Import Testing Project into Eclipse

#### Import the Maven Project
1. **Open Eclipse**
2. **File → Import**
3. Expand **Maven** folder
4. Select **Existing Maven Projects**
5. Click **Next**
6. **Root Directory:** Browse to `S:\Quiz Application Frugal Task\quiz-automation-testing`
7. Ensure `pom.xml` is checked
8. Click **Finish**

#### Wait for Dependencies to Download
- Look at **bottom-right corner** of Eclipse
- You'll see: "Building workspace" or "Downloading dependencies"
- This takes **5-10 minutes** the first time
- Maven is downloading Selenium, TestNG, etc.
- **Don't interrupt this process!**

#### Verify Project Setup
1. Expand project in Package Explorer
2. You should see:
   - 📁 `src/main/java/com/quiz/pages`
   - 📁 `src/main/java/com/quiz/utils`
   - 📁 `src/test/java/com/quiz/tests`
   - 📁 Maven Dependencies (with many JARs)

---

### Step 6: Install TestNG Plugin in Eclipse

1. **Help → Eclipse Marketplace**
2. Search: **"TestNG"**
3. Find **"TestNG for Eclipse"**
4. Click **Install**
5. Accept licenses and restart Eclipse

#### Verify TestNG Installation
1. Right-click any test file
2. You should see: **"Run As → TestNG Test"**
3. If you see this option, TestNG is installed! ✅

---

### Step 7: Configure Quiz URL

1. In Eclipse, open: `config.properties`
2. Update the `app.url` line:
   ```properties
   app.url=file:///S:/Quiz%20Application%20Frugal%20Task/quiz-app/index.html
   ```
3. **Important:**
   - Use forward slashes `/` (not backslashes `\`)
   - Replace spaces with `%20`
   - Example for C drive: `file:///C:/Projects/quiz-app/index.html`
4. Save the file

---

### Step 8: Run Your First Test

1. **Open:** `src/test/java/com/quiz/tests/QuizAutomationTest.java`
2. **Right-click** on the file
3. Select: **Run As → TestNG Test**
4. **Watch the magic happen:**
   - Chrome browser opens automatically
   - Quiz application loads
   - Tests run automatically
   - Screenshots captured
   - Browser closes
   - Report opens in browser

---

## ✅ Installation Checklist

Use this checklist to ensure everything is installed:

- [ ] Java JDK 11 or 17 installed
- [ ] `java -version` command works in CMD
- [ ] JAVA_HOME environment variable set
- [ ] Eclipse IDE installed and launched
- [ ] Git installed (optional but recommended)
- [ ] Chrome browser installed
- [ ] quiz-automation-testing project imported in Eclipse
- [ ] Maven dependencies downloaded (check Maven Dependencies folder)
- [ ] TestNG plugin installed in Eclipse
- [ ] config.properties updated with correct quiz URL
- [ ] Test runs successfully (Run As → TestNG Test)

---

## 🔧 Alternative IDEs (Optional)

### IntelliJ IDEA (Alternative to Eclipse)

If you prefer IntelliJ:

1. **Download:** https://www.jetbrains.com/idea/download/
2. Choose **Community Edition** (Free)
3. Install
4. **Open Project:**
   - File → Open
   - Select `quiz-automation-testing` folder
   - IntelliJ will auto-detect Maven project
5. **Run Tests:**
   - Right-click `QuizAutomationTest.java`
   - Run 'QuizAutomationTest'

### VS Code (Alternative - Lightweight)

1. **Download:** https://code.visualstudio.com/
2. Install extensions:
   - Extension Pack for Java
   - Test Runner for Java
3. Open project folder
4. Run tests from test explorer

---

## 🐛 Common Installation Issues

### Issue 1: "JAVA_HOME is not set"
**Solution:**
1. Open CMD as Administrator
2. Run:
   ```cmd
   setx JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-17.x.x" /M
   ```
3. Restart CMD and Eclipse

### Issue 2: "Maven Dependencies not downloading"
**Solution:**
1. Right-click project → Maven → Update Project
2. Check "Force Update of Snapshots/Releases"
3. Click OK
4. Wait 5-10 minutes

### Issue 3: "Cannot find TestNG"
**Solution:**
1. Help → Eclipse Marketplace
2. Search "TestNG"
3. Install "TestNG for Eclipse"
4. Restart Eclipse

### Issue 4: "Project has build errors"
**Solution:**
1. Right-click project → Properties
2. Java Build Path → Libraries
3. Ensure JRE System Library is present
4. If not, Add Library → JRE System Library → Select JRE

### Issue 5: "Chrome driver not found"
**Solution:**
- This project uses WebDriverManager - NO manual driver needed!
- Just ensure Chrome browser is installed
- WebDriverManager downloads driver automatically

---

## 📦 What Gets Installed

### Java Packages
- **JDK 11/17:** Java Development Kit
- **JRE:** Java Runtime Environment

### Eclipse Packages
- **Eclipse IDE:** Development environment
- **M2E (Maven):** Maven integration (built-in)
- **TestNG Plugin:** Test framework support

### Maven Dependencies (Auto-downloaded)
- Selenium WebDriver 4.15.0
- TestNG 7.8.0
- ExtentReports 5.1.1
- WebDriverManager 5.6.2
- Log4j2 2.21.1
- Apache Commons IO

---

## 🚀 Quick Start After Installation

1. **Open Eclipse**
2. **Import project** (if not already done)
3. **Wait for Maven** to download dependencies
4. **Update** `config.properties` with your quiz URL
5. **Right-click** `QuizAutomationTest.java`
6. **Run As → TestNG Test**
7. **Watch** tests execute
8. **View** HTML report (opens automatically)

---

## 📞 Need Help?

### Verify Java Installation
```cmd
java -version
javac -version
echo %JAVA_HOME%
```

### Verify Maven (in Eclipse)
- Window → Show View → Other → Maven → Maven Repositories
- You should see local repository with downloaded libraries

### Verify Project Structure
In Eclipse Package Explorer, you should see:
```
quiz-automation-testing
├── src/main/java
│   └── com.quiz.pages
│   └── com.quiz.utils
├── src/test/java
│   └── com.quiz.tests
├── Maven Dependencies (50+ JARs)
├── pom.xml
├── testng.xml
└── config.properties
```

---

## 🎯 Next Steps

After installation:
1. ✅ Run test to verify setup
2. 📸 Start screen recording tool (Windows + G)
3. 🧪 Execute all tests
4. 📊 Collect test reports from `test-output/`
5. 📤 Upload to Google Drive
6. 🐙 Push to GitHub

---

## 📚 Download Links Summary

| Software | Link | Purpose |
|----------|------|---------|
| Java JDK | https://adoptium.net/temurin/releases/ | Required - Java Runtime |
| Eclipse IDE | https://www.eclipse.org/downloads/ | Required - Development Tool |
| Git | https://git-scm.com/download/win | Optional - Version Control |
| Chrome | https://www.google.com/chrome/ | Required - Test Browser |

---

**Installation Time Estimate:** 30-45 minutes (including downloads)

**Ready to test?** Follow this guide step-by-step and you'll be running automated tests in no time! 🚀
