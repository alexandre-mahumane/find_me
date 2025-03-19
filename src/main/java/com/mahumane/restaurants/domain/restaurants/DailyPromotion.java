package com.mahumane.restaurants.domain.restaurants;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant_daily_promotion")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyPromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @ManyToOne
    @JoinColumn(name = "restaurant_variant_id", nullable = false)
    private RestaurantVariants restaurantVariants;

    @OneToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;
}
