package com.DomDevs.app.rest.Controller;

import com.DomDevs.app.rest.Exceptions.*;
import com.DomDevs.app.rest.Models.User;
import com.DomDevs.app.rest.Repo.UserRepo;
import com.DomDevs.app.rest.UserService.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class apiControllers {
    @Autowired
    private IUserService userService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage() {
        return "Welcome to my CRUD REST API project using Spring Boot, JPA, Maven and MYSQL. " + "Please use Postman/Insomnia to create POST/PUT/DELETE requests. " + "To get all users, please submit a GET request to '/users' " + "To create a new user, please submit a POST request to '/users/create' and fill in the JSON body with firstName, lastName and age. " + "To update a user, please submit a PUT request to '/users/update/{id}' and fill in the JSON body with the content you wish to update " + "To delete a user, please submit a DELETE request to 'users/delete/{id}' ";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping(value = "/users/{pageNo}/{pageSize}")
    public List<User> getPaginatedUsers(@PathVariable int pageNo, @PathVariable int pageSize) {
        return userService.findAllPaginated(pageNo, pageSize);
    }

    @PostMapping(value = "/users/create")
    public String createNewUser(@RequestBody @Valid User user, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        } else {
            userRepo.save(user);
            return "User Created";
        }

    }

    @GetMapping(value = "/users/id/{id}")
    public User getOneUser(@PathVariable long id) {
        try {
            return userRepo.findById(id).get();
        } catch (RuntimeException exception) {
            throw new UserNotFoundException(id);
        }
    }

    @GetMapping("/users/age/{age}")
    public List<User> getAllUsersByAge(@Valid @PathVariable int age) {
        try {
            return userService.findAllByAge(age);
        } catch (RuntimeException exception) {
            throw new AgeNotFoundException(age);
        }
    }


    @GetMapping("/users/firstname/{firstName}")
    public List<User> getAllByFirstName(@PathVariable String firstName) {
        try {
            return userService.findAllByFirstName(firstName);
        } catch (RuntimeException exception) {
            exception.getStackTrace();
            throw new FirstNameNotFoundException(firstName);
        }
    }

    @GetMapping("/users/lastname/{lastName}")
    public List<User> getAllByLastname(@PathVariable String lastName) {
        try {
            return userService.findAllByLastName(lastName);
        } catch (RuntimeException exception) {
            exception.getStackTrace();
            throw new LastNameNotFoundException(lastName);
        }
    }

    @PutMapping(value = "users/update/{id}")
    public String updateUser(@PathVariable long id, @Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        } else {
            User updatedUser = userRepo.findById(id).get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setAge(user.getAge());
            userRepo.save(updatedUser);
            return "Updated User With The id: " + id;
        }
//        try {
//            User updatedUser = userRepo.findById(id).get();
//            updatedUser.setFirstName(user.getFirstName());
//            updatedUser.setLastName(user.getLastName());
//            updatedUser.setAge(user.getAge());
//            userRepo.save(updatedUser);
//            return "Updated User With The id: " + id;
//        } catch (ValidationException exception) {
//            if (errors.hasErrors()) {
//                exception.getStackTrace();
//                throw new ValidationException(errors);
//            }
//            return "Error:";
//        }
    }

    @DeleteMapping(value = "users/delete/{id}")
    public String deleteUser(@PathVariable @Valid long id) {
        try {
            User deletedUser = userRepo.findById(id).get();
            userRepo.delete(deletedUser);
            return "Deleted User With The id: " + id;
        } catch (RuntimeException exception) {
            exception.getStackTrace();
            throw new UserNotFoundException(id);
        }
    }
}
