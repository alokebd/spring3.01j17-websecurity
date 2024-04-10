package com.vision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vision.entity.UserInfo;
import com.vision.service.UserService;

@RestController
@RequestMapping ("/api")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }

}
