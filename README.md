# school-registration

School registration system

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

### Get All Student by Course
curl --location --request GET 'http://localhost:8082/courses/16/students' \
--data-raw ''

### Get All Student without Course
curl --location --request GET 'http://localhost:8082/students/nocourses' \
--data-raw ''

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

### Get All Course by Student
curl --location --request GET 'http://localhost:8082/students/20/courses' \
--data-raw ''

### Get All Course without Student
curl --location --request GET 'http://localhost:8082/courses/nostudents' \
--data-raw ''

### Enroll Student in Course
curl --location --request PUT 'http://localhost:8082/courses/12/students/11' \
--data-raw ''

## How to setup project



