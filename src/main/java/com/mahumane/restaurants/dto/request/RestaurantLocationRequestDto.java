package com.mahumane.restaurants.dto.request;

public record RestaurantLocationRequestDto(
        String city,
        String avenue,
        Long street,
        Double latitude,
        Double longitude
) {
}
