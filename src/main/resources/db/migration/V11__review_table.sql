CREATE TABLE review (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    comment VARCHAR(255),
    rating INT,

    user_id BIGINT NOT NULL,
    restaurant_variant_id BIGINT NOT NULL,


    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (restaurant_variant_id) REFERENCES restaurant_variants(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
)