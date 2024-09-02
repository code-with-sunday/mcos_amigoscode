package com.sunday.notification.service;

import com.sunday.clients.Notification.NotificationRequest;
import com.sunday.notification.repository.NotificationRepository;
import com.sunday.notification.entity.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {

        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerEmail())
                .sender("Sunday")
                .message(notificationRequest.message())
                .build();

       notificationRepository.save(notification);
    }

}
