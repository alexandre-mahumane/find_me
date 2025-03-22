package com.mahumane.restaurants.service;

import com.mahumane.restaurants.domain.restaurants.Menu;
import com.mahumane.restaurants.domain.restaurants.RestaurantLocation;
import com.mahumane.restaurants.domain.restaurants.RestaurantVariants;
import com.mahumane.restaurants.dto.request.RestaurantVariantRequestDto;
import com.mahumane.restaurants.dto.response.RestaurantLocationResponseDto;
import com.mahumane.restaurants.dto.response.RestaurantMenuResponseDto;
import com.mahumane.restaurants.dto.response.RestaurantVariantResponseDto;
import com.mahumane.restaurants.exception.ConflictException;
import com.mahumane.restaurants.exception.NotFoundException;
import com.mahumane.restaurants.repository.RestaurantMenuRepository;
import com.mahumane.restaurants.repository.RestaurantRepository;
import com.mahumane.restaurants.repository.RestaurantVariantRepository;
import com.mahumane.restaurants.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantVariantService {
    private final RestaurantVariantRepository restaurantVariantRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantLocationService restaurantLocationService;
    private final RestaurantMenuRepository restaurantMenuRepository;

    public List<RestaurantVariantResponseDto> showAll(){
        List<RestaurantVariants> restaurantVariants = this.restaurantVariantRepository.findAll();

        List<Menu> menus = this.restaurantMenuRepository.findAllMenuByRestaurantVariant(restaurantVariants);

        return restaurantVariants.stream().map(
                (item)->
                        new RestaurantVariantResponseDto(
                                item.getName(),
                                item.getType(),
                                item.getImageLink(),
                                menus.stream().map(
                                        (menu)->
                                                new RestaurantMenuResponseDto(
                                                        menu.getName(),
                                                        menu.getPrice(),
                                                        menu.getCategory(),
                                                        menu.getImageLink())
                                ).collect(Collectors.toSet()),
                                new RestaurantLocationResponseDto(
                                        item.getLocation().getCity(),
                                        item.getLocation().getAvenue(),
                                        item.getLocation().getStreetNumber(),
                                        item.getLocation().getLatitude(),
                                        item.getLocation().getLongitude()
                                        )
                        )).collect(Collectors.toList());
    }


    public List<RestaurantVariantResponseDto> showAllByRestaurantId(){

        var user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found")
                );

        var restaurant = this.restaurantRepository.findRestaurantByOwnerId(user.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found")
                );

        List<RestaurantVariants> restaurantVariants = this.restaurantVariantRepository
                .findAllByRestaurantId(restaurant.getId());

        List<Menu> menus = this.restaurantMenuRepository.findAllMenuByRestaurantVariant(restaurantVariants);

        return restaurantVariants.stream().map(
                (item)->
                        new RestaurantVariantResponseDto(
                                item.getName(),
                                item.getType(),
                                item.getImageLink(),
                                menus.stream().map(
                                        (menu)->
                                                new RestaurantMenuResponseDto(
                                                        menu.getName(),
                                                        menu.getPrice(),
                                                        menu.getCategory(),
                                                        menu.getImageLink())
                                ).collect(Collectors.toSet()),
                                new RestaurantLocationResponseDto(
                                        item.getLocation().getCity(),
                                        item.getLocation().getAvenue(),
                                        item.getLocation().getStreetNumber(),
                                        item.getLocation().getLatitude(),
                                        item.getLocation().getLongitude()
                                )
                        )).collect(Collectors.toList());
    }
    @Transactional
    public void create(RestaurantVariantRequestDto dto){

        this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found")
                );

        var restaurant = this.restaurantRepository.findRestaurantByOwnerId(1L)
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found")
                );

        var checkVariantName = this.restaurantVariantRepository.findRestaurantVariantByName(dto.name());

        if (checkVariantName.isPresent()){
            throw  new ConflictException("Variant name already exist");
        }

        RestaurantLocation location = this.restaurantLocationService.register(dto.location());

        RestaurantVariants restaurantVariant = new RestaurantVariants();

        restaurantVariant.setName(dto.name());
        restaurantVariant.setLocation(location);
        restaurantVariant.setRestaurant(restaurant);

        RestaurantVariants restaurantVariants = this.restaurantVariantRepository.save(restaurantVariant);

        this.restaurantLocationService.AddProvider(restaurantVariants, location.getId());
    }


    @Transactional
    public void remove(Long id){
        this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found")
                );
        var restaurant = this.restaurantVariantRepository.findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant variant not found")
                );
        this.restaurantVariantRepository.delete(restaurant);
    }
}
