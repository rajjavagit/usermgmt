package com.myproject.springbootrestapp.repos;

import com.myproject.springbootrestapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
