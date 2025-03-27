package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.restaurants.Menu;
import com.mahumane.restaurants.domain.restaurants.RestaurantVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RestaurantMenuRepository extends JpaRepository<Menu, Long> {
    Menu findMenuByName(String name);
    List<Menu> findAllMenuByRestaurantVariant(List<RestaurantVariants> restaurantVariants);
    List<Menu> findAllMenuByRestaurantVariantId(Long id);

    Optional<Menu> findByIdAndRestaurantVariantId(Set<Long> restaurantVariantId, Long menuContentId);
}
