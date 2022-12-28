package com.DomDevs.app.rest.Repo;

import com.DomDevs.app.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findAllByFirstName(String firstName);
    List<User> findAllByLastName(String lastName);
    List<User> findAllByAge(int age);
}
