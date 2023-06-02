package demo.controllers;

public class Meal {

    private String mealCategory;
    private String mealDuration;
    private String mealTimesPerWeek;
    private String mealDifficulty;
    private byte[] mealImage;
    private static int mealId;

    public Meal(String mealCategory, String mealDuration, String mealTimesPerWeek, String mealDifficulty,
                byte[] mealImage, int mealId) {
        this.mealCategory = mealCategory;
        this.mealDuration = mealDuration;
        this.mealTimesPerWeek = mealTimesPerWeek;
        this.mealDifficulty = mealDifficulty;
        this.mealImage = mealImage;
        this.mealId = mealId;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public String getMealDuration() {
        return mealDuration;
    }

    public String getMealTimesPerWeek() {
        return mealTimesPerWeek;
    }

    public String getMealDifficulty() {
        return mealDifficulty;
    }

    public byte[] getMealImage() {
        return mealImage;
    }

    public static int getMealId() {
        return mealId;
    }

    public void setMeal(String mealCategory, String mealDuration, String mealTimesPerWeek, String mealDifficulty, byte[] mealImage) {
        setMealCategory(mealCategory);
        setMealDuration(mealDuration);
        setMealTimesPerWeek(mealTimesPerWeek);
        setMealDifficulty(mealDifficulty);
        setMealImage(mealImage);
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public void setMealDuration(String mealDuration) {
        this.mealDuration = mealDuration;
    }

    public void setMealTimesPerWeek(String mealTimesPerWeek) {
        this.mealTimesPerWeek = mealTimesPerWeek;
    }

    public void setMealDifficulty(String mealDifficulty) {
        this.mealDifficulty = mealDifficulty;
    }

    public void setMealImage(byte[] mealImage) {
        this.mealImage = mealImage;
    }

    public static void setMealId(int mealId) {
        mealId = mealId;
    }
}
