package ru.kata.spring.boot_security.demo.contriller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }



    @GetMapping("admin/user-create")
    public String createUserForm(Model model){
        model.addAttribute("user", new User());
        return "user-create";

    }
    @PostMapping("admin/user-create")
    public String createUser( @ModelAttribute("user")User user,
                              @ModelAttribute("role") String[] role){
        userService.saveUser(user, role);
        return "redirect:/admin";
    }



    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        model.addAttribute("user", userService.findByUserName(principal.getName()));
        return "user";
    }
    @GetMapping("/admin/info")
    public String adminPage(Principal principal, Model model) {
        model.addAttribute("user",
                userService.findByUserName(principal.getName()));
        return "admin-info";
    }


    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "user-update";
    }
    @PostMapping("/admin/user-update")
    public String updateUser(@ModelAttribute("user") User user,
                             @ModelAttribute("role")String[] roles){
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }



    @GetMapping("admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

}

