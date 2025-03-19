package com.mahumane.restaurants.dto.response;

import java.util.List;

public record RestaurantResponseDto(
        String name,
        String description,
        List<String> category,
        String owner
) {
}
