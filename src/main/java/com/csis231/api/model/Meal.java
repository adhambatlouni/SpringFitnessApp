package com.csis231.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "meal")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meal_id", nullable = false)
    private Integer id;

    @NotEmpty(message = "meal category cannot be empty")
    @Size(max = 50)
    @Column(name = "meal_category", nullable = false)
    private String mealCategory;

    @NotEmpty(message = "meal duration cannot be empty")
    @Size(max = 20)
    @Column(name = "meal_duration", nullable = false)
    private String mealDuration;

    @NotEmpty(message = "meal times per week cannot be empty")
    @Size(max = 10)
    @Column(name = "meal_times_per_week", nullable = false)
    private String mealTimesPerWeek;

    @NotEmpty(message = "meal difficulty cannot be empty")
    @Size(max = 20)
    @Column(name = "meal_difficulty", nullable = false)
    private String mealDifficulty;

    @Lob
    @Column(name = "meal_image", nullable = false)
    private byte[] mealImage;

    public Meal() {
    }

    public Meal(Integer id, String mealCategory, String mealDuration, String mealTimesPerWeek, String mealDifficulty, byte[] mealImage) {
        this.id = id;
        this.mealCategory = mealCategory;
        this.mealDuration = mealDuration;
        this.mealTimesPerWeek = mealTimesPerWeek;
        this.mealDifficulty = mealDifficulty;
        this.mealImage = mealImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getMealDuration() {
        return mealDuration;
    }

    public void setMealDuration(String mealDuration) {
        this.mealDuration = mealDuration;
    }

    public String getMealTimesPerWeek() {
        return mealTimesPerWeek;
    }

    public void setMealTimesPerWeek(String mealTimesPerWeek) {
        this.mealTimesPerWeek = mealTimesPerWeek;
    }

    public String getMealDifficulty() {
        return mealDifficulty;
    }

    public void setMealDifficulty(String mealDifficulty) {
        this.mealDifficulty = mealDifficulty;
    }

    public byte[] getMealImage() {
        return mealImage;
    }

    public void setMealImage(byte[] mealImage) {
        this.mealImage = mealImage;
    }
}
