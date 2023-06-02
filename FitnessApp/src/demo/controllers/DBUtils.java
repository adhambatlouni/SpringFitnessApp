package demo.controllers;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

public class DBUtils {
    public static ArrayList<Meal> getMealInfo() {
        String url = "http://localhost:8080/api/v1/mealsPage/getMeals";
        String responseBody = getMethod(url);

        if (responseBody != null) {
            JSONArray jsonArray = new JSONArray(responseBody);
            ArrayList<Meal> meals = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String mealCategory = jsonObject.getString("mealCategory");
                String mealDuration = jsonObject.getString("mealDuration");
                String mealTimesPerWeek = jsonObject.getString("mealTimesPerWeek");
                String mealDifficulty = jsonObject.getString("mealDifficulty");
                byte[] mealImage = Base64.getDecoder().decode(jsonObject.getString("mealImage"));
                int mealId = jsonObject.getInt("id");

                Meal meal = new Meal(mealCategory, mealDuration, mealTimesPerWeek, mealDifficulty, mealImage, mealId);
                meals.add(meal);
            }
            return meals;
        } else {
            return null;
        }
    }

    public static ArrayList<Workout> getWorkoutInfo() {
        String url = "http://localhost:8080/api/v1/workoutsPage/getWorkouts";
        String responseBody = getMethod(url);

        if (responseBody != null) {
            JSONArray jsonArray = new JSONArray(responseBody);
            ArrayList<Workout> workouts = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String workoutCategory = jsonObject.getString("workoutCategory");
                String workoutDuration = jsonObject.getString("workoutDuration");
                String workoutTimesPerWeek = jsonObject.getString("workoutTimesPerWeek");
                String workoutDifficulty = jsonObject.getString("workoutDifficulty");
                byte[] workoutImage = Base64.getDecoder().decode(jsonObject.getString("workoutImage"));
                int workoutId = jsonObject.getInt("id");

                Workout workout = new Workout(workoutCategory, workoutDuration, workoutTimesPerWeek,
                        workoutDifficulty, workoutImage, workoutId);
                workouts.add(workout);
            }
            return workouts;
        } else {
            return null;
        }
    }

    public static String getMethod(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("Failed: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String deleteMethod(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("Failed: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HttpResponse<String> postMethod(String url, String jsonBody) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public static HttpResponse<String> putMethod(String url, String body) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public static boolean usernameExists(String username) {
        String url = "http://localhost:8080/api/v1/signUp/usernameExists?username=" + username;
        String responseBody = getMethod(url);

        if (responseBody != null && responseBody.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean emailExists(String email) {
        String url = "http://localhost:8080/api/v1/signUp/emailExists?email=" + email;
        String responseBody = getMethod(url);

        if (responseBody != null && responseBody.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean passwordExists(String password) throws NoSuchAlgorithmException {
        String url = "http://localhost:8080/api/v1/logIn/passwordExists?password=" + password;

        String responseBody = getMethod(url);

        if (responseBody != null && responseBody.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public static TranslateTransition createShakeTransition(Node node, Duration duration) {
        TranslateTransition shakeTransition = new TranslateTransition(duration, node);
        shakeTransition.setCycleCount(4);
        shakeTransition.setByX(10);
        shakeTransition.setAutoReverse(true);
        return shakeTransition;
    }
}
