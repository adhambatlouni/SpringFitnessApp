package demo.controllers;
import javafx.scene.image.Image;

public class WorkoutSearchModel{
    String userName;
    String userEmail;
    String workoutCategory;
    String workoutDuration;
    String workoutTimesperweek;
    String workoutDifficulty;
    private Image workoutImage;
    public WorkoutSearchModel(String userName, String userEmail, String workoutCategory, String workoutDuration,
                           String workoutTimesperweek, String workoutDifficulty, Image workoutImage) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.workoutCategory = workoutCategory;
        this.workoutDuration = workoutDuration;
        this.workoutTimesperweek = workoutTimesperweek;
        this.workoutDifficulty = workoutDifficulty;
        this.workoutImage = workoutImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getWorkoutCategory() {
        return workoutCategory;
    }

    public void setWorkoutCategory(String workoutCategory) {
        this.workoutCategory = workoutCategory;
    }

    public String getWorkoutDuration() {
        return workoutDuration;
    }

    public void setWorkoutDuration(String workoutDuration) {
        this.workoutDuration = workoutDuration;
    }

    public String getWorkoutTimesPerWeek() {
        return workoutTimesperweek;
    }

    public void setWorkoutTimesPerWeek(String workoutTimesperweek) {
        this.workoutTimesperweek = workoutTimesperweek;
    }

    public String getWorkoutDifficulty() {
        return workoutDifficulty;
    }

    public void setWorkoutDifficulty(String workoutDifficulty) {
        this.workoutDifficulty = workoutDifficulty;
    }

    public Image getWorkoutImage() {
        return workoutImage;
    }

    public void setWorkoutImage(Image workoutImage) {
        this.workoutImage = workoutImage;
    }
}
