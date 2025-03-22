package com.mahumane.restaurants.domain.restaurants;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "restaurant_menu")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false, unique = false)
    private  String name;

    @Column(nullable = false)
    private  Double price;

    @Column(nullable = false)
    private  String category;

    @Column(name = "imageLink")
    private  String imageLink;

    @OneToMany
    @JoinColumn(name = "restaurant_variant_id", nullable = false)
    private Set<RestaurantVariants> restaurantVariants;
}
