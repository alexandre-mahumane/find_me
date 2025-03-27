package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.restaurants.RestaurantVariants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RestaurantVariantRepository extends JpaRepository<RestaurantVariants, Long> {
   Optional<RestaurantVariants> findRestaurantVariantByName(String name);

    List<RestaurantVariants> findAllByRestaurantId(long id);
    Set<RestaurantVariants> findAllById(Set<Long> id);
    List<RestaurantVariants> findRestaurantVariantByRestaurantId(Long id);

 Set<RestaurantVariants> findRestaurantVariantsByIdAndRestaurantId(Set<Long> restaurantVariantId, Long id);
    RestaurantVariants findRestaurantVariantsByIdAndRestaurantId(Long restaurantVariantId, Long id);

}
