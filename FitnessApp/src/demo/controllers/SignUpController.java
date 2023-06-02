package demo.controllers;

import demo.AnchorPaneUtils;
import demo.Main;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.json.JSONObject;

import java.net.URL;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.ResourceBundle;

public class SignUpController extends AnchorPane implements Initializable {
    public TextField usernamefield;
    public TextField emailfield;
    public PasswordField passwordfield;
    public Button signUpBtn;

    public Label loginLabel;
    private Main application;
    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private VBox childVbox;
    @FXML
    private AnchorPane childAnchorPane;

    private static String user;
    private static String email;
    private static int userId;

    public void setApp(Main application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeVbox(childAnchorPane, childVbox);

        AnchorPaneUtils.btnHover(signUpBtn);
        AnchorPaneUtils.labelHover(loginLabel);
    }

    public void goToLogin(MouseEvent mouseEvent) throws Exception {
        application.replaceSceneContent("fxml/Login.fxml");
    }

    public void signUp() throws Exception {
        TranslateTransition shakeTransition1 = DBUtils.createShakeTransition(usernamefield, Duration.millis(100));
        TranslateTransition shakeTransition2 = DBUtils.createShakeTransition(emailfield, Duration.millis(100));
        TranslateTransition shakeTransition3 = DBUtils.createShakeTransition(passwordfield, Duration.millis(100));

        String username = usernamefield.getText();
        String email = emailfield.getText();
        String password = passwordfield.getText();

        setUsername(username);
        setEmail(email);

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            shakeTransition1.playFromStart();
            shakeTransition2.playFromStart();
            shakeTransition3.playFromStart();
            return;
        }

        if (DBUtils.usernameExists(username)) {
            shakeTransition1.playFromStart();
            return;
        }

        if (DBUtils.emailExists(email)) {
            shakeTransition2.playFromStart();
            return;
        }

        addUser(username, email, password);

        usernamefield.setText("");
        emailfield.setText("");
        passwordfield.setText("");
    }

    public void setUsername(String username) {
        this.user = usernamefield.getText();
    }
    public void setEmail(String email) {
        this.email = emailfield.getText();
    }

    private void addUser(String username, String email, String password) throws Exception {
        String url = "http://localhost:8080/api/v1/signUp/add";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String hash = Base64.getEncoder().encodeToString(hashBytes);

        String jsonBody = String.format("{\"username\":\"%s\",\"email\":\"%s\",\"password\":\"%s\"}", username, email, hash);

        HttpResponse<String> response = DBUtils.postMethod(url, jsonBody);
        if(response.statusCode() == 201) {
            String responseBody = response.body();
            System.out.println(responseBody);
            JSONObject jsonObject = new JSONObject(responseBody);
            int userId = jsonObject.getInt("id");
            Main.currentUserId = userId;
            System.out.println("Successfully added user with ID: " + userId);
            application.replaceSceneContent("fxml/Info.fxml");
       }
       else {
            System.out.println("Failed to add user");
        }
    }
}

