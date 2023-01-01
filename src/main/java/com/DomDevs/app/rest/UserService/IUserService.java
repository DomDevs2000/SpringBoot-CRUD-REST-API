package com.DomDevs.app.rest.UserService;


import com.DomDevs.app.rest.Models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll();

    List<User> findAllPaginated(int page, int pageSize);

    List<User> findAllByAge(int age);

    List<User> findAllByFirstName(String firstName);

    List<User> findAllByLastName(String lastName);

    Optional<User> findById(long id);

    User saveUser(User user);

}
