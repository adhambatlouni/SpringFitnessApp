package com.csis231.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_favorite_meals")
public class FavoriteMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_favorite_meal_id", nullable = false)
    private Integer id;

    @NotNull(message = "user_id cannot be null")
    @Column(name = "user_id", nullable = false, insertable = true)
    private Integer userId;

    @NotNull(message = "meal_id cannot be null")
    @Column(name = "meal_id", nullable = false, insertable = true)
    private Integer mealId;

    @Column(name = "is_favorite", columnDefinition = "tinyint(1) default 0")
    private boolean isFavorite;

    public FavoriteMeal() {
    }

    public FavoriteMeal(Integer id, Integer userId, Integer mealId, boolean isFavorite) {
        this.id = id;
        this.userId = userId;
        this.mealId = mealId;
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

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
