package com.mahumane.restaurants.dto.request;

public record AuthRequestDto(
        String login,
        String password
) {
}
