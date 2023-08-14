package com.myproject.springbootrestapp.service;

import com.myproject.springbootrestapp.entities.Role;

import com.myproject.springbootrestapp.entities.User;
import com.myproject.springbootrestapp.repos.RoleRepo;
import com.myproject.springbootrestapp.repos.UserRepo;
//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
//import java.util.Base64;
import org.apache.commons.codec.binary.Base64;

import org.apache.commons.codec.binary.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    
    private static  Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public List<User> getUsers(){
        return userRepo.findAll();
    }
    
    public Object getUserByUserId(long id){
        return userRepo.existsById(id) ? userRepo.findById(id) : "User with id " + id + " do not exist";
    }
    
    public Object createUsers(List<User> users) throws UnsupportedEncodingException {
    	
    	//Encrypt password before storing to the database
    	for(User user : users) {
    		user.setPassword(new Pbkdf2PasswordEncoder().encode(user.getPassword()));
    	}
    	
    	LOGGER.debug("User details Log: " + users.toString());
    	
    	List<Long> list = new ArrayList<Long>();
    	
    	for(User user : users) {
        	if(userRepo.existsById(user.getId())){
        		list.add(user.getId());
        	} 
    	} 
    	
    	if(!list.isEmpty()) {
    		return "Role with id "+ list.toString() + " already exist";
    	} else {
        	return userRepo.saveAll(users);
    	}
    }
    
    public Object updateUser(User user, long id){
    	
    	//Encrypt password before storing to the database
        user.setPassword(new Pbkdf2PasswordEncoder().encode(user.getPassword()));
    	
    	if(!userRepo.existsById(id)){
    		return "User with id "+ id + " do not exist";
    	} 
    	
    	if(user.getId()!=(userRepo.findById(id).get().getId())) {
            return "User Ids do not match";
    	}
    	else
    		return userRepo.save(user);
    }
    
    public String deleteUser(long id){
    	if(userRepo.existsById(id)) {
    		userRepo.deleteById(id);
    		return "User with id "+ id + " is deleted successfully!";
    	}
    	return "User with id " + id + " do not exist";
    }
    
    public Object deleteARolefromAUser(long uid, long rid) {

        if(!userRepo.existsById(uid)) {
        	return "User with id " + uid + " do not exist"; 
        } else if (!roleRepo.existsById(rid)){
        	return "Role with id " + rid + " do not exist"; 
        } else {
            User user = userRepo.findById(uid).get();
            Role role = roleRepo.findById(rid).get();
        	user.getRoles().remove(role);
        	userRepo.save(user);
        	return user;
        }
    }
}

//TODO
//Configure Security for POST/PUT APIs
