package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.restaurants.DailyPromotion;
import com.mahumane.restaurants.domain.restaurants.RestaurantVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyPromotionRepository extends JpaRepository<DailyPromotion, Long> {
    List<DailyPromotion> findAllByRestaurantVariant(RestaurantVariants restaurantVariant);
}
