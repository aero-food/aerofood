package com.codeup.aerofood.repositories;

import com.codeup.aerofood.models.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {
}
