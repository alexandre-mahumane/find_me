package com.mahumane.restaurants.dto.request;

public record RestaurantMenuRequestDto(
        String name,
        Double price,
        String image_link,
        String category
) {
}
