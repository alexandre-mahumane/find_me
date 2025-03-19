package com.mahumane.restaurants.dto.request;


public record RestaurantVariantRequestDto(
        String name,
        String type,
        String imageLink,
        RestaurantLocationRequestDto location

) {
}
