package uiass.eia.ecomapi.service;

import uiass.eia.ecomapi.model.Comment;
import uiass.eia.ecomapi.model.Order;
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
    List<Comment> getCommentsByProductId(int productId);

    void addComment(User user, int productId, String name, String details, String title, double rating);

    Order createOrder(Order order);

    List<Order> findOrdersByUser(Long userId);
    void deleteOrdersByUser(Long userId);

    void deleteCommentsByUser(Long id);
}
