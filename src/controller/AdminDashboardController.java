/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.sun.jdi.connect.spi.Connection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author lugtu
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Pane userDashboradWindow;
    @FXML
    private Pane blurringEffect;
    @FXML
    private HBox sidePanelTitleColor;
    @FXML
    private Label lblTimeDashboard;
    @FXML
    private Label lblDateDashboard;
    @FXML
    private Pane dashBoardWindow;
    @FXML
    private Pane courseYearWindow;
    @FXML
    private Pane sectionStudWindow;
    @FXML
    private Pane officerWindow;
    @FXML
    private Pane feedbackWindow;
    @FXML
    private Pane trashWindow;
    @FXML
    private AnchorPane clockPane;
    @FXML
    private Pane sidePanel;
    @FXML
    private Pane dashBoardButton;
    @FXML
    private Pane courseYearButton;
    @FXML
    private Pane sectionButton;
    @FXML
    private Pane officerButton;
    @FXML
    private Pane feedbackButton;
    @FXML
    private Pane trashButton;
    private ImageView gradientCursor;
    private boolean isSidePanelOpen = false;

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    private double x = 0;
    private double y = 0;
    @FXML
    private TextField txtStudentID;
    @FXML
    private TextField txtPassword;
    private TextField txtCourseID;
    private TextField txtSectionID;
    @FXML
    private TableView<Feedback> tableFeedBack;
    @FXML
    private TableColumn<Feedback, String> designRate;
    @FXML
    private TableColumn<Feedback, String> functionRate;
    @FXML
    private TableColumn<Feedback, String> ExperienceRate;
    @FXML
    private TableColumn<Feedback, String> FeedBackComment;
    @FXML
    private TextField txtRoleID;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtMiddlename;
    @FXML
    private TextField txtSuffix;
    @FXML
    private ComboBox<String> cbCourse;
    @FXML
    private ComboBox<String> cbSectionYear;
    @FXML
    private Button btnCreateAccount;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<OfficerAccountData> tblOfficerData;
    @FXML
    private TableColumn<OfficerAccountData, String> tblStudentID;
    @FXML
    private TableColumn<OfficerAccountData, String> tblPassword;
    @FXML
    private TableColumn<OfficerAccountData, String> tblRoleID;
    @FXML
    private TableColumn<OfficerAccountData, String> tblSurname;
    @FXML
    private TableColumn<OfficerAccountData, String> tblFirstName;
    @FXML
    private TableColumn<OfficerAccountData, String> tblMiddlename;
    @FXML
    private TableColumn<OfficerAccountData, String> tblSuffix;
    @FXML
    private TableColumn<OfficerAccountData, String> tblCourse;
    @FXML
    private TableColumn<OfficerAccountData, String> tblYearSection;

    @FXML
    private Button btnRateFeedback;
    @FXML
    private Button btnClear;
    @FXML
    private TextField txtCourseAbb;
    @FXML
    private TextField txtCourseName;
    @FXML
    private Button btnCreateCourse;
    @FXML
    private Button btnDeleteCourse;
    @FXML
    private TableView<getCourseData> tblCourseData;
    @FXML
    private TableColumn<getCourseData, String> tblCourseAbb;
    @FXML
    private TableColumn<getCourseData, String> tblCourseName;
    @FXML
    private TextField txtYearSection;
    @FXML
    private TableColumn<getYearSectionData, String> tblYearCourse;
    @FXML
    private Button btnCreateYearSection;
    @FXML
    private Button btnDeleteYearSection;
    @FXML
    private TableView<getYearSectionData> tblYearSectionData;
    @FXML
    private TextField searchOfficerTField;
    @FXML
    private TextField searchCourseTF;
    @FXML
    private TextField searchSection;
    @FXML
    private TableView<OfficerAccountData> tblStudentAcc;
    @FXML
    private TableColumn<OfficerAccountData, String> tblStudentID1;
    @FXML
    private TableColumn<OfficerAccountData, String> tblPassword1;
    @FXML
    private TableColumn<OfficerAccountData, String> tblRoleID1;
    @FXML
    private TableColumn<OfficerAccountData, String> tblSurname1;
    @FXML
    private TableColumn<OfficerAccountData, String> tblFirstName1;
    @FXML
    private TableColumn<OfficerAccountData, String> tblMiddlename1;
    @FXML
    private TableColumn<OfficerAccountData, String> tblSuffix1;
    @FXML
    private TableColumn<OfficerAccountData, String> tblCourse1;
    @FXML
    private TableColumn<OfficerAccountData, String> tblYearSection1;
    @FXML
    private Pane eduhubAccount;
    @FXML
    private TextField searchStudent;
    @FXML
    private Text lblStudentID;
    @FXML
    private Text lblSuffix;
    @FXML
    private Text lblPassword;
    @FXML
    private Text lblSurname;
    @FXML
    private Text lblFirstName;
    @FXML
    private Text lblMiddleName;
    @FXML
    private Text lblCourse;
    @FXML
    private Text lblYearSection;
    @FXML
    private Label lblNumberOfStudents;
    @FXML
    private Label lblCoursesAvailable;
    @FXML
    private Label lblOfficerNumber;
    @FXML
    private Label lblHighestCountStudent;
    @FXML
    private Label lblHighestCountCourse;
    @FXML
    private Label lblLowestCountStudent;
    @FXML
    private Label lblLowestCountCourse;
    @FXML
    private BarChart<String, Number> bchartStudentNumber;
    @FXML
    private Label lblTodoCount;
    @FXML
    private BarChart<String, Number> bchartTaskPDeadline;
    @FXML
    private Label lblAdminNumber;
    @FXML
    private TableView<GetArchiveCourseData> archiveCoursetbl;
    @FXML
    private TableColumn<GetArchiveCourseData, String> archiveCourse;
    @FXML
    private TableColumn<GetArchiveCourseData, String> archiveCourseName;
    @FXML
    private Label courseArchiveCount;
    @FXML
    private TableView<GetArchiveYearSectionData> archiveYearSectionTbl;
    @FXML
    private TableColumn<GetArchiveYearSectionData, String> archiveYearSection;
    @FXML
    private TableView<GetArchiveFeedBack> archiveFeedBacktbl;
    @FXML
    private TableColumn<GetArchiveFeedBack, Integer> archiveDesignRate;
    @FXML
    private TableColumn<GetArchiveFeedBack, Integer> archiveFunctionRate;
    @FXML
    private TableColumn<GetArchiveFeedBack, Integer> archiveExperienceRate;
    @FXML
    private TableColumn<GetArchiveFeedBack, Integer> archiveFeedBackComment;
    @FXML
    private TableView<?> archiveStudentAccTbl;
    @FXML
    private TableColumn<?, ?> archiveStudID;
    @FXML
    private TableColumn<?, ?> archivePassword;
    @FXML
    private TableColumn<?, ?> archiveRoleID;
    @FXML
    private TableColumn<?, ?> archiveSurname;
    @FXML
    private TableColumn<?, ?> archiveFirstname;
    @FXML
    private TableColumn<?, ?> archiveMiddleName;
    @FXML
    private TableColumn<?, ?> archiveSuffix;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        closeDefaultNavi();
        gradientCursor = new ImageView(new Image("media/f4a64b5df91d325a4c7a3b673827c312.jpg"));
        gradientCursor.setFitWidth(100); // Set the width of the image
        gradientCursor.setPreserveRatio(true); // Maintain the image's aspect ratio
        gradientCursor.setSmooth(true); // Enable image smoothing
        // Add the gradientCursor to the main pane
        userDashboradWindow.getChildren().add(0, gradientCursor);

        // Apply BoxBlur effect
        applyBoxBlurEffect(gradientCursor);

        // Add mouse move event handler
        userDashboradWindow.setOnMouseMoved(this::handleMouseMove);

        // Load the image from your computer
        Image backgroundImage = new Image("/media/gradient-design-pane.png");

        // Set the background image of the AnchorPane
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(
                        BackgroundSize.AUTO,
                        BackgroundSize.AUTO,
                        false,
                        false,
                        true,
                        true
                )
        );

        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();

        sidePanel.setVisible(true);
        dashBoardWindow.setVisible(true);
        courseYearWindow.setVisible(false);
        sectionStudWindow.setVisible(false);
        officerWindow.setVisible(false);
        feedbackWindow.setVisible(false);
        trashWindow.setVisible(false);

        timeNowForDashboard();
        dateLabelForDashboard();

        // Initialize columns
        designRate.setCellValueFactory(new PropertyValueFactory<>("designRating"));
        functionRate.setCellValueFactory(new PropertyValueFactory<>("functionRating"));
        ExperienceRate.setCellValueFactory(new PropertyValueFactory<>("experienceRating"));
        FeedBackComment.setCellValueFactory(new PropertyValueFactory<>("feedbackReport"));

        // Load feedback data
        loadFeedbackData();

        fetchCourseToComboBox(cbCourse);
        fetchSectionToComboBox(cbSectionYear);

        // Initialize columns
        tblStudentID.setCellValueFactory(new PropertyValueFactory<>("tblStudentID"));
        tblPassword.setCellValueFactory(new PropertyValueFactory<>("tblPassword"));
        tblRoleID.setCellValueFactory(new PropertyValueFactory<>("tblRoleID"));
        tblSurname.setCellValueFactory(new PropertyValueFactory<>("tblSurname"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("tblFirstName"));
        tblMiddlename.setCellValueFactory(new PropertyValueFactory<>("tblMiddlename"));
        tblSuffix.setCellValueFactory(new PropertyValueFactory<>("tblSuffix"));
        tblCourse.setCellValueFactory(new PropertyValueFactory<>("tblCourse"));
        tblYearSection.setCellValueFactory(new PropertyValueFactory<>("tblYearSection"));

        // Load officer account data
        loadOfficerAccountData();

        // Add event handler to the table
        tblOfficerData.setOnMouseClicked(event -> {
            // Check if a row is clicked
            if (event.getClickCount() == 1) {
                handleTableClickOfficer();
            }
        });

        tblStudentAcc.setOnMouseClicked(event -> {
            // Check if a row is clicked
            if (event.getClickCount() == 1) {
                handleTableClickStudent();
            }
        });

        tblCourseAbb.setCellValueFactory(new PropertyValueFactory<>("CourseAbb"));
        tblCourseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        loadCourseData();

        tblYearCourse.setCellValueFactory(new PropertyValueFactory<>("SectionName"));
        loadYearSectionData();

        // Initialize columns
        tblStudentID1.setCellValueFactory(new PropertyValueFactory<>("tblStudentID"));
        tblPassword1.setCellValueFactory(new PropertyValueFactory<>("tblPassword"));
        tblRoleID1.setCellValueFactory(new PropertyValueFactory<>("tblRoleID"));
        tblSurname1.setCellValueFactory(new PropertyValueFactory<>("tblSurname"));
        tblFirstName1.setCellValueFactory(new PropertyValueFactory<>("tblFirstName"));
        tblMiddlename1.setCellValueFactory(new PropertyValueFactory<>("tblMiddlename"));
        tblSuffix1.setCellValueFactory(new PropertyValueFactory<>("tblSuffix"));
        tblCourse1.setCellValueFactory(new PropertyValueFactory<>("tblCourse"));
        tblYearSection1.setCellValueFactory(new PropertyValueFactory<>("tblYearSection"));

        // Load Officer Account data
        loadStudentAccountData();

        int studentCount = fetchNumberOfStudents();
        lblNumberOfStudents.setText(String.valueOf(studentCount));

        int coursesCount = fetchNumberOfCourses();
        lblCoursesAvailable.setText(String.valueOf(coursesCount));

        int officerCount = fetchNumberOfOfficer();
        lblOfficerNumber.setText(String.valueOf(officerCount));

        int adminCount = fetchNumberOfAdmin();
        lblAdminNumber.setText(String.valueOf(adminCount));

        barChartStudentNumber();
        fetchHighestStudentCount();
        fetchLowestStudentCount();
        barChartTaskPerDeadline();

        fetchToDoPerDeadlineCount();

        archiveCourse.setCellValueFactory(new PropertyValueFactory<>("CourseAbb"));
        archiveCourseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        loadArchiveCourseData();
        updateCourseArchiveCount();

        archiveYearSection.setCellValueFactory(new PropertyValueFactory<>("SectionName"));
        loadArchiveYearSectionData();

        archiveDesignRate.setCellValueFactory(new PropertyValueFactory<>("designRating"));
        archiveFunctionRate.setCellValueFactory(new PropertyValueFactory<>("functionRating"));
        archiveExperienceRate.setCellValueFactory(new PropertyValueFactory<>("experienceRating"));
        archiveFeedBackComment.setCellValueFactory(new PropertyValueFactory<>("feedbackReport"));
        loadArchiveFeedBackData();

    }

    private final boolean stop = false;
    private Timeline timeline;
    private boolean running = false;
    private int seconds = 0;
    private Timeline countdownTimeline;
    private int countdownSeconds;
    private int hour;
    private int minute;
    private int second;
    private Thread timerThread;

    private void handleMouseMove(MouseEvent event) {
        // Update image position based on mouse cursor
        double x = event.getX();
        double y = event.getY();
        updateImagePosition(x, y);
    }

    private void updateImagePosition(double x, double y) {
        // Adjust the position to center the image at the cursor tip
        double imageWidth = gradientCursor.getBoundsInLocal().getWidth();
        double imageHeight = gradientCursor.getBoundsInLocal().getHeight();

        gradientCursor.setLayoutX(x - imageWidth / 2 + 145);  // Adjusting by 50 pixels in x (left)
        gradientCursor.setLayoutY(y - imageHeight / 2 + 145); // Adjusting by 50 pixels in y (downward)
    }

    private void applyBoxBlurEffect(ImageView imageView) {
        // Create a BoxBlur effect with specified width, height, and iterations
        BoxBlur boxBlur = new BoxBlur(99.0, 108.0, 3);
        imageView.setEffect(boxBlur);
    }

    @FXML
    private void minimizeButton(ActionEvent event) {
        Stage stage = (Stage) userDashboradWindow.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before exiting?");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out");
            stage.close();
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before loging out?");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out");

            userDashboradWindow.getScene().getWindow().hide();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/signInWindow.fxml"));
                Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                ((Node) (event.getSource())).getScene().getWindow().hide();
                stage.setWidth(785);
                stage.setHeight(514);

                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                stage.setX(centerX - 392.5);
                stage.setY(centerY - 257);

                Scene scene = new Scene(root, 785, 514);

                stage.setScene(scene);
                stage.show();

                root.setOnMousePressed((mouseEvent) -> {
                    x = mouseEvent.getSceneX();
                    y = mouseEvent.getSceneY();
                });

                root.setOnMouseDragged((mouseEvent) -> {
                    stage.setX(mouseEvent.getScreenX() - x);
                    stage.setY(mouseEvent.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((mouseEvent) -> {
                    stage.setOpacity(1);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void slidePanelButton(ActionEvent event) {
        // Apply blur effect during sliding animation
        applyBlurEffect(true);

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sidePanel);
        slider1.setToX(0);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();
    }

    @FXML
    private void closeSideNavigation(ActionEvent event) {
        // Apply blur effect during closing animation
        applyBlurEffect(false);

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sidePanel);
        slider1.setToX(-370);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();
    }

// ...
    private void applyBlurEffect(boolean apply) {
        BoxBlur boxBlur = new BoxBlur(5, 5, 3); // You can adjust these values based on your preference

        if (apply) {
            // Apply blur effect
            blurringEffect.setEffect(boxBlur);
        } else {
            // Remove blur effect
            blurringEffect.setEffect(null);
        }
    }

    private void closeDefaultNavi() {
        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sidePanel);
        slider1.setToX(-370);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();
    }

    private Pane lastClickedButton = dashBoardButton;

    @FXML
    public void SwitchForm(MouseEvent event) {

        Pane clickedButton = (Pane) event.getSource();

        if (clickedButton == lastClickedButton) {
            // Ignore the click if the same button was clicked twice in a row
            return;
        }

        if (clickedButton == dashBoardButton) {
            // ... (rest of the code remains the same)
        }

        try {
            // Update the last clicked button
            lastClickedButton = clickedButton;
            if (clickedButton == dashBoardButton) {
                setButtonColor(dashBoardButton, true);
                setButtonColor(courseYearButton, false);
                setButtonColor(sectionButton, false);
                setButtonColor(officerButton, false);
                setButtonColor(feedbackButton, false);
                setButtonColor(trashButton, false);

                dashBoardWindow.setVisible(true);
                courseYearWindow.setVisible(false);
                sectionStudWindow.setVisible(false);
                officerWindow.setVisible(false);
                feedbackWindow.setVisible(false);
                trashWindow.setVisible(false);

            } else if (clickedButton == courseYearButton) {
                setButtonColor(dashBoardButton, false);
                setButtonColor(courseYearButton, true);
                setButtonColor(sectionButton, false);
                setButtonColor(officerButton, false);
                setButtonColor(feedbackButton, false);
                setButtonColor(trashButton, false);

                dashBoardWindow.setVisible(false);
                courseYearWindow.setVisible(true);
                sectionStudWindow.setVisible(false);
                officerWindow.setVisible(false);
                feedbackWindow.setVisible(false);
                trashWindow.setVisible(false);

            } else if (clickedButton == sectionButton) {
                setButtonColor(dashBoardButton, false);
                setButtonColor(courseYearButton, false);
                setButtonColor(sectionButton, true);
                setButtonColor(officerButton, false);
                setButtonColor(feedbackButton, false);
                setButtonColor(trashButton, false);

                dashBoardWindow.setVisible(false);
                courseYearWindow.setVisible(false);
                sectionStudWindow.setVisible(true);
                officerWindow.setVisible(false);
                feedbackWindow.setVisible(false);
                trashWindow.setVisible(false);

            } else if (clickedButton == officerButton) {
                setButtonColor(dashBoardButton, false);
                setButtonColor(courseYearButton, false);
                setButtonColor(sectionButton, false);
                setButtonColor(officerButton, true);
                setButtonColor(feedbackButton, false);
                setButtonColor(trashButton, false);

                dashBoardWindow.setVisible(false);
                courseYearWindow.setVisible(false);
                sectionStudWindow.setVisible(false);
                officerWindow.setVisible(true);
                feedbackWindow.setVisible(false);
                trashWindow.setVisible(false);

            } else if (clickedButton == feedbackButton) {
                setButtonColor(dashBoardButton, false);
                setButtonColor(courseYearButton, false);
                setButtonColor(sectionButton, false);
                setButtonColor(officerButton, false);
                setButtonColor(feedbackButton, true);
                setButtonColor(trashButton, false);

                dashBoardWindow.setVisible(false);
                courseYearWindow.setVisible(false);
                sectionStudWindow.setVisible(false);
                officerWindow.setVisible(false);
                feedbackWindow.setVisible(true);
                trashWindow.setVisible(false);
            } else if (clickedButton == trashButton) {
                setButtonColor(dashBoardButton, false);
                setButtonColor(courseYearButton, false);
                setButtonColor(sectionButton, false);
                setButtonColor(officerButton, false);
                setButtonColor(feedbackButton, false);
                setButtonColor(trashButton, true);

                dashBoardWindow.setVisible(false);
                courseYearWindow.setVisible(false);
                sectionStudWindow.setVisible(false);
                officerWindow.setVisible(false);
                feedbackWindow.setVisible(false);
                trashWindow.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setButtonColor(Pane pane, boolean isSelected) {
        if (isSelected) {
            pane.getStyleClass().add("selected-button");
        } else {
            pane.getStyleClass().remove("selected-button");
        }
    }

    private void timeNowForDashboard() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                final String timeNow = sdf.format(new Date());
                Platform.runLater(() -> {
                    lblTimeDashboard.setText(timeNow);
                });
            }
        });

        thread.start();
    }

    private void dateLabelForDashboard() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(dateFormat);
        lblDateDashboard.setText(formattedDate);
    }

    ////////////////////////////////
    // FEEDBACK RATE SECTION
    @FXML
    private void RateFeedbackButton(ActionEvent event) throws SQLException {
        Feedback selectedFeedback = tableFeedBack.getSelectionModel().getSelectedItem();

        if (selectedFeedback != null) {
            // Show confirmation alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Feedback");
            alert.setContentText("Are you sure you want to delete this feedback entry?");

            Optional<ButtonType> result = alert.showAndWait();
            connect = database.getConnection();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User clicked OK, proceed with deletion

                // Remove from UI
                tableFeedBack.getItems().remove(selectedFeedback);

                // Remove from the database
                deleteFeedbackFromDatabase(selectedFeedback);
                // Now, insert the course into the archive table (trash_course)
                insertIntoArchiveFeedbackTable(connect, selectedFeedback);
                loadFeedbackData();

                loadArchiveFeedBackData();

                showSuccessAlert("Course removed successfully!");
            }
        } else {
            // Inform the user that no item is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Feedback Selected");
            alert.setContentText("Please select a feedback entry in the table.");
            alert.showAndWait();
        }
    }

    private void deleteFeedbackFromDatabase(Feedback feedback) {
        connect = database.getConnection();

        try {
            String deleteQuery = "DELETE FROM ratings_feedback WHERE designRating = ? AND functionRating = ? AND experienceRating = ? AND feedbackReport = ?";
            prepare = connect.prepareStatement(deleteQuery);
            prepare.setString(1, feedback.getDesignRating());
            prepare.setString(2, feedback.getFunctionRating());
            prepare.setString(3, feedback.getExperienceRating());
            prepare.setString(4, feedback.getFeedbackReport());

            int affectedRows = prepare.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Feedback deleted successfully from the database.");
            } else {
                System.out.println("Failed to delete feedback from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private java.sql.Connection connect;

    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private ObservableList<Feedback> feedbackList;

    private void loadFeedbackData() {
        feedbackList = FXCollections.observableArrayList();
        connect = database.getConnection();

        // Add logic to retrieve feedback data from the database
        // Replace the placeholders with your actual column names
        try {
            prepare = connect.prepareStatement("SELECT designRating, functionRating, experienceRating, feedbackReport FROM ratings_feedback");
            result = prepare.executeQuery(); // Execute the query and obtain the result set

            while (result.next()) {
                Feedback feedback = new Feedback();
                feedback.setDesignRating(result.getString("designRating"));
                feedback.setFunctionRating(result.getString("functionRating"));
                feedback.setExperienceRating(result.getString("experienceRating"));
                feedback.setFeedbackReport(result.getString("feedbackReport"));
                feedbackList.add(feedback);
            }

            tableFeedBack.setItems(feedbackList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection in a finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //////////////////////////////////////////////
    // OFFICER ACCOUNT SECTION
    private void fetchCourseToComboBox(ComboBox<String> comboBox) {

        try {
            prepare = connect.prepareStatement("SELECT CourseAbb FROM filter_course");
            result = prepare.executeQuery();

            List<String> items = new ArrayList<>();
            while (result.next()) {
                String itemName = result.getString("CourseAbb");
                items.add(itemName);
            }

            comboBox.getItems().addAll(items);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching course data: " + e.getMessage());
        }
    }

    private void fetchSectionToComboBox(ComboBox<String> comboBox) {

        try {
            prepare = connect.prepareStatement("SELECT SectionName FROM filter_section");
            result = prepare.executeQuery();

            List<String> items = new ArrayList<>();
            while (result.next()) {
                String itemName = result.getString("SectionName");
                items.add(itemName);
            }

            comboBox.getItems().addAll(items);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching course data: " + e.getMessage());
        }
    }

    @FXML
    private void handleCreateAccount(ActionEvent event) {
        String sql = "INSERT INTO account_student (StudentID, Password, RoleID, Surname, Firstname, Middlename, Suffix, CourseID, SectionID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connect = database.getConnection();
            prepare = connect.prepareStatement(sql);

            // Set parameters for the prepared statement
            prepare.setString(1, txtStudentID.getText());
            prepare.setString(2, txtPassword.getText());
            prepare.setInt(3, 2); // Assuming RoleID has a default value of 2
            prepare.setString(4, txtSurname.getText());
            prepare.setString(5, txtFirstname.getText());
            prepare.setString(6, txtMiddlename.getText());
            prepare.setString(7, txtSuffix.getText());
            prepare.setString(8, cbCourse.getValue()); // Assuming you're using the value from the ComboBox
            prepare.setString(9, cbSectionYear.getValue()); // Assuming you're using the value from the ComboBox

            // Execute the SQL query
            prepare.executeUpdate();

            // Show success alert
            showSuccessAlert("Account created successfully!");

            // Load and refresh the TableView
            loadOfficerAccountData();
            int officerCount = fetchNumberOfOfficer();
            lblOfficerNumber.setText(String.valueOf(officerCount));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("controller.AdminDashboardController.handleCreateAccount()");
        }
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private ObservableList<OfficerAccountData> officerAccountDataList;

    private void loadOfficerAccountData() {
        officerAccountDataList = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            // Assume your database connection is already established
            prepare = connect.prepareStatement("SELECT StudentID, Password, RoleID, Surname, Firstname, Middlename, Suffix, CourseID, SectionID FROM account_student WHERE RoleID = 2");
            result = prepare.executeQuery(); // Execute the query and obtain the result set

            while (result.next()) {
                OfficerAccountData officerAccount = new OfficerAccountData();
                officerAccount.setTblStudentID(result.getString("StudentID"));
                officerAccount.setTblPassword(result.getString("Password"));
                officerAccount.setTblRoleID(result.getString("RoleID"));
                officerAccount.setTblSurname(result.getString("Surname"));
                officerAccount.setTblFirstName(result.getString("Firstname"));
                officerAccount.setTblMiddlename(result.getString("Middlename"));
                officerAccount.setTblSuffix(result.getString("Suffix"));
                officerAccount.setTblCourse(result.getString("CourseID"));
                officerAccount.setTblYearSection(result.getString("SectionID"));

                officerAccountDataList.add(officerAccount);
            }
            tblOfficerData.setItems(officerAccountDataList);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection in a finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void handleTableClickOfficer() {
        // Get the selected item from the table
        OfficerAccountData selectedAccount = tblOfficerData.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedAccount != null) {
            // Populate the text fields and combo boxes with the selected item's data
            txtStudentID.setText(selectedAccount.getTblStudentID());
            txtPassword.setText(selectedAccount.getTblPassword());
            txtSurname.setText(selectedAccount.getTblSurname());
            txtFirstname.setText(selectedAccount.getTblFirstName());
            txtMiddlename.setText(selectedAccount.getTblMiddlename());
            txtSuffix.setText(selectedAccount.getTblSuffix());

            // Assuming cbCourse and cbSectionYear are ComboBox<String>
            cbCourse.setValue(selectedAccount.getTblCourse());
            cbSectionYear.setValue(selectedAccount.getTblYearSection());
        }
    }

    @FXML
    private void btnClear(ActionEvent event) {
        // Clear or set the text fields to be empty
        txtStudentID.clear();
        txtPassword.clear();
        txtSurname.clear();
        txtFirstname.clear();
        txtMiddlename.clear();
        txtSuffix.clear();

        // Clear or set the combo boxes to be empty
        cbCourse.setValue(null);
        cbSectionYear.setValue(null);
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        OfficerAccountData selectedOfficer = tblOfficerData.getSelectionModel().getSelectedItem();

        if (selectedOfficer != null) {
            // Show confirmation alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Officer Account");
            alert.setContentText("Are you sure you want to delete this officer account?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User clicked OK, proceed with deletion

                // Remove from UI
                tblOfficerData.getItems().remove(selectedOfficer);

                // Remove from the database
                deleteOfficerFromDatabase(selectedOfficer);

                // Inform the user about successful deletion
                showAlert("Success", "Officer Account Deleted", "Officer account removed successfully.");
                txtStudentID.clear();
                txtPassword.clear();
                txtSurname.clear();
                txtFirstname.clear();
                txtMiddlename.clear();
                txtSuffix.clear();

                // Clear or set the combo boxes to be empty
                cbCourse.setValue(null);
                cbSectionYear.setValue(null);
                int officerCount = fetchNumberOfOfficer();
                lblOfficerNumber.setText(String.valueOf(officerCount));
            }
        } else {
            // Inform the user that no item is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Officer Account Selected");
            alert.setContentText("Please select an officer account in the table.");
            alert.showAndWait();
        }
    }

    private void deleteOfficerFromDatabase(OfficerAccountData officerAccount) {
        connect = database.getConnection();

        try {
            String deleteQuery = "DELETE FROM account_student WHERE StudentID = ?";
            prepare = connect.prepareStatement(deleteQuery);
            prepare.setString(1, officerAccount.getTblStudentID());

            int affectedRows = prepare.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Officer account deleted successfully from the database.");
            } else {
                System.out.println("Failed to delete officer account from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void btnUpdate(ActionEvent event) {
        OfficerAccountData selectedOfficer = tblOfficerData.getSelectionModel().getSelectedItem();

        if (selectedOfficer != null) {
            // Get the updated values from your text fields or other input components
            String updatedPassword = txtPassword.getText(); // Replace with your actual text field
            String updatedSurname = txtSurname.getText(); // Replace with your actual text field
            String updatedFirstName = txtFirstname.getText(); // Replace with your actual text field
            String updatedMiddlename = txtMiddlename.getText(); // Replace with your actual text field
            String updatedSuffix = txtSuffix.getText(); // Replace with your actual text field
            String updatedCourse = cbCourse.getValue(); // Replace with your actual combo box
            String updatedYearSection = cbSectionYear.getValue(); // Replace with your actual combo box

            // Update in the database
            updateOfficerInDatabase(selectedOfficer, updatedPassword, updatedSurname, updatedFirstName,
                    updatedMiddlename, updatedSuffix, updatedCourse, updatedYearSection);

            // Inform the user about successful update
            showAlert("Success", "Officer Account Updated", "Officer account updated successfully.");

            // Clear and reload the data in the TableView
            clearAndLoadOfficerAccountData();
            txtStudentID.clear();
            txtPassword.clear();
            txtSurname.clear();
            txtFirstname.clear();
            txtMiddlename.clear();
            txtSuffix.clear();
        } else {
            // Inform the user that no item is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Officer Account Selected");
            alert.setContentText("Please select an officer account in the table.");
            alert.showAndWait();
        }
    }

    private void updateOfficerInDatabase(OfficerAccountData officer, String updatedPassword, String updatedSurname,
            String updatedFirstName, String updatedMiddlename, String updatedSuffix, String updatedCourse,
            String updatedYearSection) {
        connect = database.getConnection();

        try {
            String updateQuery = "UPDATE account_student SET Password = ?, Surname = ?, Firstname = ?, Middlename = ?, "
                    + "Suffix = ?, CourseID = ?, SectionID = ? WHERE StudentID = ?";
            prepare = connect.prepareStatement(updateQuery);

            prepare.setString(1, updatedPassword);
            prepare.setString(2, updatedSurname);
            prepare.setString(3, updatedFirstName);
            prepare.setString(4, updatedMiddlename);
            prepare.setString(5, updatedSuffix);
            prepare.setString(6, updatedCourse);
            prepare.setString(7, updatedYearSection);
            prepare.setString(8, officer.getTblStudentID());

            int affectedRows = prepare.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Officer Account updated successfully in the database.");
            } else {
                System.out.println("Failed to update Officer Account in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void clearAndLoadOfficerAccountData() {
        // Clear the existing data
        tblOfficerData.getItems().clear();

        // Reload the data
        loadOfficerAccountData();
    }

    @FXML
    private void searchOfficerAccount(ActionEvent event) {
        String searchText = searchOfficerTField.getText();

        if (searchText.isEmpty()) {
            loadOfficerAccountData(); // Call the loadOfficerAccountData() method to show all data
            return;
        }

        ObservableList<OfficerAccountData> officerAccountData = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement("SELECT StudentID, Password, RoleID, Surname, Firstname, Middlename, Suffix, CourseID, SectionID FROM account_student WHERE RoleID = 2 AND (StudentID LIKE ? OR Password LIKE ? OR RoleID LIKE ? OR Surname LIKE ? OR Firstname LIKE ? OR Middlename LIKE ? OR Suffix LIKE ? OR CourseID LIKE ? OR SectionID LIKE ?)");
            for (int i = 1; i <= 9; i++) {
                prepare.setString(i, "%" + searchText + "%");
            }

            result = prepare.executeQuery();

            while (result.next()) {
                OfficerAccountData officerAccount = new OfficerAccountData();
                officerAccount.setTblStudentID(result.getString("StudentID"));
                officerAccount.setTblPassword(result.getString("Password"));
                officerAccount.setTblRoleID(result.getString("RoleID"));
                officerAccount.setTblSurname(result.getString("Surname"));
                officerAccount.setTblFirstName(result.getString("Firstname"));
                officerAccount.setTblMiddlename(result.getString("Middlename"));
                officerAccount.setTblSuffix(result.getString("Suffix"));
                officerAccount.setTblCourse(result.getString("CourseID"));
                officerAccount.setTblYearSection(result.getString("SectionID"));

                officerAccountData.add(officerAccount);
            }

            tblOfficerData.setItems(officerAccountData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // Add listener to detect when search text is cleared
        searchOfficerTField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                loadOfficerAccountData(); // Call the loadOfficerAccountData() method to show all data
            }
        });
    }

    //////////////////////////////////////////////
    // COURSE SECTION
    @FXML
    private void handleCreateCourse(ActionEvent event) {
        String sql = "Insert into filter_course (CourseAbb, CourseName)" + "Values (? , ?)";

        try {
            connect = database.getConnection();
            prepare = connect.prepareStatement(sql);

            // Set parameters for the prepared statement
            prepare.setString(1, txtCourseAbb.getText());
            prepare.setString(2, txtCourseName.getText());

            // Execute the SQL query
            prepare.executeUpdate();

            showSuccessAlert("Course created successfully!");
            cbCourse.getItems().clear();
            fetchCourseToComboBox(cbCourse);

            txtCourseAbb.clear();
            txtCourseName.clear();
            clearAndLoadCourseData();
            int coursesCount = fetchNumberOfCourses();
            lblCoursesAvailable.setText(String.valueOf(coursesCount));

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (show error message, log, etc.)
        }
    }

    @FXML
    private void handleDeleteCourse(ActionEvent event) {
        // Show a confirmation alert
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to remove this item?");
        alert.setContentText("This will move the data to archive.");

        // Wait for user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, proceed with removal

            try {
                connect = database.getConnection();

                // Get the selected item from the table
                getCourseData selectedCourse = tblCourseData.getSelectionModel().getSelectedItem();

                if (selectedCourse != null) {
                    // First, delete the course from the task table (filter_course)
                    deleteFromTaskTable(connect, selectedCourse.getCourseAbb());

                    // Now, insert the course into the archive table (trash_course)
                    insertIntoArchiveTable(connect, selectedCourse);

                    showSuccessAlert("Course removed successfully!");

                    // Refresh the table and ComboBox after removal
                    loadCourseData();
                    cbCourse.getItems().clear();
                    fetchCourseToComboBox(cbCourse);
                    int coursesCount = fetchNumberOfCourses();
                    lblCoursesAvailable.setText(String.valueOf(coursesCount));
                    loadArchiveCourseData();
                    updateCourseArchiveCount();
                } else {
                    // If no item is selected, show a warning or error message
                    showWarningAlert("Please select a course to remove.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            } finally {
                closeResources();
            }
        }
    }

    private void showWarningAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private ObservableList<getCourseData> courseData;

    private void loadCourseData() {
        courseData = FXCollections.observableArrayList();
        connect = database.getConnection();

        // Add logic to retrieve feedback data from the database
        // Replace the placeholders with your actual column names
        try {
            prepare = connect.prepareStatement("SELECT CourseAbb,CourseName FROM filter_course");
            result = prepare.executeQuery(); // Execute the query and obtain the result set

            while (result.next()) {
                getCourseData CourseData = new getCourseData();
                CourseData.setCourseAbb(result.getString("CourseAbb"));
                CourseData.setCourseName(result.getString("CourseName"));
                courseData.add(CourseData);
            }

            tblCourseData.setItems(courseData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection in a finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void clearAndLoadCourseData() {
        // Clear the existing data
        tblCourseData.getItems().clear();

        // Reload the data
        loadCourseData();
    }

    @FXML
    private void searchCourse(ActionEvent event) {
        String searchText = searchCourseTF.getText();

        if (searchText.isEmpty()) {
            loadCourseData(); // Call the loadCourseData() method to show all data
            return;
        }

        ObservableList<getCourseData> filteredCourseData = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement("SELECT CourseAbb, CourseName FROM filter_course WHERE CourseAbb LIKE ? OR CourseName LIKE ?");
            prepare.setString(1, "%" + searchText + "%");
            prepare.setString(2, "%" + searchText + "%");

            result = prepare.executeQuery();

            while (result.next()) {
                getCourseData courseData = new getCourseData();
                courseData.setCourseAbb(result.getString("CourseAbb"));
                courseData.setCourseName(result.getString("CourseName"));
                filteredCourseData.add(courseData);
            }

            tblCourseData.setItems(filteredCourseData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // Add listener to detect when search text is cleared
        searchCourseTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                loadCourseData(); // Call the loadCourseData() method to show all data
            }
        });
    }

    //////////////////////////////////////////////
    // SECTION SECTION
    @FXML
    private void handleCreateYearSection(ActionEvent event) {
        String sql = "Insert into filter_section (SectionName)" + "Values (?)";

        try {
            connect = database.getConnection();
            prepare = connect.prepareStatement(sql);

            // Set parameters for the prepared statement
            prepare.setString(1, txtYearSection.getText());

            // Execute the SQL query
            prepare.executeUpdate();

            showSuccessAlert("Course created successfully!");

            cbSectionYear.getItems().clear();
            fetchSectionToComboBox(cbSectionYear);

            txtCourseAbb.clear();
            txtCourseName.clear();
            clearAndLoadSectionNameData();
            // Optionally, you can show a success message or perform other actions here
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (show error message, log, etc.)
        }
    }

    @FXML
    private void handleDeleteYearSection(ActionEvent event) {
        // Get the selected item from the table
        getYearSectionData selectedCourse = tblYearSectionData.getSelectionModel().getSelectedItem();

        if (selectedCourse != null) {
            try {

                connect = database.getConnection();
                deleteFromYearSectionTable(connect, selectedCourse.getSectionName()); // Corrected variable name

                insertIntoArchiveYearSectionTable(connect, selectedCourse);

                showSuccessAlert("Year & Section deleted successfully!");
                loadYearSectionData();

                // Refresh the table after deletion
                clearAndLoadSectionNameData();
                cbSectionYear.getItems().clear();
                fetchSectionToComboBox(cbSectionYear);
                loadArchiveYearSectionData();
                updateCourseArchiveCount();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            } finally {
                closeResources();
            }
        } else {
            // If no item is selected, show a warning or error message
            showWarningAlert("Please select a Year & Section to delete.");
        }
    }

    private ObservableList<getYearSectionData> YearSectionData;

    private void loadYearSectionData() {
        YearSectionData = FXCollections.observableArrayList();
        connect = database.getConnection();

        // Add logic to retrieve feedback data from the database
        // Replace the placeholders with your actual column names
        try {
            prepare = connect.prepareStatement("SELECT SectionName FROM filter_section");
            result = prepare.executeQuery(); // Execute the query and obtain the result set

            while (result.next()) {
                getYearSectionData yearSectionData = new getYearSectionData();
                yearSectionData.setSectionName(result.getString("SectionName"));
                YearSectionData.add(yearSectionData);
            }

            tblYearSectionData.setItems(YearSectionData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection in a finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void clearAndLoadSectionNameData() {
        // Clear the existing data
        tblYearSectionData.getItems().clear();

        // Reload the data
        loadYearSectionData();
    }

    @FXML
    private void searchSection(ActionEvent event) {
        String searchText = searchSection.getText();

        if (searchText.isEmpty()) {
            loadYearSectionData(); // Call the loadYearSectionData() method to show all data
            return;
        }

        ObservableList<getYearSectionData> filteredYearSectionData = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement("SELECT SectionName FROM filter_section WHERE SectionName LIKE ?");
            prepare.setString(1, "%" + searchText + "%");

            result = prepare.executeQuery();

            while (result.next()) {
                getYearSectionData yearSectionData = new getYearSectionData();
                yearSectionData.setSectionName(result.getString("SectionName"));
                filteredYearSectionData.add(yearSectionData);
            }

            tblYearSectionData.setItems(filteredYearSectionData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // Add listener to detect when search text is cleared
        searchSection.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                loadYearSectionData(); // Call the loadYearSectionData() method to show all data
            }
        });
    }

    //////////////////////////////////////////////
    // STUDENT SECTION
    @FXML
    private void deleteAcc(ActionEvent event) {
        // Get the selected officer account data from the table
        OfficerAccountData selectedstudent = tblStudentAcc.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Officer Account");
        alert.setContentText("Are you sure you want to delete this officer account?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            // Call the method to delete the officer account data from the database
            deleteStudentAccountFromDatabase(selectedstudent);

            // Remove the selected officer account from the table view
            tblStudentAcc.getItems().remove(selectedstudent);
            showSuccessAlert("Student Account deleted successfully!");

        } else {
            System.out.println("Please select an officer account to delete.");
            showWarningAlert("Please select a Student Account to delete.");
        }
    }

    private void deleteStudentAccountFromDatabase(OfficerAccountData officerAccount) {
        connect = database.getConnection();

        try {
            String deleteQuery = "DELETE FROM account_student WHERE StudentID = ?";
            PreparedStatement prepare = connect.prepareStatement(deleteQuery);
            prepare.setString(1, officerAccount.getTblStudentID());

            int affectedRows = prepare.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Officer account deleted successfully from the database.");
            } else {
                System.out.println("Failed to delete officer account from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchStudent(ActionEvent event) {
        // Assuming you have a TextField named searchStudent for user input
        String searchKeyword = searchStudent.getText().trim().toLowerCase();

        if (!searchKeyword.isEmpty()) {
            ObservableList<OfficerAccountData> searchResultList = FXCollections.observableArrayList();

            for (OfficerAccountData officerAccount : studentAccountList) {
                // Iterate through all columns and check if the search keyword matches any column value
                if (containsIgnoreCase(officerAccount.getTblStudentID(), searchKeyword)
                        || containsIgnoreCase(officerAccount.getTblPassword(), searchKeyword)
                        || containsIgnoreCase(officerAccount.getTblRoleID(), searchKeyword)
                        || containsIgnoreCase(officerAccount.getTblSurname(), searchKeyword)
                        || containsIgnoreCase(officerAccount.getTblFirstName(), searchKeyword)
                        || containsIgnoreCase(officerAccount.getTblMiddlename(), searchKeyword)
                        || containsIgnoreCase(officerAccount.getTblSuffix(), searchKeyword)
                        || containsIgnoreCase(officerAccount.getTblCourse(), searchKeyword)
                        || containsIgnoreCase(officerAccount.getTblYearSection(), searchKeyword)) {

                    searchResultList.add(officerAccount);
                }
            }

            // Update the TableView with the search results
            tblStudentAcc.setItems(searchResultList);
        } else {
            // If the search field is empty, display all students
            tblStudentAcc.setItems(studentAccountList);
        }

        // Add listener to detect when search text is cleared
        searchStudent.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                loadStudentAccountData(); // Call the loadYearSectionData() method to show all data
            }
        });
    }

    // Helper method to check if a string contains another string (case-insensitive)
    private boolean containsIgnoreCase(String source, String target) {
        return source.toLowerCase().contains(target);
    }
    private ObservableList<OfficerAccountData> studentAccountList;

    private void loadStudentAccountData() {
        studentAccountList = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            PreparedStatement prepare = connect.prepareStatement("SELECT StudentID, Password, RoleID, Surname, Firstname, Middlename, Suffix, CourseID, SectionID FROM account_student WHERE RoleID = 3");
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                OfficerAccountData officerAccount = new OfficerAccountData();
                officerAccount.setTblStudentID(result.getString("StudentID"));
                officerAccount.setTblPassword(result.getString("Password"));
                officerAccount.setTblRoleID(result.getString("RoleID"));
                officerAccount.setTblSurname(result.getString("Surname"));
                officerAccount.setTblFirstName(result.getString("Firstname"));
                officerAccount.setTblMiddlename(result.getString("Middlename"));
                officerAccount.setTblSuffix(result.getString("Suffix"));
                officerAccount.setTblCourse(result.getString("CourseID"));
                officerAccount.setTblYearSection(result.getString("SectionID"));

                studentAccountList.add(officerAccount);
            }
            tblStudentAcc.setItems(studentAccountList);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void handleTableClickStudent() {
        // Get the selected item from the table
        OfficerAccountData selectedAccount = tblStudentAcc.getSelectionModel().getSelectedItem();

        // Check if an item is selected
        if (selectedAccount != null) {
            // Populate the text fields and combo boxes with the selected item's data
            lblStudentID.setText(selectedAccount.getTblStudentID());
            lblPassword.setText(selectedAccount.getTblPassword());
            lblSurname.setText(selectedAccount.getTblSurname());
            lblFirstName.setText(selectedAccount.getTblFirstName());
            lblMiddleName.setText(selectedAccount.getTblMiddlename());
            lblSuffix.setText(selectedAccount.getTblSuffix());
            lblCourse.setText(selectedAccount.getTblCourse());
            lblYearSection.setText(selectedAccount.getTblYearSection());

        }
    }

    @FXML
    private void PrintData(ActionEvent event) {
        // Capture the content of the specified pane
        WritableImage writableImage = new WritableImage((int) eduhubAccount.getWidth(), (int) eduhubAccount.getHeight());
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        eduhubAccount.snapshot(snapshotParameters, writableImage);

        // Ask the user if they want to print the data
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Print Confirmation");
        alert.setHeaderText("Do you want to print the data?");
        alert.setContentText("Choose your option.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked "OK", proceed with saving the image
            saveImage(writableImage);
        }
    }

    private void saveImage(WritableImage writableImage) {
        // Use a FileChooser to prompt the user for the file location
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("PNG Files", "*.png"));

        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                // Convert the WritableImage to a BufferedImage
                java.awt.image.BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);

                // Save the BufferedImage to the selected file location
                ImageIO.write(bufferedImage, "png", file);

                // Show a success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Data successfully printed and saved to your computer.");
                successAlert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                // Show an error alert if there's an issue with saving the image
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Error saving the image. Please try again.");
                errorAlert.showAndWait();
            }
        }
    }

    //////////////////////////////////////////////
    // DASHBOARD SECTION
    private int fetchNumberOfStudents() {

        int count = 0;

        try {

            if (connect.isClosed()) {
                // If the connection is closed, open it
                connect = database.getConnection();
            }

            prepare = connect.prepareStatement("Select count(UserID) from account_student where roleID = 3");
            result = prepare.executeQuery();

            if (result.next()) {
                count = result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching count of student: " + e.getMessage());
        } finally {
            // Close resources
            closeResources();
        }

        return count;
    }

    private int fetchNumberOfCourses() {

        int count = 0;

        try {

            if (connect.isClosed()) {
                // If the connection is closed, open it
                connect = database.getConnection();
            }

            prepare = connect.prepareStatement("Select count(CourseID) from filter_course");
            result = prepare.executeQuery();

            if (result.next()) {
                count = result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching count of student: " + e.getMessage());
        } finally {
            // Close resources
            closeResources();
        }

        return count;
    }

    private int fetchNumberOfOfficer() {

        int count = 0;

        try {

            if (connect.isClosed()) {
                // If the connection is closed, open it
                connect = database.getConnection();
            }

            prepare = connect.prepareStatement("Select count(UserID) from account_student where roleID = 2");
            result = prepare.executeQuery();

            if (result.next()) {
                count = result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching count of student: " + e.getMessage());
        } finally {
            // Close resources
            closeResources();
        }

        return count;
    }

    private int fetchNumberOfAdmin() {

        int count = 0;

        try {

            if (connect.isClosed()) {
                // If the connection is closed, open it
                connect = database.getConnection();
            }

            prepare = connect.prepareStatement("Select count(UserID) from account_student where roleID = 1");
            result = prepare.executeQuery();

            if (result.next()) {
                count = result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching count of student: " + e.getMessage());
        } finally {
            // Close resources
            closeResources();
        }

        return count;
    }

    private XYChart.Series<String, Number> barChartStudentNumber() {

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        try {

            if (connect.isClosed()) {
                // If the connection is closed, open it
                connect = database.getConnection();
            }

            prepare = connect.prepareStatement("SELECT CourseID, StudentCount FROM (SELECT CourseID, COUNT(*) AS StudentCount, 'DESC' AS OrderDirection FROM account_student where roleID = 3 GROUP BY CourseID ORDER BY StudentCount DESC LIMIT 1) AS queryHighest UNION SELECT CourseID, StudentCount FROM (SELECT CourseID, COUNT(*) AS StudentCount, 'ASC' AS OrderDirection FROM account_student where roleID = 3 GROUP BY CourseID ORDER BY StudentCount ASC LIMIT 1) AS queryLowest");
            result = prepare.executeQuery();

            while (result.next()) {
                String courseID = result.getString("CourseID");
                double count = result.getDouble("StudentCount");

                series.getData().add(new XYChart.Data<>(courseID, count));
            }
            bchartStudentNumber.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Always close resources in a finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                // Don't close the connection here; reuse it if needed
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return series;
    }

    private XYChart.Series<String, Number> barChartTaskPerDeadline() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        try {
            // Assuming 'connect' is your database connection
            if (connect.isClosed()) {
                // If the connection is closed, open it
                connect = database.getConnection();
            }

            // Assuming 'prepare' is your PreparedStatement
            prepare = connect.prepareStatement("select count(*) as NumberOfToDo, Deadline from todo group by deadline");
            result = prepare.executeQuery();

            while (result.next()) {
                String deadline = result.getString("Deadline");
                double numberOfToDo = result.getDouble("NumberOfToDo");

                series.getData().add(new XYChart.Data<>(deadline, numberOfToDo));
            }

            bchartTaskPDeadline.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Always close resources in a finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                // Don't close the connection here; reuse it if needed
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return series;
    }

    private void fetchHighestStudentCount() {
        try {
            connect = database.getConnection();

            // Your SQL query
            String sqlQuery = "SELECT CourseID, COUNT(*) AS StudentCount FROM account_student where roleID = 3 GROUP BY CourseID ORDER BY StudentCount DESC LIMIT 1";

            // Execute the query
            prepare = connect.prepareStatement(sqlQuery);
            result = prepare.executeQuery();

            // Check if there are results
            if (result.next()) {
                // Get data from the result set
                String highestCourseID = result.getString("CourseID");
                int highestStudentCount = result.getInt("StudentCount");

                // Update the labels in JavaFX
                lblHighestCountCourse.setText(highestCourseID);
                lblHighestCountStudent.setText(String.valueOf(highestStudentCount));
            } else {
                // Handle the case where there are no results
                lblHighestCountCourse.setText("No results found.");
                lblHighestCountStudent.setText("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }

    }

    private void fetchLowestStudentCount() {
        try {
            connect = database.getConnection();

            // Your SQL query
            String sqlQuery = "SELECT CourseID, COUNT(*) AS StudentCount FROM account_student where roleID = 3 GROUP BY CourseID ORDER BY StudentCount ASC LIMIT 1";

            // Execute the query
            prepare = connect.prepareStatement(sqlQuery);
            result = prepare.executeQuery();

            // Check if there are results
            if (result.next()) {
                // Get data from the result set
                String lowestCourseID = result.getString("CourseID");
                int lowestStudentCount = result.getInt("StudentCount");

                // Update the labels in JavaFX
                lblLowestCountCourse.setText(lowestCourseID);
                lblLowestCountStudent.setText(String.valueOf(lowestStudentCount));
            } else {
                // Handle the case where there are no results
                lblHighestCountCourse.setText("No results found.");
                lblHighestCountStudent.setText("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }

    }

    private void fetchToDoPerDeadlineCount() {
        try {
            connect = database.getConnection();

            // Your SQL query
            String sqlQuery = "select count(*) as ToDoCount from todo";

            // Execute the query
            prepare = connect.prepareStatement(sqlQuery);
            result = prepare.executeQuery();

            // Check if there are results
            if (result.next()) {
                // Get data from the result set
                int toDoCount = result.getInt("ToDoCount");

                // Update the labels in JavaFX
                lblTodoCount.setText(String.valueOf(toDoCount));
            } else {
                // Handle the case where there are no results
                lblHighestCountCourse.setText("No results found.");
                lblHighestCountStudent.setText("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }

    }

    private void closeResources() {
        try {
            if (result != null) {
                result.close();
            }
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////
    // ARCHIVES ADMIN SECTIONS / TABLES
    // COURSE GOES TO ARCHIVE 
    private void deleteFromTaskTable(java.sql.Connection conn, String courseAbb) throws SQLException {
        String deleteQuery = "DELETE FROM filter_course WHERE CourseAbb = ?";
        try (PreparedStatement prepare = conn.prepareStatement(deleteQuery)) {
            prepare.setString(1, courseAbb);
            prepare.executeUpdate();
        }
    }

    private void insertIntoArchiveTable(java.sql.Connection conn, getCourseData courseData) throws SQLException {
        String insertQuery = "INSERT INTO trash_course (CourseAbb, CourseName) VALUES (?, ?)";
        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setString(1, courseData.getCourseAbb());
            prepare.setString(2, courseData.getCourseName());
            prepare.executeUpdate();
        }
    }

    private ObservableList<GetArchiveCourseData> archiveCourseData; // Corrected variable name

    private void loadArchiveCourseData() {
        archiveCourseData = FXCollections.observableArrayList(); // Corrected variable name
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement("SELECT CourseAbb, CourseName FROM trash_course");
            result = prepare.executeQuery();

            while (result.next()) {
                GetArchiveCourseData courseData = new GetArchiveCourseData(); // Corrected variable name
                courseData.setCourseAbb(result.getString("CourseAbb"));
                courseData.setCourseName(result.getString("CourseName"));
                archiveCourseData.add(courseData); // Corrected variable name
            }

            archiveCoursetbl.setItems(archiveCourseData); // Assuming archiveCoursetbl is the TableView in your FXML
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection in a finally block
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void restoreCourse(ActionEvent event) {
        // Show a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to retrieve this item?");
        alert.setContentText("This action will retrieve the selected data to your Course List");

        // Wait for user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                connect = database.getConnection();

                // Get the selected item from the table
                GetArchiveCourseData selectedCourse = archiveCoursetbl.getSelectionModel().getSelectedItem();

                if (selectedCourse != null) {
                    // Insert the course into the filter_course table
                    insertIntoCourseTable(connect, selectedCourse);

                    // Delete the course from the trash_course table
                    deleteFromArchiveCourseTable(connect, selectedCourse);

                    showSuccessAlert("Course retrieved successfully!");

                    // Refresh the table and ComboBox after retrieval
                    loadArchiveCourseData();
                    fetchCourseToComboBox(cbCourse);
                    int coursesCount = fetchNumberOfCourses();
                    lblCoursesAvailable.setText(String.valueOf(coursesCount));
                    loadCourseData();
                } else {
                    // If no item is selected, show a warning or error message
                    showWarningAlert("Please select a course to retrieve.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            } finally {
                closeResources();
            }
        }
    }

    private void insertIntoCourseTable(java.sql.Connection conn, GetArchiveCourseData courseData) throws SQLException {
        String insertQuery = "INSERT INTO filter_course (CourseAbb, CourseName) VALUES (?, ?)";
        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setString(1, courseData.getCourseAbb());
            prepare.setString(2, courseData.getCourseName());
            prepare.executeUpdate();
        }
    }

    private void deleteFromArchiveCourseTable(java.sql.Connection conn, GetArchiveCourseData courseData) throws SQLException {
        String deleteQuery = "DELETE FROM trash_course WHERE CourseAbb = ?";
        try (PreparedStatement prepare = conn.prepareStatement(deleteQuery)) {
            prepare.setString(1, courseData.getCourseAbb());
            prepare.executeUpdate();
        }
    }

    @FXML
    private void deleteCourseArchive(ActionEvent event) {
        // Show a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to permanently delete this item?");
        alert.setContentText("This action cannot be undone.");

        // Wait for user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                connect = database.getConnection();

                // Get the selected item from the table
                GetArchiveCourseData selectedCourse = archiveCoursetbl.getSelectionModel().getSelectedItem();

                if (selectedCourse != null) {
                    // Insert the course into the backup_course table
                    insertIntoBackupCourseDatabase(connect, selectedCourse);

                    // Delete the course from the trash_course table
                    deleteFromArchiveCourseTable(connect, selectedCourse);

                    showSuccessAlert("Course permanently deleted!");

                    // Refresh the table after permanent deletion
                    loadArchiveCourseData();
                } else {
                    // If no item is selected, show a warning or error message
                    showWarningAlert("Please select a course to permanently delete.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            } finally {
                closeResources();
            }
        }
    }

    private void insertIntoBackupCourseDatabase(java.sql.Connection conn, GetArchiveCourseData courseData) throws SQLException {
        String insertQuery = "INSERT INTO backup_course (CourseAbb, CourseName) VALUES (?, ?)";
        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setString(1, courseData.getCourseAbb());
            prepare.setString(2, courseData.getCourseName());
            prepare.executeUpdate();
        }
    }

    private void updateCourseArchiveCount() {
        try {
            connect = database.getConnection();
            prepare = connect.prepareStatement("SELECT COUNT(CourseID) FROM trash_course");
            result = prepare.executeQuery();
            int count = result.next() ? result.getInt(1) : 0;
            courseArchiveCount.setText(count + " items");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    ////////////////////////////////// 
    // YEAR GOES TO ARCHIVE 
    private void deleteFromYearSectionTable(java.sql.Connection conn, String sectionName) throws SQLException {
        String deleteQuery = "DELETE FROM filter_section WHERE SectionName = ?";
        try (PreparedStatement prepare = conn.prepareStatement(deleteQuery)) {
            prepare.setString(1, sectionName);
            prepare.executeUpdate();
        }

    }

    private void insertIntoArchiveYearSectionTable(java.sql.Connection conn, getYearSectionData yearSectionData) throws SQLException {
        String insertQuery = "INSERT INTO trash_section (SectionName) VALUES (?)";
        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setString(1, yearSectionData.getSectionName());
            prepare.executeUpdate();
        }
    }

    private ObservableList<GetArchiveYearSectionData> archiveYearSectionData;

    private void loadArchiveYearSectionData() {
        archiveYearSectionData = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement("SELECT SectionName FROM trash_section");
            result = prepare.executeQuery();

            while (result.next()) {
                GetArchiveYearSectionData yearSectionData = new GetArchiveYearSectionData();
                yearSectionData.setSectionName(result.getString("SectionName"));
                archiveYearSectionData.add(yearSectionData);
            }

            archiveYearSectionTbl.setItems(archiveYearSectionData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection in a finally block
            closeResources();
        }
    }

    @FXML
    private void restoreYearSection(ActionEvent event) {
        // Show a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to retrieve this item?");
        alert.setContentText("This action will retrieve the selected data to your Course List");

        // Wait for user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                connect = database.getConnection();

                // Get the selected item from the table
                GetArchiveYearSectionData selectedYearSection = archiveYearSectionTbl.getSelectionModel().getSelectedItem(); // Corrected variable name

                if (selectedYearSection != null) {
                    // Insert the section into the filter_section table
                    insertIntoYearSectionTable(connect, selectedYearSection);

                    // Delete the section from the trash_section table
                    deleteFromArchiveYearSectionTable(connect, selectedYearSection);
                    updateCourseArchiveCount();

                    showSuccessAlert("Year & Section retrieved successfully!");

                    // Refresh the table and ComboBox after retrieval
                    loadArchiveYearSectionData();
                    fetchSectionToComboBox(cbSectionYear);
                    loadYearSectionData();

                } else {
                    // If no item is selected, show a warning or error message
                    showWarningAlert("Please select a Year & Section to retrieve.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            } finally {
                closeResources();
            }
        }
    }

    private void insertIntoYearSectionTable(java.sql.Connection conn, GetArchiveYearSectionData yearSectionData) throws SQLException {
        String insertQuery = "INSERT INTO filter_section (SectionName) VALUES (?)";
        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setString(1, yearSectionData.getSectionName());
            prepare.executeUpdate();
        }
    }

    private void deleteFromArchiveYearSectionTable(java.sql.Connection conn, GetArchiveYearSectionData yearSectionData) throws SQLException {
        String deleteQuery = "DELETE FROM trash_section WHERE SectionName = ?";
        try (PreparedStatement prepare = conn.prepareStatement(deleteQuery)) {
            prepare.setString(1, yearSectionData.getSectionName());
            prepare.executeUpdate();
        }
    }

    @FXML
    private void deleteYearSectionArchive(ActionEvent event) {
        // Show a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to permanently delete this item?");
        alert.setContentText("This action cannot be undone.");

        // Wait for user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                connect = database.getConnection();

                // Get the selected item from the table
                GetArchiveYearSectionData selectedYearSection = archiveYearSectionTbl.getSelectionModel().getSelectedItem(); // Corrected variable name

                if (selectedYearSection != null) {
                    // Insert the section into the backup_section table
                    insertIntoBackupYearSectionDatabase(connect, selectedYearSection);

                    // Delete the section from the trash_section table
                    deleteFromArchiveYearSectionTable(connect, selectedYearSection);
                    updateCourseArchiveCount();

                    showSuccessAlert("Year & Section permanently deleted!");

                    // Refresh the table after permanent deletion
                    loadArchiveYearSectionData();
                } else {
                    // If no item is selected, show a warning or error message
                    showWarningAlert("Please select a Year & Section to permanently delete.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            } finally {
                closeResources();
            }
        }
    }

    private void insertIntoBackupYearSectionDatabase(java.sql.Connection conn, GetArchiveYearSectionData yearSectionData) throws SQLException {
        String insertQuery = "INSERT INTO backup_section (SectionName) VALUES (?)";
        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setString(1, yearSectionData.getSectionName());
            prepare.executeUpdate();
        }

    }

    ////////////////////////////////// 
    // FEEDBACK GOES TO ARCHIVE 
    private ObservableList<GetArchiveFeedBack> archiveFeedBackData;

    private void loadArchiveFeedBackData() {
        archiveFeedBackData = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement("SELECT designRating, functionRating, experienceRating, feedbackReport FROM trash_feedback");
            result = prepare.executeQuery();

            while (result.next()) {
                GetArchiveFeedBack feedback = new GetArchiveFeedBack();
                feedback.setDesignRating(result.getInt("designRating"));
                feedback.setFunctionRating(result.getInt("functionRating"));
                feedback.setExperienceRating(result.getInt("experienceRating"));
                feedback.setFeedbackReport(result.getString("feedbackReport"));
                archiveFeedBackData.add(feedback);
            }

            archiveFeedBacktbl.setItems(archiveFeedBackData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @FXML
    private void restoreFeedBack(ActionEvent event) {
        GetArchiveFeedBack selectedFeedBack = archiveFeedBacktbl.getSelectionModel().getSelectedItem();

        if (selectedFeedBack != null) {
            try {
                connect = database.getConnection();
                insertIntoFeedbackTable(connect, selectedFeedBack);
                deleteFromArchiveFeedBackTable(connect, selectedFeedBack);

                showSuccessAlert("Feedback retrieved successfully!");

                loadArchiveFeedBackData();
                loadFeedbackData();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            } finally {
                closeResources();
            }
        } else {
            showWarningAlert("Please select a feedback to retrieve.");
        }
    }

    @FXML
    private void deleteFeedBackArchive(ActionEvent event) {
        GetArchiveFeedBack selectedFeedBack = archiveFeedBacktbl.getSelectionModel().getSelectedItem();

        if (selectedFeedBack != null) {
            try {
                connect = database.getConnection();
                insertIntoBackupFeedBackDatabase(connect, selectedFeedBack);
                deleteFromArchiveFeedBackTable(connect, selectedFeedBack);

                showSuccessAlert("Feedback permanently deleted!");

                loadArchiveFeedBackData();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            } finally {
                closeResources();
            }
        } else {
            showWarningAlert("Please select a feedback to permanently delete.");
        }
    }

    private void insertIntoArchiveFeedbackTable(java.sql.Connection conn, Feedback feedback) throws SQLException {
        String insertQuery = "INSERT INTO trash_feedback (designRating, functionRating, experienceRating, feedbackReport) VALUES (?, ?, ?, ?)";
        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setString(1, feedback.getDesignRating());
            prepare.setString(2, feedback.getFunctionRating());
            prepare.setString(3, feedback.getExperienceRating());
            prepare.setString(4, feedback.getFeedbackReport());
            prepare.executeUpdate();
        }
    }

    private void insertIntoFeedbackTable(java.sql.Connection conn, GetArchiveFeedBack feedBack) throws SQLException {
        String insertQuery = "INSERT INTO ratings_feedback (designRating, functionRating, experienceRating, feedbackReport) VALUES (?, ?, ?, ?)";

        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setInt(1, feedBack.getDesignRating());
            prepare.setInt(2, feedBack.getFunctionRating());
            prepare.setInt(3, feedBack.getExperienceRating());
            prepare.setString(4, feedBack.getFeedbackReport());
            prepare.executeUpdate();
        }

    }

    private void deleteFromArchiveFeedBackTable(java.sql.Connection conn, GetArchiveFeedBack feedBack) throws SQLException {
        String deleteQuery = "DELETE FROM trash_feedback WHERE designRating = ? AND functionRating = ? AND experienceRating = ? AND feedbackReport = ?";

        try (PreparedStatement prepare = conn.prepareStatement(deleteQuery)) {
            prepare.setInt(1, feedBack.getDesignRating());
            prepare.setInt(2, feedBack.getFunctionRating());
            prepare.setInt(3, feedBack.getExperienceRating());
            prepare.setString(4, feedBack.getFeedbackReport());
            prepare.executeUpdate();
        }
    }

    private void insertIntoBackupFeedBackDatabase(java.sql.Connection conn, GetArchiveFeedBack feedBack) throws SQLException {
        String insertQuery = "INSERT INTO backup_feedback (designRating, functionRating, experienceRating, feedbackReport) VALUES (?, ?, ?, ?)";

        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setInt(1, feedBack.getDesignRating());
            prepare.setInt(2, feedBack.getFunctionRating());
            prepare.setInt(3, feedBack.getExperienceRating());
            prepare.setString(4, feedBack.getFeedbackReport());
            prepare.executeUpdate();
        }
    }

    
    ///////////////////////////////// 
    // STUDENT MANAGEMENT GOES TO ARCHIVE
    
    
}
