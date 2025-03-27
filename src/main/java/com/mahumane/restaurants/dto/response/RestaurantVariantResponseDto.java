package com.mahumane.restaurants.dto.response;

import com.mahumane.restaurants.dto.request.RestaurantLocationRequestDto;

import java.util.List;
import java.util.Set;

public record RestaurantVariantResponseDto(
        Long id,
        String name,
        List<String> type,
        String imageLink,
        Set<RestaurantMenuResponseDto> menu,
        RestaurantLocationResponseDto location
) {
}
