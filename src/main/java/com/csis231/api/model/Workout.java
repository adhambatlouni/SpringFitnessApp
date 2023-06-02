package com.csis231.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="workout_id", nullable = false)
    private Integer id;

    @NotEmpty(message = "workout category cannot be empty")
    @Size(max = 50)
    @Column(name = "workout_category", nullable = false)
    private String workoutCategory;

    @NotEmpty(message = "workout duration cannot be empty")
    @Size(max = 20)
    @Column(name = "workout_duration", nullable = false)
    private String workoutDuration;

    @NotEmpty(message = "workout times per week cannot be empty")
    @Size(max = 20)
    @Column(name = "workout_times_per_week", nullable = false)
    private String workoutTimesPerWeek;

    @NotEmpty(message = "workout difficulty cannot be empty")
    @Size(max = 20)
    @Column(name = "workout_difficulty", nullable = false)
    private String workoutDifficulty;

    @Lob
    @Column(name = "workout_image", nullable = false)
    private byte[] workoutImage;

    public Workout() {
    }

    public Workout(Integer id, String workoutCategory, String workoutDuration, String workoutTimesPerWeek, String workoutDifficulty, byte[] workoutImage) {
        this.id = id;
        this.workoutCategory = workoutCategory;
        this.workoutDuration = workoutDuration;
        this.workoutTimesPerWeek = workoutTimesPerWeek;
        this.workoutDifficulty = workoutDifficulty;
        this.workoutImage = workoutImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkoutCategory() {
        return workoutCategory;
    }

    public void setWorkoutCategory(String workoutCategory) {
        this.workoutCategory = workoutCategory;
    }

    public String getWorkoutDuration() {
        return workoutDuration;
    }

    public void setWorkoutDuration(String workoutDuration) {
        this.workoutDuration = workoutDuration;
    }

    public String getWorkoutTimesPerWeek() {
        return workoutTimesPerWeek;
    }

    public void setWorkoutTimesPerWeek(String workoutTimesPerWeek) {
        this.workoutTimesPerWeek = workoutTimesPerWeek;
    }

    public String getWorkoutDifficulty() {
        return workoutDifficulty;
    }

    public void setWorkoutDifficulty(String workoutDifficulty) {
        this.workoutDifficulty = workoutDifficulty;
    }

    public byte[] getWorkoutImage() {
        return workoutImage;
    }

    public void setWorkoutImage(byte[] workoutImage) {
        this.workoutImage = workoutImage;
    }
}
