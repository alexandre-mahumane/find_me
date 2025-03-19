package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.restaurants.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurants, Long> {
    Optional<Restaurants> findRestaurantByName(String name);

    void findRestaurantByOwnerIdAndDelete(long id);

    Optional<Restaurants> findRestaurantByOwnerId(long l);
}
