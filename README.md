# Test‑Automation‑Web‑UI

Comprehensive web UI test automation framework built with **Katalon Studio** and **Gradle**, featuring data‑driven testing, modular design, and custom keywords.

---

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Prerequisites](#prerequisites)
4. [Installation & Setup](#installation--setup)
5. [Project Structure](#project-structure)
6. [Configuration & Profiles](#configuration--profiles)
7. [Writing & Organizing Test Cases](#writing--organizing-test-cases)
8. [Data‑Driven Testing](#data‑driven-testing)
9. [Custom Keywords & Scripts](#custom-keywords--scripts)
10. [Execution](#execution)
11. [Reporting & Logs](#reporting--logs)
12. [Contributing](#contributing)
13. [License](#license)

---

## Overview

This framework automates functional and regression testing of web applications using:

* **Katalon Studio** for test design and execution.
* **Groovy** scripts for custom keywords.
* **Gradle** and the Katalon Gradle plugin for headless, CI‑friendly runs.
* **Excel/CSV/JSON** for data‑driven scenarios.

Use this framework to accelerate test cycles, enforce consistency, and integrate smoothly with your CI/CD pipelines.

---

## Features

* **Modular Test Suites**: Organize tests by feature, module, or release.
* **Data‑Driven**: Externalize test data to Excel, CSV, or JSON.
* **Custom Keywords**: Reusable actions (login, navigation, assertions).
* **Environment Profiles**: Switch endpoints and credentials easily.
* **Reporting**: Generate HTML, JUnit XML, and PDF reports.
* **CI/CD Ready**: Out‑of‑the‑box Gradle scripts for Jenkins/GitHub Actions.

---

## Prerequisites

* **Java JDK 8 or 11** (set `JAVA_HOME`)
* **Katalon Studio 8.x+**
* **Gradle** (wrapper included)
* **Git**

Optional:

* **Docker** (for containerized test agents)
* **Allure** (for advanced reporting)

---

## Installation & Setup

1. **Clone the repository**:

   ```bash
   git clone https://github.com/zuchalbastian/Test-Automation-Web-UI.git
   cd Test-Automation-Web-UI
   ```
2. **Verify Java & Gradle**:

   ```bash
   java -version
   ./gradlew -v
   ```
3. **Import into Katalon Studio**:

   * Open Katalon Studio → **File → Open Project** → select the project folder.
4. **Install Dependencies** (if any custom plugins) via **Project → Settings → External Libraries**.

---

## Project Structure

```plaintext
Test-Automation-Web-UI/
├── Data Files/              # Excel, CSV, JSON for data-driven tests
├── Include/                 # Global variables, configs, utilities
│   └── config/
├── Object Repository/       # Web element locators
├── Profiles/                # Environment-specific profiles
├── Scripts/                 # Custom keywords (Groovy)
├── Test Cases/              # Individual test cases
├── Test Suites/             # Suites grouping test cases
├── Test Suites Collection/  # Parallel or sequential suite collections
├── settings/                # Katalon project settings
├── build.gradle             # Gradle build & plugin config
├── gradle.properties       # Gradle properties (e.g. GC options)
├── console.properties       # CLI overrides (browser, report format)
├── README.md                # This documentation
└── LICENSE                  # License
```

---

## Configuration & Profiles

* **Profiles/**: define variables per environment (`default`, `staging`, `production`).
* Activate in Katalon: **Profiles → Activate Profile**.
* Override via CLI:

  ```bash
  -Pprofile=staging
  ```
* **Global Variables**: in `Include/config/globalVars.groovy`.

---

## Writing & Organizing Test Cases

* **Naming Convention**: `TC_[Feature]_[Scenario]_[Priority]` (e.g., `TC_Login_InvalidPassword_P1`).
* **Folder Structure**: group by module (Login, Dashboard, Checkout).
* **Preconditions & TearDown**:

  * Use **Setup** and **TearDown** methods in Test Suite.
* **Assertions**:

  ```groovy
  WebUI.verifyElementText(findTestObject('Object Repository/Login/msg_Error'), 'Invalid credentials')
  ```

---

## Data‑Driven Testing

1. Place your data files in **Data Files/**:

   * Excel (`.xlsx`), CSV (`.csv`), JSON (`.json`).
2. In a Test Case, link the data file via **Data Binding**:
   ![Data Binding Screenshot](docs/images/data_binding.png)
3. Iterate tests:

   ```groovy
   for (row = 1; row <= findTestData('Data Files/Data Test').getRowNumbers(); row++) {
       username = findTestData('Data Files/Data Test').getValue(1, row)
       password = findTestData('Data Files/Data Test').getValue(2, row)
       // call login keyword
   }
   ```

---

## Custom Keywords & Scripts

* Location: **Scripts/**
* Example: `LoginKeywords.groovy`

  ```groovy
  package customKeywords

  import com.kms.katalon.core.annotation.Keyword
  import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

  class LoginKeywords {
      @Keyword
      def login(String url, String user, String pass) {
          WebUI.openBrowser(url)
          WebUI.setText(findTestObject('Login/txt_Username'), user)
          WebUI.setEncryptedText(findTestObject('Login/txt_Password'), pass)
          WebUI.click(findTestObject('Login/btn_Login'))
      }
  }
  ```

---

## Execution

### Katalon Studio GUI

1. Select a Test Suite → **Run** → choose target browser.
2. Monitor execution in **Log Viewer**.
3. Find reports under **Reports/**.

### Gradle CLI

Run headlessly with:

```bash
./gradlew katalonExecute \
  -PkatalonProjectPath="${PWD}" \
  -PkatalonDistributionUrl="https://release-katalon-studio.s3.amazonaws.com/Katalon_Studio_8.3.0/Katalon_Studio_8.3.0.zip" \
  -PbrowserType="Chrome" \
  -Pprofile="staging"
```

Key Properties:

* `katalonProjectPath`: project root
* `katalonDistributionUrl`: download link for Katalon ZIP
* `browserType`: `Chrome`, `Firefox`, etc.
* `profile`: environment profile

## Reporting & Logs

* Reports generated per suite at:

  ```
  Reports/<SuiteName>/<yyyyMMdd_HHmmss>/
  ```
* Formats: `JUnit XML`, `HTML`, `PDF` (via Allure plugin).
* **Log Files**: `.log` and `.trc` under same directory.

---

## Contributing

1. Fork the repository
2. Create a branch (`feature/<name>`)
3. Commit changes & push
4. Open a Pull Request detailing your modifications.

---

## License

Distributed under the [MIT License](LICENSE).

---

*Generated with ❤️ by Zuchal Ari Bastian*
