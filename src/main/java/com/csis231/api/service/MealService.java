package com.csis231.api.service;

import com.csis231.api.model.Meal;
import com.csis231.api.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
}
