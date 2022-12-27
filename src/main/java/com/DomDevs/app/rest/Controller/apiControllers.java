package com.DomDevs.app.rest.Controller;

import com.DomDevs.app.rest.Exceptions.UserNotFoundException;
import com.DomDevs.app.rest.Models.User;
import com.DomDevs.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class apiControllers {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage() {
        return "Welcome to my CRUD REST API project using Spring Boot, JPA, Maven and MYSQL. " +
                "Please use Postman/Insomnia to create POST/PUT/DELETE requests. " +
                "To get all users, please submit a GET request to '/users' " +
                "To create a new user, please submit a POST request to '/users/create' and fill in the JSON body with firstName, lastName and age. " +
                "To update a user, please submit a PUT request to '/users/update/{id}' and fill in the JSON body with the content you wish to update " +
                "To delete a user, please submit a DELETE request to 'users/delete/{id}' ";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        try {
            return userRepo.findAll();
        } catch (Exception e) {
//            throw new UserNotFoundException();
            return null;
        }

    }

    @PostMapping(value = "/users/create")
    public String saveUser(@RequestBody User user) {
        try {
            userRepo.save(user);
            return "User Created";
        } catch (Exception e) {
            return "Error Creating User...";
        }

    }

    @GetMapping(value = "/users/{id}")
    public User getOneUser(@PathVariable long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping(value = "users/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
            User updatedUser = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setAge(user.getAge());
            userRepo.save(updatedUser);
            return "Updated User With The id: " + id;
    }

    @DeleteMapping(value = "users/delete/{id}")
    public String deleteUser(@PathVariable long id) {
            User deletedUser = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
            userRepo.delete(deletedUser);
            return "Deleted User With The id: " + id;
    }
}
