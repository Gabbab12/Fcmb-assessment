# FCMB Security Starter Project

This project demonstrates a reusable Spring Boot Security Starter module (core-security-starter) and a Sample Application module (sample-application) that consumes it.
The goal is to separate security concerns into a reusable module that can be plugged into multiple services.

# PROJECT STRUCTURE

fcmb-assessment-parent
│
├── core-security-starter      → Reusable security module
│
└── sample-application         → Demo application using the starter

# TECHNOLOGIES USED

. Java 21
. Spring Boot 3+
. Spring Security
. JWT 
. Maven
. Lombok

# MODULES OVERVIEW
1. core-security-starter

A reusable Spring Boot security starter that provides:
. JWT authentication
. Custom authentication filter
. Security filter chain configuration
. Custom access denied handler
. Custom authentication entry point
. JWT token provider
. Security auto configuration

NOTE: This module can be reused across multiple applications.

2. sample-application

A demo Spring Boot application that:

. Uses the core-security-starter
. Implements login endpoint
. Secures endpoints using JWT
. Uses in-memory authentication

# How to Build the Project
From the root directory (fcmb-assessment-parent):
__run : mvn clean install_

This will:
. Build core-security-starter
. Install it in your local Maven repository
. Build sample-application

How to Run the Application:
After building successfully:
    . cd sample-application
    . mvn spring-boot:run
Or run from IntelliJ by starting:
    . SampleApplication.java

The application run on :
  . http://localhost:20800

The application uses Spring Security InMemoryUserDetailsManager.
Default Users
Username	      Password	          Role
gabriel	        ICEmala234!@	      USER
gabriel-admin	  ICEmala234!@	      ADMIN

Passwords are encoded using BCrypt.

# Authentication Flow:
. User calls login endpoint
. Spring Security authenticates using in-memory users
. JWT token is generated
. Token must be sent in Authorization header for secured endpoints

API Requests:
. Login request
  Method : POST
  url : /api/auth/login

  sample requestBody : {
                        "username": "gabriel",
                        "password": "ICEmala234!@"
                       }

Security Rules
The application uses JWT-based stateless authentication with the following access rules:

| Pattern            | Access                              |
| ------------------ | ----------------------------------- |
| `/api/public/**`   | Public (no authentication required) |
| `/api/auth/login`  | Public (used to obtain JWT token)   |
| `/api/admin/**`    | Requires `ADMIN` role               |
| Any other endpoint | Requires authentication             |


Design Decisions
1. Modular Security Architecture
  . Security logic is extracted into core-security-starter to:
  . Promote reusability
  . Maintain separation of concerns
  . Allow plug-and-play integration
  . Reduce duplication across services


