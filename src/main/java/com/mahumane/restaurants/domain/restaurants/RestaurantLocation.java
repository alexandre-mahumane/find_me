package com.mahumane.restaurants.domain.restaurants;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant_location")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private  String city;

    private  String avenue;

    @Column(name = "street_number", nullable = false)
    private Long streetNumber;

    private Double latitude;

    private  Double longitude;

    @OneToOne
    @JoinColumn(name = "restaurant_variant_id" , nullable = false)
    private  RestaurantVariants restaurantVariants;
}
