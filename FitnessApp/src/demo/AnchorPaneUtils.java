package demo;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class AnchorPaneUtils {

    public static boolean isFavorite1 = false;

    public static void intializeAnchorPane(AnchorPane parentAnchorPane, AnchorPane childAnchorPane) {

        parentAnchorPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            AnchorPane.setLeftAnchor(childAnchorPane, (newVal.doubleValue() - childAnchorPane.getPrefWidth()) / 2);
            AnchorPane.setRightAnchor(childAnchorPane, (newVal.doubleValue() - childAnchorPane.getPrefWidth()) / 2);
        });
        parentAnchorPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            AnchorPane.setTopAnchor(childAnchorPane, (newVal.doubleValue() - childAnchorPane.getPrefHeight()) / 2);
            AnchorPane.setBottomAnchor(childAnchorPane, (newVal.doubleValue() - childAnchorPane.getPrefHeight()) / 2);
        });
    }
    public static void intializeVbox(AnchorPane childAnchorPane, VBox childVbox) {
        AnchorPane.setTopAnchor(childVbox, (childAnchorPane.getPrefHeight() - childVbox.getPrefHeight()) / 2);
        AnchorPane.setBottomAnchor(childVbox, (childAnchorPane.getPrefHeight() - childVbox.getPrefHeight()) / 2);
        AnchorPane.setLeftAnchor(childVbox, (childAnchorPane.getPrefWidth() - childVbox.getPrefWidth()) / 2);
        AnchorPane.setRightAnchor(childVbox, (childAnchorPane.getPrefWidth() - childVbox.getPrefWidth()) / 2);
    }

    public static void intializeHbox(AnchorPane childAnchorPane, HBox childHbox) {
        AnchorPane.setTopAnchor(childHbox, (childAnchorPane.getPrefHeight() - childHbox.getPrefHeight()) / 2);
        AnchorPane.setBottomAnchor(childHbox, (childAnchorPane.getPrefHeight() - childHbox.getPrefHeight()) / 2);
        AnchorPane.setLeftAnchor(childHbox, (childAnchorPane.getPrefWidth() - childHbox.getPrefWidth()) / 2);
        AnchorPane.setRightAnchor(childHbox, (childAnchorPane.getPrefWidth() - childHbox.getPrefWidth()) / 2);
    }

    public static void initializeImage(AnchorPane parentAnchorPane, VBox child1) {
        parentAnchorPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            AnchorPane.setLeftAnchor(child1, (newVal.doubleValue() - child1.getPrefWidth()) / 2);
            AnchorPane.setRightAnchor(child1, (newVal.doubleValue() - child1.getPrefWidth()) / 2);
        });

        parentAnchorPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            AnchorPane.setTopAnchor(child1, (newVal.doubleValue() - child1.getPrefHeight()) / 2);
            AnchorPane.setBottomAnchor(child1, (newVal.doubleValue() - child1.getPrefHeight()) / 2);
        });
    }

    public static void setNavBar(VBox vbox) {
        vbox.setOnMouseEntered((event)->vbox.setStyle("-fx-background-color: #C0C0C0"));
        vbox.setOnMouseExited((event)->vbox.setStyle("-fx-background-color: #ffffff"));
    }

    public static void btnHover(Button btn) {
        btn.setOnMouseEntered((event)->btn.setStyle("-fx-background-color: #0066EE"));
        btn.setOnMouseExited((event)->btn.setStyle("-fx-background-color: #199FDC"));
    }

    public static void btnHover2(Button btn) {
        btn.setOnMouseEntered((event)->btn.setOpacity(0.7));
        btn.setOnMouseExited((event)->btn.setOpacity(1));
    }

    public static void labelHover(Label label) {
        label.setOnMouseEntered((event)->label.setStyle("-fx-text-fill: #0066EE"));
        label.setOnMouseExited((event)->label.setStyle("-fx-text-fill: #199FDC"));
    }

    public static void labelHover2(Label label) {
        label.setOnMouseEntered((event)->label.setStyle("-fx-text-fill: #199FDC"));
        label.setOnMouseExited((event)->label.setStyle("-fx-text-fill: #0066EE"));
    }

    public static void labelHover3(Label label) {
        label.setOnMouseEntered((event)->label.setOpacity(0.7));
        label.setOnMouseExited((event)->label.setOpacity(1));
    }

    public static void svgHover(SVGPath svg) {
        svg.setOnMouseEntered((event)->svg.setStyle("-fx-fill: #199FDC"));
        svg.setOnMouseExited((event)->svg.setStyle("-fx-fill: #0066EE"));
    }

    public static void goToProfile(VBox vbox){
        vbox.setOnMouseClicked((event)-> {
            try {
                Main.replaceSceneContent("fxml/Homepage.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void goToFavorites(VBox vbox){
        vbox.setOnMouseClicked((event)-> {
            try {
                Main.replaceSceneContent("fxml/Favoritespage.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void goToMeals(VBox vbox){
        vbox.setOnMouseClicked((event)-> {
            try {
                Main.replaceSceneContent("fxml/Mealspage.fxml");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void svg1(SVGPath svg) {
        if (isFavorite1) {
            svg.setFill(Color.GOLD);
        } else {
            svg.setFill(Color.valueOf("#515f66"));
        }
    }

    public static void goToWorkouts(VBox vbox){
        vbox.setOnMouseClicked((event)-> {
            try {
                Main.replaceSceneContent("fxml/Workoutpage.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void goToNotification(VBox vbox){
        vbox.setOnMouseClicked((event)-> {
            try {
                Main.replaceSceneContent("fxml/Notifications.fxml");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
