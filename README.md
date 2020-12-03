# interseller

A project to exercise and implement object oriented principles (re-usability flexibility and maintainability) working with spring boot, java 8, maven, H2 in memory database and hibernate.

# instructions to run the program

Build command using Maven:
      mvn clean install


Run command using Maven:
      mvn spring-boot:run


Request mapping to get shortest path using Port 8080 on localhost:
      http://localhost:8080/shortest-path


Request body example (JSON Format):
      {
          "routeRequest" : "K"
      }


Response example:
      [(A : C), (C : F), (F : K)]
