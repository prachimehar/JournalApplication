package com.prachimehar.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendMail() {
        emailService.sendMail("prachimehar841@gmail.com","Testing java mail sender","Congratulations! your Email service is working");

    }
}