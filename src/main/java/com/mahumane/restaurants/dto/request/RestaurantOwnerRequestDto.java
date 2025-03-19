package com.mahumane.restaurants.dto.request;

public record RestaurantOwnerRequestDto(
        UserRequestDto user,
        String phone) {
}
