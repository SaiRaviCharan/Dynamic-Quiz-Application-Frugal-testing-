@echo off
REM Quiz Application - Test Execution Script
REM This script runs the Selenium automation tests

echo ================================================
echo   Quiz Application - Automation Test Execution
echo ================================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Maven is not installed or not in PATH
    echo Please install Maven or run tests from Eclipse IDE
    pause
    exit /b 1
)

echo [INFO] Maven detected
echo [INFO] Starting test execution...
echo.

REM Run Maven tests
call mvn clean test

echo.
echo ================================================
echo   Test Execution Completed
echo ================================================
echo.

REM Check if report was generated
if exist "test-output\reports\*.html" (
    echo [SUCCESS] Test report generated successfully
    echo [INFO] Opening test report in browser...
    
    REM Find the latest report file
    for /f "delims=" %%f in ('dir /b /od "test-output\reports\*.html"') do set "latest=%%f"
    
    REM Open report in default browser
    start "" "test-output\reports\%latest%"
) else (
    echo [WARNING] No test report found
)

echo.
echo Screenshots location: test-output\screenshots\
echo Reports location: test-output\reports\
echo.

pause
