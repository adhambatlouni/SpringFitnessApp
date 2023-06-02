package com.csis231.api.controller;

import com.csis231.api.model.User;
import com.csis231.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("api/v1/userProfile")
public class ProfilePageController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Integer userId, @RequestBody User updatedUser) {

        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        String username = updatedUser.getUsername();
        String email = updatedUser.getEmail();

        if (!existingUser.getUsername().equals(username) && userService.usernameExists(username)) {
            return ResponseEntity.badRequest().build();
        }

        if (!existingUser.getEmail().equals(email) && userService.emailExists(email)) {
            return ResponseEntity.badRequest().build();
        }

        existingUser.setUsername(username);
        existingUser.setEmail(email);

        User savedUser = userService.updateUser(existingUser);

        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/updateUserImage/{userId}")
    public ResponseEntity<User> updateUserImage(@PathVariable Integer userId, @RequestBody Map<String, String> requestBody) {
        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        String imageData = requestBody.get("imageData");
        byte[] imageBytes = Base64.getDecoder().decode(imageData);

        try {
            existingUser.setUserProfileImage(imageBytes);
            User savedUser = userService.updateUser(existingUser);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getUserImage/{userId}")
    public ResponseEntity<byte[]> getUserImage(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageData = user.getUserProfileImage();

        if (imageData == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imageData);
    }
}
