    ALTER TABLE restaurant_variants ADD CONSTRAINT fk_restaurants_variants FOREIGN KEY (restaurant_location_id) REFERENCES restaurant_location(id) ON DELETE CASCADE
