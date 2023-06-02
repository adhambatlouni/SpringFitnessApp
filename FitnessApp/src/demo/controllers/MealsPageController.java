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

public class MealsPageController extends AnchorPane implements Initializable {
    public static SVGPath svg1;
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
    private ArrayList<Meal> meals;
    public static int mealId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeHbox(childAnchorPane, childHbox);

        List<SVGPath> mealSVGS = new ArrayList<>();

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

        meals = DBUtils.getMealInfo();
        int row = 1;
        int column = 0;
        for (int i = 0; i < meals.size(); i++) {
            Meal meal = meals.get(i);

            AnchorPane mealAnchorPane = new AnchorPane();
            mealAnchorPane.setLayoutX(200.0);
            mealAnchorPane.setLayoutY(100.0);
            mealAnchorPane.setMaxHeight(Double.NEGATIVE_INFINITY);
            mealAnchorPane.setMaxWidth(Double.NEGATIVE_INFINITY);
            mealAnchorPane.setMinHeight(Double.NEGATIVE_INFINITY);
            mealAnchorPane.setMinWidth(Double.NEGATIVE_INFINITY);
            mealAnchorPane.setPrefHeight(350.0);
            mealAnchorPane.setPrefWidth(300.0);
            mealAnchorPane.setStyle("-fx-background-color: white; -fx-background-radius: 15;");

            VBox vbox = new VBox();

            byte[] mealImage = meal.getMealImage();
            ByteArrayInputStream bis = new ByteArrayInputStream(mealImage);
            Image image = new Image(bis);

            ImageView mealImageView = new ImageView();
            mealImageView.setImage(image);
            mealImageView.setTranslateX(40.0);
            VBox.setMargin(mealImageView, new Insets(15.0, 8.0, 8.0, 8.0));

            Label mealCategory = new Label(meal.getMealCategory());
            mealCategory.setAlignment(Pos.TOP_LEFT);
            mealCategory.setMinWidth(Region.USE_PREF_SIZE);
            mealCategory.setPrefWidth(192.0);
            mealCategory.setPrefHeight(20.0);
            mealCategory.setTextFill(Color.web("#505f66"));
            mealCategory.setTranslateX(35.0);
            mealCategory.setFont(Font.font("System Bold", 14));
            VBox.setMargin(mealCategory, new Insets(6.0, 6.0, 4.0, 6.0));

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.setTranslateX(-22.0);
            hbox.setSpacing(3.0);

            Label mealduration = new Label(meal.getMealDuration());
            mealduration.setAlignment(Pos.TOP_LEFT);
            mealduration.setContentDisplay(ContentDisplay.CENTER);
            mealduration.setPrefWidth(42.0);
            mealduration.setPrefHeight(18.0);
            mealduration.setTextFill(Color.web("#515f66"));

            Label dotLabel = new Label("•");
            dotLabel.setTextFill(Color.web("#515f66"));

            Label mealtimes = new Label(meal.getMealTimesPerWeek());
            mealtimes.setTextFill(Color.web("#515f66"));

            hbox.getChildren().addAll(mealduration, dotLabel, mealtimes);

            Label mealdifficulty = new Label(meal.getMealDifficulty());
            mealdifficulty.setAlignment(Pos.TOP_LEFT);
            mealdifficulty.setPrefWidth(149.0);
            mealdifficulty.setPrefHeight(18.0);
            mealdifficulty.setTextFill(Color.web("#515f66"));
            mealdifficulty.setTranslateX(13.0);

            SVGPath svg = new SVGPath();
            svg.setContent("M64.395,1.969l15.713,36.79l39.853,3.575c1.759,0.152,3.06,1.701,2.907,3.459c-0.073,0.857-0.479,1.604-1.079,2.129 l0.002,0.001L91.641,74.25l8.917,39.021c0.395,1.723-0.683,3.439-2.406,3.834c-0.883,0.203-1.763,0.018-2.466-0.441L61.441,96.191 L27.087,116.73c-1.516,0.906-3.48,0.412-4.387-1.104c-0.441-0.736-0.55-1.58-0.373-2.355h-0.003l8.918-39.021L1.092,47.924 c-1.329-1.163-1.463-3.183-0.301-4.512c0.591-0.676,1.405-1.042,2.235-1.087l39.748-3.566l15.721-36.81 c0.692-1.627,2.572-2.384,4.199-1.692C63.494,0.597,64.084,1.225,64.395,1.969L64.395,1.969z M74.967,43.023L61.441,11.351 L47.914,43.023l-0.004-0.001c-0.448,1.051-1.447,1.826-2.665,1.932l-34.306,3.078l25.819,22.545c0.949,0.74,1.438,1.988,1.152,3.24 l-7.674,33.578l29.506-17.641c0.986-0.617,2.274-0.672,3.342-0.033l29.563,17.674l-7.673-33.578l0.003-0.002 c-0.252-1.109,0.096-2.318,1.012-3.119l25.955-22.664L77.815,44.97C76.607,44.932,75.472,44.208,74.967,43.023L74.967,43.023z");
            String svgId = "meal_" + (i + 1);
            svg.setId(svgId);
            if (checkSvg(svg)) {
                svg.setFill(Color.GOLD);
            } else {
                svg.setFill(Color.web("#515f66"));
            }
            mealSVGS.add(svg);
            svg.setTranslateX(150.0);
            svg.setTranslateY(-40.0);
            svg.setScaleX(0.2);
            svg.setScaleY(0.2);

            vbox.getChildren().addAll(mealImageView, mealCategory, hbox, mealdifficulty, svg);
            vbox.setAlignment(Pos.CENTER);
            mealAnchorPane.getChildren().add(vbox);
            gridPane.add(mealAnchorPane, column, row);
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

        for (SVGPath svg : mealSVGS) {
            svg.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        handleMeal(event);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    public boolean checkSvg(SVGPath svg) {
        String id = svg.getId();
        int mealId = Integer.parseInt(id.split("_")[1]);

        int userId = Main.currentUserId;
        String url = "http://localhost:8080/api/v1/userFavoriteMeals/" + userId + "/" + mealId + "/isMealFavorite";
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
    public void handleMeal(MouseEvent event) throws IOException, InterruptedException {
        SVGPath svg = (SVGPath) event.getSource();
        String id = svg.getId();
        int mealId = Integer.parseInt(id.split("_")[1]);
        meals = DBUtils.getMealInfo();
        MealsPageController.mealId = mealId;

        System.out.println(MealsPageController.mealId);
        int userId = Main.currentUserId;

        if (checkMealInFavorites(userId, mealId)) {
            removeMealFromFavorites(userId, mealId, svg);
        }
        else {
            addMealToFavorites(userId, mealId, svg);
        }
    }

    public static boolean checkMealInFavorites(int userId, int mealId) {
        String url = "http://localhost:8080/api/v1/userFavoriteMeals/" + userId + "/" + mealId + "/isMealInFavorites";
        String selectResponse = DBUtils.getMethod(url);
        return (selectResponse != null && Boolean.parseBoolean(selectResponse));
    }

    public static void setFavoriteMeal(String url, int userId, int mealId, SVGPath svg) throws IOException, InterruptedException {
        String jsonBody = "{\"userId\":" + userId + ", \"mealId\":" + mealId + "}";
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

    public static void addMealToFavorites(int userId, int mealId, SVGPath svg) throws IOException, InterruptedException {
        String addUrl = "http://localhost:8080/api/v1/userFavoriteMeals/" + userId + "/" + mealId + "/addFavoriteMeal";
        String jsonBody = "{\"userId\":" + userId + ", \"mealId\":" + mealId + "}";
        HttpResponse<String> response = DBUtils.postMethod(addUrl, jsonBody);
        if (response.statusCode() == 201) {
            String updateUrl = "http://localhost:8080/api/v1/userFavoriteMeals/" + userId + "/" + mealId + "/updateFavoriteMeal";
            setFavoriteMeal(updateUrl, userId, mealId, svg);
        }
    }

    public static void removeMealFromFavorites(int userId, int mealId, SVGPath svg) {
        String url = "http://localhost:8080/api/v1/userFavoriteMeals/" + userId + "/" + mealId + "/deleteFavoriteMeal";
        String deleteResponse = DBUtils.deleteMethod(url);
        if (deleteResponse != null && deleteResponse.contains("Successfully deleted")) {
            svg.setFill(Color.valueOf("#515f66"));
        }
    }
}



