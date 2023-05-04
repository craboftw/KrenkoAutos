package uca;

;

import ch.qos.logback.core.net.server.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"uca.core.dominio"})
@EnableJpaRepositories
//@ComponentScan(basePackages = {"uca.core", "uca.controller"})
public class SpringbootThymeleafWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootThymeleafWebAppApplication.class, args);
	}
}