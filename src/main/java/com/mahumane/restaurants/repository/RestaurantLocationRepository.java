package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.restaurants.RestaurantLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantLocationRepository extends JpaRepository<RestaurantLocation, Long> {
}
