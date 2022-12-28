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
    public List<User> findAllPaginated(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo,pageSize);
        Page<User> pagedResult = userRepo.findAll(page);
        return pagedResult.toList();
    }
    //    public List<User> findByAgePaginated(int pageNo, int pageSize, int age) {
//        Pageable page = PageRequest.of(pageNo,pageSize);
//        Page<User> pagedResult = userRepo.findAll(page);
//        return pagedResult.toList();
//    }

//    @Override
//    public Page<User> findAllByAge(int age) {
//        return null;
//    }

//    @Override
//    public Page<User> findAllByFirstName(String firstName) {
//        return null;
//    }
//
//    @Override
//    public Page<User> findAllByLastName(String lastName) {
//        return null;
//    }
}
