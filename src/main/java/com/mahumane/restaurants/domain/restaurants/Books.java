package com.mahumane.restaurants.domain.restaurants;

import com.mahumane.restaurants.domain.restaurants.enums.BookEnum;
import com.mahumane.restaurants.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "restaurant_books")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToMany
    @JoinTable(
            name = "book_table",
            joinColumns = @JoinColumn(name = "table_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Tables> tables;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private BookEnum status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "restaurant_variant_id", nullable = false)
    private RestaurantVariants restaurantVariants;


}
