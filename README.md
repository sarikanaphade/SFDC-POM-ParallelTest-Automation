SFCD Automation Project
This repository contains automation tests for the SFCD application, utilizing TestNG for parallel test execution and ExtentReports for detailed HTML reporting.

Setup & Installation
Clone the repo:
git clone https://github.com/yourusername/sfcd-automation.git
cd sfcd-automation

Install dependencies:
mvn clean install


To execute tests:
mvn test

Parallel Execution
Tests are executed in parallel using TestNG. The configuration is set in the testng.xml file (parallel="tests" and thread-count="4").

Reports
Test results are generated in HTML format using ExtentReports and stored under resources/Reports/SFCDReport* folder/index.html.

