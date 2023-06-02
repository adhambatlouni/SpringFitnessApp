package demo.controllers;

public class Workout {
    private String workoutCategory;
    private String workoutDuration;
    private String workoutTimesPerWeek;
    private String workoutDifficulty;
    private byte[] workoutImage;
    private int workoutId;

    public Workout(String workoutCategory, String workoutDuration, String workoutTimesPerWeek, String workoutDifficulty, byte[] workoutImage, int workoutId) {
        this.workoutCategory = workoutCategory;
        this.workoutDuration = workoutDuration;
        this.workoutTimesPerWeek = workoutTimesPerWeek;
        this.workoutDifficulty = workoutDifficulty;
        this.workoutImage = workoutImage;
        this.workoutId = workoutId;
    }

    public String getWorkoutCategory() {
        return workoutCategory;
    }

    public String getWorkoutDuration() {
        return workoutDuration;
    }

    public String getWorkoutTimesPerWeek() {
        return workoutTimesPerWeek;
    }

    public String getWorkoutDifficulty() {
        return workoutDifficulty;
    }

    public byte[] getWorkoutImage() {
        return workoutImage;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setMeal(String workoutCategory, String workoutDuration, String workoutTimesPerWeek, String workoutDifficulty, byte[] workoutImage){
        setWorkoutCategory(workoutCategory);
        setWorkoutDuration(workoutDuration);
        setWorkoutTimesPerWeek(workoutTimesPerWeek);
        setWorkoutDifficulty(workoutDifficulty);
        setWorkoutImage(workoutImage);
    }

    public void setWorkoutCategory(String workoutCategory) {
        this.workoutCategory = workoutCategory;
    }

    public void setWorkoutDuration(String workoutDuration) {
        this.workoutDuration = workoutDuration;
    }

    public void setWorkoutTimesPerWeek(String workoutTimesPerWeek) {
        this.workoutTimesPerWeek = workoutTimesPerWeek;
    }

    public void setWorkoutDifficulty(String workoutDifficulty) {
        this.workoutDifficulty = workoutDifficulty;
    }

    public void setWorkoutImage(byte[] workoutImage) {
        this.workoutImage = workoutImage;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }
}
