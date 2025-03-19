package com.mahumane.restaurants.domain.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant_owner")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantsOwners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false , unique = true)
    private  String phone;

    @Column(nullable = false , unique = true)
    private  String email;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

}
