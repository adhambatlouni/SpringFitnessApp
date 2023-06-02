package com.csis231.api.service;


import com.csis231.api.exception.ResourceNotFoundException;
import com.csis231.api.model.User;
import com.csis231.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(@Valid User user) {
        Optional<User> userByUsername = userRepository.findByUsername(user.getUsername());
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByUsername.isPresent()) {
            throw new IllegalStateException("username taken.");
        }
        if(userByEmail.isPresent()) {
            throw new IllegalStateException("email taken.");
        }
        userRepository.save(user);
        return user;
    }

    public boolean usernameExists(String username) {
        Optional<User> userByUsername = userRepository.findByUsername(username);
        return userByUsername.isPresent();
    }

    public boolean emailExists(String email) {
        Optional<User> userByEmail = userRepository.findByEmail(email);
        return userByEmail.isPresent();
    }

    public boolean passwordExists(String password) {
            Optional<User> userByPassword = userRepository.findByPassword(password);
            return userByPassword.isPresent();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public Integer login(String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if(user.isPresent()) {
            return user.get().getId();
        } else {
            throw new IllegalStateException("Invalid credentials.");
        }
    }

    public User updateUser(User user) {
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }
}