CREATE TABLE restaurant_books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date_time TIMESTAMP NOT NULL,
    status VARCHAR(15) NOT NULL,
    user_id BIGINT NOT NULL,
    table_id BIGINT NOT NULL,
    restaurant_variant_id BIGINT NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (table_id) REFERENCES restaurant_tables(id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_variant_id) REFERENCES restaurant_variants(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE

)