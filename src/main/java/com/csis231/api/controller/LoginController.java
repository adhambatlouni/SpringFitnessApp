package com.csis231.api.controller;

import com.csis231.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
@RequestMapping("api/v1/logIn")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameExists")
    public boolean checkUsername(@RequestParam String username) {
        return userService.usernameExists(username);
    }

    @GetMapping("/passwordExists")
    public boolean checkPassword(@RequestParam String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String hash = Base64.getEncoder().encodeToString(hashBytes);
        return userService.passwordExists(hash);
    }

    @GetMapping("/getUserId")
    public ResponseEntity<Integer> getUserId(@RequestParam String username, @RequestParam String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String hash = Base64.getEncoder().encodeToString(hashBytes);
        Integer userId = userService.login(username, hash);
        return ResponseEntity.ok(userId);
    }
}
