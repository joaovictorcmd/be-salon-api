# BeSalon API

<p>
   <a href="#prerequisites">Prerequisites</a> 
   &nbsp; | &nbsp;
   <a href="#getting-started">Getting Started</a> 
   &nbsp; | &nbsp; 
   <a href="#routes">API Endpoints</a> 
   &nbsp; | &nbsp;
   <a href="#colab">Collaborators</a> 
   &nbsp; | &nbsp;
   <a href="#contribute">Contribute</a> 
</p>

<p>
   This project is a RESTful API for the BeSalon platform, developed using Spring Boot and following the MVC
   architecture pattern. The API enables secure and efficient management of user authentication
   through JWT tokens.
</p>

<h2 id="prerequisites">📌 Prerequisites</h2>

Before running the **BeSalon API** project, make sure you have the following installed:

### 🔧 Development Environment

- **Java 21**
- **Maven 3.8+**
- **Recommended IDE:** IntelliJ IDEA / Eclipse / VS Code

### 📦 Main Dependencies

This project uses the following dependencies with **Spring Boot 3.4.2**:

- **Spring Boot Starter Web** → To create the REST API
- **Spring Boot Starter Data JPA** → For database integration
- **Spring Boot Starter Security** → For authentication and security
- **Spring Boot Starter Validation** → For data validation
- **Java JWT** → For authentication using JWT tokens
- **Lombok** → To reduce boilerplate code
- **H2 Database** → In-memory database for local development and testing
- **PostgreSQL Driver** → For connecting to a PostgreSQL database

### 💾 Database

This project supports the following databases:

- **H2 Database** (for testing)
- **PostgreSQL** (for production)

If using PostgreSQL, configure your credentials in `application.properties`.


<h2 id="getting-started">🚀 Getting Started</h2>

Follow these steps to set up and run the BeSalon API locally:

1. Clone the repository and set up your environment
   ```shell
   git clone https://github.com/joaovictorcmd/be-salon-api.git
   cd be-salon-api
   ```
2. Configure the database in the appropriate profile (test, dev, or prod). In case of test `src/main/resources/application-test.properties`:
   ```properties
   spring.datasource.url=
   spring.datasource.username=
   spring.datasource.password=
   ```

3. Install dependencies using Maven
   ```shell
   mvn clean install
   ```

4. Run the application
   ```shell
   mvn spring-boot:run
   ```

5. Access the API via Postman or an integrated frontend.


<h2 id="routes">📍 API Endpoints</h2>

| Method   | Endpoint         | Description               |
|----------|------------------|---------------------------|
| `POST`   | `/auth/register` | Registers a new user      |
| `POST`   | `/auth/login`    | Authenticates a user      |

<h3 id="post-auth-register">POST /auth/register</h3>

**REQUEST**

```json
{
   "name": "Name",
   "lastName": "LastName",
   "phoneNumber": "1234567890",
   "birthDate": "1990-01-01",
   "email": "example@example.com",
   "password": "examplePassword1",
   "role": "ROLE_CUSTOMER"
}
```

**RESPONSE**

```json
{
   "timestamp": "2025-04-06T02:34:31.224540Z",
   "status": 201,
   "message": "User registered successfully",
   "data": {
      "id": "1c2dcea3-75ec-4f9b-8ab5-4dd07ee2263d",
      "name": "Name",
      "lastName": "LastName",
      "email": "example@example.com",
      "role": "CUSTOMER"
   }
}
```

<h3 id="post-auth-login">POST /auth/login</h3>

**REQUEST**

```json
{
  "email": "example@example.com",
  "password": "examplePassword1"
}
```

**RESPONSE**

```json
{
   "timestamp": "2025-04-06T02:27:48.946487700Z",
   "status": 200,
   "message": "Login successful",
   "data": {
      "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJiZS1zYWxvbi1hcGkiLCJpYXQiOjE3NDM5MDY0NjgsImV4cCI6MTc0MzkyMDg2OCwic3ViIjoiam9obi5kb2VAZXhhbXBsZS5jb20ifQ.vP5ChHSmPTULSgHen-4fJaHTXo-TLHzpmQ-enDWGNpY",
      "expiration": "2025-04-06T06:27:48.946487700Z",
      "userDTO": {
         "id": "e4fb2500-5820-4663-ace5-1648bdffcc4d",
         "name": "Name",
         "lastName": "LastName",
         "email": "example@example.com",
         "role": "CUSTOMER"
      }
   }
}
```

<h2 id="colab">🤝 Collaborators</h2>

Special thanks to everyone who contributed to this project.

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/joaovictorcmd">
        <img src="https://github.com/joaovictorcmd.png" width="100px;" alt="João Victor Profile Picture"/><br>
        <sub>
          <b>João Victor Machado</b>
        </sub>
      </a>
    </td>
  </tr>
</table>


<h2 id="contribute">📫 Contribute</h2>

Want to contribute? Follow these steps to help improve **BeSalon**!

1. Fork the repository and clone it:
   ```sh
   git clone https://github.com/joaovictorcmd/be-salon-api.git
   ```

2. Create a new branch following the naming convention:
   ```sh
   git checkout -b feature/your-feature-name
   ```

3. Follow the commit message pattern (Conventional Commits)

4. Push your changes to your fork and open a Pull Request (PR):
    - Provide a clear description of the issue solved or feature added.
    - If applicable, attach screenshots of visual modifications.
    - Wait for the review and feedback! 🚀

### Documentations that might help

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://www.conventionalcommits.org/en/v1.0.0/)