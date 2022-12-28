package com.DomDevs.app.rest.UserService;


import com.DomDevs.app.rest.Models.User;

import java.util.List;

public interface IUserService {
    List<User> findAllPaginated(int pageNo, int pageSize);
}
