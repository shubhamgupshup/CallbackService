package io.gupshup.callbackService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.logging.LogManager;
import java.util.logging.Logger;


@SpringBootApplication
@EnableJpaRepositories
public class CallbackServiceApplication {

//	private static final Logger log = LogManager.getLogger(String.valueOf(CallbackServiceApplication.class));

	public static void main(String[] args) {

		SpringApplication.run(CallbackServiceApplication.class, args);
	}

}
