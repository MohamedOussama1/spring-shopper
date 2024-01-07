package uiass.eia.ecomapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uiass.eia.ecomapi.model.User;
import uiass.eia.ecomapi.service.IServiceMetier;

@Controller
public class ClientWebController {
    @Autowired
    IServiceMetier serviceMetier;
    @RequestMapping(path="/home", method = RequestMethod.GET)
    public String getHome(Model model){
        model.addAttribute("userList", serviceMetier.getUsers());
        return "home";
    }
    @RequestMapping(path="/deleteUser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable(value = "id") Long id){
        serviceMetier.deleteUser(id);
        return "redirect:/home";
    }
    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
        public String update(@PathVariable(value = "id") Long id, Model model){
            model.addAttribute("id", id);
            return "redirect:/updateUserForm/" + id;
        }
    @RequestMapping(path = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model){
        User user = new User();
        model.addAttribute(user);
        return "form";
    }
    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public String addUserForm(@ModelAttribute User user){
        serviceMetier.addUser(
                user.getName(), user.getPassword(), user.getEmail()
        );
        return "redirect:/home";
    }
    @RequestMapping(path="/updateUserForm/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable(value = "id") Long id, Model model){
        User user = serviceMetier.findUserById(id);
        model.addAttribute(user);
        model.addAttribute("id", id);
        return "update-user";
    }
    @RequestMapping(path="/updateUser", method = RequestMethod.PUT)
    public String submitUpdateForm(@ModelAttribute User user){
        serviceMetier.updateUser(user);
        return "redirect:/home";
    }
    @RequestMapping(path="/form", method = RequestMethod.GET)
    public String getForm(Model model){
        return "form";
    }
}
