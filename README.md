# PhaseZero Catalog Service

This is a Spring Boot backend service built as part of the PhaseZero Java Practical Assignment.  
It implements a simple Product Catalogue with features such as adding products, searching, filtering, sorting, and calculating total inventory value.

---

## 1. How to Build and Run the Application

### Prerequisites
- Java 17  
- Maven  
- Any Java IDE (IntelliJ recommended)

### Steps to Run the Application
```bash
# Clone the repository
git clone https://github.com/shivu-valgan/phasezero-catalog-service.git

# Navigate into the project
cd phasezero-catalog-service

# Start the application
# if you are using any ide just click run from the main application
# or use the below command
./mvnw spring-boot:run
```

### The server will run on: 
http://localhost:1010

### Accessing the H2 Database Console
```bash
http://localhost:8080/h2-console
```
### Use the following database settings:
- JDBC URL: jdbc:h2:mem:catalogdb
- Username: sa
- Password: (blank)

---

## 2. Short Design Overview (Layers and Main Classes)
The project is designed using a clear layered approach:

### Entity Layer
- Contains the Product entity.
- Uses Jakarta Validation:
  - @NotBlank for required fields
  - @PositiveOrZero for price and stock
- partName is converted to lowercase before saving (business rule).

### Repository Layer
- Uses Spring Data JPA.
- Provides methods for:
  - Searching by name
  - Filtering by category
  - Sorting by price
  - Checking if partNumber already exists

### Service Layer
- Implements business logic:
  - Ensures partNumber is unique
  - Converts partName to lowercase
  - Computes total inventory value
- Throws custom exceptions like DataExistsException.

### Controller Layer
- Exposes REST API endpoints.
- Accepts requests, forwards them to service, and returns responses.

### Exception Handling
- A Global Exception Handler returns consistent JSON responses:
```json
{ "message": "<error-message>" }
```
- Proper HTTP status codes:
  - 400 for validation errors
  - 409 for duplicate partNumber
  - 201 for successful creation

---

## 3.List of Available Endpoints

| **Method** | **Endpoint** | **Description** |
| --- | --- | --- |
| **POST** | **/products** | **Add a new product** |
| **GET** | **/products** | **Get all products** |
| **GET** | **/products/search?name=** | **Search products by partName** |
| **GET** | **/products/filter?category=** | **Filter products by category** |
| **GET** | **/products/sort/price** | **Get products sorted by price** |
| **GET** | **/products/inventory/value** | **Get total inventory value** |

---

## 4. Example Requests and Responses

### Example 1 — Add Product
Request
```json
{
  "partNumber": "P1001",
  "partName": "Hydraulic Filter",
  "category": "Engine",
  "price": 200,
  "stock": 10
}
```

Response (201 CREATED)
```json
{
  "id": 1,
  "partNumber": "P1001",
  "partName": "hydraulic filter",
  "category": "Engine",
  "price": 200,
  "stock": 10
}
```
### Example 2 — Validation Error

Response (400 BAD REQUEST)
```json
{
  "message": "price cannot be negative"
}
```
### Example 3 — Duplicate partNumber

Response (409 CONFLICT)
```json
{
  "message": "Product with this partNumber already exists"
}
```

---

## 5. Assumptions and Limitations

- Update and delete endpoints are not included because they were not required in the assignment.
- id is used as an internal database identifier; partNumber is the business identifier.
- H2 in-memory DB resets on every restart.
- All validation errors return a single message in the "message" field.
- No authentication or authorization is included, since it was outside the scope of the assignment.
- Only the functionalities specified in the PDF were implemented.
