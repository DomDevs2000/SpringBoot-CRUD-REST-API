package com.DomDevs.app.rest.Repo;

import com.DomDevs.app.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
