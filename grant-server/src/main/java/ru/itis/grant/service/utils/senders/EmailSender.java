package ru.itis.grant.service.utils.senders;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendRegistrationMessage() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("grant-exchange", "messages", "Hello from RabbitMQ! " + i);
        }
    }
}
