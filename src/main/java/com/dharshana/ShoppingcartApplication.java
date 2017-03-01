package com.dharshana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.dharshana","com.dharshana.repository"})
//@Import({AppConfig.class})
@ComponentScan(basePackageClasses = {AppConfig.class})
public class ShoppingcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
	}
        
}
