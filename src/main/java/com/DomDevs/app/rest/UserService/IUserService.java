package com.DomDevs.app.rest.UserService;


import com.DomDevs.app.rest.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    List<User> findAllPaginated(int pageNo, int pageSize);

    List<User> findAllByAge(int age);

    List<User> findAllByFirstName(String firstName);

    List<User> findAllByLastName(String lastName);
}
