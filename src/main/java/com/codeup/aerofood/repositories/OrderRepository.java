package com.codeup.aerofood.repositories;

import com.codeup.aerofood.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
