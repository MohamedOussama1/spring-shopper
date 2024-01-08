package uiass.eia.ecomapi.service;

import jakarta.validation.ConstraintViolationException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uiass.eia.ecomapi.model.Comment;
import uiass.eia.ecomapi.model.Order;
import uiass.eia.ecomapi.model.Product;
import uiass.eia.ecomapi.model.User;
import uiass.eia.ecomapi.repository.CommentRepository;
import uiass.eia.ecomapi.repository.OrderRepository;
import uiass.eia.ecomapi.repository.ProductRepository;
import uiass.eia.ecomapi.repository.UserRepository;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class ServiceMetierImpl implements IServiceMetier{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
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
    public List<Comment> getCommentsByProductId(Long productId) {
        Product product = productRepository.findProductById(productId);
        return commentRepository.findCommentsByProduct(product);
    }

    @Override
    public void addComment(User user, Long productId, String name, String details, String title, double rating) {
        Product product = productRepository.findProductById(productId);
        Comment comment = new Comment(user, product, name, details, title, rating);
        productRepository.save(product);
        userRepository.save(user);
        commentRepository.save(comment);
    }

    @Override
    public void initializeProducts(int lenProducts) {
        for (int i = 1; i <= lenProducts; i++)
            productRepository.save(new Product((long) i));
    }

    @Override
    public Order createOrder(Order order) {
        Order newOrder = new Order(order.getOrderDetails(), order.getUser(), order.getDate());
        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> findOrdersByUser(Long userId) {
        List<Order> orders = new ArrayList<>();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent())
            orders = orderRepository.findOrdersByUser(user.get());
        return orders;
    }
}
