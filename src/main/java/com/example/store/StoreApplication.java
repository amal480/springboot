package com.example.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {

		ApplicationContext context=SpringApplication.run(StoreApplication.class, args);
//        var user = User.builder()
//                .name("john")
//                .password("password")
//                .email("example.mail")
//                .build();
//        var profile= Profile.builder()
//                        .bio("bio")
//                                .build();
//        user.setProfile(profile);
//        profile.setUser(user);
//        System.out.println(user);
    }

}
