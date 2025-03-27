package com.mahumane.restaurants.service;

import com.mahumane.restaurants.domain.restaurants.DailyPromotion;
import com.mahumane.restaurants.dto.request.DailyPromotionRequestDto;
import com.mahumane.restaurants.dto.response.DailyPromotionResponseDto;
import com.mahumane.restaurants.dto.response.MatchDailyPromotionAndRestaurantResponseDto;
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
public class DailyPromotionService {
    private final DailyPromotionRepository dailyPromotionRepository;
    private final RestaurantMenuRepository menuRepository;
    private final RestaurantVariantRepository restaurantVariantRepository;
    private final UserRepository userRepository;
    private final RestaurantOwnerRepository ownerRepository;
    private final RestaurantRepository restaurantRepository;


    public List<DailyPromotionResponseDto> showAll(){
        List<DailyPromotion> promotions = this.dailyPromotionRepository.findAll();

        return  promotions.stream().map(
                        (item)-> new DailyPromotionResponseDto(
                                item.getId(),
                                item.getDiscountPercentage(),
                                item.getDescription(),
                                item.getRestaurantVariants().stream().map((restaurantVariants)->
                                                new MatchDailyPromotionAndRestaurantResponseDto(
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


    public List<DailyPromotionResponseDto> showAllByRestaurant(Long restaurantVariantId){

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

        List<DailyPromotion> promotions = this.dailyPromotionRepository.findAllByRestaurantVariant(restaurantVariant);

        return  promotions.stream().map(
                        (item)-> new DailyPromotionResponseDto(
                                item.getId(),
                                item.getDiscountPercentage(),
                                item.getDescription(),
                                item.getRestaurantVariants().stream().map((restaurantVariants)->
                                                new MatchDailyPromotionAndRestaurantResponseDto(
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



    public DailyPromotionResponseDto showById(Long promotionId){

        DailyPromotion promotions = this.dailyPromotionRepository.findById(promotionId)
                .orElseThrow(()-> new NotFoundException("Promotion not found"));

        return  new DailyPromotionResponseDto(
                                promotions.getId(),
                promotions.getDiscountPercentage(),
                promotions.getDescription(),
                promotions.getRestaurantVariants().stream().map((restaurant)->
                        new MatchDailyPromotionAndRestaurantResponseDto(
                                restaurant.getId(),
                                restaurant.getName(),
                                restaurant.getImageLink()))
                        .collect(Collectors.toSet()),
                                new RestaurantMenuResponseDto(
                                        promotions.getMenu().getId(),
                                        promotions.getMenu().getName(),
                                        promotions.getMenu().getPrice(),
                                        promotions.getMenu().getCategory(),
                                        promotions.getMenu().getImageLink()
                                )

                        );
    }


    public void create(
            DailyPromotionRequestDto dto,
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

        DailyPromotion promotion = new DailyPromotion();

        promotion.setDescription(dto.description());
        promotion.setMenu(findMenuContent);
        promotion.setDiscountPercentage(dto.discountPercentage());
        promotion.setRestaurantVariants(findRestaurantVariant);

        this.dailyPromotionRepository.save(promotion);
    }


    public void update(
            DailyPromotionRequestDto dto,
            Long promotionId,
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

    var promotion = this.dailyPromotionRepository.findById(promotionId)
            .orElseThrow(()-> new NotFoundException("Promotion not found"));

        promotion.setDescription(dto.description());
        promotion.setDiscountPercentage(dto.discountPercentage());
        promotion.setRestaurantVariants(findRestaurantVariant);

        this.dailyPromotionRepository.save(promotion);

    }

    public void remove(Long promotionId){
        var user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found")
                );
        var owner = this.ownerRepository.findOwnerById(user.getId())
            .orElseThrow(
                ()-> new NotFoundException("Owner not found not found")
        );

        var restaurant = this.restaurantRepository.findRestaurantByOwnerId(owner.getId())
                .orElseThrow(
                        ()-> new NotFoundException("Restaurant not found")
                );


        var promotion = this.dailyPromotionRepository.findById(promotionId)
                .orElseThrow(()-> new NotFoundException("Promotion not found"));

//        if (promotion.getRestaurantVariants().)

        this.dailyPromotionRepository.delete(promotion);
    }

}
