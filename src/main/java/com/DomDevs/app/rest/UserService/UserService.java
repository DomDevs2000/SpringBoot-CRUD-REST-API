package com.DomDevs.app.rest.UserService;

import com.DomDevs.app.rest.Models.User;
import com.DomDevs.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> findAllPaginated(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> pagedResult = userRepo.findAll(pageable);
        return pagedResult.toList();
    }

    @Override
    public List<User> findAllByAge(int age) {
        return userRepo.findAllByAge(age);
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return userRepo.findAllByFirstName(firstName);
    }

    @Override
    public List<User> findAllByLastName(String lastName) {
        return userRepo.findAllByLastName(lastName);
    }

}
