package com.mahumane.restaurants.dto.response;

public record RestaurantLocationResponseDto(
        String city,
        String avenue,
        Long street,
        Double latitude,
        Double longitude
) {
}
