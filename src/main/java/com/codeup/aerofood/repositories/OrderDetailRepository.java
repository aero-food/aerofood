package com.codeup.aerofood.repositories;

import com.codeup.aerofood.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository  extends JpaRepository<OrderDetail,Long> {

}
