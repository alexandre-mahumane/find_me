CREATE TABLE restaurant_daily_promotion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    discount_percentage DECIMAL(10,2) NOT NULL,
    menu_id BIGINT NOT NULL,
    description VARCHAR(255),
    status VARCHAR(20)
    restaurant_variant_id BIGINT NOT NULL,


    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (restaurant_variant_id) REFERENCES restaurant_variants(id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES restaurant_menu(id) ON DELETE CASCADE
)