package com.csis231.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_favorite_workouts")
public class FavoriteWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_favorite_workout_id", nullable = false)
    private Integer id;

    @NotNull(message = "user_id cannot be null")
    @Column(name = "user_id", nullable = false, insertable = true)
    private Integer userId;

    @NotNull(message = "workout_id cannot be null")
    @Column(name = "workout_id", nullable = false, insertable = true)
    private Integer workoutId;

    @Column(name = "is_favorite", columnDefinition = "tinyint(1) default 0")
    private boolean isFavorite;

    public FavoriteWorkout() {

    }
    public FavoriteWorkout(Integer id, Integer userId, Integer workoutId, boolean isFavorite) {
        this.id = id;
        this.userId = userId;
        this.workoutId = workoutId;
        this.isFavorite = isFavorite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Integer workoutId) {
        this.workoutId = workoutId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
