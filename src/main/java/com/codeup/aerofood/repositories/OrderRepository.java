package com.codeup.aerofood.repositories;

import com.codeup.aerofood.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
