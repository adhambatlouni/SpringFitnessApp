package com.csis231.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private Integer id;

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 2, max = 25)
    @Column(name = "user_name", nullable = false)
    private String username;

    @NotEmpty(message = "email cannot be empty")
    @Size(min = 10, max = 45)
    @Column(name = "user_email", nullable = false)
    private String email;

    @NotEmpty(message = "password cannot be empty")
    @Size(min = 6, max = 100)
    @Column(name = "user_password", nullable = false)
    private String password;

    @Lob
    @Column(name = "user_profile_image", columnDefinition = "LongBlob")
    private byte[] userProfileImage;

    public User() {
    }

    public User(Integer id, String username, String email, String password, byte[] userProfileImage) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userProfileImage = userProfileImage;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getUserProfileImage() { return userProfileImage; }

    public void setUserProfileImage(byte[] userProfileImage) { this.userProfileImage = userProfileImage; }
}
