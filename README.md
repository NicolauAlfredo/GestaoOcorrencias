# Traffic Occurrence Management System

## Overview

Traffic Occurrence Management System is a full-stack web application developed to manage traffic-related occurrences, administrators, citizens involved in occurrences, witnesses, municipalities, professions, ranks, and other administrative entities.

The system was built using Java EE technologies following the MVC architecture pattern, combining Java Servlets, JSP, JDBC, MySQL, and Bootstrap for a complete web-based management solution.

This project represents my first complete Full Stack Web Development project, covering:

- Backend development with Java
- Database modeling with MySQL
- Server-side rendering with JSP
- MVC architecture
- CRUD operations
- Authentication and session management
- Pagination and filtering
- Dynamic database queries
- Frontend UI with Bootstrap

---

# Features

## Authentication & Session Management

- Secure administrator login
- Session-based authentication
- Logout functionality
- Access control

## Administrator Management

- Register administrators
- Edit administrator information
- Delete administrators
- View administrator details
- Search administrators by name
- Paginated administrator listing

## Autuado Management

- Full CRUD operations
- Search by:
  - Name
  - BI number
  - Birth date
- Paginated listing
- Profession and municipality association

## Municipality Management

- Full CRUD operations
- Search by municipality name
- Search by province
- Pagination support

## Additional Modules

- Profession management
- Province management
- Rank management
- Workplace management
- Witness management
- Traffic occurrence type management

---

# Technologies Used

## Backend

- Java EE
- Java Servlets
- JSP (Java Server Pages)
- JDBC

## Frontend

- HTML5
- CSS3
- Bootstrap
- jQuery

## Database

- MySQL
- MySQL Workbench

## Architecture

- MVC (Model View Controller)
- DAO Pattern

---

# Project Structure

```bash
src/
│
├── controlo/
│   ├── AdministradorServlet.java
│   ├── AutuadoServlet.java
│   └── ...
│
├── dao/
│   ├── AdministradorDAO.java
│   ├── AutuadoDAO.java
│   ├── MunicipioDAO.java
│   └── ...
│
├── modelo/
│   ├── Administrador.java
│   ├── Autuado.java
│   ├── Municipio.java
│   └── ...
│
├── util/
│   ├── Conexao.java
│   └── ...
│
web/
│
├── paginas/
│   ├── administrador/
│   ├── autuado/
│   ├── municipio/
│   └── ...
│
├── menus/
│   ├── cabecalho.jsp
│   └── rodape.jsp
│
└── Bootstrap/
```

---

# Database

The application uses a relational MySQL database with normalized tables and relationships between entities.

Main entities include:

- Administrador
- Autuado
- Município
- Profissão
- Província
- Ocorrência
- Testemunha
- Patente

---

# Implemented Improvements

## Pagination

Real database pagination was implemented using:

```sql
LIMIT ? OFFSET ?
```

This significantly improved performance and scalability for large datasets.

## Dynamic Search Structure

The system architecture was prepared for future AJAX-based live search implementation.

## Code Refactoring

Several improvements were made:

- SQL query centralization
- Reusable methods
- Defensive validation
- Better exception handling
- Cleaner DAO organization
- Reusable JSP fragments

---

# Learning Outcomes

Through this project I learned:

- Full CRUD implementation
- MVC architecture in Java EE
- Database relationships
- Session handling
- JDBC best practices
- Pagination logic
- Form validation
- JSP dynamic rendering
- DAO pattern implementation
- Clean code organization
- Backend/frontend integration

---

# Future Improvements

Planned future upgrades:

- AJAX live search
- REST API integration
- Password encryption
- Input sanitization
- Better UI/UX
- Responsive dashboard
- Export to PDF
- Role-based permissions
- Migration to Spring Boot

---

# Screenshots

Add screenshots of:

- Login page
- Dashboard
- Administrator management
- Municipality listing
- Pagination
- Search system

---

# How to Run

## Requirements

- JDK 8+
- Apache Tomcat
- MySQL Server
- NetBeans IDE (recommended)

## Steps

### 1. Clone the repository

```bash
git clone https://github.com/your-username/your-repository.git
```

### 2. Create the database

Import the SQL script into MySQL Workbench.

### 3. Configure database connection

Edit:

```java
util/Conexao.java
```

Update:

```java
private static final String URL = "...";
private static final String USER = "...";
private static final String PASSWORD = "...";
```

### 4. Run the project

Deploy the project on Apache Tomcat and access:

```bash
http://localhost:8080/project-name
```

---

# Author

Developed by Nicolau as part of the learning journey in Full Stack Web Development.

This project marks my first complete full-stack application using Java EE technologies.