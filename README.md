# Introduction

UserCycleManagement is a Spring Boot Java REST API application developed to operate within a microservice architecture.
It utilizes a cloud-based MongoDB (version 7.0.12) for data management, focusing on three primary collections: cycle, user, and dailyUsage.

# Overview

UserCycleManagement offers APIs for managing mobile phone usage and customer billing cycles. It's designed to efficiently handle large volumes of data, with the capability to manage millions of documents. The APIs enable querying of both current and historical usage data, as well as managing user information.

# Prerequisites

The project is built on Java and Spring Boot and is containerised on docker for scalability and easy deployment.
Here is the list of prerequisites to run the project:

- **Java 18** - Ensure you have Java 18 installed on your machine. You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk18-downloads.html).
- **Gradle Wrapper** - The project uses the Gradle build tool. The gradle wrapper scripts enable to run the project without pre-installed gradle setup. 
- **Spring Boot** - The project is built on Spring Boot.
- **Docker** - The project is containerised on Docker. Ensure you have Docker installed on your machine. You can download it from [here](https://www.docker.com/products/docker-desktop).
- **MongoDB** - The project uses MongoDB as the database. You can use a cloud-based MongoDB service like MongoDB Atlas or install MongoDB locally on your machine. You can download MongoDB from [here](https://www.mongodb.com/try/download/community).

# Installation and Setup

Follow these steps to clone the application and run it on Docker:

1. **Clone the Repository**

Open your terminal and navigate to the directory where you want to clone the project. Then, run the following command:

    git clone git@github.com:vatsa28/userCycleManagement.git
2. **Navigate to the Project Directory**
   
After cloning the repository, navigate to the project directory:

    cd userCycleManagement

3. **Set Environment Variables**

In the existing environment before doing gradle build, please set the following environment variables:

    export DBNAME=<mongoDbName>
    export MONGO_USERNAME=<mongoDbUsername>
    export MONGO_PASSWORD=<mongoDbPassword>

4. **Build the Project with Gradle** 

The project uses Gradle as a build tool. You can use the Gradle Wrapper included in the project to build it. Run the following command:

    ./gradlew clean build (or)
    ./gradlew clean build -x test (without Unit Tests)

5. **Build the Docker Image**
   
Before you can run the application on Docker, you need to build a Docker image of the application. Make sure you have Docker installed on your machine. Then, run the following command:

    docker build -t usercyclemanagement .

6. **Run the Docker Container**

First, get the db details ready, i.e DBName, MONGO_USERNAME, MONGO_PASSWORD denoting the MongoDB database name, username, and password respectively for the MongoDB Cloud resource. 
After the Docker image has been built, you can run it with the following command:

    docker run -p 8080:8080 -e "DBNAME=<mongoDbName>" -e "MONGO_USERNAME=<mongoDbUsername>" -e "MONGO_PASSWORD=<mongoDbPassword>" usercyclemanagement

This command runs the Docker image and maps the application's port (8080) to the same port on your machine.

7. **Access the Application**

Once the Docker container is running, you can access the application by opening a web browser and navigating to:

    http://localhost:8080/swagger-ui/index.html

# Database Design

## Schema Design

The database schema consists of three primary collections: cycle, user, and dailyUsage. Here is a brief overview of each collection:

1. **Cycle Collection** - The cycle collection stores information about the billing cycle for each user. It includes fields such as id, mdn, startDate, endDate, and userId.
2. **User Collection** - The user collection stores information about each user. It includes fields such as is, firstName, lastName, email and password.
3. **DailyUsage Collection** - The dailyUsage collection stores daily usage data for each user. It includes fields such as id, mdn, userId, usageDate, usedInMb.

## Scalability and Performance

1. **Cycle Collection** - Assumed to have 10 million documents.
2. **User Collection** - Assumed to have 1 million documents.
3. **DailyUsage Collection** - Assumed to have 100 million documents.

The database schema is designed to handle large volumes of data efficiently. 
Indexes are created on fields that are frequently queried. This helps to improve query performance and reduce the time taken to fetch data.
Here are details of the indexes created on each collection:

1. **Cycle Collection** - Compound Index on userId and mdn fields.
2. **User Collection** - Index on email field.
3. **DailyUsage Collection** - Compound Index on userId, mdn and usageDate fields.

All the collections are indexed on id field which is automatically generated by MongoDB for efficient querying.

# API Documentation

The detailed API documentation is available [here](./API_DOC.md).

# Testing

This document provides an overview of the testing strategy employed in this Spring Boot project. The testing strategy includes:

1. **Unit Tests with Mock Data**: To test individual components in isolation, focusing on the logic within controllers, services, and other classes.

2. **Data Tests with Embedded MongoDB Server**: To validate the functionality of the data layer, ensuring proper interaction with MongoDB without requiring an external MongoDB instance. Using an annotation-based approach achieved by @DataMongoTest.

The tests are located in the `src/test/java` directory.
To run the tests, you can use the following command after setting up the above mentioned environment variables:

    ./gradlew test

The tests are also run as part of the CI pipeline on Github Actions.

# Github Actions

The project is integrated with Github Actions for Continuous Integration.
The CI pipeline runs the Gradle build and tests on every push to the repository. 
The pipeline is defined in the `.github/workflows/ci.yml` file.

# Design Philosophy

The project is designed with scalability, performance, and maintainability in mind. Here are some key design principles:

### 1. **Modularity and Separation of Concerns**

The service is structured to separate concerns clearly:
- **Controller Layer:** Manages incoming HTTP requests, handles validation, and routes the requests to the appropriate service methods.
- **Service Layer:** Contains the core business logic for user operations such as creating and updating user profiles.
- **Data Layer (not shown in the code snippet):** Would be responsible for interacting with the database to persist and retrieve user data

This separation ensures that each layer has a single responsibility, making the system easier to maintain, test, and extend.

### 2. **Scalability and Flexibility**

The system is designed with scalability in mind:
- **Microservices Architecture:** The User Cycle Management Service is part of a microservices-based architecture. It can be easily integrated with other services to build a scalable and modular system.
- **RESTful API Design:** The service exposes RESTful APIs, which are stateless, making the service more scalable and easier to cache responses.

### 3. **Validation and Error Handling**

Input validation is handled at the controller level using annotations like `@Valid`, ensuring that only valid data is passed to the service layer. This approach simplifies error handling and provides immediate feedback to the client if any input is invalid.

The service also includes comprehensive error handling to cover scenarios such as invalid requests, resource not found, and internal server errors. This approach ensures that the system is robust and can gracefully handle unexpected situations.

### 4. **Extensibility**

The design allows for easy extension in the future:
- **Adding New Endpoints:** New user-related operations (e.g., deleting a user, retrieving user details) can be added with minimal changes to the existing codebase.
- **Integration with Other Services:** The service can easily integrate with other microservices or third-party services (e.g., authentication services, notification services) by extending the service layer or adding new services.

### 5. **Testing and Quality Assurance**

The service is designed with testing in mind:
- **Unit Tests:** The controller and service layers can be easily unit tested by mocking dependencies, ensuring that each component works as expected.
- **Integration Tests:** The service can be tested end-to-end using integration tests that verify the interaction between different components.
- **Continuous Integration:** The service is integrated with a CI/CD pipeline to automate testing and deployment, ensuring that the codebase is always in a deployable state.

# Future Scope for Improvements

1. **Reactive Programming**: Implementing reactive programming with Mono or Spring WebFlux to handle large volumes of data more efficiently and improve performance.
2. **Sharding**: Implementing sharding to distribute data across multiple MongoDB instances for horizontal scaling.
3. **Data Archiving**: Implementing data archiving mechanisms to move historical data to a separate storage system and reduce the load on the database.
4. **Caching**: Implementing caching mechanisms to reduce the load on the database and improve response times.
5. **Monitoring**: Implementing monitoring tools like Prometheus and Grafana to monitor the application's performance and health.
6. **Load Testing**: Conducting load testing to identify bottlenecks and optimize the application for scalability.
