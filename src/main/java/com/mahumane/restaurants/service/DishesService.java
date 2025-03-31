package com.mahumane.restaurants.service;

import com.mahumane.restaurants.domain.restaurants.DishesOfTheDay;
import com.mahumane.restaurants.dto.request.DishesOfTheDayRequestDto;
import com.mahumane.restaurants.dto.response.DailyDishesResponseDto;
import com.mahumane.restaurants.dto.response.DishesOfTheDayResponseDto;
import com.mahumane.restaurants.dto.response.RestaurantMenuResponseDto;
import com.mahumane.restaurants.exception.NotFoundException;
import com.mahumane.restaurants.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DishesService {
    
    private final DishesOfTheDayRepository dishesRepository;
    private final RestaurantMenuRepository menuRepository;
    private final RestaurantVariantRepository restaurantVariantRepository;
    private final UserRepository userRepository;
    private final RestaurantOwnerRepository ownerRepository;
    private final RestaurantRepository restaurantRepository;


    public List<DishesOfTheDayResponseDto> showAll(){
        List<DishesOfTheDay> dish = this.dishesRepository.findAll();

        return  dish.stream().map(
                        (item)-> new DishesOfTheDayResponseDto(
                                item.getId(),
                                item.getDescription(),
                                item.getRestaurantVariants().stream().map((restaurantVariants)->
                                                new DailyDishesResponseDto(
                                                        restaurantVariants.getId(),
                                                        restaurantVariants.getName(),
                                                        restaurantVariants.getImageLink()))
                                        .collect(Collectors.toSet()),
                                new RestaurantMenuResponseDto(
                                        item.getMenu().getId(),
                                        item.getMenu().getName(),
                                        item.getMenu().getPrice(),
                                        item.getMenu().getCategory(),
                                        item.getMenu().getImageLink()
                                )

                        )
                )
                .collect(Collectors.toList());
    }


    public List<DishesOfTheDayResponseDto> showAllByRestaurant(Long restaurantVariantId){

        var user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found")
                );

        var restaurant = this.restaurantRepository.findRestaurantByOwnerId(user.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found")
                );

        var restaurantVariant = this.restaurantVariantRepository
                .findRestaurantVariantsByIdAndRestaurantId(restaurantVariantId,restaurant.getId());

        List<DishesOfTheDay> dish = this.dishesRepository.findAllByRestaurantVariant(restaurantVariant);

        return  dish.stream().map(
                        (item)-> new DishesOfTheDayResponseDto(
                                item.getId(),
                                item.getDescription(),
                                item.getRestaurantVariants().stream().map((restaurantVariants)->
                                                new DailyDishesResponseDto(
                                                        restaurantVariants.getId(),
                                                        restaurantVariants.getName(),
                                                        restaurantVariants.getImageLink()))
                                        .collect(Collectors.toSet()),
                                new RestaurantMenuResponseDto(
                                        item.getMenu().getId(),
                                        item.getMenu().getName(),
                                        item.getMenu().getPrice(),
                                        item.getMenu().getCategory(),
                                        item.getMenu().getImageLink()
                                )

                        )
                )
                .collect(Collectors.toList());
    }

    public DishesOfTheDayResponseDto showById(Long dishId){

        DishesOfTheDay dish = this.dishesRepository.findById(dishId)
                .orElseThrow(()-> new NotFoundException("dish not found"));

        return  new DishesOfTheDayResponseDto(
                dish.getId(),

                dish.getDescription(),
                dish.getRestaurantVariants().stream().map((restaurant)->
                                new DailyDishesResponseDto(
                                        restaurant.getId(),
                                        restaurant.getName(),
                                        restaurant.getImageLink()))
                        .collect(Collectors.toSet()),
                new RestaurantMenuResponseDto(
                        dish.getMenu().getId(),
                        dish.getMenu().getName(),
                        dish.getMenu().getPrice(),
                        dish.getMenu().getCategory(),
                        dish.getMenu().getImageLink()
                )

        );
    }


    public void create(
            DishesOfTheDayRequestDto dto,
            Long menuContentId,
            Set<Long> restaurantVariantId){

        var user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found")
                );

        var restaurant = this.restaurantRepository.findRestaurantByOwnerId(user.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found")
                );

        var findRestaurantVariant  = this.restaurantVariantRepository
                .findRestaurantVariantsByIdAndRestaurantId(restaurantVariantId,restaurant.getId());


        var findMenuContent = this.menuRepository.findByIdAndRestaurantVariantId(restaurantVariantId, menuContentId)
                .orElseThrow(()-> new NotFoundException("Menu content is not associate"));

        DishesOfTheDay dish = new DishesOfTheDay();

        dish.setDescription(dto.description());
        dish.setMenu(findMenuContent);

        dish.setRestaurantVariants(findRestaurantVariant);

        this.dishesRepository.save(dish);
    }


    public void update(
            DishesOfTheDayRequestDto dto,
            Long dishId,
            Set<Long> restaurantVariantId ){

        var user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found")
                );

        var restaurant = this.restaurantRepository.findRestaurantByOwnerId(user.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found")
                );

        var findRestaurantVariant  = this.restaurantVariantRepository
                .findRestaurantVariantsByIdAndRestaurantId(restaurantVariantId,restaurant.getId());

        var dish = this.dishesRepository.findById(dishId)
                .orElseThrow(()-> new NotFoundException("Dish not found"));

        var menu = this.menuRepository.findById(dto.menuId())
                .orElseThrow(()-> new NotFoundException("Menu not found"));
        dish.setDescription(dto.description());

        dish.setMenu(menu);
        dish.setRestaurantVariants(findRestaurantVariant);

        this.dishesRepository.save(dish);

    }

    public void remove(Long dishId){
        var user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found")
                );
        var owner = this.ownerRepository.findOwnerById(user.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Owner not found not found")
                );

        this.restaurantRepository.findRestaurantByOwnerId(owner.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found")
                );


        var dish = this.dishesRepository.findById(dishId)
                .orElseThrow(()-> new NotFoundException("Dish not found"));

//        if (dish.getRestaurantVariants().)

        this.dishesRepository.delete(dish);
    }

}
