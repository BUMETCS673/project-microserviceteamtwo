# ProManager - Project Management and Notification System

## CS673 Software Engineering

### Team 2 - ProManager

**Team Members:**

| **Name**            | **Role**                          |
|---------------------|-----------------------------------|
| Dipayan Mazumder    | Security Lead                     | 
| Pranjal Ekhande     | Requirement Lead                  | 
| Aman Jain           | Configuration Lead                | 
| Mukul Jangid        | Design and Implementation Lead    | 
| Praveen Singh       | Team Lead                         | 

## Overview

We are proposing to contribute to a learning platform with a Project Management Module designed to facilitate project-based learning for students and professionals. The platform will allow users to create and manage projects, access learning resources, collaborate with team members, and receive assessments and feedback. The key components include:

- **User Management**
- **Project Management**
- **Collaboration**
- **Notification**

## Proposed High-Level Requirements

### Functional Requirements

**Essential Features:**

- **Project Creation:** Allow users to create and manage projects.
  - Estimated person-hours: 40-50
- **Task Management:** Enable project members to create, update, and track tasks.
  - Estimated person-hours: 60-80
- **Project Access Control:** Allow project owners to manage access and privacy settings.
  - Estimated person-hours: 30-40
- **Project Search and Filtering:** Enable users to find relevant projects easily.
  - Estimated person-hours: 20-30

**Desirable Features:**

- **Integration with Other Services:** Integrate with User Management, Collaboration, and Notification services.
- **Project Templates:** Provide pre-defined templates for common project types.
- **Project Reporting and Analytics:** Offer reports and analytics on project progress and team performance.

**Optional Features:**

1. Advanced reporting and visualization tools.
2. Project portfolio management.
3. Integration with external project management tools or APIs.

### Non-functional Requirements

- **Security Requirements:**
  - Role-based access control (RBAC)
  - Data encryption
  - Input validation and protection against common web vulnerabilities
  - Secure communication using HTTPS and JWT authentication

## Management Plan

### Objectives and Priorities

1. Deliver a fully functional and reliable Project Management Service.
2. Maintain high code quality and test coverage.
3. Ensure proper integration with other services.

## Configuration Management Plan

### Tools

- **Version Control:** Git and GitHub
- **IDE:** SpringBoot STS or IntelliJ
- **CI/CD:** GitHub Actions
- **Dependency Management:** Maven

### Testing

- **JUnit:** Java Testing
- **Postman:** API Testing
- **Manual Testing:** Exploratory, Usability, Regression
- **Automation Testing:** Unit Testing, Integration Testing
