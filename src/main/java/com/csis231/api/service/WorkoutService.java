package com.csis231.api.service;

import com.csis231.api.model.Workout;
import com.csis231.api.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }
}
