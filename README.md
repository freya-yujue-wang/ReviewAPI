# Reviews API 
Supports operations for writing reviews and listing reviews for a product but with no sorting or filtering.

### Prerequisites
MySQL
MySQL needs to be installed and configured. Please make sure a MySQL instance is running and listening to port 3306. A database named c3 is already created.

MongoDb
Please make sure a MongoDb daemon is running and listening at port 27017 and the MongoDb user has appropriate credentials to create a jdnb-c3 database.

Please change properties in application.properties to match proper credentials for the application to connect to the database servers.

### Finshed Work
* Configure the MySQL & MongoDB Datasource in application.properties.
* Add Flyway scripts in src/main/resources/db/migration.
* Define JPA & MongoDB Entities and relationships.
* Define Spring Data JPA & MongoDB Repositories.
* Add tests for JPA & MongoDB Repositories.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

