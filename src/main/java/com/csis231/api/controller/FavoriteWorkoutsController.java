package com.csis231.api.controller;

import com.csis231.api.model.FavoriteWorkout;
import com.csis231.api.service.FavoriteWorkoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/userFavoriteWorkouts/{userId}/{workoutId}")
public class FavoriteWorkoutsController {

    @Autowired
    private FavoriteWorkoutService favoriteWorkoutService;

    @GetMapping("/getUserFavoriteWorkouts")
    public List<FavoriteWorkout> getUserFavoriteWorkoutsByUserIdAndWorkoutId(@PathVariable Integer userId,
                                                                             @PathVariable Integer workoutId) {
        return favoriteWorkoutService.getUserFavoriteWorkoutsByUserIdAndWorkoutId(userId, workoutId);
    }

    @GetMapping("/isWorkoutInFavorites")
    public boolean isWorkoutInFavorites(@PathVariable Integer userId, @PathVariable Integer workoutId) {
        return favoriteWorkoutService.isWorkoutInFavorites(userId, workoutId);
    }

    @DeleteMapping("/deleteFavoriteWorkout")
    public ResponseEntity<String> deleteUserFavoriteWorkout(@PathVariable Integer userId,
                                                            @PathVariable Integer workoutId) {
        try {
            favoriteWorkoutService.deleteFavoriteWorkout(userId, workoutId);
            return ResponseEntity.ok("Successfully deleted user favorite workout with user Id: " + userId +
                    " and workout Id: " + workoutId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user favorite workout " +
                    "with user Id: " + userId + " and workout Id: " + workoutId + "\n" + e.getMessage());
        }
    }

    @PostMapping("/addFavoriteWorkout")
    public ResponseEntity<FavoriteWorkout> addFavoriteWorkout(@Valid @RequestBody FavoriteWorkout favoriteWorkout,
                                                              BindingResult result) {
        if(result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        FavoriteWorkout savedUserFavoriteWorkout = favoriteWorkoutService.addFavoriteWorkout(favoriteWorkout);
        return new ResponseEntity<FavoriteWorkout>(savedUserFavoriteWorkout, HttpStatus.CREATED);
    }

    @PutMapping("/updateFavoriteWorkout")
    public ResponseEntity<String> updateFavoriteWorkout(@PathVariable Integer userId, @PathVariable Integer workoutId) {
        try {
            List<FavoriteWorkout> favoriteWorkouts = favoriteWorkoutService.getUserFavoriteWorkoutsByUserIdAndWorkoutId(userId, workoutId);
            if (favoriteWorkouts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User favorite meal not found with user ID: "
                        + userId + " and meal ID: " + workoutId);
            }
            FavoriteWorkout favoriteWorkout = favoriteWorkouts.get(0);
            favoriteWorkout.setFavorite(true);
            favoriteWorkoutService.updateFavoriteWorkout(favoriteWorkout);
            return ResponseEntity.ok("Successfully updated user favorite workout with user ID: " + userId +
                    " and meal ID: " + workoutId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user favorite workout" +
                    " with user ID: " + userId + " and meal ID: " + workoutId + "\n" + e.getMessage());
        }
    }

    @GetMapping("/isWorkoutFavorite")
    public List<FavoriteWorkout> isWorkoutFavorite(@PathVariable Integer userId, @PathVariable Integer workoutId) {
        return favoriteWorkoutService.getUserFavoriteWorkoutsByUserIdAndWorkoutId(userId, workoutId);
    }
}

