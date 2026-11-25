# Nirvana — E-commerce Starter

A starter project with a React + Vite frontend, Spring Boot REST API, and PostgreSQL configuration.

## Requirements
- Node.js 20+ and npm
- Java 17+
- Maven 3.9+
- PostgreSQL 15+

## 1. Create the database
In pgAdmin, connect to your PostgreSQL server and create a database named `nirvana`.

## 2. Configure the backend
Open `backend/src/main/resources/application.properties` and replace `YOUR_POSTGRES_PASSWORD` with the password for your PostgreSQL `postgres` user.

From a terminal:
```bash
cd backend
mvn spring-boot:run
```
API: `http://localhost:8080/api/products`

The backend uses `spring.jpa.hibernate.ddl-auto=update` for this development starter.

## 3. Run the frontend
Open a second terminal:
```bash
cd frontend
npm install
npm run dev
```
Open the URL printed by Vite (usually `http://localhost:5173`).

## Notes
- Docker is not required.
- The frontend displays sample products and a demo cart.
- The backend exposes product list and create endpoints. It is a development starter, not a production-ready store.
- Do not share your database password or commit it to GitHub.
