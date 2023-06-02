package com.csis231.api.controller;

import com.csis231.api.model.Workout;
import com.csis231.api.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/workoutsPage")
public class WorkoutsPageController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/getWorkouts")
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }
}
