package rpp2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Rpp2021Application {

	public static void main(String[] args) {
		SpringApplication.run(Rpp2021Application.class, args);
	}

}