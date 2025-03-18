package com.mahumane.restaurants.domain.restaurants;

import com.mahumane.restaurants.domain.users.RestaurantsOwners;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurants")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false)
    private  String description;

    @Column(nullable = false)
    private  String category;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private RestaurantsOwners restaurantsOwnersId;
}
