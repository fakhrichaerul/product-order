package com.fakhri.productorder.repository;

import com.fakhri.productorder.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
