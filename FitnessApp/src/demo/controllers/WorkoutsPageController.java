package demo.controllers;

import demo.AnchorPaneUtils;
import demo.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkoutsPageController extends AnchorPane implements Initializable {

    @FXML
    public AnchorPane parentAnchorPane;
    @FXML
    public AnchorPane childAnchorPane;
    public HBox childHbox;
    public VBox svg1Nav;
    public VBox svg2Nav;
    public VBox svg3Nav;
    public VBox svg4Nav;
    public VBox svg5Nav;
    public GridPane gridPane;

    private ArrayList<Workout> workouts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeHbox(childAnchorPane, childHbox);
        List<SVGPath> workoutSVGS = new ArrayList<>();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMaxWidth(Double.NEGATIVE_INFINITY);
        gridPane.setMinWidth(Double.NEGATIVE_INFINITY);
        gridPane.setPrefWidth(1445.0);
        gridPane.setTranslateX(-20.0);

        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(40);
        gridPane.setVgap(40);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);

        scrollPane.setFitToWidth(false);
        scrollPane.setFitToHeight(true);
        childHbox.getChildren().add(scrollPane);

        workouts = DBUtils.getWorkoutInfo();
        int row = 1;
        int column = 0;
        for (int i = 0; i < workouts.size(); i++) {
            Workout workout = workouts.get(i);

            AnchorPane workoutAnchorPane = new AnchorPane();
            workoutAnchorPane.setId("childAnchorPane");
            workoutAnchorPane.setLayoutX(200.0);
            workoutAnchorPane.setLayoutY(100.0);
            workoutAnchorPane.setMaxHeight(Double.NEGATIVE_INFINITY);
            workoutAnchorPane.setMaxWidth(Double.NEGATIVE_INFINITY);
            workoutAnchorPane.setMinHeight(Double.NEGATIVE_INFINITY);
            workoutAnchorPane.setMinWidth(Double.NEGATIVE_INFINITY);
            workoutAnchorPane.setPrefHeight(350.0);
            workoutAnchorPane.setPrefWidth(300.0);
            workoutAnchorPane.setStyle("-fx-background-color: white; -fx-background-radius: 15;");

            VBox vbox = new VBox();

            byte [] workoutImage = workout.getWorkoutImage();
            ByteArrayInputStream bis = new ByteArrayInputStream(workoutImage);
            Image image = new Image(bis);

            ImageView workoutImageView = new ImageView();
            workoutImageView.setSmooth(true);
            workoutImageView.setImage(image);
            workoutImageView.setTranslateX(30.0);
            VBox.setMargin(workoutImageView, new Insets(20.0, 8.0, 8.0, 8.0));

            Label workoutCategory = new Label(workout.getWorkoutCategory());
            workoutCategory.setAlignment(Pos.TOP_LEFT);
            workoutCategory.setMinWidth(Region.USE_PREF_SIZE);
            workoutCategory.setPrefWidth(192.0);
            workoutCategory.setPrefHeight(20.0);
            workoutCategory.setTextFill(Color.web("#505f66"));
            workoutCategory.setTranslateX(15.0);
            workoutCategory.setFont(Font.font("System Bold", 14));
            VBox.setMargin(workoutCategory, new Insets(6.0, 6.0, 4.0, 6.0));

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.TOP_LEFT);
            hbox.setTranslateX(40.0);
            hbox.setSpacing(3.0);

            Label workoutduration = new Label(workout.getWorkoutDuration());
            workoutduration.setAlignment(Pos.TOP_LEFT);
            workoutduration.setContentDisplay(ContentDisplay.CENTER);
            workoutduration.setPrefWidth(42.0);
            workoutduration.setPrefHeight(18.0);
            workoutduration.setTextFill(Color.web("#515f66"));

            Label dotLabel = new Label("•");
            dotLabel.setTextFill(Color.web("#515f66"));

            Label workouttimes = new Label(workout.getWorkoutTimesPerWeek());
            workouttimes.setId("meal1times");
            workouttimes.setTextFill(Color.web("#515f66"));

            hbox.getChildren().addAll(workoutduration, dotLabel, workouttimes);

            Label workoutdifficulty = new Label(workout.getWorkoutDifficulty());
            workoutdifficulty.setAlignment(Pos.TOP_LEFT);
            workoutdifficulty.setPrefWidth(149.0);
            workoutdifficulty.setPrefHeight(18.0);
            workoutdifficulty.setTextFill(Color.web("#515f66"));
            workoutdifficulty.setTranslateX(-5.0);

            SVGPath svg = new SVGPath();
            svg.setContent("M64.395,1.969l15.713,36.79l39.853,3.575c1.759,0.152,3.06,1.701,2.907,3.459c-0.073,0.857-0.479,1.604-1.079,2.129 l0.002,0.001L91.641,74.25l8.917,39.021c0.395,1.723-0.683,3.439-2.406,3.834c-0.883,0.203-1.763,0.018-2.466-0.441L61.441,96.191 L27.087,116.73c-1.516,0.906-3.48,0.412-4.387-1.104c-0.441-0.736-0.55-1.58-0.373-2.355h-0.003l8.918-39.021L1.092,47.924 c-1.329-1.163-1.463-3.183-0.301-4.512c0.591-0.676,1.405-1.042,2.235-1.087l39.748-3.566l15.721-36.81 c0.692-1.627,2.572-2.384,4.199-1.692C63.494,0.597,64.084,1.225,64.395,1.969L64.395,1.969z M74.967,43.023L61.441,11.351 L47.914,43.023l-0.004-0.001c-0.448,1.051-1.447,1.826-2.665,1.932l-34.306,3.078l25.819,22.545c0.949,0.74,1.438,1.988,1.152,3.24 l-7.674,33.578l29.506-17.641c0.986-0.617,2.274-0.672,3.342-0.033l29.563,17.674l-7.673-33.578l0.003-0.002 c-0.252-1.109,0.096-2.318,1.012-3.119l25.955-22.664L77.815,44.97C76.607,44.932,75.472,44.208,74.967,43.023L74.967,43.023z");
            String svgId = "meal_" + (i + 1);
            svg.setId(svgId);
            if(checkSvg(svg)) {
                svg.setFill(Color.GOLD);
            }
            else {
                svg.setFill(Color.web("#515f66"));
            }
            workoutSVGS.add(svg);
            svg.setTranslateX(140.0);
            svg.setTranslateY(-40.0);
            svg.setScaleX(0.2);
            svg.setScaleY(0.2);

            vbox.getChildren().addAll(workoutImageView, workoutCategory, hbox, workoutdifficulty, svg);
            vbox.setAlignment(Pos.CENTER);
            workoutAnchorPane.getChildren().add(vbox);
            gridPane.add(workoutAnchorPane, column, row);
            column++;
            if (column == 3) {
                row++;
                column = 0;
            }
        }

        AnchorPaneUtils.setNavBar(svg1Nav);
        AnchorPaneUtils.setNavBar(svg2Nav);
        AnchorPaneUtils.setNavBar(svg3Nav);
        AnchorPaneUtils.setNavBar(svg4Nav);
        AnchorPaneUtils.setNavBar(svg5Nav);

        AnchorPaneUtils.goToProfile(svg1Nav);
        AnchorPaneUtils.goToFavorites(svg2Nav);
        AnchorPaneUtils.goToMeals(svg3Nav);
        AnchorPaneUtils.goToWorkouts(svg4Nav);
        AnchorPaneUtils.goToNotification(svg5Nav);

        for(SVGPath svg : workoutSVGS) {
            svg.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        handleWorkout(event);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    public boolean checkSvg(SVGPath svg) {
        String id = svg.getId();
        int workout = Integer.parseInt(id.split("_")[1]);

        int userId = Main.currentUserId;
        String url = "http://localhost:8080/api/v1/userFavoriteWorkouts/" + userId + "/" + workout + "/isWorkoutFavorite";
        String responseString = DBUtils.getMethod(url);
        System.out.println(responseString);

        Pattern pattern = Pattern.compile("\"favorite\":(true|false)");
        Matcher matcher = pattern.matcher(responseString);

        boolean isFavorite = false;

        if (matcher.find()) {
            String favoriteValue = matcher.group(1);
            isFavorite = Boolean.parseBoolean(favoriteValue);
        }

        return isFavorite;
    }

    @FXML
    public void handleWorkout(MouseEvent event) throws IOException, InterruptedException {
        SVGPath svg = (SVGPath) event.getSource();
        String id = svg.getId();
        int workoutId = Integer.parseInt(id.split("_")[1]);
        workouts = DBUtils.getWorkoutInfo();

        System.out.println(workoutId);
        int userId = Main.currentUserId;

        if (checkWorkoutInFavorites(userId, workoutId)) {
            removeWorkoutFromFavorites(userId, workoutId, svg);
        }
        else {
            addWorkoutToFavorites(userId, workoutId, svg);
        }
    }

    public static boolean checkWorkoutInFavorites(int userId, int workoutId) {
        String url = "http://localhost:8080/api/v1/userFavoriteWorkouts/" + userId + "/" + workoutId + "/isWorkoutInFavorites";
        String selectResponse = DBUtils.getMethod(url);
        return (selectResponse != null && Boolean.parseBoolean(selectResponse));
    }

    public static void setFavoriteWorkout(String url, int userId, int workoutId, SVGPath svg) throws IOException, InterruptedException {
        String jsonBody = "{\"userId\":" + userId + ", \"workoutId\":" + workoutId + "}";
        HttpResponse<String> response = DBUtils.putMethod(url, jsonBody);
        if (response.statusCode() == 200) {
            svg.setFill(Color.GOLD);
            updateFavoriteStatusInUI(svg, true);
        }
    }

    public static void updateFavoriteStatusInUI(SVGPath svg, boolean isFavorite) {
        if (isFavorite) {
            svg.setFill(Color.GOLD);
        } else {
            svg.setFill(Color.BLACK);
        }
    }

    public static void addWorkoutToFavorites(int userId, int workoutId, SVGPath svg) throws IOException, InterruptedException {
        String addUrl = "http://localhost:8080/api/v1/userFavoriteWorkouts/" + userId + "/" + workoutId + "/addFavoriteWorkout";
        String jsonBody = "{\"userId\":" + userId + ", \"workoutId\":" + workoutId + "}";
        HttpResponse<String> response = DBUtils.postMethod(addUrl, jsonBody);
        if (response.statusCode() == 201) {
            String updateUrl = "http://localhost:8080/api/v1/userFavoriteWorkouts/" + userId + "/" + workoutId + "/updateFavoriteWorkout";
            setFavoriteWorkout(updateUrl, userId, workoutId, svg);
        }
    }

    public static void removeWorkoutFromFavorites(int userId, int workoutId, SVGPath svg) {
        String url = "http://localhost:8080/api/v1/userFavoriteWorkouts/" + userId + "/" + workoutId + "/deleteFavoriteWorkout";
        String deleteResponse = DBUtils.deleteMethod(url);
        if (deleteResponse != null && deleteResponse.contains("Successfully deleted")) {
            svg.setFill(Color.valueOf("#515f66"));
        }
    }
}

