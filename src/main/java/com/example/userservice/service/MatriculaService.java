package com.example.userservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.dto.MatriculaDto;
import com.example.userservice.messaging.MessagePublisher;
import com.example.userservice.model.EmailNotification;

@Service
public class MatriculaService {

    private static final Logger logger = LoggerFactory.getLogger(MatriculaService.class);

    private final MessagePublisher messagePublisher;

    public MatriculaService(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    public void sendMessage(MatriculaDto matriculaDto) {
        try {
            // Create email notification
            EmailNotification notification = EmailNotification.forNewUserRegistration(matriculaDto);

            // Send notification to RabbitMQ (o simplemente registra si RabbitMQ no está disponible)
            messagePublisher.publishEmailNotification(notification);
        } catch (Exception e) {
            // Log the error but don't fail the user creation
            logger.error("Failed to process email notification: {}", e.getMessage());
        }

    }
}
