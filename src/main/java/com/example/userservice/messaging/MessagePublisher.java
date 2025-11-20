package com.example.userservice.messaging;

import com.example.userservice.model.EmailNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    private static final Logger logger = LoggerFactory.getLogger(MessagePublisher.class);
    private static final int MAX_RETRIES = 5;
    private static final long INITIAL_RETRY_DELAY = 1000; // 1 segundo

    private final RabbitTemplate rabbitTemplate;
    private boolean rabbitMQAvailable = true;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    public MessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        // Verificar si RabbitMQ está disponible
        checkRabbitMQConnection();
    }

    private void checkRabbitMQConnection() {
        try {
            rabbitTemplate.execute(channel -> null);
            logger.info("RabbitMQ connection successful");
            rabbitMQAvailable = true;
        } catch (AmqpConnectException e) {
            logger.warn("RabbitMQ connection failed during initialization: {}. Notifications will be logged only.", e.getMessage());
            rabbitMQAvailable = false;
        }
    }

    public void publishEmailNotification(EmailNotification notification) {
        try {
            if (rabbitMQAvailable) {
                logger.info("Sending email notification to queue: {}", notification.getTo());
                sendWithRetry(notification);
                logger.info("Email notification sent successfully to queue");
            } else {
                // Si RabbitMQ no está disponible, simplemente registramos la notificación
                logger.info("RabbitMQ not available. Would have sent email to: {}", notification.getTo());
                logger.info("Email Subject: {}", notification.getSubject());
                logger.info("Email Body: {}", notification.getBody());
            }
        } catch (Exception e) {
            logger.error("Error sending email notification: {}", e.getMessage(), e);
            // No lanzamos la excepción para evitar que falle toda la operación
            logger.info("Email would have been sent to: {}", notification.getTo());
        }
    }

    private void sendWithRetry(EmailNotification notification) {
        int attempt = 0;
        long delayMillis = INITIAL_RETRY_DELAY;

        while (attempt < MAX_RETRIES) {
            try {
                rabbitTemplate.convertAndSend(exchange, routingKey, notification);
                logger.debug("Message sent successfully on attempt {}", attempt + 1);
                return;
            } catch (AmqpConnectException e) {
                attempt++;
                if (attempt < MAX_RETRIES) {
                    logger.warn("Failed to send message (attempt {}/{}). Retrying in {}ms...", 
                        attempt, MAX_RETRIES, delayMillis);
                    try {
                        Thread.sleep(delayMillis);
                        delayMillis *= 1.5; // Exponential backoff
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Interrupted while waiting for retry", ie);
                    }
                } else {
                    logger.error("Failed to send message after {} attempts", MAX_RETRIES);
                    throw e;
                }
            }
        }
    }
}