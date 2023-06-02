package demo.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import demo.AnchorPaneUtils;
import demo.Main;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class LoginController extends AnchorPane implements Initializable {

    public TextField usernamefield;
    public PasswordField passwordfield;
    public Label forgetpassLabel;
    public Label registerLabel;
    private Main application;
    @FXML
    private VBox childVbox;
    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private Button loginBtn;
    @FXML
    private AnchorPane childAnchorPane;

    public void setApp(Main application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeVbox(childAnchorPane, childVbox);
        AnchorPaneUtils.btnHover(loginBtn);
        AnchorPaneUtils.labelHover(forgetpassLabel);
        AnchorPaneUtils.labelHover(registerLabel);
    }

    public void goToRegister(MouseEvent mouseEvent) throws Exception {
        application.replaceSceneContent("fxml/SignUp.fxml");
    }

    public void logIn(ActionEvent actionEvent) throws Exception {
        TranslateTransition shakeTransition1 = DBUtils.createShakeTransition(usernamefield, Duration.millis(100));
        TranslateTransition shakeTransition2 = DBUtils.createShakeTransition(passwordfield, Duration.millis(100));

        String username = usernamefield.getText();
        String password = passwordfield.getText();

        if (username.isEmpty() || password.isEmpty()) {
            shakeTransition1.playFromStart();
            shakeTransition2.playFromStart();
            return;
        }
        String url = "http://localhost:8080/api/v1/logIn/getUserId?username=" + username + "&password=" + password;
        String responseBody = DBUtils.getMethod(url);

        try {
            int userId = Integer.parseInt(responseBody);
            Main.currentUserId = userId;
            System.out.println("Logged in user's userId is " + userId);
            application.replaceSceneContent("fxml/Homepage.fxml");
            if (!DBUtils.usernameExists(username)) {
                shakeTransition1.playFromStart();
            } else if (!DBUtils.passwordExists(password)) {
                shakeTransition2.playFromStart();
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid user ID: " + responseBody);
        }
    }
}
