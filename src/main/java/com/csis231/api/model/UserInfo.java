package com.csis231.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "fitbit_user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_info_id", nullable = false)
    private Integer id;

    @NotNull(message = "user_id cannot be null")
    @Column(name = "user_id", nullable = false, insertable = true)
    private Integer user_id;

    @NotEmpty(message = "gender cannot be empty")
    @Size(max = 10)
    @Column(name = "user_gender", nullable = false)
    private String gender;

    @NotEmpty(message = "country cannot be empty")
    @Size(max = 50)
    @Column(name = "user_country", nullable = false)
    private String country;

    @NotNull(message = "height cannot be empty")
    @Column(name = "user_height", nullable = false)
    private Double height;

    @NotNull(message = "weight cannot be empty")
    @Column(name = "user_weight", nullable = false)
    private Double weight;

    @NotNull(message = "age cannot be null")
    @Column(name = "user_age", nullable = false)
    private Integer age;

    @NotEmpty(message = "goal cannot be empty")
    @Size(max = 50)
    @Column(name = "user_goal", nullable = false)
    private String goal;

    public UserInfo() {}

    public Integer getId() {
        return id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public UserInfo(Integer id, Integer user_id, String gender, String country, Double height, Double weight, Integer age, String goal) {
        this.id = id;
        this.user_id = user_id;
        this.gender = gender;
        this.country = country;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.goal = goal;
    }

    public void setId(Integer id) { this.id = id;}

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
