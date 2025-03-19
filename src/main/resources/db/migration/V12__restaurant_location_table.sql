CREATE TABLE restaurant_location (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR(50) NOT NULL,
    avenue VARCHAR(50) NOT NULL,
    street_number BIGINT NOT NULL,

    latitude DECIMAL(10,2),
    longitude DECIMAL(10,2),
    restaurant_variant_id BIGINT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (restaurant_variant_id) REFERENCES restaurant_variants(id) ON DELETE CASCADE

)
