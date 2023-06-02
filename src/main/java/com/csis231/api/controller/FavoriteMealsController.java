package com.csis231.api.controller;

import com.csis231.api.model.FavoriteMeal;
import com.csis231.api.service.FavoriteMealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/userFavoriteMeals/{userId}/{mealId}")
public class FavoriteMealsController {

    @Autowired
    private FavoriteMealService favoriteMealService;

    @GetMapping("/getUserFavoriteMeals")
    public List<FavoriteMeal> getUserFavoriteMealsByUserIdAndMealId(@PathVariable Integer userId,
                                                                    @PathVariable Integer mealId) {
        return favoriteMealService.getUserFavoriteMealsByUserIdAndMealId(userId, mealId);
    }

    @GetMapping("/isMealInFavorites")
    public boolean isMealInFavorites(@PathVariable Integer userId, @PathVariable Integer mealId) {
        return favoriteMealService.isMealInFavorites(userId, mealId);
    }

    @DeleteMapping("/deleteFavoriteMeal")
    public ResponseEntity<String> deleteUserFavoriteMeal(@PathVariable Integer userId, @PathVariable Integer mealId) {
        try {
            favoriteMealService.deleteFavoriteMeal(userId, mealId);
            return ResponseEntity.ok("Successfully deleted user favorite meal with user Id: " + userId +
                    " and meal Id: " + mealId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user favorite " +
                    "meal with user Id: " + userId + " and meal Id: " + mealId + "\n" + e.getMessage());
        }
    }

    @PostMapping("/addFavoriteMeal")
    public ResponseEntity<FavoriteMeal> addFavoriteMeal(@Valid @RequestBody FavoriteMeal favoriteMeal,
                                                        BindingResult result) {
        if(result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        FavoriteMeal savedUserFavoriteMeal = favoriteMealService.addFavoriteMeal(favoriteMeal);
        return new ResponseEntity<FavoriteMeal>(savedUserFavoriteMeal, HttpStatus.CREATED);
    }

    @PutMapping("/updateFavoriteMeal")
    public ResponseEntity<String> updateFavoriteMeal(@PathVariable Integer userId, @PathVariable Integer mealId) {
        try {
            List<FavoriteMeal> favoriteMeals = favoriteMealService.getUserFavoriteMealsByUserIdAndMealId(userId, mealId);
            if (favoriteMeals.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User favorite meal not found with user ID: "
                        + userId + " and meal ID: " + mealId);
            }
            FavoriteMeal favoriteMeal = favoriteMeals.get(0);
            favoriteMeal.setFavorite(true);
            favoriteMealService.updateFavoriteMeal(favoriteMeal);
            return ResponseEntity.ok("Successfully updated user favorite meal with user ID: " + userId +
                    " and meal ID: " + mealId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user favorite meal " +
                    "with user ID: " + userId + " and meal ID: " + mealId + "\n" + e.getMessage());
        }
    }

    @GetMapping("/isMealFavorite")
    public List<FavoriteMeal> isMealFavorite(@PathVariable Integer userId, @PathVariable Integer mealId) {
        return favoriteMealService.getUserFavoriteMealsByUserIdAndMealId(userId, mealId);
    }

    @GetMapping("/getMealInfo")
    public ResponseEntity<List<Map<String, Object>>> getUserFavoriteMealInfo(@PathVariable Integer userId) {
        List<Map<String, Object>> favoriteMealInfoList = favoriteMealService.getUserFavoriteMealInfo(userId);
        return ResponseEntity.ok(favoriteMealInfoList);
    }
}
