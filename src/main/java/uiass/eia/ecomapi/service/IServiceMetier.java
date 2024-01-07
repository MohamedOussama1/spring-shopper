package uiass.eia.ecomapi.service;

import org.springframework.context.annotation.Bean;
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
}
