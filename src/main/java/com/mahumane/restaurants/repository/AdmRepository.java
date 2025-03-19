package com.mahumane.restaurants.repository;

import com.mahumane.restaurants.domain.users.Adm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmRepository extends JpaRepository<Adm, Long> {
}
