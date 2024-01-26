# Spring Boot 3.0 Security with JWT 
This project demonstrates the implementation of APIs Creation and security using Spring Boot 3.0 and (JWT) JSON Web Tokens, To Create Secure Endpoints To Secure BackEnd APIs

![Screenshot 2024-01-26 170125](https://github.com/YousefTsh/SpringBoot_JWT/assets/157696730/6ee3e22e-a4b3-4413-8f28-578b9075b700)



## Features
* User registration and login with JWT authentication
* Customized access denied handling
* Password encryption using BCrypt
* Role-based authorization with Spring Security
* Logout mechanism
* RestFull APIs

## Technologies
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven
* Docker
* PostgreSQL
 
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+


To build and run the project, follow these steps:

* Clone the repository: `git clone https://github.com/YousefTsh/SpringBoot_JWT.git`
* Navigate to the project directory: cd SpringBoot_JWT
* Run Docker Compose File to Create PostgreSQL Container
* Add database "jwt_security" to postgres 
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run 

-> The application will be available at http://localhost:8081.
