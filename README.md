# Todo-App
 A. DOWNLOAD THE INTELLIJ

 B. DOWNLOAD THE FILE PROJECT IN YOUR SYSTEM

 C.  EXTRACT ALL THAT ZIP FILE PROJECT

 D .GO TO INTELLIJ -> FILE -> OPEN AND SELECT THAT FOLDER NAME 

 ONCE YOU OPEN THE FOLDER here:- 

  # E. GO TO src -> Go to main  -> Go to resources -> GO TO APPLICATION.PROPERTIES

  STEPS TO BE FOLLOWED:

#  1 TO SET YOUR OWN CREDENTIAL OF SQL DATABASE (ASSUMING YOU DOWNLOADED SQL IN YOUR PC) LIKE THIS:

 spring.datasource.username= put your username of SQL
  spring.datasource.password= put your password of SQL

# 2. SET YOUR SERVER PORT:
 server.port = By default is 8080 

# 3. SET YOUR SQL DATABASE CONNECTION:
spring.datasource.url=jdbc:mysql://localhost:3306(Bydefault)/todo-app

# 4.SET YOUR USERNAME AND PASSWORD FOR BASIC AUTHENTICATION TO USE ENDPOINTS :

 app.security.user.name=YOUR USERNAME
 app.security.user.password= USER PASSWORD
app.security.admin.name= ADMINNAME
 app.security.admin.password= ADMIN PASSWORD

IMPORTANT NOTE:- CHOOSE YOUR ROLE(ADMIN OR USER):- NAME AND PASSWORD
 AND CAN ACCESS ENDPOINTS ACCORDINGLY

# 4.  GO TO src -> Go to main  -> Go to java -> SELECT To-doApplication AND CLick right button select Run to Application

your project is ready to give response:)



F. AFTER CHOOSING YOUR ROLE PUT USERNAME AND PASSWORD

#  TO ACCESS ALL ENDPOINTS  USING TWO WAYS 1 POSTMAN , 2 SWAGGER:
 # FOR POSTMAN:
ALL REQUESTS:Type this link -
POST: http://localhost:8080/task/create
GET : http://localhost:8080/task/getAll
GET : http://localhost:8080/task/getTask/id
PUT: http://localhost:8080/task/update/id
DELETE: http://localhost:8080/task/delete/id 
 NOTE: PORT: 8080 IS BYDEFAULT,IF YOUR APPLICATION RUNNNING IN USE SO CHOOSE OTHER PORT
TO ACCESS THESE:

 OPEN POSTMAN -> GO TO AUTHORIZATION -> SELECT BASIC AUTH -> PUT ADMIN OR USER NAME AND ITS PASSWORD
 NOTE: USER CAN ACCESS ONLY GET Requests
NOTE: ADMIN CAN ACEESS ALL REQUESTS 

# BY SWAGGER:
USE THIS LINK BY PUTTING YOUR SERVER PORT:  http://localhost:8080/swagger-ui/index.html
#  NOTE: PORT: 8080 IS BYDEFAULT,IF YOUR APPLICATION RUNNNING IN USE SO CHOOSE OTHER PORT


# Sign In:
user: your role(user or admin)
password: which you set in application.properties
 NOTE: USER CAN ACCESS ONLY GET Requests
 NOTE: ADMIN CAN ACEESS ALL REQUESTS 
