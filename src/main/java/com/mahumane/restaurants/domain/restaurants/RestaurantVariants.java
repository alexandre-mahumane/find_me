package com.mahumane.restaurants.domain.restaurants;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "restaurant_variants")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantVariants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private List<String> type;

    @Column(name = "image_link")
    private String imageLink;
    @OneToOne
    @JoinColumn(nullable = false, name = "restaurant_location_id")
    private RestaurantLocation location;

    @ManyToOne
    @JoinColumn(nullable = false, name = "main_restaurant_id")
    private Restaurants restaurant;
}
