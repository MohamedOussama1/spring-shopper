package uiass.eia.ecomapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uiass.eia.ecomapi.model.User;
import uiass.eia.ecomapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceMetierImpl implements IServiceMetier{
    @Autowired
    private UserRepository userRepository;
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
        User user = userRepository.save(new User(name, password, email));
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
        User user = userRepository.findUserByEmailAndPassword(email, password);
        return authService.createToken(JWT_ISSUER, user.getId());
    }
}
