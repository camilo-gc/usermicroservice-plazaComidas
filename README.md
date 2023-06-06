<br />
<div align="center">
<h3 align="center">user microservice</h3>
  <p align="center">
    micro-service in charge of user management and authentication in the system of a restaurant chain.
  </p>
</div>

### Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
* ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
* ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps.

### Prerequisites

* JDK 17 [https://jdk.java.net/java-se-ri/17](https://jdk.java.net/java-se-ri/17)
* Gradle [https://gradle.org/install/](https://gradle.org/install/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)

### Recommended Tools
* IntelliJ Community [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* Postman [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

### Installation

1. Clone the repository
2. Change directory
   ```sh
   cd usermicroservice-powerUP-v3
   ```
3. Create a new database in MySQL called users-powerup
4. Update the database connection settings
   ```yml
   # src/main/resources/application-dev.yml
   spring:
      datasource:
          url: jdbc:mysql://localhost/users-powerup
          username: root
          password: <your-password>
   ```
5. After the tables are created execute src/main/resources/data.sql content to populate the database
6. Open Swagger UI and search the /auth/login endpoint and login with userName: admin@gmail.com, password: 1111


<!-- RUN -->
## RUN

1. Right-click the class UserMicroserviceApplication and choose Run
2. Open [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html) in your web browser


<!-- USAGE -->
## Usage

1. **Add a New Owner**

   **Endpoint:** /user/new/owner

   **Description:** This endpoint is used to add a new owner to the system.

   **HTTP Method:** POST
   
   **Request Body:**
   ```json
   {
   "name": "string",
   "surname": "string",
   "dni": "string",
   "phone": "+631220127689",
   "email": "string",
   "password": "string",
   "birth_date": "string"
   }
   ```
   **Response:**
   
   200 OK if the owner is successfully added.
   ```json
   {
   "message": "string"
   }
   ```
   401 Bad Request, Unauthorized user.
   ```json
   {
   "error": "string"
   }
   ```
   403 Bad Request, Role not allowed for user creation.
   ```json
   {
   "error": "string"
   }
   ```
   409 Bad Request if the request body is missing required fields or contains invalid data.
   ```json
   {
   "error": "string"
   }
   ```
   
2. **Add a New Employee**

   **Endpoint:** /user/new/employee

   **Description:** This endpoint is used to add a new employee to the system.

   **HTTP Method:** POST

   **Request Body:**
   ```json
   {
   "name": "string",
   "surname": "string",
   "dni": "string",
   "phone": "+126876758842",
   "email": "string",
   "password": "string"
   }
   ```
   **Response:**

   200 OK if the employee is successfully added.
   ```json
   {
   "message": "string"
   }
   ```
   401 Bad Request, Unauthorized user.
   ```json
   {
   "error": "string"
   }
   ```
   403 Bad Request, Role not allowed for user creation.
   ```json
   {
   "error": "string"
   }
   ```
   409 Bad Request if the request body is missing required fields or contains invalid data.
   ```json
   {
   "error": "string"
   }
   ```

3. **Add a New Client**
   
   **Endpoint:** /user/new/client
   
   **Description:** This endpoint is used to add a new client to the system.
   
   **HTTP Method:** POST
   
   **Request Body:**
   ```json
   {
   "name": "string",
   "surname": "string",
   "dni": "string",
   "phone": "+956890265587",
   "email": "string",
   "password": "string"
   }
   ```
   
   **Response:**

   200 OK if the client is successfully added.
   ```json
   {
   "message": "string"
   }
   ```
   409 Bad Request if the request body is missing required fields or contains invalid data.
   ```json
   {
   "error": "string"
   }
   ```

4. **Get a User**

   **Endpoint:** /user/{id}
   
   **Description:** This endpoint is used to retrieve information about a user based on their ID.
   
   **HTTP Method:** GET
   
   **Response:**
   
   200 OK along with the user information in the response body if the user is found.
   ```json
   {
   "id": 0,
   "name": "string",
   "surname": "string",
   "dni": "string",
   "phone": "string",
   "birthDate": "string",
   "email": "string",
   "password": "string",
   "id_role": 0
   }
   ```
   401 Bad Request, Unauthorized user.
   ```json
   {
   "error": "string"
   }
   ```
   404 Not Found if the user with the specified ID does not exist.
   ```json
   {
   "error": "string"
   }
   ```
   
Please note that some endpoints require authentication. You should include the appropriate authentication headers with your requests.


<!-- ROADMAP -->
## Tests

- Right-click the test folder and choose Run tests with coverage
