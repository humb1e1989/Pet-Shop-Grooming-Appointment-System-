package com.cpt202.appointment_system.Unit_Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.cpt202.appointment_system.Services.EmailServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private JavaMailSender emailSender;

    @Test
    public void sendSimpleMessageTest() {
        String to = "recipient@example.com";
        String subject = "Test email";
        String text = "This is a test email.";

        emailService.sendSimpleMessage(to, subject, text);

        verify(emailSender).send(any(SimpleMailMessage.class));
    }
}
