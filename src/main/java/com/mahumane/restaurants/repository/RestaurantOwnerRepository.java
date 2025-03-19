package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.users.RestaurantsOwners;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantOwnerRepository extends JpaRepository<RestaurantsOwners, Long> {
    RestaurantsOwners findOwnerByPhone(String phone);

    RestaurantsOwners findOwnerById(long l);
}
