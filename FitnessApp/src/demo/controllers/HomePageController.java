package demo.controllers;

import demo.AnchorPaneUtils;
import demo.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

public class HomePageController extends AnchorPane implements Initializable {

    public AnchorPane parentAnchorPane;

    public AnchorPane childAnchorPane;
    public HBox childHbox;
    public VBox svg1Nav;
    public VBox svg2Nav;
    public VBox svg3Nav;
    public VBox svg4Nav;
    public VBox svg5Nav;
    public Label welcomeText;
    public Button startBtn;
    public Label editProfileLabel;
    public Label logoutlabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeHbox(childAnchorPane, childHbox);

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

        AnchorPaneUtils.btnHover2(startBtn);
        AnchorPaneUtils.labelHover3(editProfileLabel);
        AnchorPaneUtils.labelHover3(logoutlabel);

        try {
            welcomeUser();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void welcomeUser() throws IOException, InterruptedException {
        String url = "http://localhost:8080/api/v1/homePage/" + Main.currentUserId;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200) {
            String body = response.body();
            System.out.println("Response body: " + body);
            JSONObject jsonObject = new JSONObject((body));
            String username = jsonObject.getString("username");
            welcomeText.setText("Welcome " + username);
        }
        else {
            System.out.println("Failed.");
        }
    }

    public void goToHomepage(ActionEvent actionEvent) throws Exception {
        Main.replaceSceneContent("fxml/Mealspage.fxml");
    }

    public void editProfile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Profile.fxml"));
        Parent root = loader.load();
        ProfileController overlayController = loader.getController();

        Dialog<Void> dialog = new Dialog<>();
        dialog.getDialogPane().setContent(root);
        ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(closeButton);
        dialog.showAndWait();
        overlayController.setDialog(dialog);
    }

    public void logout(MouseEvent event) throws Exception {
        Main.replaceSceneContent("fxml/Startup.fxml");
    }
}
