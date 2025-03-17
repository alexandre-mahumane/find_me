CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    dateTime TIMESTAMP NOT NULL,
    status VARCHAR(15) NOT NULL,
    user_id BIGINT NOT NULL,
    table_number_id BIGINT NOT NULL,
    restaurant_variant_id BIGINT NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (table_number_id) REFERENCES tables(id),
    FOREIGN KEY (restaurant_variant_id) REFERENCES restaurant_variants(id),
    FOREIGN KEY (user_id) REFERENCES users(id)

)