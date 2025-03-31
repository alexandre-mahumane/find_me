package com.mahumane.restaurants.dto.response;

import java.util.Set;

public record DishesOfTheDayResponseDto(
        Long id,
        String description,
        Set<DailyDishesResponseDto> restaurant,
        RestaurantMenuResponseDto menu

) {
}
