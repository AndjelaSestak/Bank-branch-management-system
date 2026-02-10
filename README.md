# Bank and Branch Management System

This is a full-stack web application designed for the administrative management of banks and their respective branches. The project demonstrates a complete integration of a Spring Boot backend with an Angular frontend, focusing on robust data modeling and RESTful service communication.

---

## Tech Stack

### Backend
* Java 17
* Spring Boot (Web, Data JPA)
* Hibernate (ORM)
* Relational Database (PostgreSQL / H2)
* Maven (Dependency Management)

### Frontend
* Angular (TypeScript)
* HTML5 and CSS3
* RxJS (Asynchronous data streams)

---

## Key Technical Concepts Implemented

### Backend Logic (Spring Boot)
* **Entity Mapping**: Utilized `@Entity` and `@JoinColumn` to define relational schemas, managing the One-to-Many relationship between Bank and Branch entities.
* **Data Serialization**: Implemented Java serialization to convert objects into byte streams for secure network transmission, using `serialVersionUID` for version control and compatibility.
* **Dynamic Routing**: Developed REST Controllers leveraging `@PathVariable` to create dynamic endpoints for specific resource retrieval.

### Frontend Logic (Angular)
* **Component-Based Architecture**: Structured the application into reusable components using the `@Component` decorator to manage distinct UI segments.
* **Structural Directives**: Implemented `*ngIf` for conditional DOM rendering based on the presence or state of backend data.
* **Property Mapping**: Ensured precise data alignment between frontend TypeScript models and backend JSON responses to maintain data integrity.
* **UI Interaction**: Integrated Material-style dialogs for creating, updating, and deleting records within the administrative dashboard.

---

## API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| GET | /api/banks | Retrieve all registered banks |
| GET | /api/banks/{id} | Retrieve detailed information for a specific bank |
| POST | /api/branches | Register a new branch to a bank |
| DELETE | /api/branches/{id} | Permanently remove a branch record |
