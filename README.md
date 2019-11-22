# jpmorgan-tech-task

Author: Bernardo Vieira Carneiro

### Description

The project ***backend.automated.tests*** contains automated tests for validating the behavior of the REST API https://jsonplaceholder.typicode.com/. It was built in Java, using Cucumber for test scenario description and TestNG for test execution.

Internally you can find the project organized as follows:

[MODULE backend-tests]
- `src/test/java`: contains the implementation of the tests, step definitions and test classes;
- `src/test/resources`: contains feature files with test scenario descriptions, as well as a properties file which specifies some variables;

[MODULE core]
- `src/main/java`: consists of a simple framework for automating tests in a more reusable and maintainable way;

### How to run the test

- From an IDE of your choice:
Import the project in your preferred IDE, open the class ***JsonPlaceHolderTests.java*** and click the RUN button. You can also right click the mentioned class and choose the option "Run As > TestNG Test".

- From maven:
From a command line tool, access the folder "jpmorgan-tech-task" and then execute the following command: ***mvn clean test***
