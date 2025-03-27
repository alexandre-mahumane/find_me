package com.mahumane.restaurants.dto.response;

import java.util.List;

public record RestaurantResponseDto(
        Long id,
        String name,
        String description,
        List<String> category,
        String owner
) {
}
