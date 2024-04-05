# classroom - Classroom Management System

<h2> Spring Boot "Microservice" Project</h2>

This is a sample Java / Maven / Spring Boot application that can be used as a starter for managing students, collaborators, and courses.

## How to Run 

This application is packaged as a war which has Tomcat 8 embedded.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running 
```mvn clean package```

Once the application runs you should see something like this

```
2024-04-05T17:45:32.932-04:00  INFO 83794 --- [classroom] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-04-05T17:45:32.947-04:00  INFO 83794 --- [classroom] [  restartedMain] b.c.v.classroom.ClassroomApplication     : Started ClassroomApplication in 4.495 seconds (process running for 5.109)
2024-04-05T17:45:33.120-04:00  INFO 83794 --- [classroom] [)-192.168.18.12] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-04-05T17:45:33.121-04:00  INFO 83794 --- [classroom] [)-192.168.18.12] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-04-05T17:45:33.124-04:00  INFO 83794 --- [classroom] [)-192.168.18.12] o.s.web.servlet.DispatcherServlet        : Completed initialization in 2 ms
```

## About the Service

The service is just a simple service for managing students, collaborators, and courses. It uses an in-memory database (H2), to access the database just access the port:

```
http://localhost:8080/h2-console
```

### Get information about students, collaborators, and courses

```
http://localhost:8080/student
http://localhost:8080/collaborator
http://localhost:8080/course
```

### Create a student resource

```
POST /student
Accept: application/json
Content-Type: application/json

[
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "123.456.789-00",
    "address": {
        "street": "123 Main Street",
        "city": "City",
        "state": "State",
        "zip": "12345"
    }
  },
  {
    "name": "Mary Doe",
    "email": "mary.doe@example.com",
    "phone": "987.654.321-00",
    "address": {
        "street": "123 Main Street",
        "city": "City",
        "state": "State",
        "zip": "12345"
    }
  }
]
```
RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/student/{id}

### Update a student resource

```
PUT /student/{id}
Accept: application/json
Content-Type: application/json

{
    "name": "John Doe Modified",
    "email": "johnv.doe@example.com"
}
```
RESPONSE: HTTP 200 (OK)

### Delete a student resource

```
DELETE /student/id
Accept: application/json
Content-Type: application/json

{
   "id": id
}
```
RESPONSE: HTTP 204 (No Content)

---------------------------------------
### Create a collaborator
```
POST /collaborator
Accept: application/json
Content-Type: application/json

[
  {
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "role": "DEVELOPER",
    "address": {
        "street": "456 Oak Avenue",
        "city": "City",
        "state": "State",
        "zip": "54321"
    }
  },
  {
    "name": "John Smith",
    "email": "john.smith@example.com",
    "role": "TESTER",
    "address": {
        "street": "456 Oak Avenue",
        "city": "City",
        "state": "State",
        "zip": "54321"
    }
  }
]
```
RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/collaborator/id

### Update a collaborator

```
PUT /collaborator/id
Accept: application/json
Content-Type: application/json

{
    "name": "Jane Smith Modified",
    "email": "janeModified.smith@example.com"
}
```
RESPONSE: HTTP 200 (OK)

### Delete a collaborator

```
DELETE /collaborator/id
Accept: application/json
Content-Type: application/json

{
   "id": id
}
```
RESPONSE: HTTP 204 (No Content)

---------------------------------------
### Create a course
```
POST /course
Accept: application/json
Content-Type: application/json

{
    "name": "Introduction to Java Programming",
    "description": "This course provides an introduction to Java programming language.",
    "duration": "12 weeks",
    "students": [
        {
            "id": 1
        },
        {
            "id": 2
        }
    ],
    "collaborator": {
            "id": 1
    }
}
```
RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/course/id

### Update a course

```
PUT /course/id
Accept: application/json
Content-Type: application/json

{
    "name": "Introduction to React Programming",
    "collaborator": [
        {
            "id": 2
        }
    ]
}
```
RESPONSE: HTTP 200 (OK)

### Delete a course

```
DELETE /course/id
Accept: application/json
Content-Type: application/json

{
   "id": id
}
```
RESPONSE: HTTP 204 (No Content)
