package com.mahumane.restaurants.dto.response;

import java.util.Set;

public record DailyPromotionResponseDto(
        Long id,
        Double discount_percentage,
        String description,
        Set<DailyDishesResponseDto> restaurant,
        RestaurantMenuResponseDto menu


) {
}
