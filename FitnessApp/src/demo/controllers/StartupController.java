package demo.controllers;

import demo.AnchorPaneUtils;
import demo.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StartupController extends AnchorPane implements Initializable {

    public Button startBtn;
    public Label loginLabel;
    @FXML
    private VBox childVbox;
    @FXML
    private AnchorPane parentAnchorPane;
    private Main application;
    @FXML
    private VBox child1;
    @FXML
    private AnchorPane childAnchorPane;
    public void setApp(Main application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parentAnchorPane.toFront();
        childAnchorPane.toBack();

        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane,childAnchorPane);
        AnchorPaneUtils.intializeVbox(childAnchorPane, childVbox);
        AnchorPaneUtils.initializeImage(parentAnchorPane, child1);

        AnchorPaneUtils.btnHover(startBtn);
        AnchorPaneUtils.labelHover(loginLabel);
    }

    public void goToLogin(MouseEvent mouseEvent) throws Exception {
        application.replaceSceneContent("fxml/Login.fxml");
    }

    public void goToSignUp(ActionEvent actionEvent) throws Exception {
        application.replaceSceneContent("fxml/SignUp.fxml");
    }
}
