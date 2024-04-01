# BankingApp Backend

BankingApp is a backend service for a banking application designed to simplify modern financial transactions. It is developed using Java Spring Boot and provides support for fundamental banking operations such as account management and money transfers through a RESTful API.

## Technologies

- **Spring Boot** - Framework for building modern, spring-based applications.
- **Spring Data JPA** - For database operations.
- **Spring Security** - For authentication and authorization.
- **H2 Database** - In-memory database for development and testing purposes.
- **PostgreSQL** - For production database.
- **Swagger 3 (OpenAPI)** - For API documentation and testing.

## Getting Started

To run this project locally, follow these steps:

### Prerequisites

- JDK 11 or newer
- Maven 3.2 or newer

### Installation

1. Clone the repository:
   git clone https://github.com/cansinbayir/bankingApp.git
2. Navigate to the project directory:
  cd bankingApp
3. Build the project with Maven:
  mvn clean install

### Running the Application

To start the application, run the following command:
mvn spring-boot:run
The application will start running at http://localhost:8080.

### API Documentation and Testing with Swagger UI
Once the application is running, you can access the Swagger UI to explore and test the API endpoints at:
http://localhost:8080/swagger-ui/swagger-ui/index.html


###Contact
Cansın Bayır - cansinbayir@alumni.sabanciuniv.edu

Project Link: https://github.com/cansinbayir/bankingApp
