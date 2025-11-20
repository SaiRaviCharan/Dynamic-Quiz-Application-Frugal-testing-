#!/bin/bash
# Quiz Application - Test Execution Script (Linux/Mac)

echo "================================================"
echo "  Quiz Application - Automation Test Execution"
echo "================================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "[ERROR] Maven is not installed or not in PATH"
    echo "Please install Maven or run tests from Eclipse/IntelliJ"
    exit 1
fi

echo "[INFO] Maven detected"
echo "[INFO] Starting test execution..."
echo ""

# Run Maven tests
mvn clean test

echo ""
echo "================================================"
echo "  Test Execution Completed"
echo "================================================"
echo ""

# Check if report was generated
if ls test-output/reports/*.html 1> /dev/null 2>&1; then
    echo "[SUCCESS] Test report generated successfully"
    
    # Get the latest report
    latest_report=$(ls -t test-output/reports/*.html | head -1)
    
    echo "[INFO] Report location: $latest_report"
    
    # Open report in default browser (works on macOS)
    if [[ "$OSTYPE" == "darwin"* ]]; then
        open "$latest_report"
    # Open report in default browser (works on Linux with xdg-open)
    elif command -v xdg-open &> /dev/null; then
        xdg-open "$latest_report"
    fi
else
    echo "[WARNING] No test report found"
fi

echo ""
echo "Screenshots location: test-output/screenshots/"
echo "Reports location: test-output/reports/"
echo ""
