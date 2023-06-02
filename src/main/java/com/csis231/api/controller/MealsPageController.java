package com.csis231.api.controller;

import com.csis231.api.model.Meal;
import com.csis231.api.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mealsPage")
public class MealsPageController {

    @Autowired
    private MealService mealService;

    @GetMapping("/getMeals")
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }
}
