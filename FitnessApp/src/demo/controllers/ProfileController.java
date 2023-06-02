package demo.controllers;

import demo.AnchorPaneUtils;
import demo.Main;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.sql.*;
import java.util.Base64;
import java.util.ResourceBundle;

public class ProfileController extends AnchorPane implements Initializable {

    public Label uploadLabel;
    public TextField usernametextfield;
    public TextField emailtextfield;
    public Button updateBtn;
    @FXML
    public AnchorPane parentAnchorpane;

    public VBox childVbox;
    public ImageView userImageView;
    public SVGPath profileIcon;
    private Dialog<Void> dialog;

    public void setDialog(Dialog<Void> dialog) {
        this.dialog = dialog;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeVbox(parentAnchorpane, childVbox);
        AnchorPaneUtils.btnHover2(updateBtn);
        AnchorPaneUtils.labelHover2(uploadLabel);

        try {
            userInfo();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void userInfo() throws IOException, InterruptedException {
        Integer userId = Main.currentUserId;
        String url = "http://localhost:8080/api/v1/userProfile/user/" + userId;

        String response = DBUtils.getMethod(url);
        if (response != null) {
            JSONObject jsonResponse = new JSONObject(response);
            String username = jsonResponse.getString("username");
            String email = jsonResponse.getString("email");
            String userProfileImageUrl = jsonResponse.optString("userProfileImageUrl");
            System.out.println(userProfileImageUrl);
            usernametextfield.setText(username);
            emailtextfield.setText(email);
            displayUserImage(userId);
        } else {
            System.out.println("Failed to retrieve user info.");
        }
    }

    public void editUser() throws IOException, InterruptedException {
        String username = usernametextfield.getText();
        String useremail = emailtextfield.getText();
        int userId = Main.currentUserId;

        TranslateTransition shakeTransition1 = DBUtils.createShakeTransition(usernametextfield, Duration.millis(100));
        TranslateTransition shakeTransition2 = DBUtils.createShakeTransition(emailtextfield, Duration.millis(100));

        if (username.isEmpty() && useremail.isEmpty()) {
            shakeTransition1.playFromStart();
            shakeTransition2.playFromStart();
            return;
        } else if (username.isEmpty()) {
            shakeTransition1.playFromStart();
            return;
        } else if (useremail.isEmpty()) {
            shakeTransition2.playFromStart();
            return;
        } else if (DBUtils.usernameExists(username) && DBUtils.emailExists(useremail)) {
            shakeTransition1.playFromStart();
            shakeTransition2.playFromStart();
            return;
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("email", useremail);

        String url = "http://localhost:8080/api/v1/userProfile/updateUser/" + userId;

        HttpResponse<String> response = DBUtils.putMethod(url, requestBody.toString());
        if (response.statusCode() == 200) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Failed to update user.");
        }

        usernametextfield.setText("");
        emailtextfield.setText("");
    }

    public void updateUserImage() throws IOException, InterruptedException {
        int userId = Main.currentUserId;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        File imageFile = fileChooser.showOpenDialog(null);

        if (imageFile != null) {
            try {
                byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
                String imageData = Base64.getEncoder().encodeToString(imageBytes);

                String requestBody = "{ \"userId\": " + userId + ", \"imageData\": \"" + imageData + "\" }";
                String url = "http://localhost:8080/api/v1/userProfile/updateUserImage/" + userId;

                HttpResponse<String> response = DBUtils.putMethod(url, requestBody);
                System.out.println(response);
                if (response.statusCode() == 200) {
                    System.out.println("User image updated successfully.");

                    displayUserImage(userId);
                } else {
                    System.out.println("Failed to update user image.");
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayUserImage(int userId) throws IOException, InterruptedException {
        String url = "http://localhost:8080/api/v1/userProfile/getUserImage/" + userId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<InputStream> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofInputStream());

        if (response.statusCode() == 200) {
            InputStream imageStream = response.body();
            Image userImage = new Image(imageStream);
            userImageView.setImage(userImage);
            profileIcon.setVisible(false);
        } else {
            System.out.println("Failed to retrieve user image.");
        }
    }

    public void updateUser(ActionEvent actionEvent) throws SQLException, IOException, InterruptedException {
        editUser();
    }

    public void uploadPhoto(MouseEvent event) throws SQLException, IOException, InterruptedException {
        updateUserImage();
    }
}
