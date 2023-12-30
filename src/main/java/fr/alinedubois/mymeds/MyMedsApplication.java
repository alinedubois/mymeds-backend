package fr.alinedubois.mymeds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyMedsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMedsApplication.class, args);
	}

}
