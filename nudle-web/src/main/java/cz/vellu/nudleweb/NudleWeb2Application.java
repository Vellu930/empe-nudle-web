package cz.vellu.nudleweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Web Application for a store presentation.
 * @Author Martina Panchal
 */

@SpringBootApplication(scanBasePackageClasses = NudleWeb2Application.class)
public class NudleWeb2Application {
	public static void main(String[] args) {
		SpringApplication.run(NudleWeb2Application.class, args);
	}
}
