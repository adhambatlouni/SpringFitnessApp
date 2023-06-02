package com.csis231.api.repository;

import com.csis231.api.model.FavoriteMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteMealRepository extends JpaRepository<FavoriteMeal, Integer> {
    List<FavoriteMeal> findByUserIdAndMealId(Integer userId, Integer mealId);

    List<FavoriteMeal> findByUserId(Integer userId);
}
