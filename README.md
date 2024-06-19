# ProManager - Project Management MicroService System (CS673 Software Engineering)

ProManager is a project management system developed as part of the CS673 Software Engineering course.


## Team Members

| **Name**            | **Role**                          |
|---------------------|-----------------------------------|
| Dipayan Mazumder    | Security Lead                     | 
| Pranjal Ekhande     | Requirement Lead                  | 
| Aman Jain           | Configuration Lead                | 
| Mukul Jangid        | Design and Implementation Lead    | 
| Praveen Singh       | Team Lead                         | 

## Overview

We have contributed to a learning platform with a Project Management Module designed to facilitate project-based learning for students and professionals. 

The platform will allow users to create and manage projects, access learning resources, collaborate with team members. 

The key components include:
- **Project Management**
- **Task Management**
- **Comment Management**
- **Collaboration**

## Code setup and installation
### Prerequisites

- Java 8 or higher
- Maven
- MySQL

### Setup

1. Clone the repository:
-- git clone
```bash
https://github.com/your-repo/pro-manager.git
```
2. Navigate to the project directory:
```bash
cd pro-manager
```
3. Create a MySQL database and update the database configuration in `src/main/resources/application.properties`.

4. Run the SQL script located in the `code` folder to initialize the database schema and seed data.


### Build and Run

1. Build the project using Maven:
```java
mvn clean install
```
2. Run the Spring Boot application:
```java
mvn spring-boot:run
```
The application will start on `http://localhost:8080`.

Demo Application is hosted on "https://promanager-v1-b1f0addf3fcb.herokuapp.com/apiv1/project/getIdWiseProject/proj_001" this is one of the endpoints of it.

### API Demo

The API endpoints are documented in the `API demo` folder, which contains a Python notebook with examples of REST calls implemented in the application.

### Docker Deployment

To deploy the application using Docker and Heroku, follow these steps:

1. Login to Heroku
```
login heroku
```
2. Build the Docker image:
```
docker build -t registry.heroku.com/promanager-v1/web .
```
2. Push the Docker image to Heroku:
```
docker push registry.heroku.com/promanager-v1/web
```
3. Release the Docker container on Heroku:
```
heroku container:release web -a APPLICATION_NAME
```
4. View the application logs:
```
heroku logs --tail -a promanager-v1
```


## Proposed High-Level Requirements

### Functional Requirements

**Essential Features:**

- **Project Creation:** Allow users to create and manage projects.
  - Estimated person-hours: 80-120
- **Task Management:** Enable project members to create, update, and track tasks.
  - Estimated person-hours: 160-180
- **Project Access Control:** Allow project owners to manage access and privacy settings.
  - Estimated person-hours: 150-200
- **Comment Management:** Adding Reviews and comments to the project.
  - Estimated person-hours: 150-200

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


### Code Coverage

- Integrated JaCoCo for code coverage analysis.
- Current overall code coverage stands at 6%, with continuous efforts to improve this metric.

### Updates

- Integrated JaCoCo for code coverage.
- Written automated and manual test cases to increase code coverage.
- Discussed and established the roadmap for testing.

### Future Work

- Implement CI/CD pipelines.
- Enhance project deployment strategies.
- Increase code coverage to at least 70%.
- Optimize database queries for better performance.

## Contributing

If you would like to contribute to the project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push your changes to your forked repository.
5. Create a pull request against the main repository.

## License

This project is licensed under the [MIT License](LICENSE).
