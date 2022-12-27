package com.DomDevs.app.rest.Controller;

import com.DomDevs.app.rest.Models.User;
import com.DomDevs.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class apiControllers {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage() {
        return "Hello World";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/users/create")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "User saved..";
    }

}
