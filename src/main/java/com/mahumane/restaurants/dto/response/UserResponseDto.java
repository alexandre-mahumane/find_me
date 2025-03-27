package com.mahumane.restaurants.dto.response;

public record UserResponseDto(
        Long id,
        String username,
        String email
) {
}
