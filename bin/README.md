# Employee Management System

## Introduction
This project is an Employee Management System that allows users to perform CRUD operations on employees and also generates an email to the respective manager for each newly added employee (make sure that you use a valid mail id).

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- Apache Maven
- MySQL database

## Setup
1. Clone the repository to your local machine:
   git clone https://github.com/your-username/employee-management-system.git
2. Navigate to the project directory:
   cd employee-management-system
3. Configure the database settings in `application.properties` file located in `src/main/resources`:

   spring.datasource.url=jdbc:mysql://localhost:3306/employee_db

   spring.datasource.username=root

   spring.datasource.password=password
5. Run the application:
   mvn spring-boot:run

## API Documentation

### 1. GET All Employees
- URL: /api/v1/getemployees
- Method: GET
- Description: Retrieves a list of all employees.
- Input: None

### 2. POST Save Employee
- URL: /api/v1/saveemployee
- Method: POST
- Description: Saves a new employee.
- Input JSON Structure:

  {

  "employeeName": "John Doe",

  "phoneNumber": "1234567890",

  "email": "john@example.com",

  "reportsTo": "managerId (00000000-0000-0000-0000-000000000000)",

  "profileImage": "http://example.com/profile.jpg"

  }

### 3. PUT Update Employee
- URL: /api/v1/updateemployee/{id}
- Method: PUT
- Description: Updates an existing employee.
- Input JSON Structure:

  {
  
  "id": "employeeId",

  "employeeName": "Jane Doe",

  "phoneNumber": "9876543210",

  "email": "jane@example.com",

  "reportsTo": "managerId",

  "profileImage": "http://example.com/jane.jpg"

  }

  ### 5. DELETE Delete Employee
- URL: /api/v1/deleteemployee/{id}
- Method: DELETE
- Description: Deletes an employee by ID.
- Input: None

### 6. GET Nth Level Manager of Employee
- URL: /api/v1/getnthlevelmanager/{employeeId}/{level}
- Method: GET
- Description: Retrieves the Nth level manager of an employee.
- Input: None
