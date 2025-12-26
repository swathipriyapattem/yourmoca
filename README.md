# YourMoca Playwright Automation Project

This project is a test automation framework for "YourMoca" using **Playwright** with **Java** and **TestNG**, following the **Page Object Model (POM)** design pattern.

## Project Structure

- **src/main/java/com/yourmoca/pages**: Contains Page Object classes representing the web pages.
- **src/test/java/com/yourmoca/tests**: Contains Test classes.

## Prerequisites

- Java 11 or higher
- Maven

## How to Run Tests

You can run the tests using Maven:

```bash
mvn test
```

This will execute the tests defined in `testng.xml`.

## Pages and Tests

The project currently contains the following pages and corresponding tests (stubs):

1.  SignUp
2.  Login
3.  ForgotPassword
4.  HomePage
5.  AllCategories
6.  FindWork
7.  CastingCalls
8.  Competitions
