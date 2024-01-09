package uiass.eia.ecomapi.service;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uiass.eia.ecomapi.model.*;
import uiass.eia.ecomapi.repository.CommentRepository;
import uiass.eia.ecomapi.repository.OrderRepository;
import uiass.eia.ecomapi.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceMetierImpl implements IServiceMetier{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private IAuthService authService;
    String JWT_ISSUER = "auth0";

    @Override
    public User findUserById(Long id) {
        System.out.println(id);
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.get();
    }

    @Override
    public List<User> getUsers() {
        return  userRepository.findAll();
    }

    @Override
    public Long addUser(String name, String password, String email) {
        User user;
        try {
            user = userRepository.save(new User(name, password, email));
        } catch (ConstraintViolationException exception) {
            return -1L;
        }
        return user.getId();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findUsersByName(name);
    }

    @Override
    public int verifyEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return (user == null) ? 1 : 2;
    }
    @Override
    public String verifyLogin(String email, String password){
        String token = "";
        User user = userRepository.findUserByEmailAndPassword(email, password);
        if (user != null)
            token =  authService.createToken(JWT_ISSUER, user.getId());
        return token;
    }

    @Override
    public List<Comment> getCommentsByProductId(int productId) {
        return commentRepository.findCommentsByProduct(productId);
    }

    @Override
    public void addComment(User user, int productId, String name, String details, String title, double rating) {
        Comment comment = new Comment(user, productId, name, details, title, rating);
        userRepository.save(user);
        commentRepository.save(comment);
    }
    @Override
    public Order createOrder(Order order) {
        Order newOrder = new Order(order.getOrderDetails(), order.getUser(), order.getDate());
        List<CartItem> cartItems = order.getOrderDetails();
        cartItems.forEach((cartItem -> cartItem.setOrder(newOrder)));
        List<CartItem> orderDetails = new ArrayList<>(cartItems);
        newOrder.setOrderDetails(orderDetails);
        orderRepository.save(newOrder);
        return newOrder;
    }
    @Override
    public List<Order> findOrdersByUser(Long userId) {
        List<Order> orders = new ArrayList<>();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent())
            orders = orderRepository.findOrdersByUser(user.get());
        return orders;
    }

    @Override
    public void deleteOrdersByUser(Long userId) {
        List<Order> orders = findOrdersByUser(userId);
        orderRepository.deleteAll(orders);
    }

    @Override
    public void deleteCommentsByUser(Long id) {
        commentRepository.deleteAll(commentRepository.findCommentsByUser(findUserById(id)));
    }
}
