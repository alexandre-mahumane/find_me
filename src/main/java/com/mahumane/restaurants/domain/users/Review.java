package com.mahumane.restaurants.domain.users;


import com.mahumane.restaurants.domain.restaurants.RestaurantVariants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "review")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String comment;

    private  Integer rating;

    @ManyToOne
    @JoinColumn(name = "restaurant_variant_id")
    private RestaurantVariants restaurantVariantsId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}
