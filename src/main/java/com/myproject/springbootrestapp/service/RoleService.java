package com.myproject.springbootrestapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.springbootrestapp.entities.Role;
import com.myproject.springbootrestapp.entities.User;
import com.myproject.springbootrestapp.repos.RoleRepo;

@Service
public class RoleService {
	
	@Autowired
	RoleRepo roleRepo;

    public Object createRoles(List<Role> roles){
    	
    	List<Long> list = new ArrayList<Long>();
    	
    	for(Role role : roles) {
        	if(roleRepo.existsById(role.getId())){
        		list.add(role.getId());
        	} 
    	} 
    	
    	if(!list.isEmpty()) {
    		return "Role Id or Ids "+ list.toString() + " already exist";
    	} else {
        	return roleRepo.saveAll(roles);
    	}
    }

	public List<Role> getRoles() {
		return roleRepo.findAll();
	}
	
    public Object getRoleByRoleId(long id){
        return roleRepo.existsById(id) ? roleRepo.findById(id) : "User with Id " + id + " do not exist";
    }
}
