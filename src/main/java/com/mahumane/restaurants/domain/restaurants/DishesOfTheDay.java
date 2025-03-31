package com.mahumane.restaurants.domain.restaurants;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "restaurant_daily_promotion")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DishesOfTheDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;


    @OneToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private String description;

    @ManyToOne
    @JoinColumn(name = "restaurant_variant_id")
    private Set<RestaurantVariants> restaurantVariants;

}
