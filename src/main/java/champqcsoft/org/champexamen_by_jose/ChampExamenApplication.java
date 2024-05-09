package champqcsoft.org.champexamen_by_jose;

// Imports
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;

public class ChampExamenApplication extends Application {

    // Fields
    private Exam exam;
    private VBox root;
    private Label labelShowingGrade;
    private VBox[] questionVBoxes;

    // Launch Method
    public static void main(String[] args) {
        launch(args);
    }

    // (File - Edit - Quiz - Extras - Help) MenuBar Method
    public MenuBar buildMenu() {
        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction(e -> openFileDialog());    // Open File
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> exitAppDialog());     // Exit App Dialog
        fileMenu.getItems().addAll(openMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        // Edit menu
        Menu editMenu = new Menu("Edit");
        MenuItem cutMenuItem = new MenuItem("Cut");
        MenuItem copyMenuItem = new MenuItem("Copy");
        MenuItem pasteMenuItem = new MenuItem("Paste");
        editMenu.getItems().addAll(cutMenuItem, copyMenuItem, pasteMenuItem);

        // Quiz menu
        Menu quizMenu = new Menu("Quiz");
        MenuItem startQuizMenuItem = new MenuItem("Start Quiz");
        MenuItem viewResultsMenuItem = new MenuItem("View Results");
        quizMenu.getItems().addAll(startQuizMenuItem, viewResultsMenuItem);

        // Extras menu
        Menu extrasMenu = new Menu("Extras");
        MenuItem settingsMenuItem = new MenuItem("Settings");
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(e -> displayAboutDialog()); // Display About Dialog Page
        extrasMenu.getItems().addAll(settingsMenuItem, aboutMenuItem);

        // Help menu
        Menu helpMenu = new Menu("Help");
        MenuItem helpContentMenuItem = new MenuItem("Help Content");
        helpContentMenuItem.setOnAction(e -> displayHelpDialog());      // Display Help Dialog
        MenuItem aboutAppMenuItem = new MenuItem("About App");
        aboutAppMenuItem.setOnAction(e -> displayAboutAppDialog());    // Display AboutApp Dialog Page
        helpMenu.getItems().addAll(helpContentMenuItem, new SeparatorMenuItem(), aboutAppMenuItem);

        // Create and populate the menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, quizMenu, extrasMenu, helpMenu);

        return menuBar;
    }

    /*
     THESE NEXT DIALOG METHODS ARE PURELY AESTHETIC FOR THE MENU ITEMS. (Non-Functional)
     Source for information about JavaFX Dialogs: https://code.makery.ch/blog/javafx-dialogs-official/
    */

    // Method to display the AboutApp dialog
    private void displayAboutAppDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About App");
        alert.setHeaderText("Champlain Examen App");
        alert.setContentText("This application was developed by Jose Villegas in the Java2 class for the St-Lambert Champlain College.");
        alert.showAndWait();
    }

    // Method to display the About dialog
    private void displayAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("This application was made in JavaFX alongside Java.");
        alert.showAndWait();
    }

    // Method to display the ExitApp dialog
    private void exitAppDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXITING");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("(The save button isn't functional.)");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0); // Exit the application
        } else {
            alert.close();
        }
    }

    // Method to display the Help dialog
    private void displayHelpDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Send Message to Developer");
        dialog.setHeaderText("Message Developer");
        dialog.setContentText("Message:");
        dialog.showAndWait();
    }

    private void openFileDialog(){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Java File");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Java files (*.java)", "*.java");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.showOpenDialog(null);
    }

    // (Logo + Gif) Loader Method
    public HBox buildTopBanner() {
        Image logo = new Image("file:///C:\\Users\\super\\IdeaProjects\\ChampExamen_by_Jose\\src\\main\\resources\\champqcsoft\\org\\champexamen_by_jose\\ChampLogo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(300);

        Image banner = new Image(new File("C:\\Users\\super\\IdeaProjects\\ChampExamen_by_Jose\\src\\main\\resources\\champqcsoft\\org\\champexamen_by_jose\\Banner.gif").toURI().toString());
        ImageView imageview = new ImageView(banner);
        Group viewImage = new Group(imageview);
        HBox topBanner = new HBox(logoView, viewImage);
        topBanner.setAlignment(Pos.TOP_CENTER);


        return topBanner;
    }

    // (Clear - Save - Submit) Buttons Method
    public HBox buildNavigationBar() {
        // Create button pane
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(new ClearButtonHandler());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> saveExamAnswers());

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(new SubmitButtonHandler());

        HBox buttonPane = new HBox(10);
        buttonPane.getChildren().addAll(clearButton, submitButton, saveButton);
        buttonPane.setAlignment(Pos.CENTER);

        return buttonPane;
    }


    // Set Question Answer Method
    private void setQuestionAnswer(int questionNumber, String a) {
        this.exam.submittedAnswers.put(questionNumber, a);
    }

    // Clear ExamAnswers (Not Functional)
    private void clearExamAnswers() {
        this.exam.submittedAnswers.clear();
        this.exam.submittedAnswers = new HashMap<>();
        ToggleGroup mcqGroup = new ToggleGroup();
        mcqGroup.selectToggle(null);

    }
    // Save ExamAnswers (Empty)
    private void saveExamAnswers() {}

    // Create Exam Page Method
    private VBox[] createExamPage() {
        int numberOfQuestion = this.exam.questions.size();
        System.out.println("Number of questions created : " + numberOfQuestion);
        System.out.println(); // Skip Line

        VBox[] vBoxes = new VBox[numberOfQuestion];

        for (int i = 0; i < numberOfQuestion; i++) {
            Question q = this.exam.questions.get(i);

            if (q.getQuestionType() == QuestionType.MCQ) { // MCQ Question
                vBoxes[i] = buildMCQ(i, (MCQQuestion) q);
            } else {                                       // TCQ Question
                vBoxes[i] = buildTFQ(i, (TFQuestion) q);
            }
            vBoxes[i].setSpacing(15);
            vBoxes[i].setAlignment(Pos.CENTER_LEFT);
        }
        return vBoxes;
    }

    // Build MCQ Method
    public VBox buildMCQ(int questionNumber, MCQQuestion mcqQuestion) {
        String str = String.format("%d ) %s", questionNumber + 1, mcqQuestion.getQuestionText());
        Label mcqLabel = new Label(str);
        mcqLabel.setFont(new Font("Arial", 14));

        ToggleGroup mcqGroup = new ToggleGroup();
        RadioButton mcqOptionA = new RadioButton("A. " + mcqQuestion.getOptions().get(0));
        RadioButton mcqOptionB = new RadioButton("B. " + mcqQuestion.getOptions().get(1));
        RadioButton mcqOptionC = new RadioButton("C. " + mcqQuestion.getOptions().get(2));
        RadioButton mcqOptionD = new RadioButton("D. " + mcqQuestion.getOptions().get(3));

        mcqOptionA.setToggleGroup(mcqGroup);
        mcqOptionA.setOnAction(e -> setQuestionAnswer(questionNumber, "A"));

        mcqOptionB.setToggleGroup(mcqGroup);
        mcqOptionB.setOnAction(e -> setQuestionAnswer(questionNumber, "B"));

        mcqOptionC.setToggleGroup(mcqGroup);
        mcqOptionC.setOnAction(e -> setQuestionAnswer(questionNumber, "C"));

        mcqOptionD.setToggleGroup(mcqGroup);
        mcqOptionD.setOnAction(e -> setQuestionAnswer(questionNumber, "D"));

        VBox mcqContainer = new VBox(10);
        mcqContainer.getChildren().addAll(mcqLabel, mcqOptionA, mcqOptionB, mcqOptionC, mcqOptionD);
        mcqContainer.setAlignment(Pos.CENTER);

        return mcqContainer;
    }

    // Build TFQ Method
    public VBox buildTFQ(int questionNumber, TFQuestion tfQuestion) {
        // Create label for question text
        String str = String.format("%d ) %s", questionNumber + 1, tfQuestion.getQuestionText());
        Label questionLabel = new Label(str);
        questionLabel.setFont(new Font("Arial", 14));

        // Create ToggleGroup for True/False options
        ToggleGroup tfGroup = new ToggleGroup();

        // Create RadioButtons for True/False options
        RadioButton trueRadioButton = new RadioButton("True");
        trueRadioButton.setToggleGroup(tfGroup);
        trueRadioButton.setOnAction(e -> setQuestionAnswer(questionNumber, "true"));

        RadioButton falseRadioButton = new RadioButton("False");
        falseRadioButton.setToggleGroup(tfGroup);
        falseRadioButton.setOnAction(e -> setQuestionAnswer(questionNumber, "false"));

        // Container for VBox
        VBox container = new VBox(10);
        container.setPadding(new Insets(10));

        // Add RadioButtons to the container
        container.getChildren().addAll(questionLabel, trueRadioButton, falseRadioButton);

        // Align the content to the center of the VBox
        container.setAlignment(Pos.CENTER);

        return container;
    }

    // SubmitAnswers Button Handler Method
    class SubmitButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            int grade = 0;
            for (int questionNumber = 0; questionNumber < exam.questions.size(); questionNumber++) {
                Question q = exam.getQuestion(questionNumber);
                System.out.println("Correct Answer of Question " + (questionNumber + 1) + ": " + q.getCorrectAnswer());
                System.out.println("Submitted Answer Of Question " + (questionNumber + 1) + ": " + exam.submittedAnswers.get(questionNumber) + '\n');

                if (q.getCorrectAnswer().equalsIgnoreCase(exam.submittedAnswers.get(questionNumber))) {
                    grade++;
                }
            }
            System.out.println("Grade: " + grade + "/" + questionVBoxes.length);
            labelShowingGrade.setText("Grade: " + grade + "/" + questionVBoxes.length);
        }
    }

    // ClearAnswer (Not functional)
     class ClearButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            clearExamAnswers();
            System.out.println("Cleared Submitted Answers");
        }
    }

    // Start Method
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Champlain Exam App");
        root = new VBox();
        Random random = new Random();

        // Read TFC & MCQ files
        QuestionBank qb = new QuestionBank();
        qb.readTFQ("C:\\Users\\super\\IdeaProjects\\ChampExamen_by_Jose\\src\\main\\resources\\champqcsoft\\org\\champexamen_by_jose\\tfq.txt");
        qb.readMCQ("C:\\Users\\super\\IdeaProjects\\ChampExamen_by_Jose\\src\\main\\resources\\champqcsoft\\org\\champexamen_by_jose\\mcq.txt");

        // Print 10 questions randomly of type TFQ or MCQ
        int[] randomAmountOfQuestions = new int[10];
        for (int i = 0; i < randomAmountOfQuestions.length; i++) {
            randomAmountOfQuestions[i] = random.nextInt(65);
        }

        LinkedList<Question> questions = qb.selectRandQuestions(randomAmountOfQuestions);
        this.exam = new Exam(questions);

        // Clear existing children and add the new page
        root.getChildren().clear();

        // Build Menu Bar
        MenuBar menuBar = buildMenu();
        root.getChildren().add(menuBar);

        // Build Top Banner
        HBox topHBox = buildTopBanner();
        root.getChildren().add(topHBox);

        // Build Grade Label
        labelShowingGrade = new Label("Grade: ");
        labelShowingGrade.setFont(Font.font(25));
        HBox gradeHbox = new HBox(labelShowingGrade);

        // Format Grade Label
        gradeHbox.setAlignment(Pos.CENTER);
        root.getChildren().add(gradeHbox);
        Insets gradeInsets = new Insets(10, 10, 10, 10);
        gradeHbox.setPadding(gradeInsets);

        // Build navigation bar
        HBox navigationBar = buildNavigationBar();
        root.getChildren().add(navigationBar);

        // Format NavBar
        navigationBar.setSpacing(10);
        Insets navInsets = new Insets(10, 10, 10, 10);
        navigationBar.setPadding(navInsets);

        // Create Exam Page
        questionVBoxes = createExamPage();
        VBox questionVbox = new VBox(questionVBoxes);

        // Format Exam Page
        questionVbox.setAlignment(Pos.CENTER_RIGHT);
        questionVbox.setSpacing(30);
        Insets examInsets = new Insets(20, 20, 20, 20);
        questionVbox.setPadding(examInsets);

        // Create ScrollPane for Exam Page
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(questionVbox);
        root.getChildren().addAll(scrollPane);

        // Create Scene
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);    // set app to fullscreen
        primaryStage.show();
    }


}