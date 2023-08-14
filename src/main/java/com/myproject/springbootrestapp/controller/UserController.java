package com.myproject.springbootrestapp.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import com.myproject.springbootrestapp.service.RoleService;
import com.myproject.springbootrestapp.service.UserService;
import com.myproject.springbootrestapp.entities.Role;
import com.myproject.springbootrestapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	//Get all users
	@GetMapping(value = "/users", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	//Get a single user by user_id
	@GetMapping(value = "/users/{user_id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getUserByUserId(@PathVariable("user_id") long id){
		return userService.getUserByUserId(id);
	}
	
	//create a list of users
	@PostMapping(value = "/users",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Object createUsers(@RequestBody List<User> users) throws UnsupportedEncodingException{
		return userService.createUsers(users);
	}
	
	//update a single user by user_id (full update)
	@PutMapping(value = "/users/{user_id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Object updateUser(@RequestBody User user, @PathVariable("user_id") long id){
		return userService.updateUser(user, id);
	}
	
	//Delete a users by user id
	@DeleteMapping(value = "/users/{user_id}")
	public String deleteUser(@PathVariable("user_id")  long id){
		return userService.deleteUser(id);
	}

	//Delete a role from a User
	@DeleteMapping(value = "/users/{user_id}/roles/{role_id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Object deleteARolefromAUser(@PathVariable("user_id") long uid,
			@PathVariable("role_id") long rid){
		return userService.deleteARolefromAUser(uid, rid);
	}
}
