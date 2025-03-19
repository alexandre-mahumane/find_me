package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.restaurants.RestaurantLocation;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RestaurantLocationRepository extends JpaRepository<RestaurantLocation, Long> {
    Optional<RestaurantLocation> findRestaurantLocationByRestaurantId(Long restaurantVariantId);
}
