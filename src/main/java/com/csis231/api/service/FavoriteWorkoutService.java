package com.csis231.api.service;

import com.csis231.api.model.FavoriteWorkout;
import com.csis231.api.model.User;
import com.csis231.api.model.Workout;
import com.csis231.api.repository.FavoriteWorkoutRepository;
import com.csis231.api.repository.UserRepository;
import com.csis231.api.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FavoriteWorkoutService {

    @Autowired
    private FavoriteWorkoutRepository favoriteWorkoutRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    public List<FavoriteWorkout> getUserFavoriteWorkoutsByUserIdAndWorkoutId(Integer userId, Integer workoutId) {
        return favoriteWorkoutRepository.findByUserIdAndWorkoutId(userId, workoutId);
    }

    public FavoriteWorkout addFavoriteWorkout(@Valid FavoriteWorkout favoriteWorkout) {
        favoriteWorkoutRepository.save(favoriteWorkout);
        return favoriteWorkout;
    }

    public void deleteFavoriteWorkout(Integer userId, Integer workoutId) {
        List<FavoriteWorkout> favoriteWorkouts = favoriteWorkoutRepository.findByUserIdAndWorkoutId(userId, workoutId);
        if (favoriteWorkouts.isEmpty()) {
            System.out.println("No record found to delete");
        } else {
            for (FavoriteWorkout favoriteWorkout : favoriteWorkouts) {
                System.out.println("Deleting record with id " + favoriteWorkout.getId());
                favoriteWorkoutRepository.delete(favoriteWorkout);
            }
        }
    }

    public boolean isWorkoutInFavorites(Integer userId, Integer workoutId) {
        List<FavoriteWorkout> favoriteWorkouts = favoriteWorkoutRepository.findByUserIdAndWorkoutId(userId, workoutId);
        return !favoriteWorkouts.isEmpty();
    }

    public void updateFavoriteWorkout(FavoriteWorkout favoriteWorkout) {
        favoriteWorkoutRepository.save(favoriteWorkout);
    }

    public List<Map<String, Object>> getUserFavoriteWorkoutInfo(Integer userId) {
        List<FavoriteWorkout> favoriteWorkouts = favoriteWorkoutRepository.findByUserId(userId);
        List<Map<String, Object>> favoriteWorkoutInfoList = new ArrayList<>();

        if (favoriteWorkouts.isEmpty()) {
            System.out.println("No favorite workouts found for given user ID and workout ID");
            return favoriteWorkoutInfoList;
        }

        for (FavoriteWorkout favoriteWorkout : favoriteWorkouts) {
            User user = userRepository.findById(favoriteWorkout.getUserId()).orElse(null);
            Workout workout = workoutRepository.findById(favoriteWorkout.getWorkoutId()).orElse(null);

            if (user == null || workout == null) {
                System.out.println("User or workout not found for given IDs");
                continue;
            }

            Map<String, Object> favoriteWorkoutInfo = new HashMap<>();
            favoriteWorkoutInfo.put("userName", user.getUsername());
            favoriteWorkoutInfo.put("userEmail", user.getEmail());
            favoriteWorkoutInfo.put("workoutCategory", workout.getWorkoutCategory());
            favoriteWorkoutInfo.put("workoutDuration", workout.getWorkoutDuration());
            favoriteWorkoutInfo.put("workoutTimesPerWeek", workout.getWorkoutTimesPerWeek());
            favoriteWorkoutInfo.put("workoutDifficulty", workout.getWorkoutDifficulty());
            favoriteWorkoutInfo.put("workoutImage", workout.getWorkoutImage());
            favoriteWorkoutInfoList.add(favoriteWorkoutInfo);
        }
        return favoriteWorkoutInfoList;
    }
}
