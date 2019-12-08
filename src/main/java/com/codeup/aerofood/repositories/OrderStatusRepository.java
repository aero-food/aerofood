package com.codeup.aerofood.repositories;

import com.codeup.aerofood.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long>{
}
