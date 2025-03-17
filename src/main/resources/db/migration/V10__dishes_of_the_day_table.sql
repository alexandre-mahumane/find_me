CREATE TABLE dishes_of_the_day (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    menu_id BIGINT NOT NULL,
    restaurant_variant_id BIGINT NOT NULL,


    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (restaurant_variant_id) REFERENCES restaurant_variants(id),
    FOREIGN KEY (menu_id) REFERENCES menu(id)
)