package com.sunday.notification.dto;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerEmail,
        String sender,
        String message
) {
}
