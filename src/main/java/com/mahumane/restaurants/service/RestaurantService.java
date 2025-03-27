package com.mahumane.restaurants.service;

import com.mahumane.restaurants.domain.restaurants.Restaurants;
import com.mahumane.restaurants.dto.request.RestaurantRequestDto;
import com.mahumane.restaurants.dto.response.RestaurantResponseDto;
import com.mahumane.restaurants.exception.ConflictException;
import com.mahumane.restaurants.exception.NotFoundException;
import com.mahumane.restaurants.repository.RestaurantOwnerRepository;
import com.mahumane.restaurants.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantOwnerRepository ownerRepository;


    public List<RestaurantResponseDto> showAll(){
        var restaurants = this.restaurantRepository.findAll();
        return restaurants.stream().map(
                (item)-> new RestaurantResponseDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getCategory(),
                        item.getRestaurantsOwners()
                                .getUser()
                                .getUsername()))
                        .collect(Collectors.toList());
    }

    public RestaurantResponseDto showById(){
        var restaurant = this.restaurantRepository.findRestaurantByOwnerId(1L).orElseThrow(
                ()-> new NotFoundException("Restaurant not found")
        );

        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getDescription(),
                restaurant.getCategory(),
                restaurant.getRestaurantsOwners().getUser().getUsername());

    }


    @Transactional
    public void update(RestaurantRequestDto dto){
        var owner = this.ownerRepository.findOwnerById(1L);
        var restaurant = this.restaurantRepository.findRestaurantByOwnerId(1L).orElseThrow(
                ()-> new NotFoundException("Restaurant not found")
        );

        if (owner == null) throw  new NotFoundException("Owner not found");


        if (!Objects.equals(restaurant.getName(), dto.name())){
            var checkName = this.restaurantRepository.findRestaurantByName(dto.name());
            if (checkName.isEmpty()){
                restaurant.setName(dto.name());
            }
        }

        restaurant.setDescription(dto.description());
        restaurant.setCategory(dto.category());

        this.restaurantRepository.save(restaurant);
    }

    @Transactional
    public void register(RestaurantRequestDto dto){
        var checkRestaurantName = this.restaurantRepository.findRestaurantByName(dto.name());
        var owner = this.ownerRepository.findOwnerById(1L).orElseThrow(
                ()-> new NotFoundException("Owner not found"));

        if (checkRestaurantName.isPresent()) throw  new ConflictException("Restaurant name is already exist");


        Restaurants restaurant = new Restaurants();

        restaurant.setName(dto.name());
        restaurant.setDescription(dto.description());
        restaurant.setCategory(dto.category());
        restaurant.setRestaurantsOwners(owner);

        this.restaurantRepository.save(restaurant);
    }



    @Transactional
    public void remove(){
        this.restaurantRepository.findRestaurantByOwnerIdAndDelete(1L);
    }
}
