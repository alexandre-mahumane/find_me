package com.mahumane.restaurants.dto.response;

import com.mahumane.restaurants.dto.request.RestaurantLocationRequestDto;

import java.util.List;

public record RestaurantVariantResponseDto(
        String name,
        List<String> type,
        String imageLink,
        RestaurantLocationResponseDto location
) {
}
