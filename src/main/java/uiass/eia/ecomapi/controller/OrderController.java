package uiass.eia.ecomapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uiass.eia.ecomapi.model.Order;
import uiass.eia.ecomapi.model.OrderDto;
import uiass.eia.ecomapi.model.User;
import uiass.eia.ecomapi.service.IServiceMetier;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    @Autowired
    IServiceMetier serviceMetier;
    @GetMapping(path="/{id}")
    public List<Order> getOrdersByUser(@PathVariable(value = "id") Long userId){
        return serviceMetier.findOrdersByUser(userId);
    }
    @GetMapping(path="/")
    public List<User> getUsers(){
        return serviceMetier.getUsers();
    }
    @PostMapping(path="/", consumes = "application/json", produces = "application/json")
    public Order postOrder(@RequestBody OrderDto orderDto){
        System.out.println(orderDto);
        User user = serviceMetier.findUserById(orderDto.getUserId());
        Order order = new Order(orderDto.getOrderDetails(), user, orderDto.getDate());
        return serviceMetier.createOrder(order);
    }
}
