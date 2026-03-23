# Job Tracker Backend (Spring Boot)

This is a REST API project built using Spring Boot that allows users to manage their job applications.

## 🚀 Features

- User Registration & Login (JWT Authentication)
- Create, Update, Delete Job Applications
- Pagination support
- Search jobs by company
- Global Exception Handling
- Input Validation
- Swagger API Documentation

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- H2 / MySQL
- Maven

## 🔐 Authentication

- JWT-based authentication
- Token required for all protected APIs

## 📦 API Endpoints

### Auth
- POST /auth/register
- POST /auth/login

### Jobs
- GET /jobs
- POST /jobs
- PUT /jobs/{id}
- DELETE /jobs/{id}
- GET /jobs/search?keyword=...

## 📄 Swagger UI

Access API docs here:

http://localhost:8080/swagger-ui/index.html

(After deployment, replace with live URL)

## 🌐 Deployment

Deployed on Render:
https://your-app.onrender.com

## 👨‍💻 Author

Pavan Nayak
