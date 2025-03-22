package com.mahumane.restaurants.service;

import com.mahumane.restaurants.domain.restaurants.Menu;

import com.mahumane.restaurants.domain.restaurants.RestaurantVariants;
import com.mahumane.restaurants.dto.request.RestaurantMenuRequestDto;
import com.mahumane.restaurants.dto.response.RestaurantMenuResponseDto;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantMenuService {
    private final RestaurantMenuRepository restaurantMenuRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantVariantRepository restaurantVariantRepository;
    private final UserRepository userRepository;

    public List<RestaurantMenuResponseDto> showAll(){
       List<Menu> menus = this.restaurantMenuRepository.findAll();

        return menus.stream().map(
                (item)-> new RestaurantMenuResponseDto(
                        item.getName(),
                        item.getPrice(),
                        item.getCategory(),
                        item.getImageLink()
//                        ,item.getRestaurantVariants()
//                                .stream().map((restaurant)-> new RestaurantVariantResponseDto(
//                                        restaurant.getName(),
//                                        restaurant.getType(),
//                                        restaurant.getImageLink(),
//                                        new RestaurantLocationResponseDto(
//                                                restaurant.getLocation().getCity(),
//                                                restaurant.getLocation().getAvenue(),
//                                                restaurant.getLocation().getStreetNumber(),
//                                                restaurant.getLocation().getLatitude(),
//                                                restaurant.getLocation().getLongitude()
//                                        )
//                                )
//                                ).collect(Collectors.toSet())
                )
        ).collect(Collectors.toList());
    }


    public List<RestaurantMenuResponseDto> showAllByRestaurant(Long restaurantId){
        var user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found"));

        this.restaurantRepository
                .findRestaurantByOwnerId(user.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found"));


        List<Menu> menus = this.restaurantMenuRepository.findAllMenuByRestaurantVariantId(restaurantId);

        return menus.stream().map(
                (item)-> new RestaurantMenuResponseDto(
                        item.getName(),
                        item.getPrice(),
                        item.getCategory(),
                        item.getImageLink()
//                       , item.getRestaurantVariants()
//                                .stream().map((restaurant)-> new RestaurantVariantResponseDto(
//                                                restaurant.getName(),
//                                                restaurant.getType(),
//                                                restaurant.getImageLink()
//                                                ,new RestaurantLocationResponseDto(
//                                                        restaurant.getLocation().getCity(),
//                                                        restaurant.getLocation().getAvenue(),
//                                                        restaurant.getLocation().getStreetNumber(),
//                                                        restaurant.getLocation().getLatitude(),
//                                                        restaurant.getLocation().getLongitude()
//                                                )
//                                        )
//                                ).collect(Collectors.toSet())
                )
        ).collect(Collectors.toList());
    }



    @Transactional
    public void register(
            RestaurantMenuRequestDto dto,
            Set<Long> restaurantsVariantsId){

        var user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found"));

        var restaurant = this.restaurantRepository
                .findRestaurantByOwnerId(user.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found"));


        Set<RestaurantVariants> restaurants = this.restaurantVariantRepository.findAllById(restaurantsVariantsId);

        restaurants.forEach(System.out::println);

        restaurants.forEach((item)->

                {
                    if(item.getId() != restaurant.getId()){
                        throw new NotFoundException("Restaurant variant isn't associate with Restaurant. Restaurant variant with id " + item.getId());
                    }
                }

                );

        Menu menu = new Menu();
        menu.setCategory(dto.category());
        menu.setPrice(dto.price());
        menu.setName(dto.name());
        menu.setImageLink(dto.image_link());
        menu.setRestaurantVariants(restaurants);


    this.restaurantMenuRepository.save(menu);

    }


    @Transactional
    public void update(Long menuId, RestaurantMenuRequestDto dto){

        var user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found"));

        var restaurant = this.restaurantRepository
                .findRestaurantByOwnerId(user.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found"));

        var restaurantsVariants = this.restaurantVariantRepository.findRestaurantVariantByRestaurantId(restaurant.getId());

        //Mudar as exceptions para esses coisas
        if (restaurantsVariants == null){
            throw new NotFoundException("Restaurants variant not found");
        }
        var menu = this.restaurantMenuRepository.findById(menuId).orElseThrow(()-> new NotFoundException("Menu not found"));

        var result = menu.getRestaurantVariants().stream().filter((item)-> item.getId() == restaurantsVariants.getFirst().getId()).count();

        if (result == 0){
            throw new NotFoundException("Restaurants are not associate are this menu");
        }

        menu.setPrice(dto.price());
        menu.setCategory(dto.category());
        menu.setImageLink(dto.image_link());

        if (menu.getName() != dto.name()){
           var checkName = this.restaurantMenuRepository.findMenuByName(dto.name());

            if (checkName != null){
                throw  new ConflictException("Plate name already exist");
            }

            menu.setName(dto.name());

        }

    this.restaurantMenuRepository.save(menu);
    }

    
    @Transactional
    public void remove(Long menuId){
        
        var user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found"));

        var restaurant = this.restaurantRepository
                .findRestaurantByOwnerId(user.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found"));

        var restaurantsVariants = this.restaurantVariantRepository.findRestaurantVariantByRestaurantId(restaurant.getId());

        //Mudar as exceptions para esses coisas
        if (restaurantsVariants == null){
            throw new NotFoundException("Restaurants variant not found");
        }
        var menu = this.restaurantMenuRepository.findById(menuId).orElseThrow(()-> new NotFoundException("Menu not found"));

        var result = menu.getRestaurantVariants().stream().filter((item)-> item.getId() == restaurantsVariants.getFirst().getId()).count();

        if (result == 0){
            throw new NotFoundException("Restaurants are not associate are this menu");
        }

        this.restaurantMenuRepository.delete(menu);
    }
    
}
