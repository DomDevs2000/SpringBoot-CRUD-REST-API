package com.DomDevs.app.rest.UserService;


import com.DomDevs.app.rest.Models.User;

import java.util.List;

public interface IUserService {
    List<User> findAllPaginated(int page, int pageSize);

    List<User> findAllByAge(int age);

    List<User> findAllByFirstName(String firstName);

    List<User> findAllByLastName(String lastName);
}
