package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.restaurants.RestaurantVariants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantVariantRepository extends JpaRepository<RestaurantVariants, Long> {
   Optional<RestaurantVariants> findRestaurantVariantByName(String name);

    List<RestaurantVariants> findAllByRestaurantId(long id);
}
