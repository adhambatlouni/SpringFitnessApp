package com.csis231.api.controller;

import com.csis231.api.model.User;
import com.csis231.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/signUp")
public class SignUpController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User savedUser = userService.addUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/usernameExists")
    public boolean checkUsername(@RequestParam String username) {
        return userService.usernameExists(username);
    }

    @GetMapping("/emailExists")
    public boolean checkEmail(@RequestParam String email) {
        return userService.emailExists(email);
    }
}

