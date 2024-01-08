package uiass.eia.ecomapi.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uiass.eia.ecomapi.model.User;
import uiass.eia.ecomapi.service.IAuthService;
import uiass.eia.ecomapi.service.IServiceMetier;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    IServiceMetier serviceMetier;
    @Autowired
    IAuthService authService;

    @GetMapping(path="/{id}")
    public User getUserById(@PathVariable(value = "id") Long id){
        return serviceMetier.findUserById(id);
    }
    @GetMapping(path="/")
    public List<User> getUsers(){
        return serviceMetier.getUsers();
    }
    @PostMapping(path="/", consumes = "application/json")
    public Long postUser(@RequestBody User user) throws ConstraintViolationException {
        User maybeUser = serviceMetier.findUserByEmail(user.getEmail());
        if (maybeUser != null)
            return -2L;
        return serviceMetier.addUser(user.getName(), user.getPassword(), user.getEmail());
    }
    @PutMapping(path="/", consumes = "application/json")
    public int putUser(@RequestBody User user, @RequestHeader(value = "Authorization") String bearer){
        String token = bearer.split(" ")[1];
        System.out.println(token);
        DecodedJWT decodedJWT = authService.verifyToken(token);
        if (decodedJWT == null)
            return -1;
        long userId = Long.parseLong(decodedJWT.getSubject());
        if (userId == user.getId()) {
            serviceMetier.updateUser(user);
            return 1;
        } else
            return 0;
    }
    @DeleteMapping(path="{id}")
    public void deleteUser(@PathVariable(value = "id") Long id){
        serviceMetier.deleteUser(id);
    }
    @GetMapping(path="deleteUser/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        serviceMetier.deleteUser(id);
    }
    @GetMapping(path = "verifyEmail/{email}")
    public int verifyEmail(@PathVariable(value = "email") String email){
        return serviceMetier.verifyEmail(email);
    }
    @PostMapping(path = "/auth", consumes = "application/json", produces = "application/json")
    public Map<String, String> login(@RequestBody User user){
        System.out.println(user);
        String jwt = serviceMetier.verifyLogin(user.getEmail(), user.getPassword());
        Map<String, String> authResult = new HashMap<>();
        authResult.put("idToken", jwt);
        authResult.put("expiresIn", "72000");
        return authResult;
    }
    @GetMapping(path="/by-mail/{email}")
    public User getUserByEmail(@PathVariable(value = "email") String email){
        return serviceMetier.findUserByEmail(email);
    }
    @GetMapping(path="/by-name/{name}")
    public User getUserByName(@PathVariable(value = "name") String name){
        return serviceMetier.findUserByName(name);
    }
}
