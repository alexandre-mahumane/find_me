package com.mahumane.restaurants.dto.request;

import java.util.List;

public record RestaurantRequestDto(
        String name,
        String description,
        List<String> category
) {
}
