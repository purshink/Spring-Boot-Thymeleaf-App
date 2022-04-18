<b>Spring & Thymeleaf project</b>

* * * * *

A Spring Web MVC application that renders thymeleaf templates as HTML. Supports integration with Spring Security and provides login logout support. Uses Spring Data to persist data into the MYSQL db.

<b>Prerequisites</b>

* * * * *

-   JDK 11 and JAVA_HOME environment variable set



<b>Building the project</b>

* * * * *

Clone the repository:

<pre>git clone https://github.com/purshink/Spring-Boot-Thymeleaf-App </pre>
Navigate to the newly created folder:

<pre>cd Spring-Boot-Thymeleaf-App</pre>

Run the project with:

<pre>./mvnw clean spring-boot:run</pre>

Navigate to:

<pre>[http://localhost:8080](http://localhost:8080/)</pre>

Login with: 
<pre>user or business,
password:topsecret</pre>

* * * * *

<b>Spring Mail</b>

Make sure to specify a valid spring.mail.username and spring.mail.password in the application.properties file in order to be able to send Email confirmation for updating user-entries.

IMPORTANT: if you decide not to specify mail credentials, you will get  javax.mail.AuthenticationFailedException on launching. The rest of the application should work normally despite this exception.
