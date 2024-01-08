package uiass.eia.ecomapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uiass.eia.ecomapi.model.Order;
import uiass.eia.ecomapi.model.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUser(User user);
}
