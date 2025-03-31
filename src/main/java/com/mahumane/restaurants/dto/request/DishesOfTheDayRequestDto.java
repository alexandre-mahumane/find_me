package com.mahumane.restaurants.dto.request;

import java.util.Set;

public record DishesOfTheDayRequestDto(
        Long menuId,
        Set<Long> restaurantsVariants,
        String description
) {

}
