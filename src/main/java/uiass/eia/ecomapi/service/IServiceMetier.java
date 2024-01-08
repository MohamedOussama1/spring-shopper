package uiass.eia.ecomapi.service;

import org.springframework.context.annotation.Bean;
import uiass.eia.ecomapi.model.Comment;
import uiass.eia.ecomapi.model.Order;
import uiass.eia.ecomapi.model.Product;
import uiass.eia.ecomapi.model.User;

import java.util.List;

public interface IServiceMetier {
    User findUserById(Long id);
    List<User> getUsers();
    Long addUser(String name, String email, String password);
    void updateUser(User user);

    void deleteUser(Long id);
    User findUserByEmail(String email);
    User findUserByName(String name);

    int verifyEmail(String email);
    String verifyLogin(String email, String password);
    List<Comment> getCommentsByProductId(Long productId);

    void addComment(User user, Long productId, String name, String details, String title, double rating);
    void initializeProducts(int lenProducts);

    Order createOrder(Order order);

    List<Order> findOrdersByUser(Long userId);
}
