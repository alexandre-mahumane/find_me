package com.mahumane.restaurants.dto.response;

public record MatchDailyPromotionAndRestaurantResponseDto(
        Long restaurant_id,
        String restaurant_name,
        String restaurant_image_link
) {
}
