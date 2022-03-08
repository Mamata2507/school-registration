# school-registration

School registration system

## Technology stack

• Java
• Maven
• Spring Boot
• Docker (docker-compose)
• JUnit
• MySQL

## Requirement

Design and implement simple school registration system
- Assuming you already have a list of students
- Assuming you already have a list of courses
- A student can register to multiple courses
- A course can have multiple students enrolled in it.
- A course has 50 students maximum
- A student can register to 5 course maximum

Provide the following REST API:
- Create students CRUD
- Create courses CRUD
- Create API for students to register to courses
- Create abilities for user to view all relationships between students and courses
+ Filter all students with a specific course
+ Filter all courses for a specific student
+ Filter all courses without any students
+ Filter all students without any courses


## Endpoints and payloads

## CRUD Students

### Create Student
curl --location --request POST 'http://localhost:8082/students' \
--header 'Content-Type: application/json' \
--data-raw '{
"studentName": "Manuel Torrez Huanca",
"studentAddress":"Ravignani 2273",
"studentDocument": "923689"
}'

### Update Student by Id
curl --location --request PUT 'http://localhost:8082/students/5' \
--header 'Content-Type: application/json' \
--data-raw '{
"studentName": "Yoselin Reyes de Torrez",
"studentAddress":"Ravig 2273",
"studentDocument": ""
}'

### Delete Student by Id
curl --location --request DELETE 'http://localhost:8082/students/4' \
--header 'Content-Type: application/json' \
--data-raw '{
"departmentName": "Math",
"departmentAddress":"Ravignani 2273",
"departmentCode": "01"
}'

### Get Student by Id
curl --location --request GET 'http://localhost:8082/students/23' \
--data-raw ''

### Get All Student
curl --location --request GET 'http://localhost:8082/students' \
--data-raw ''

## CRUD Courses

### Create Course
curl --location --request POST 'http://localhost:8082/courses' \
--header 'Content-Type: application/json' \
--data-raw '{
"courseName": "Language",
"courseDescription": "Teach science Math"
}'

### Update Course
curl --location --request PUT 'http://localhost:8082/courses/7' \
--header 'Content-Type: application/json' \
--data-raw '{
"courseName": "Math",
"courseDescription": "Teach science Mathss"
}'

### Delete Course
curl --location --request DELETE 'http://localhost:8082/courses/37' \
--data-raw ''

### Get Course by Id
curl --location --request GET 'http://localhost:8082/courses/18' \
--data-raw ''

### Get All Course
curl --location --request GET 'http://localhost:8082/courses' \
--data-raw ''

## Filter

### Get All Course by Student
curl --location --request GET 'http://localhost:8082/students/20/courses' \
--data-raw ''

### Get All Student by Course
curl --location --request GET 'http://localhost:8082/courses/16/students' \
--data-raw ''

### Get All Course without Student
curl --location --request GET 'http://localhost:8082/courses/nostudents' \
--data-raw ''

### Get All Student without Course
curl --location --request GET 'http://localhost:8082/students/nocourses' \
--data-raw ''

## API for students to register to courses
curl --location --request PUT 'http://localhost:8082/courses/12/students/11' \
--data-raw ''

## How to setup project

### Compose file
### 1. Review, api has a docker-compose.yml, should look like this:

version: "3.7"
services:
mysql_db:
image: "mysql:8.0"
restart: unless-stopped
env_file: .env
environment:
- MYSQL_ROOT_PASSWORD=$MYSQLDB_ROOT_PASSWORD
- MYSQL_DATABASE=$MYSQLDB_DATABASE
ports:
- $MYSQLDB_LOCAL_PORT:$MYSQLDB_DOCKER_PORT
volumes:
- db:/var/lib/mysql
api_service:
depends_on:
- mysql_db
build: .
restart: on-failure
env_file: .env
ports:
- $SPRING_LOCAL_PORT:$SPRING_DOCKER_PORT
environment:
SPRING_APPLICATION_JSON: '{
"spring.datasource.url"  : "jdbc:mysql://mysql_db:$MYSQLDB_DOCKER_PORT/$MYSQLDB_DATABASE?allowPublicKeyRetrieval=true&useSSL=false",
"spring.datasource.username" : "$MYSQLDB_USER",
"spring.datasource.password" : "$MYSQLDB_ROOT_PASSWORD",
"spring.datasource.driver-class-name" : "com.mysql.cj.jdbc.Driver",
"spring.jpa.properties.hibernate.dialect" : "org.hibernate.dialect.MySQL5InnoDBDialect",
"spring.jpa.hibernate.ddl-auto" : "update"
}'
volumes:
- .m2:/root/.m2
stdin_open: true
tty: true
volumes:
db:

### 2. Run the application stack
Start up the application stack using the docker-compose up command. 
We’ll add the -d flag to run everything in the background.
    $ docker-compose up -d

### 3. View list all the containers
$ docker ps

Review container NAMES and STATUS should be Up:

school-registration_api_service_1
school-registration_mysql_db_1

### 4. At this point, you should be able to open the app and see it running. 



