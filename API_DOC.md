# User Management API Documentation

## Overview

This document describes the REST APIs provided by the application.

## Endpoints

### User Endpoints

1. **Create a New User**

| **Attribute** | **Details** |
| --------------|-------------|
| URL | /api/v1/createUser |
| Method | POST |
| Description | Creates a new user with the provided details. |
| Request Body (required) | - firstName (string): First name of the user. <br> - lastName (string): Last name of the user. <br> - email (string): Email of the user. <br> - password (string): Password for the user (min length: 8). |
| Responses | - 201: Successfully created user. Returns the user details. <br> - 400: Invalid input provided. |

2. **Update User**

| **Attribute** | **Details** |
| --------------|-------------|
| URL | /api/v1/updateUser |
| Method | PUT |
| Description | Updates the details of an existing user. |
| Request Body (required) | - userId (string): ID of the user to update. <br> - firstName (string): First name of the user. <br> - lastName (string): Last name of the user. <br> - email (string): Email of the user. |
| Responses | - 200: Successfully updated user. Returns the updated user details. <br> - 400: Invalid input provided. <br> - 404: User not found. |

### Cycle Endpoints

3. Get Cycle History

| **Attribute** | **Details**                                        |
| --------------|----------------------------------------------------|
| URL | /api/v1/getCycleHistory                            |
| Method | GET                                                |
| Description | Retrieves the cycle history with a userId and mdn. |
| Request Parameters | - userId (string): ID of the user. <br> - mdn (string): Mobile Device Number. |
| Responses | - 200: Successfully retrieved cycle history. Returns the cycle details. <br> - 400: Invalid input provided. <br> - 404: Cycle history not found. |

4. Get Daily Usage of Current Cycle

| **Attribute** | **Details**                                                                 |
| --------------|-----------------------------------------------------------------------------|
| URL | /api/v1/getDailyUsage                                                       |
| Method | GET                                                                         |
| Description | Retrieves the daily usage data for the current cycle with a userId and mdn. |
| Request Parameters | - userId (string): ID of the user. <br> - mdn (string): Mobile Device Number. |
| Responses | - 200: Successfully retrieved daily usage data. Returns the usage details. <br> - 400: Invalid input provided. <br> - 404: Daily usage data not found. |

## Assumptions

1. The application relies on JWTs for secure user authentication, provided by an upstream micro-service, to ensure robust and secure operations within the distributed system.
2. If the MDN is reassigned to a different user, the associated cycle and data usage history will remain with the original user. This approach helps mitigate potential security risks by ensuring that the cycle and data usage records are not transferred to the new user.
3. Cycle start and end dates are considered inclusive, and there will be no overlap between the end date of one cycle and the start date of the subsequent cycle.