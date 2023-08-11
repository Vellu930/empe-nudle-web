
# Nudle Shop
Sample noodles shop website made with Java 17, Spring Boot 2.7.2, Spring 5.3.22, Flyway, H2 in-memory database on the back-end 
and Thymeleaf, CSS, Bootstrap 4, HTML5 on the front-end.

Contact form submit action sends email to shop owner with order or message and the contact details filled in the form are stored in H2 database.

Build application with `mvn clean install` at project root folder, 
run it with `mvn spring-boot:run` at *nudle-web* folder and open localhost on port 8080.

For successful creation of Spring's MailSender add your email host information to properties file
and specify path in run configurations using: `--spring.config.location=file:///your/path/to/application.properties`.
