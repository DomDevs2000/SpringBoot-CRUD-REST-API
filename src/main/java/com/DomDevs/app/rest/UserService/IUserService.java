package com.DomDevs.app.rest.UserService;


import com.DomDevs.app.rest.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    List<User> findAllPaginated(int pageNo, int pageSize);

//    List<User> findByAgePaginated(int pageNo, int pageSize, int age);
//    Page<User> findAllByAge(int age);

//    Page<User> findAllByFirstName(String firstName);
//
//    Page<User> findAllByLastName(String lastName);

}
