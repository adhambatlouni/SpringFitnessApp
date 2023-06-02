package com.csis231.api.repository;

import com.csis231.api.model.FavoriteWorkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteWorkoutRepository extends JpaRepository<FavoriteWorkout, Integer> {

    List<FavoriteWorkout> findByUserIdAndWorkoutId(Integer userId, Integer workoutId);

    List<FavoriteWorkout> findByUserId(Integer userId);
}
