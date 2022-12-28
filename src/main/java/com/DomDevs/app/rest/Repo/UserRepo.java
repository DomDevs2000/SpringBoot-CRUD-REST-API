package com.DomDevs.app.rest.Repo;

import com.DomDevs.app.rest.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;



public interface UserRepo extends JpaRepository<User, Long> {
    Page<User> findAllByAge(int age, Pageable pageable);

}
