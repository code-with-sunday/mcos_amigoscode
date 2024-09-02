package com.sunday.notification.rabbitmq;

import com.sunday.clients.Notification.NotificationRequest;
import com.sunday.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("consumer {} : from queue", notificationRequest);

        notificationService.sendNotification(notificationRequest);
    }
}
