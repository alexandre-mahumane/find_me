CREATE TABLE tables (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    table_number INT NOT NULL UNIQUE,
    seats INT NOT NULL,
    status VARCHAR(25) NOT NULL,
    restaurant_variant_id BIGINT NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (restaurant_variant_id) REFERENCES restaurant_variants(id)
)