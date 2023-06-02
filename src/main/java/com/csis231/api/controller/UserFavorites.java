package com.csis231.api.controller;

import com.csis231.api.service.FavoriteMealService;
import com.csis231.api.service.FavoriteWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/FavoritesInfo/{userId}")
public class UserFavorites {

    @Autowired
    private FavoriteMealService favoriteMealService;

    @Autowired
    private FavoriteWorkoutService favoriteWorkoutService;

    @GetMapping("/getMealInfo")
    public ResponseEntity<List<Map<String, Object>>> getUserFavoriteMealInfo(@PathVariable Integer userId) {
        List<Map<String, Object>> favoriteMealInfoList = favoriteMealService.getUserFavoriteMealInfo(userId);
        return ResponseEntity.ok(favoriteMealInfoList);
    }

    @GetMapping("/getWorkoutInfo")
    public ResponseEntity<List<Map<String, Object>>> getUserFavoriteWorkoutInfo(@PathVariable Integer userId) {
        List<Map<String, Object>> favoriteWorkoutInfoList = favoriteWorkoutService.getUserFavoriteWorkoutInfo(userId);
        return ResponseEntity.ok(favoriteWorkoutInfoList);
    }
}
