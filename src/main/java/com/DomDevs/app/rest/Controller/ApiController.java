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
import java.util.Optional;
import java.util.logging.Logger;


@RestController
public class ApiController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserRepo userRepo;

    private final Logger LOGGER = Logger.getLogger(String.valueOf(ApiController.class));

    @GetMapping("/")
    public String getPage() {
        return  "Welcome to my CRUD REST API project using Spring Boot, JPA, Maven and MYSQL. \n" + "Please use Postman/Insomnia to create POST/PUT/DELETE requests. \n" + "To get all users, please submit a GET request to '/users', for pagination of results, please use /users/page={page}/size={pageSize}. \n" + "To create a new user, please submit a POST request to '/users' and fill in the JSON body with firstName, lastName and age. \n" + "To update a user, please submit a PUT request to '/users/{id}' and fill in the JSON body with the content you wish to update.\n" + "To delete a user, please submit a DELETE request to 'users/{id}'\n";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        try {
            LOGGER.info("Successful GET Request");
            return userService.findAll();
        } catch (RuntimeException e) {
            LOGGER.info("Failed to GET /users");
            throw new RuntimeException();
        }


    }

    @GetMapping(value = "/users/page={page}/size={pageSize}")
    public List<User> getPaginatedUsers(@PathVariable int page, @PathVariable int pageSize) {
        try {
            LOGGER.info("Successful GET Request");
            return userService.findAllPaginated(page, pageSize);
        } catch (RuntimeException e) {
            LOGGER.info("Failed to GET Paginated Users");
            throw new RuntimeException();
        }
    }

    @PostMapping(value = "/users")
    public String createNewUser(@RequestBody @Valid User user, BindingResult errors) {
        if (errors.hasErrors()) {
            LOGGER.info("Error:" + errors);
            throw new ValidationException(errors);
        } else {
            LOGGER.info("Successful POST Request");
            userService.saveUser(user);
            return "User Created";
        }
    }

    @GetMapping(value = "/users/{id}")
    public Optional<User> getOneUser(@PathVariable long id) {
        try {
            LOGGER.info("Successful GET Request");
            return userService.findById(id);
        } catch (RuntimeException exception) {
            LOGGER.info("Error Finding User");
            throw new UserNotFoundException(id);
        }
    }

    @GetMapping("/users/age/{age}")
    public List<User> getAllByAge(@Valid @PathVariable int age) {
        try {
            LOGGER.info("Successful GET Request");
            return userService.findAllByAge(age);
        } catch (RuntimeException e) {
            e.getStackTrace();
            LOGGER.info("Error Finding User");
            throw new AgeNotFoundException(age);
        }
    }


    @GetMapping("/users/firstname/{firstName}")
    public List<User> getAllByFirstName(@PathVariable String firstName) {
        try {
            LOGGER.info("Successful GET Request");
            return userService.findAllByFirstName(firstName);
        } catch (RuntimeException exception) {
            exception.getStackTrace();
            LOGGER.info("Error Finding User");
            throw new FirstNameNotFoundException(firstName);
        }
    }

    @GetMapping("/users/lastname/{lastName}")
    public List<User> getAllByLastname(@PathVariable String lastName) {
        try {
            LOGGER.info("Success GET Request");
            return userService.findAllByLastName(lastName);
        } catch (RuntimeException exception) {
            exception.getStackTrace();
            LOGGER.info("Error Finding User");
            throw new LastNameNotFoundException(lastName);
        }
    }

    @PutMapping(value = "users/{id}")
    public String updateUser(@PathVariable long id, @Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        } else {
            try {
                User updatedUser = userService.findById(id).get();
                updatedUser.setFirstName(user.getFirstName());
                updatedUser.setLastName(user.getLastName());
                updatedUser.setAge(user.getAge());
                userService.saveUser(updatedUser);
                LOGGER.info("Successful PUT Request");
                return "Updated User With The id: " + id;
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("Error Finding User");
                throw new UserNotFoundException(id);
            }
        }
    }

    @DeleteMapping(value = "users/{id}")
    public String deleteUser(@PathVariable @Valid long id) {
        try {

            User deletedUser = userService.findById(id).get();
            userRepo.delete(deletedUser);
            LOGGER.info("Successful DELETE Request");
            return "Deleted User With The id: " + id;
        } catch (RuntimeException exception) {
            exception.getStackTrace();
            LOGGER.info("Error Finding User");
            throw new UserNotFoundException(id);
        }
    }
}
