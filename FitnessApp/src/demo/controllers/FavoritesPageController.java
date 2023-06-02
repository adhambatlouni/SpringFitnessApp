package demo.controllers;
import demo.AnchorPaneUtils;
import demo.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class FavoritesPageController extends AnchorPane implements Initializable {
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
    public TextField searchTextField1;
    public TextField searchTextField2;
    public AnchorPane tableAnchorPane;
    @FXML
    private TableView<MealSearchModel> favoriteMealsTableView;
    @FXML
    private TableColumn<MealSearchModel, String> usernameTable1Column;
    @FXML
    private TableColumn<MealSearchModel, String> useremailTable1Column;
    @FXML
    private TableColumn<MealSearchModel, String> mealCategoryTableColumn;
    @FXML
    private TableColumn<MealSearchModel, String> mealDurationTableColumn;
    @FXML
    private TableColumn<MealSearchModel, String> mealTimesperweekTableColumn;
    @FXML
    private TableColumn<MealSearchModel, String> mealDifficultyTableColumn;
    @FXML
    private TableColumn<MealSearchModel, Image> mealImageTableColumn;

    @FXML
    private TableView<WorkoutSearchModel>favoriteWorkoutsTableView;
    @FXML
    private TableColumn<WorkoutSearchModel, String> usernameTable2Column;
    @FXML
    private TableColumn<WorkoutSearchModel, String> useremailTable2Column;
    @FXML
    private TableColumn<WorkoutSearchModel, String> workoutCategoryTableColumn;
    @FXML
    private TableColumn<WorkoutSearchModel, String> workoutDurationTableColumn;
    @FXML
    private TableColumn<WorkoutSearchModel, String> workoutTimesperweekTableColumn;
    @FXML
    private TableColumn<WorkoutSearchModel, String> workoutDifficultyTableColumn;
    @FXML
    private TableColumn<WorkoutSearchModel, Image> workoutImageTableColumn;

    ObservableList<MealSearchModel> mealSearchModelObservableList = FXCollections.observableArrayList();
    ObservableList<WorkoutSearchModel> workoutSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeHbox(childAnchorPane, childHbox);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(tableAnchorPane);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        childHbox.getChildren().add(scrollPane);

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

        loadFavoriteMealsData();
        loadFavoriteWorkoutsData();
    }

    private void loadFavoriteMealsData() {
        int userId = Main.currentUserId;
        String url = "http://localhost:8080/api/v1/FavoritesInfo/" + userId + "/getMealInfo";

        String responseBody = DBUtils.getMethod(url);

        if (responseBody != null) {
            JSONArray jsonArray = new JSONArray(responseBody);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String userName = jsonObject.getString("userName");
                String userEmail = jsonObject.getString("userEmail");
                String mealCategory = jsonObject.getString("mealCategory");
                String mealDuration = jsonObject.getString("mealDuration");
                String mealTimesPerWeek = jsonObject.getString("mealTimesPerWeek");
                String mealDifficulty = jsonObject.getString("mealDifficulty");
                String mealImageBase64 = jsonObject.getString("mealImage");

                if (!userName.isEmpty() && !userEmail.isEmpty() && !mealCategory.isEmpty()
                        && !mealDuration.isEmpty() && !mealTimesPerWeek.isEmpty() && !mealDifficulty.isEmpty()) {
                    byte[] mealImage = Base64.getDecoder().decode(mealImageBase64);

                    Image mealImageObject = null;
                    if (mealImage != null) {
                        mealImageObject = new Image(new ByteArrayInputStream(mealImage));
                    }

                    mealSearchModelObservableList.add(new MealSearchModel(userName, userEmail, mealCategory,
                            mealDuration, mealTimesPerWeek, mealDifficulty, mealImageObject));
                } else {
                    System.out.println("Error: One or more properties are missing in the JSON object");
                }
            }
        } else {
            System.out.println("Response body is null");
        }

        usernameTable1Column.setCellValueFactory(new PropertyValueFactory<>("userName"));
        useremailTable1Column.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        mealCategoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("mealCategory"));
        mealDurationTableColumn.setCellValueFactory(new PropertyValueFactory<>("mealDuration"));
        mealTimesperweekTableColumn.setCellValueFactory(new PropertyValueFactory<>("mealTimesPerWeek"));
        mealDifficultyTableColumn.setCellValueFactory(new PropertyValueFactory<>("mealDifficulty"));
        mealImageTableColumn.setCellValueFactory(new PropertyValueFactory<>("mealImage"));
        mealImageTableColumn.setCellFactory(tc -> new TableCell<MealSearchModel, Image>() {
            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView imageView = new ImageView(item);
                    imageView.setFitHeight(125);
                    imageView.setFitWidth(125);
                    setGraphic(imageView);
                }
            }
        });

        favoriteMealsTableView.setItems(mealSearchModelObservableList);

        FilteredList<MealSearchModel> filteredData1 = new FilteredList<>(mealSearchModelObservableList, b -> true);

        searchTextField1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData1.setPredicate(MealSearchModel -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if(MealSearchModel.getMealCategory().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if(MealSearchModel.getMealDuration().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if(MealSearchModel.getMealTimesPerWeek().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if(MealSearchModel.getMealDifficulty().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if(MealSearchModel.getUserName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if(MealSearchModel.getUserEmail().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<MealSearchModel> sortedData1 = new SortedList<>(filteredData1);

        sortedData1.comparatorProperty().bind(favoriteMealsTableView.comparatorProperty());

        favoriteMealsTableView.setItems(sortedData1);
    }

    private void loadFavoriteWorkoutsData() {
        int userId = Main.currentUserId;
        String url = "http://localhost:8080/api/v1/FavoritesInfo/" + userId + "/getWorkoutInfo";

        String responseBody = DBUtils.getMethod(url);

        if (responseBody != null) {
            JSONArray jsonArray = new JSONArray(responseBody);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String userName = jsonObject.getString("userName");
                String userEmail = jsonObject.getString("userEmail");
                String workoutCategory = jsonObject.getString("workoutCategory");
                String workoutDuration = jsonObject.getString("workoutDuration");
                String workoutTimesPerWeek = jsonObject.getString("workoutTimesPerWeek");
                String workoutDifficulty = jsonObject.getString("workoutDifficulty");
                String workoutImageBase64 = jsonObject.getString("workoutImage");

                if (!userName.isEmpty() && !userEmail.isEmpty() && !workoutCategory.isEmpty()
                        && !workoutDuration.isEmpty() && !workoutTimesPerWeek.isEmpty() && !workoutDifficulty.isEmpty()) {
                    byte[] workoutImage = Base64.getDecoder().decode(workoutImageBase64);

                    Image workoutImageObject = null;
                    if (workoutImage != null) {
                        workoutImageObject = new Image(new ByteArrayInputStream(workoutImage));
                    }

                    workoutSearchModelObservableList.add(new WorkoutSearchModel(userName, userEmail, workoutCategory,
                            workoutDuration, workoutTimesPerWeek, workoutDifficulty, workoutImageObject));
                } else {
                    System.out.println("Error: One or more properties are missing in the JSON object");
                }
            }
        } else {
            System.out.println("Response body is null");
        }

        usernameTable2Column.setCellValueFactory(new PropertyValueFactory<>("userName"));
        useremailTable2Column.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        workoutCategoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("workoutCategory"));
        workoutDurationTableColumn.setCellValueFactory(new PropertyValueFactory<>("workoutDuration"));
        workoutTimesperweekTableColumn.setCellValueFactory(new PropertyValueFactory<>("workoutTimesPerWeek"));
        workoutDifficultyTableColumn.setCellValueFactory(new PropertyValueFactory<>("workoutDifficulty"));
        workoutImageTableColumn.setCellValueFactory(new PropertyValueFactory<>("workoutImage"));
        workoutImageTableColumn.setCellFactory(tc -> new TableCell<WorkoutSearchModel, Image>() {
            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView imageView = new ImageView(item);
                    imageView.setFitHeight(125);
                    imageView.setFitWidth(125);
                    setGraphic(imageView);
                }
            }
        });

        favoriteWorkoutsTableView.setItems(workoutSearchModelObservableList);

        FilteredList<WorkoutSearchModel> filteredData2 = new FilteredList<>(workoutSearchModelObservableList, b -> true);

        searchTextField2.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData2.setPredicate(WorkoutSearchModel -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if(WorkoutSearchModel.getWorkoutCategory().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if(WorkoutSearchModel.getWorkoutDuration().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if(WorkoutSearchModel.getWorkoutTimesPerWeek().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if(WorkoutSearchModel.getWorkoutDifficulty().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if(WorkoutSearchModel.getUserName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else if(WorkoutSearchModel.getUserEmail().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<WorkoutSearchModel> sortedData2 = new SortedList<>(filteredData2);

        sortedData2.comparatorProperty().bind(favoriteWorkoutsTableView.comparatorProperty());

        favoriteWorkoutsTableView.setItems(sortedData2);
    }
}

