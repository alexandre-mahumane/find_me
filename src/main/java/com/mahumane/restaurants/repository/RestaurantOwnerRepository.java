package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.users.RestaurantsOwners;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantOwnerRepository extends JpaRepository<RestaurantsOwners, Long> {
    RestaurantsOwners findOwnerByPhone(String phone);

    Optional<RestaurantsOwners> findOwnerById(long l);
}
