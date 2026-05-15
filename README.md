# Traffic Occurrence Management System
# Traffic Occurrence Management System

![Java](https://img.shields.io/badge/Java-JavaEE-orange)
![JSP](https://img.shields.io/badge/JSP-JavaServerPages-blue)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Bootstrap](https://img.shields.io/badge/Bootstrap-UI-purple)
![License](https://img.shields.io/badge/License-MIT-green)
![Status](https://img.shields.io/badge/Status-Stable-success)

# Project Recovery & Refactoring

This project was originally developed as an academic Java EE management system and later fully recovered, reorganized, and modernized.

The recovery process included:

- Refactoring legacy JSP pages
- Reorganizing DAO architecture
- Implementing reusable pagination
- Adding dynamic filtering support
- Standardizing CRUD modules
- Improving code readability
- Creating reusable JSP components
- Improving UI consistency
- Preparing the project for open-source publication

The system is now publicly available for educational purposes and community contributions.

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
├── components/
│   ├── cabecalho.jsp
│   ├── paginacao.jsp
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

## Dynamic Search & Live Filtering

Several modules now support AJAX-based live search with real-time filtering and paginated results.

Implemented features include:

- Dynamic search inputs
- Server-side filtering
- Reusable pagination wrapper
- Partial JSP rendering with jQuery AJAX
- Query parameter persistence

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

# Open Source

This project is now open-source and available for study, improvement, and community contributions.

Feel free to fork, improve, and adapt the system.

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

# Known Limitations

- Passwords are not encrypted yet
- No REST API layer
- No role-based permission system
- UI still based on Bootstrap 3
- No automated testing implemented

# Screenshots
LOGIN PAGE:
<img width="1919" height="909" alt="Screenshot 2026-05-15 015022" src="https://github.com/user-attachments/assets/eb8ad984-08a6-463c-9e09-3fa1501add60" />

HOME PAGE:
<img width="1919" height="910" alt="image" src="https://github.com/user-attachments/assets/7505e8ed-b877-4b94-b785-d825cf294790" />

OCORRÊNCIA PAGE REGISTER: 
<img width="1918" height="910" alt="image" src="https://github.com/user-attachments/assets/a0dea921-7077-400b-b44b-9920cc66329b" />

OCORRÊNCIAS PAGE:
<img width="1919" height="909" alt="image" src="https://github.com/user-attachments/assets/c9ae04c9-84a9-4892-aafc-46ffd9efaee4" />

DETALHES DE UMA OCORRÊNCIA: 
<img width="1918" height="911" alt="image" src="https://github.com/user-attachments/assets/3ce9f636-40ca-4785-9df4-fb5f4d44275e" />

ADMIN PAGE:
<img width="1919" height="909" alt="image" src="https://github.com/user-attachments/assets/5bbd3a81-acd0-42c7-9bb6-94a9334b04dc" />

PAGINA MUNICÍPIOS:
<img width="1918" height="911" alt="image" src="https://github.com/user-attachments/assets/a8c9897a-99b8-41b5-8ec5-a0bf20aa6e6c" />

OPÇÕES DE PESQUISAS DE OCORRÊNCIAS: 
<img width="1919" height="909" alt="image" src="https://github.com/user-attachments/assets/89d5317d-7e53-4110-9bf3-57cd69e0e336" />

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
git clone https://github.com/NicolauAlfredo/GestaoOcorrencias.git
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

Developed and maintained by Nicolau Alfredo.

This project marks my first complete full-stack application using Java EE technologies.
