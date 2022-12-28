package com.DomDevs.app.rest.Repo;

import com.DomDevs.app.rest.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
//    Page<User> findAllByAge(Pageable pageable);

    Page<User> findAllByFirstName(String firstName, Pageable pageable);

    Page<User> findAllByLastName(String lastName, Pageable pageable);


}
