package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.restaurants.DishesOfTheDay;
import com.mahumane.restaurants.domain.restaurants.RestaurantVariants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishesOfTheDayRepository extends JpaRepository<DishesOfTheDay, Long> {
    List<DishesOfTheDay> findAllByRestaurantVariant(RestaurantVariants restaurantVariant);
}
