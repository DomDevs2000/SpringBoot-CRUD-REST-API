package com.DomDevs.app.rest.Controller;

import com.DomDevs.app.rest.Exceptions.UserNotFoundException;
import com.DomDevs.app.rest.Models.User;
import com.DomDevs.app.rest.Repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class apiControllers {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage() {
        return "Welcome to my CRUD REST API project using Spring Boot, JPA, Maven and MYSQL. " + "Please use Postman/Insomnia to create POST/PUT/DELETE requests. " + "To get all users, please submit a GET request to '/users' " + "To create a new user, please submit a POST request to '/users/create' and fill in the JSON body with firstName, lastName and age. " + "To update a user, please submit a PUT request to '/users/update/{id}' and fill in the JSON body with the content you wish to update " + "To delete a user, please submit a DELETE request to 'users/delete/{id}' ";
    }

    @GetMapping("/users")
    public Page<User> getUsers() {
        Pageable wholePage = Pageable.unpaged();
        return userRepo.findAll(wholePage);
    }

    @PostMapping(value = "/users/create")
    public String saveUser(@RequestBody @Valid User user) {
        userRepo.save(user);
        return "User Created";
    }

    @GetMapping(value = "/users/{id}")
    public User getOneUser(@PathVariable long id) {
        try {
            return userRepo.findById(id).get();
        } catch (RuntimeException exception) {
            throw new UserNotFoundException(id);
        }
    }

    @GetMapping("/users/age/{age}")
    public Page<User> findAllByAge(@Valid @PathVariable int age) {
        return userRepo.findAllByAge(age, Pageable.unpaged());
    }
        @GetMapping("/users/firstname/{firstName}")
    public List<User> findAllByFirstName(@PathVariable String firstName) {
        return userRepo.findAllByFirstName(firstName);
    }
    @GetMapping("/users/lastname/{lastName}")
    public List<User> findAllByLastname(@PathVariable String lastName) {
        return userRepo.findAllByLastName(lastName);
    }
    @PutMapping(value = "users/update/{id}")
    public String updateUser(@PathVariable long id, @Valid @RequestBody User user) {
        try {
            User updatedUser = userRepo.findById(id).get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setAge(user.getAge());
            userRepo.save(updatedUser);
            return "Updated User With The id: " + id;
        } catch (RuntimeException exception) {
            throw new UserNotFoundException(id);
        }
    }

    @DeleteMapping(value = "users/delete/{id}")
    public String deleteUser(@PathVariable @Valid long id) {
        try {
            User deletedUser = userRepo.findById(id).get();
            userRepo.delete(deletedUser);
            return "Deleted User With The id: " + id;
        } catch (RuntimeException exception) {
            throw new UserNotFoundException(id);
        }
    }
}
