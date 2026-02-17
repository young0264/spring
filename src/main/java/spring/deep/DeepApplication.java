package spring.deep;

import org.springframework.boot.SpringApplication;
import spring.deep.annotation.CustomSpringBootApplication;

@CustomSpringBootApplication
public class DeepApplication {
	public static void main(String[] args) {
		SpringApplication.run(DeepApplication.class, args);
	}
}

