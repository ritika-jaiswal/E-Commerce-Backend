E-Commerce Application Paths:

Swagger UI Paths:
You can access the Swagger UI at http://localhost:5454/swagger-ui/index.html#/.

Swagger Documentation Path:
To view the Swagger documentation, go to http://localhost:5454/v3/api-docs.

Database Configuration:

To configure the database, add the following properties to your application.properties file:
# Database configurations
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db-name     
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true

Authentication with JWT (Bearer Token):

For accessing every API endpoint, please ensure to include a Bearer Token (JWT) in the Authorization header of your request. You can obtain this token by registering and signing into the application. Once you sign in, the application will provide you with a JWT token which you need to include in the Authorization header as "Bearer {your_token}" for subsequent requests.
