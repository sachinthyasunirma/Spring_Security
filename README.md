🛡️ Spring Security & Filter Overview
🔍 What is a Filter?
A Filter is a powerful component in Java and Spring-based web applications used to intercept, inspect, and manipulate HTTP requests and responses before they reach a servlet or controller.

✅ Common Use Cases for Filters:
📜 Logging & Monitoring — Log request details or track metrics.

🔄 Request/Response Modification — Change headers, encode content, etc.

🔐 Authentication & Authorization — Core of Spring Security.

🌍 CORS Configuration

📦 Compression (e.g., GZIP)

In Spring Security, filters are a foundational part of the authentication and authorization process.

🧩 Servlet Basics
A Servlet is a Java class that handles HTTP requests and sends back responses.

Key Points:
Runs inside a Servlet Container (like Tomcat).

Responds to URLs (e.g., /login, /register).

Processes business logic and returns output (HTML, JSON, etc.).

🚦 What is DispatcherServlet?
DispatcherServlet is the front controller in Spring MVC that:

Handles all incoming HTTP requests.

Routes requests to the correct Controller, Service, or View.

Analogy:
Think of it like a traffic cop that directs incoming web traffic to the appropriate handler.

Whenever you're working with Spring Boot or Spring MVC, you’re interacting with the DispatcherServlet behind the scenes.

🔐 Spring Security and DelegatingFilterProxy
🔄 DelegatingFilterProxy
Spring Security integrates with the servlet filter chain using DelegatingFilterProxy, which:

Bridges the gap between the Servlet container and Spring's ApplicationContext.

Delegates filter logic to a Spring-managed bean.

java
Copy
Edit
public class DelegatingFilterProxy extends GenericFilterBean {
    // It delegates filter logic to a Spring-managed bean
}
This allows Spring Security to plug into the standard filter chain while managing everything via Spring beans.

🧰 Implementing Basic Authentication
Here’s a step-by-step outline for creating a custom basic authentication setup:

1. 🧑‍💻 Create a User class
2. 🔍 Create a CustomUserService
3. 🔐 Configure AuthenticationManager
4. 🔧 Define a Security Configuration

🔁 Filter Chain in Spring Security
Spring Security uses a chain of filters to process each request:

bash
Copy
Edit
Incoming Request
    ↓
SecurityContextPersistenceFilter
    ↓
UsernamePasswordAuthenticationFilter
    ↓
BasicAuthenticationFilter
    ↓
ExceptionTranslationFilter
    ↓
FilterSecurityInterceptor
    ↓
Your Controller
Each filter has a specific job, and together they ensure your app is secure, extensible, and testable.

📘 Resources
Spring Security Documentation

Spring Filter API

Servlet Specification (Jakarta EE)
