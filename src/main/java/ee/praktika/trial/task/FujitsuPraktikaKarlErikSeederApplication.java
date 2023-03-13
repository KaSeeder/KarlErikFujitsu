package ee.praktika.trial.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FujitsuPraktikaKarlErikSeederApplication {

	public static void main(String[] args) {
		SpringApplication.run(FujitsuPraktikaKarlErikSeederApplication.class, args);
	}
}
