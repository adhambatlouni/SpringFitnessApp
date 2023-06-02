package demo.controllers;

import demo.AnchorPaneUtils;
import demo.Main;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InfoController extends AnchorPane implements Initializable {
    public TextField userheight;
    public TextField userweight;
    public TextField userage;
    public Button nextBtn;
    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private AnchorPane childAnchorPane;
    @FXML
    private ComboBox<String> myComboBox1;
    @FXML
    private ComboBox<String> myComboBox2;
    public ComboBox<String> myComboBox3;
    @FXML
    private VBox childVbox;
    private Main application;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeVbox(childAnchorPane, childVbox);
        List<String> countryNames = fetchCountryNames();
        myComboBox2.getItems().addAll(countryNames);

        AnchorPaneUtils.btnHover(nextBtn);
    }

    private List<String> fetchCountryNames() {
        List<String> countryNames = new ArrayList<>();

        try {
            URL url = new URL("http://www.geognos.com/api/en/countries/info/all.json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String response = in.readLine();

            JSONObject jsonObj = new JSONObject(response);
            JSONObject resultsObj = jsonObj.getJSONObject("Results");

            for (String countryCode : resultsObj.keySet()) {
                String countryName = resultsObj.getJSONObject(countryCode).getString("Name");
                countryNames.add(countryName);
            }
            in.close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryNames;
    }

    public void addInfo(ActionEvent actionEvent) throws Exception {

        TranslateTransition shakeTransition1 = DBUtils.createShakeTransition(myComboBox1, Duration.millis(100));
        TranslateTransition shakeTransition2 = DBUtils.createShakeTransition(myComboBox2, Duration.millis(100));
        TranslateTransition shakeTransition3 = DBUtils.createShakeTransition(userheight, Duration.millis(100));
        TranslateTransition shakeTransition4 = DBUtils.createShakeTransition(userweight, Duration.millis(100));
        TranslateTransition shakeTransition5 = DBUtils.createShakeTransition(userage, Duration.millis(100));
        TranslateTransition shakeTransition6 = DBUtils.createShakeTransition(myComboBox3, Duration.millis(100));

        String gender = myComboBox1.getValue();
        String country = myComboBox2.getValue();
        String goal = myComboBox3.getValue();

        String height = userheight.getText();
        String weight = userweight.getText();
        String age = userage.getText();

        if(gender == null || gender.isEmpty() || country == null || country.isEmpty() || height.isEmpty()
                || weight.isEmpty() || age.isEmpty() || goal == null || goal.isEmpty()) {
            shakeTransition1.playFromStart();
            shakeTransition2.playFromStart();
            shakeTransition3.playFromStart();
            shakeTransition4.playFromStart();
            shakeTransition5.playFromStart();
            shakeTransition6.playFromStart();
            return;
        }

        addUserInfo(gender, country, height, weight, age, goal);

            myComboBox1.setValue("");
            myComboBox2.setValue("");
            myComboBox3.setValue("");
            userheight.setText("");
            userweight.setText("");
            userage.setText("");
    }

    private void addUserInfo(String gender, String country, String height, String weight,
                            String age, String goal) throws Exception {

        String url = "http://localhost:8080/api/v1/userInfo/add";

        int userId = Main.currentUserId;
        int ageInt = Integer.parseInt(age);
        Double weightDouble = Double.parseDouble(height);
        Double heightDouble = Double.parseDouble(weight);
        System.out.println(userId);
        String jsonBody = String.format("{\"age\": %d, \"country\": \"%s\", \"gender\": \"%s\", \"goal\": \"%s\", " +
                "\"height\": %.2f, \"weight\": %.2f, \"user_id\": %d}",
                ageInt, country, gender, goal, heightDouble, weightDouble, userId);

        System.out.println("JSON Request Body: " + jsonBody);

        HttpResponse<String> response = DBUtils.postMethod(url, jsonBody);
        System.out.println(response);
        if(response.statusCode() == 201) {
            String responseBody = response.body();
            System.out.println(responseBody);
            application.replaceSceneContent("fxml/Homepage.fxml");
        }
        else {
            System.out.println("Failed to add user");
        }
    }
}

