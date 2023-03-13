package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.ecommerce") //we need this to scan for Spring Beans
@EnableJpaRepositories("com.ecommerce.repositories") //register our DAO as a bean (since its interfaces)
@EntityScan("com.ecommerce") //register our model classes as DB entities
public class YuGiShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(YuGiShopApplication.class, args);
	}

}
