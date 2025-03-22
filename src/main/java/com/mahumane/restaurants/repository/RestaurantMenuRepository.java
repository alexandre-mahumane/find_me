package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.restaurants.Menu;
import com.mahumane.restaurants.domain.restaurants.RestaurantVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantMenuRepository extends JpaRepository<Menu, Long> {
    Menu findMenuByName(String name);
    List<Menu> findAllMenuByRestaurantVariant(List<RestaurantVariants> restaurantVariants);
    List<Menu> findAllMenuByRestaurantVariantId(Long id);

}
