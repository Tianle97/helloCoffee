/*
 * Author: Tianle Shu
 * Date: 23/01/24
 */
package com.tus.helloCoffee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tus.helloCoffee.dto.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByOrderStatus(String orderStatus);

}
