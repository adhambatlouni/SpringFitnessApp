
package demo;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import demo.controllers.LoginController;
import demo.controllers.SignUpController;
import demo.controllers.StartupController;
import demo.security.Authenticator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;
    private static Scene scene;
    public static int currentUserId;

    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("FXML Login Sample");
            scene = new Scene(new AnchorPane());
            gotoStartup();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("fxml/Login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoStartup() {
        try {
            StartupController startup = (StartupController) replaceSceneContent("fxml/Startup.fxml");
            startup.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoSignUp() {
        try {
            SignUpController signup = (SignUpController) replaceSceneContent("fxml/SignUp.fxml");
            signup.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoMealsPage() {
        try {
            SignUpController signup = (SignUpController) replaceSceneContent("fxml/Mealspage.fxml");
            signup.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoWorkoutPage() {
        try {
            SignUpController signup = (SignUpController) replaceSceneContent("fxml/Workoutpage.fxml");
            signup.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);

        } finally {
            in.close();
        }
        scene.setRoot(page);
        return (Initializable) loader.getController();
    }
}
