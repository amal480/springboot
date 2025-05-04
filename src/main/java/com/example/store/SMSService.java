package com.example.store;

import org.springframework.stereotype.Service;

@Service("sms")
public class SMSService implements NotificationService
{
    @Override
    public void send(String message) {
        System.out.println("Sending SMS:"+message);
    }
}
