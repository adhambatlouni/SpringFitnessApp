package com.csis231.api.service;

import com.csis231.api.model.*;
import com.csis231.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.*;

@Service
public class FavoriteMealService {

    @Autowired
    private FavoriteMealRepository favoriteMealRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

    public List<FavoriteMeal> getUserFavoriteMealsByUserIdAndMealId(Integer userId, Integer mealId) {
        return favoriteMealRepository.findByUserIdAndMealId(userId, mealId);
    }

    public FavoriteMeal addFavoriteMeal(@Valid FavoriteMeal favoriteMeal) {
        favoriteMealRepository.save(favoriteMeal);
        return favoriteMeal;
    }

    public void deleteFavoriteMeal(Integer userId, Integer mealId) {
        List<FavoriteMeal> favoriteMeals = favoriteMealRepository.findByUserIdAndMealId(userId, mealId);
        if (favoriteMeals.isEmpty()) {
            System.out.println("No record found to delete");
        } else {
            for (FavoriteMeal favoriteMeal : favoriteMeals) {
                System.out.println("Deleting record with id " + favoriteMeal.getId());
                favoriteMealRepository.delete(favoriteMeal);
            }
        }
    }

    public boolean isMealInFavorites(Integer userId, Integer mealId) {
        List<FavoriteMeal> favoriteMeals = favoriteMealRepository.findByUserIdAndMealId(userId, mealId);
        return !favoriteMeals.isEmpty();
    }

    public void updateFavoriteMeal(FavoriteMeal favoriteMeal) {
        favoriteMealRepository.save(favoriteMeal);
    }

    public List<Map<String, Object>> getUserFavoriteMealInfo(Integer userId) {
        List<FavoriteMeal> favoriteMeals = favoriteMealRepository.findByUserId(userId);
        List<Map<String, Object>> favoriteMealInfoList = new ArrayList<>();

        if (favoriteMeals.isEmpty()) {
            System.out.println("No favorite meals found for given user ID and meal ID");
            return favoriteMealInfoList;
        }

        for (FavoriteMeal favoriteMeal : favoriteMeals) {
            User user = userRepository.findById(favoriteMeal.getUserId()).orElse(null);
            Meal meal = mealRepository.findById(favoriteMeal.getMealId()).orElse(null);

            if (user == null || meal == null) {
                System.out.println("User or meal not found for given IDs");
                continue;
            }

            Map<String, Object> favoriteMealInfo = new HashMap<>();
            favoriteMealInfo.put("userName", user.getUsername());
            favoriteMealInfo.put("userEmail", user.getEmail());
            favoriteMealInfo.put("mealCategory", meal.getMealCategory());
            favoriteMealInfo.put("mealDuration", meal.getMealDuration());
            favoriteMealInfo.put("mealTimesPerWeek", meal.getMealTimesPerWeek());
            favoriteMealInfo.put("mealDifficulty", meal.getMealDifficulty());
            favoriteMealInfo.put("mealImage", meal.getMealImage());
            favoriteMealInfoList.add(favoriteMealInfo);
        }
        return favoriteMealInfoList;
    }
}
