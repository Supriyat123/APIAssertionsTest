# OpenLibraryAPITest

A **BDD (Behavior-Driven Development) API Test Automation Framework** built with Java, Cucumber, and JUnit for testing the [Open Library API](https://openlibrary.org/developers/api).

---

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Tech Stack](#tech-stack)
- [Setup & Installation](#setup--installation)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Writing New Tests](#writing-new-tests)
- [CI/CD Integration](#cicd-integration)

---

## Overview

This framework uses Cucumber's Gherkin syntax to write human-readable test scenarios that validate the behavior of the Open Library REST API. Tests are organized as feature files, with step definitions wired to actual HTTP calls.

---

## Project Structure

```
OpenLibraryAPITest/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org.example/          # Utility/helper classes
│   └── test/
│       ├── java/
│       │   ├── runner/
│       │   │   └── TestRunner.java   # Cucumber JUnit test runner
│       │   └── stepdefinitions/
│       │       └── OpenLibrarySteps.java  # Step definition implementations
│       └── resources/
│           └── features/
│               └── open_library.feature  # Gherkin BDD scenarios
├── target/                           # Build output & reports (auto-generated)
├── Jenkinsfile                       # CI/CD pipeline configuration
├── pom.xml                           # Maven dependencies & build config
└── .gitignore
```

---

## Prerequisites

Ensure the following are installed before setting up the project:

- **Java JDK 11+** — [Download](https://adoptium.net/)
- **Apache Maven 3.6+** — [Download](https://maven.apache.org/download.cgi)
- **IntelliJ IDEA** (recommended) or any Java IDE
- Internet access (tests call the live Open Library API)

---

## Tech Stack

| Tool | Purpose |
|------|---------|
| Java | Primary programming language |
| Maven | Build tool and dependency management |
| Cucumber | BDD framework for Gherkin-based test scenarios |
| JUnit | Test runner integration |
| REST Assured / HTTP Client | API request execution (in step definitions) |
| Cucumber HTML/JSON Plugin | Test reporting |

---

## Setup & Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd OpenLibraryAPITest
   ```

2. **Install dependencies**
   ```bash
   mvn clean install -DskipTests
   ```

3. **Open in IntelliJ IDEA**
   - File → Open → Select the project folder
   - Allow Maven to import dependencies automatically

---

## Running Tests

### Run all tests via Maven
```bash
mvn test
```

### Run a specific feature file
```bash
mvn test -Dcucumber.features="src/test/resources/features/open_library.feature"
```

### Run by tag
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

### Run directly from IDE
Right-click `TestRunner.java` → **Run 'TestRunner'**

---

## Test Reports

After a test run, reports are automatically generated in the `target/` directory:

| Report Type | Location |
|-------------|----------|
| HTML Report | `target/cucumber-report.html` |
| JSON Report | `target/cucumber.json` |
| Console (Pretty) | Printed to terminal during run |

Open the HTML report in any browser for a full visual summary of passed/failed scenarios.

---

## Writing New Tests

### 1. Add a scenario in the feature file

`src/test/resources/features/open_library.feature`

```gherkin
Feature: Open Library API

  Scenario: Search for a book by title
    Given the Open Library API is available
    When I search for a book with title "The Great Gatsby"
    Then the response status code should be 200
    And the results should contain at least 1 book
```

### 2. Implement the step definitions

`src/test/java/stepdefinitions/OpenLibrarySteps.java`

```java
@Given("the Open Library API is available")
public void theApiIsAvailable() {
    // setup base URI
}

@When("I search for a book with title {string}")
public void searchByTitle(String title) {
    // make API call
}

@Then("the response status code should be {int}")
public void verifyStatusCode(int statusCode) {
    // assert response
}
```

### 3. Run the tests to validate

```bash
mvn test
```

---

## CI/CD Integration

This project includes a `Jenkinsfile` for automated pipeline execution.

### Jenkins Pipeline Stages

```
Checkout → Build → Run Tests → Publish Reports
```

### Triggering a build

- **Automatic**: On every push to the main branch (if webhook is configured)
- **Manual**: Trigger via Jenkins UI → "Build Now"

Reports are archived as build artifacts and viewable directly in the Jenkins dashboard after each run.

---

## Contributing

1. Create a feature branch: `git checkout -b feature/my-new-test`
2. Write your scenario in the `.feature` file
3. Implement the step definitions
4. Run tests locally to confirm passing: `mvn test`
5. Open a pull request

---

## License

This project is intended for demo purposes against the publicly available [Open Library API](https://openlibrary.org/developers/api).
