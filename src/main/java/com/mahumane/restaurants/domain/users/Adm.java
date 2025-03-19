package com.mahumane.restaurants.domain.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "adm")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false , unique = true)
    private  String email;

    private  String role = "ADM";

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

}
