package com.myproject.springbootrestapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.springbootrestapp.entities.Role;
import com.myproject.springbootrestapp.entities.User;
import com.myproject.springbootrestapp.service.RoleService;

@RestController
@RequestMapping(value = "/api/v1")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	//Get all users
	@GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Role> getRoles(){
		return roleService.getRoles();
	}
	
	//Get a single role by role_id
	@GetMapping(value = "/roles/{role_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getUserByUserId(@PathVariable("role_id") long id){
		return roleService.getRoleByRoleId(id);
	}
	
	//create a list of roles
	@PostMapping(value = "/roles",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Object createRoles(@RequestBody List<Role> roles){
		return roleService.createRoles(roles);
	}
}
