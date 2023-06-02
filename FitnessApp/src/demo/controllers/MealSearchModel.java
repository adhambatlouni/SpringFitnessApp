package demo.controllers;
import javafx.scene.image.Image;

public class MealSearchModel {
    String userName;
    String userEmail;
    String mealCategory;
    String mealDuration;
    String mealTimesperweek;
    String mealDifficulty;
    private Image mealImage;
    public MealSearchModel(String userName, String userEmail, String mealCategory, String mealDuration,
                           String mealTimesperweek, String mealDifficulty, Image mealImage) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.mealCategory = mealCategory;
        this.mealDuration = mealDuration;
        this.mealTimesperweek = mealTimesperweek;
        this.mealDifficulty = mealDifficulty;
        this.mealImage = mealImage;
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

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String maelCategory) {
        this.mealCategory = maelCategory;
    }

    public String getMealDuration() {
        return mealDuration;
    }

    public void setMealDuration(String mealDuration) {
        this.mealDuration = mealDuration;
    }

    public String getMealTimesPerWeek() {
        return mealTimesperweek;
    }

    public void setMealTimesPerWeek(String mealTimesperweek) {
        this.mealTimesperweek = mealTimesperweek;
    }

    public String getMealDifficulty() {
        return mealDifficulty;
    }

    public void setMealDifficulty(String mealDifficulty) {
        this.mealDifficulty = mealDifficulty;
    }

    public Image getMealImage() {
        return mealImage;
    }

    public void setMealImage(Image mealImage) {
        this.mealImage = mealImage;
    }
}
