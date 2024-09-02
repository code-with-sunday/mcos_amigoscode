package com.sunday.clients.Notification;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerEmail,
        String message

) {}
