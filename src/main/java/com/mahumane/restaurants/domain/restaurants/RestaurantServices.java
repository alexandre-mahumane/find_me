package com.mahumane.restaurants.domain.restaurants;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant_services")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String service;

    @OneToOne
    @JoinColumn(name = "restaurant_variant_id")
    private  RestaurantVariants restaurantVariants;
}
