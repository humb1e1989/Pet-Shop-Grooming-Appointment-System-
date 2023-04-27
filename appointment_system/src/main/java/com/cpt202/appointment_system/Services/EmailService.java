package com.cpt202.appointment_system.Services;
public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
