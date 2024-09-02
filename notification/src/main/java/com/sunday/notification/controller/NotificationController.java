package com.sunday.notification.controller;

import com.sunday.clients.Notification.NotificationRequest;
import com.sunday.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping()
    void sendNotification(@RequestBody NotificationRequest notificationRequest){

        log.info("New notification... {}", notificationRequest);

        notificationService.sendNotification(notificationRequest);

    }
}
