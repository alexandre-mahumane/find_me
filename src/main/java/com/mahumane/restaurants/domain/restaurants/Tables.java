package com.mahumane.restaurants.domain.restaurants;

import com.mahumane.restaurants.domain.restaurants.enums.TablesEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant_tables")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "table_number", nullable = false)
    private Long tableNumber;

    private  Long seats;

    @Column(name = "image_link")
    private String imageLink;

    @Enumerated(EnumType.STRING)
    private TablesEnums status;
}
