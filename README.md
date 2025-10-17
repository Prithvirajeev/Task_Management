# Task Management (Spring Boot)

**Project:** Task Management REST API built with Spring Boot, Spring Data JPA, and H2 (in-memory) database. Swagger (Springfox) is included for API documentation and testing.

---

## Table of Contents

* [Project Overview](#project-overview)
* [Why this project / Theory you should know](#why-this-project--theory-you-should-know)

  * [REST & HTTP verbs](#rest--http-verbs)
  * [Spring Boot](#spring-boot)
  * [Spring Data JPA](#spring-data-jpa)
  * [H2 Database](#h2-database)
  * [Swagger (Springfox)](#swagger-springfox)
* [Prerequisites](#prerequisites)
* [Project Structure (important files)](#project-structure-important-files)
* [Steps to create this project (how it was built)](#steps-to-create-this-project-how-it-was-built)
* [How to run the project (step-by-step)](#how-to-run-the-project-step-by-step)
* [Application properties (example)](#application-properties-example)
* [Common endpoints & examples (curl)](#common-endpoints--examples-curl)
* [H2 Console & Swagger UI URLs](#h2-console--swagger-ui-urls)
* [Git: add README and push to GitHub](#git-add-readme-and-push-to-github)
* [Contributing](#contributing)
* [License](#license)

---

## Project Overview

This is a simple Task Management REST API project implementing basic CRUD plus filtering, sorting, soft-delete, and overdue-checking. It uses Spring Boot as the framework, Spring Data JPA for persistence, H2 as an embedded database for development, and Swagger for API documentation.

This repository is intended as a learning/demo project and as a starting point for production hardening.

---

## Why this project / Theory you should know

### REST & HTTP verbs

* **GET**: retrieve resource(s).
* **POST**: create a new resource.
* **PUT** / **PATCH**: update an existing resource.
* **DELETE**: remove or soft-delete a resource.

Design API endpoints around resources (e.g., `/api/v1/tasks`). Use appropriate HTTP status codes (200, 201, 204, 400, 404, 500).

### Spring Boot

* Opinionated framework for quickly building Spring-based applications.
* `@RestController` for REST endpoints, `@Service` for business logic, `@Repository` for data access.
* Auto-configuration simplifies setup.

### Spring Data JPA

* Simplifies database operations via `JpaRepository` or `CrudRepository`.
* Uses entity classes annotated with `@Entity` to map to tables.
* `spring.jpa.hibernate.ddl-auto` controls schema generation (`create`, `update`, `validate`, `none`).

### H2 Database

* Lightweight in-memory (or file-based) database useful for development and tests.
* In-memory: data is lost on application stop; easy to use.
* H2 Console lets you interact with the DB in a browser.

### Swagger (Springfox)

* Automatically generates interactive API docs (Swagger UI).
* Useful for manual testing by developers and for documenting endpoints for front-end teams.

---

## Prerequisites

* Java 11+ (check `java -version`)
* Maven (or Gradle if project uses it)
* Git
* Optional: Postman or curl for testing HTTP endpoints

---

## Project Structure (important files)

*Typical important paths:*

```
src/main/java/com/example/taskmanager/
  ├─ controller/TaskController.java
  ├─ service/TaskService.java
  ├─ repository/TaskRepository.java
  ├─ model/Task.java
  ├─ config/SwaggerConfig.java     <- add this
  └─ TaskManagerApplication.java

src/main/resources/application.properties
pom.xml
README.md
```

Make sure package names in `SwaggerConfig` and `TaskController` are consistent (here: `com.example.taskmanager`).

---

## Steps to create this project (how it was built)

1. Created a Spring Boot project (Spring Initializr or IDE), added dependencies:

   * `spring-boot-starter-web`
   * `spring-boot-starter-data-jpa`
   * `com.h2database:h2`
   * `io.springfox:springfox-boot-starter:3.0.0` (for Swagger)
2. Added `Task` entity (`@Entity`) with fields like `id`, `title`, `description`, `dueDate`, `status`, `deleted` (soft delete flag).
3. Created `TaskRepository` extending `JpaRepository<Task, Integer>` and custom queries for filtering.
4. Implemented `TaskService` for business logic (create, update, soft delete, get overdue).
5. Exposed REST endpoints via `TaskController` mapped to `/api/v1/tasks`.
6. Added `SwaggerConfig` under `config` package to enable Swagger UI.
7. Configured `application.properties` for H2 and JPA.

---

## How to run the project (step-by-step)

1. **Clone the repository**

```bash
git clone https://github.com/Prithvirajeev/Task_Management.git
cd Task_Management
```

2. **(Optional) Create a branch for your work**

```bash
git checkout -b add-swagger-and-readme
```

3. **Build the project**

```bash
mvn clean package
```

4. **Run the application**

```bash
mvn spring-boot:run
```

Or run the jar produced by the build:

```bash
java -jar target/<your-artifact-name>.jar
```

5. **Open the H2 Console (if enabled)**

* URL: `http://localhost:8080/h2-console`
* JDBC URL (example): `jdbc:h2:mem:taskdb`
* Username / Password: see `application.properties`

6. **Open Swagger UI**

* URL: `http://localhost:8080/swagger-ui/`

7. **Test APIs** - use browser, Postman, or curl (examples below).

---

## Application properties (example)

Add these in `src/main/resources/application.properties`:

```properties
server.port=8080

spring.datasource.url=jdbc:h2:mem:taskdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=admin123
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Swagger (no properties required)

# Optional: spring.security.user.name=admin
# Optional: spring.security.user.password=admin123
```

---

## Common endpoints & examples (curl)

**Base:** `http://localhost:8080/api/v1/tasks`

* **Create Task**

```bash
curl -X POST "http://localhost:8080/api/v1/tasks" -H "Content-Type: application/json" -d '{"title":"Do homework","description":"Math","dueDate":"2025-10-20","status":"OPEN"}'
```

* **Get all tasks**

```bash
curl http://localhost:8080/api/v1/tasks
```

* **Get by id**

```bash
curl http://localhost:8080/api/v1/tasks/1
```

* **Get by status**

```bash
curl http://localhost:8080/api/v1/tasks/status?status=OPEN
```

* **Update task**

```bash
curl -X PUT "http://localhost:8080/api/v1/tasks/1" -H "Content-Type: application/json" -d '{"title":"Updated"}'
```

* **Delete (soft)**

```bash
curl -X DELETE http://localhost:8080/api/v1/tasks/1
```

---

## H2 Console & Swagger UI URLs

* Swagger UI: `http://localhost:8080/swagger-ui/`
* H2 Console: `http://localhost:8080/h2-console`

Use `jdbc:h2:mem:taskdb` and `admin` / `admin123` as credentials (if you used the example properties above).

---

## Git: add README and push to GitHub

From your local project root, run:

```bash
git add README.md
git commit -m "Add README with setup and run instructions"
git push origin main
```

If you created a branch instead, push the branch:

```bash
git push origin add-swagger-and-readme
```

Then create a pull request on GitHub and merge it into `main`.

---

## Contributing

Contributions are welcome. Fork the repository, create a branch, make changes, and open a Pull Request.

---

## License

This project is provided for learning/demo purposes. Add your preferred license here (e.g., MIT).

---

*End of README*
