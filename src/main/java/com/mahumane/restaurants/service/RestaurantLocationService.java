package com.mahumane.restaurants.service;

import com.mahumane.restaurants.domain.restaurants.RestaurantLocation;
import com.mahumane.restaurants.domain.restaurants.RestaurantVariants;
import com.mahumane.restaurants.dto.request.RestaurantLocationRequestDto;
import com.mahumane.restaurants.exception.NotFoundException;
import com.mahumane.restaurants.repository.RestaurantLocationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantLocationService {

    private final RestaurantLocationRepository locationRepository;

    @Transactional
    public RestaurantLocation register(RestaurantLocationRequestDto dto){
        RestaurantLocation restaurantLocation = new RestaurantLocation();
        restaurantLocation.setAvenue(dto.avenue());
        restaurantLocation.setLatitude(dto.latitude());
        restaurantLocation.setLongitude(dto.longitude());
        restaurantLocation.setStreetNumber(dto.street());
        restaurantLocation.setCity(dto.city());

        return  this.locationRepository.save(restaurantLocation);
    }

    @Transactional
    public void AddProvider(RestaurantVariants provider, Long locationId){
        RestaurantLocation location = this.locationRepository
                .findById(locationId)
                .orElseThrow(
                        ()-> new NotFoundException("Location data not found"));

        location.setRestaurantVariants(provider);

        this.locationRepository.save(location);
    }



}
