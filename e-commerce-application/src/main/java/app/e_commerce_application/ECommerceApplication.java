package app.e_commerce_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.e_commerce_application.services.MediaSocialService;


@SpringBootApplication
public class ECommerceApplication {

	@Autowired
    private MediaSocialService mediaSocialService;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
