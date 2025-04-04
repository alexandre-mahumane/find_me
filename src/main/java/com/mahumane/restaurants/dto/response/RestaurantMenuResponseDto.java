package com.mahumane.restaurants.dto.response;

import java.util.Set;

public record RestaurantMenuResponseDto(
        Long id,
        String name,
        Double price,
        String image_link,
        String category
//,        Set<RestaurantVariantResponseDto> restaurants_Variant
) {
}
