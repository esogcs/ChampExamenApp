package champqcsoft.org.champexamen_by_jose;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChampExamTest extends Application {
    private MenuBar menuBar;

    public static void main(String[] args) {
        launch(args);
    }
    private MenuBar buildMenu() {
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        return menuBar;
    }
    private HBox buildTopBanner() {
        Image logo = new Image("C:\\Users\\Jose\\IdeaProjects\\ChampExamen_by_Jose\\src\\main\\resources\\champqcsoft\\org\\champexamen_by_jose\\x.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(800);

        HBox topBanner = new HBox(logoView);
        topBanner.setAlignment(Pos.CENTER);

        return topBanner;
    }
    private HBox buildNavigationBar() {
        Button homeButton = new Button("Home");
        Button quizzesButton = new Button("Quizzes");
        Button resultsButton = new Button("Results");

        HBox.setHgrow(homeButton, Priority.ALWAYS);
        HBox.setHgrow(quizzesButton, Priority.ALWAYS);
        HBox.setHgrow(resultsButton, Priority.ALWAYS);

        HBox navigationBar = new HBox(homeButton, quizzesButton, resultsButton);
        navigationBar.setAlignment(Pos.CENTER);

        return navigationBar;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Champs Testing App");

        // Build menu bar
        menuBar = buildMenu();

        // Build top banner
        HBox topBanner = buildTopBanner();

        // Build navigation bar
        HBox navigationBar = buildNavigationBar();

        // Create center pane for text fields
        Label gradeLabel = new Label("Grade:");
        TextField gradeTextField = new TextField();
        gradeTextField.setMaxWidth(100);

        VBox centerPane = new VBox(10);
        centerPane.getChildren().addAll(gradeLabel, gradeTextField);
        centerPane.setAlignment(Pos.CENTER);

        // Create button pane
        Button clearButton = new Button("Clear");
        Button saveButton = new Button("Save");
        Button submitButton = new Button("Submit");

        VBox buttonPane = new VBox(10);
        buttonPane.getChildren().addAll(clearButton, saveButton, submitButton);
        buttonPane.setAlignment(Pos.CENTER);

        // Create root pane
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(centerPane);
        root.setBottom(buttonPane);

        // Add top banner and navigation bar to a VBox and add it to the top of the root pane
        VBox topBar = new VBox(topBanner, navigationBar);
        topBar.setAlignment(Pos.CENTER);
        root.setTop(topBar);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
