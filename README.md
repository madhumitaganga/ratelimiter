# ratelimiter
## Requirements
* Eclipse Enterprise Edition
* Jdk version-13.0.2
* Jar executable file
 http://www.java2s.com/Code/Jar/e/Downloadexecutablejararchetype10jar.htm
* Apache-tomcat-7.0.105-windows-x64 or any latest version

##Eclipse set up for spring boot:

* Step 1 : Install JDK and set the environment variables in Java.
* Step 2 : Open Eclipse -> Market Place and download Spring Tool Suite.

If above steps do not work, set up maven as below :

* Step 1 : Download the Maven zip file. https://maven.apache.org/download.cgi
* Step 2 : Unzip the folder and install.
* Step 3 : Add M2_HOME and MAVEN_HOME variables in the Windows environment, and point it to your Maven folder.
* Step 4 : Edit PATH variable and add maven.
* Step 5 : run mvn -version to verify.

If project is still showing errors,

* Step 6 : Download Spring Boot CLI. https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/
* Step 7 : Unzip and install.
* Step 8 : Add SPRING_HOME variable and edit PATH just as it was done in step 3,4.
* Step 9 : run spring –version to verify.
* Step 10 : run mvn spring-boot:run to verify.

For in depth installtion explanation please refer :
* https://www.edureka.co/blog/spring-boot-setup-helloworld-microservices-example/

## Steps for execution :
* Step 1 : Open Eclipse IDE
* Step 2 : File > Import > Existing Maven Project
* Step 3 : Select the project
* Step 4 : Click on finish
* Step 5 : rateLimit -> src/main/java -> com.team4.rateLimit -> right click on RateLimitApplication.java -> run as: Java Application.

## To see output (See summary pdf for clear steps):
* Step 1 : Open Postman
* Step 2 : Type http://localhost:8080/authenticate with method as POST 
* Step 3 : Click on body and paste 
{
	"username":"user1",
	"password":"password"
}

* Step 4 : Click on send. A token will be received.
* Step 5 : Copy the token value.
* Step 6 : Type http://localhost:8080/name/getName or http://localhost:8080/name/getNameDiff and change method to GET.
* Step 7 : Click on header and put key as Authorization and value as the copied token.
* Step 8 : Click send. 

If send is click repeatedly then it will give an error saying too many requests. Wait for 1 second and try again, it will give 200 OK as status again.

* getName is allowed 0 rps for both user1 and user2.
* getNameDiff is allowed 1 rps for both user1 and user2.
* the above values can be changed in the code.
* for default user :
{
	"username":"",
	"password":" "
}
* for any other user Unathorised will be the error for example : 
{
	"username":"user3",
	"password":"password"
}
