package com.mahumane.restaurants.dto.request;

public record DailyPromotionRequestDto(
        Double discountPercentage,
        String description
) {
}
