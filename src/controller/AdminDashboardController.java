/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.sun.jdi.connect.spi.Connection;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
                handleTableClick();
            }
        });

        tblCourseAbb.setCellValueFactory(new PropertyValueFactory<>("CourseAbb"));
        tblCourseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        loadCourseData();

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
                Parent root = FXMLLoader.load(getClass().getResource("/view/selectRoleWindow.fxml"));
                Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setWidth(843);
                stage.setHeight(511);

                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                stage.setX(centerX - 421.5);
                stage.setY(centerY - 255.5);

                Scene scene = new Scene(root, 843, 511);

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

    @FXML
    private void RateFeedbackButton(ActionEvent event) {
        Feedback selectedFeedback = tableFeedBack.getSelectionModel().getSelectedItem();

        if (selectedFeedback != null) {
            // Show confirmation alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Feedback");
            alert.setContentText("Are you sure you want to delete this feedback entry?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User clicked OK, proceed with deletion

                // Remove from UI
                tableFeedBack.getItems().remove(selectedFeedback);

                // Remove from the database
                deleteFeedbackFromDatabase(selectedFeedback);
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

    private void fetchCourseToComboBox(ComboBox<String> comboBox) {
        String sql = "SELECT CourseAbb FROM course";

        try {
            prepare = connect.prepareStatement(sql);
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
        String sql = "SELECT SectionName FROM section";

        try {
            prepare = connect.prepareStatement(sql);
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

    private void populateComboBox(ComboBox<String> comboBox, String query) {
        try {
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {
                String item = result.getString(1); // Assuming the data is in the first column
                comboBox.getItems().add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error populating ComboBox: " + e.getMessage());
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

    private void handleTableClick() {
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
    private void handleCreateCourse(ActionEvent event) {
        String sql = "Insert into course (CourseAbb, CourseName)" + "Values (? , ?)";

        try {
            connect = database.getConnection();
            prepare = connect.prepareStatement(sql);

            // Set parameters for the prepared statement
            prepare.setString(1, txtCourseAbb.getText());
            prepare.setString(2, txtCourseName.getText());

            // Execute the SQL query
            prepare.executeUpdate();

            showSuccessAlert("Course created successfully!");

            txtCourseAbb.clear();
            txtCourseName.clear();
            clearAndLoadCourseData();
            // Optionally, you can show a success message or perform other actions here
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (show error message, log, etc.)
        }
    }

    @FXML
    private void handleDeleteCourse(ActionEvent event) {
        // Get the selected item from the table
        getCourseData selectedCourse = tblCourseData.getSelectionModel().getSelectedItem();

        if (selectedCourse != null) {
            try {
                connect = database.getConnection();
                prepare = connect.prepareStatement("DELETE FROM course WHERE CourseAbb = ?");
                prepare.setString(1, selectedCourse.getCourseAbb());

                // Execute the SQL query for deletion
                prepare.executeUpdate();

                showSuccessAlert("Course deleted successfully!");

                // Refresh the table after deletion
                loadCourseData();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            }
        } else {
            // If no item is selected, show a warning or error message
            showWarningAlert("Please select a course to delete.");
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
            prepare = connect.prepareStatement("SELECT CourseAbb,CourseName FROM course");
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
        tblOfficerData.getItems().clear();

        // Reload the data
        loadCourseData();
    }
}
