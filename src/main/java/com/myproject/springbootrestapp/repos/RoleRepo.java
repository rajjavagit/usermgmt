package com.myproject.springbootrestapp.repos;

import com.myproject.springbootrestapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

}
