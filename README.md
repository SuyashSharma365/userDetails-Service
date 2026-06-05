# userDetails-Service

A Spring Boot microservice for managing user information and storing user details in a relational database.

## Features

* Create, read, update, and delete user records (CRUD operations)
* RESTful API endpoints
* Database integration using Spring Data JPA
* Input validation and exception handling
* Layered architecture (Controller, Service, Repository)
* Maven-based project structure
* Easily scalable and maintainable

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* Maven
* MySQL
* REST APIs

## Project Structure

src/
├── controller/
├── service/
├── repository/
├── entity/
└── exception/

## Getting Started

### Prerequisites

* Java 17+
* Maven
* MySQL

### Run the Application

```bash
mvn spring-boot:run
```

The service will start on:

```bash
http://localhost:8080
```

## API Operations

* Create User
* Get User by ID
* Get All Users
* Update User
* Delete User

## Purpose

This project demonstrates the implementation of a Spring Boot microservice following industry-standard architecture and best practices for user data management.
