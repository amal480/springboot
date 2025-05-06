package com.example.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {

		ApplicationContext context=SpringApplication.run(StoreApplication.class, args);
//		var orderService=context.getBean(OrderService.class);
//		orderService.placeOrder();
		var userService=context.getBean(UserService.class);
		userService.registerUser(new User(1L,"amal@mail.com","pass","amal"));
		userService.registerUser(new User(1L,"amal@mail.com","pass","amal"));

	}

}
