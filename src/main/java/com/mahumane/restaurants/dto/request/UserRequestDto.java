package com.mahumane.restaurants.dto.request;

public record UserRequestDto(
        String username,
        String email,
        String password
) {}
