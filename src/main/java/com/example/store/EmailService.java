package com.example.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailService implements NotificationService {
    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private String port;

    @Override
    public void send(String message,String email) {
        System.out.println(email);
        System.out.println("Sending email:"+message);
    }
}
