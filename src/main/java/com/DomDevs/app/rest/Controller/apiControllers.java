package com.DomDevs.app.rest.Controller;

import com.DomDevs.app.rest.Models.User;
import com.DomDevs.app.rest.Repo.UserRepo;
import org.apache.coyote.Request;
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

    @PutMapping(value = "users/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        userRepo.save(updatedUser);
        return "User updated...";
    }

    @DeleteMapping(value="users/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deletedUser = userRepo.findById(id).get();
        userRepo.delete(deletedUser);
        return "User has been deleted";
    }
}
