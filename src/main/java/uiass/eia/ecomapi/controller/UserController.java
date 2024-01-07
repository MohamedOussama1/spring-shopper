package uiass.eia.ecomapi.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uiass.eia.ecomapi.model.User;
import uiass.eia.ecomapi.service.IServiceMetier;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    IServiceMetier serviceMetier;
    @GetMapping(path="/{id}")
    public User getUserById(@PathVariable(value = "id") Long id){
        return serviceMetier.findUserById(id);
    }
    @GetMapping(path="/")
    public List<User> getUsers(){
        return serviceMetier.getUsers();
    }
    @PostMapping(path="/", consumes = "application/json")
    public Long postUser(@RequestBody User user){
        return serviceMetier.addUser(user.getName(), user.getPassword(), user.getEmail());
    }
    @PutMapping(path="/", consumes = "application/json")
    public void putUser(@RequestBody User user){
        serviceMetier.updateUser(user);
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
    @GetMapping(path = "/verifyLogin/{email}/{password}")
    public String verifyLogin(@PathVariable(value = "email") String email,
                            @PathVariable(value = "password") String password){
        return serviceMetier.verifyLogin(email, password);
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
