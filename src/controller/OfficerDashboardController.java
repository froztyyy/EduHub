/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author lugtu
 */
public class OfficerDashboardController implements Initializable {

    @FXML
    private HBox sidePanelTitleColor;

    @FXML
    private Pane userDashboradWindow;
    private ImageView gradientCursor;
    private boolean isSidePanelOpen = false;

    private double x = 0;
    private double y = 0;
    @FXML
    private Pane blurringEffect;
    @FXML
    private AnchorPane imageGradientWelcome;
    @FXML
    private Pane homeWindow;
    @FXML
    private Label fontsizeGrow;
    @FXML
    private Pane announcementWindow;
    @FXML
    private Pane calendarWindow;
    @FXML
    private Pane homeButton;
    @FXML
    private Pane announcementButton;
    @FXML
    private Pane calendarButton;
    @FXML
    private Pane sidePanel;
    ZonedDateTime dateFocus;
    ZonedDateTime today;
    @FXML
    private Label lblTimeDashboard;
    @FXML
    private Label lblDateDashboard;
    @FXML
    private FlowPane calendarBig;
    @FXML
    private FlowPane calendarSmall;
    @FXML
    private Label yearSmall;
    @FXML
    private Label monthSmall;
    @FXML
    private Label monthBig;
    @FXML
    private Label yearBig;
    @FXML
    private Label monthNote;
    @FXML
    private Label yearNote;
    @FXML
    private Text infoNote;
    @FXML
    private Pane studentManagementWIndow;
    @FXML
    private Pane studentManagementButton;
    @FXML
    private TextField txtStudentID;
    @FXML
    private TextField txtPassword;
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
    private Button btnClear;
    @FXML
    private TableColumn<StudentAccountDataList, String> tblStudentID;
    @FXML
    private TableColumn<StudentAccountDataList, String> tblPassword;
    @FXML
    private TableColumn<StudentAccountDataList, String> tblRoleID;
    @FXML
    private TableColumn<StudentAccountDataList, String> tblSurname;
    @FXML
    private TableColumn<StudentAccountDataList, String> tblFirstName;
    @FXML
    private TableColumn<StudentAccountDataList, String> tblMiddlename;
    @FXML
    private TableColumn<StudentAccountDataList, String> tblSuffix;
    @FXML
    private TableColumn<StudentAccountDataList, String> tblCourse;
    @FXML
    private TableColumn<StudentAccountDataList, String> tblYearSection;
    @FXML
    private TableView<StudentAccountDataList> tblStudentData;
    @FXML
    private TextField searchStudent;
    @FXML
    private TextField txtTitle;
    @FXML
    private ComboBox<String> cbAudience;
    @FXML
    private ComboBox<String> cbPriorityLevel;
    @FXML
    private TextArea txtArea;
    @FXML
    private Button btnSubmit;
    @FXML
    private GridPane announcementCard;
    @FXML
    private Pane toDoButton;
    @FXML
    private Pane toDoWindow;
    @FXML
    private ComboBox<String> cbAudienceToDo;
    @FXML
    private ComboBox<String> cbPriorityToDo;
    @FXML
    private TextArea txtBodyTask;
    @FXML
    private DatePicker datePickerTask;
    @FXML
    private Button btnCreateTask;
    @FXML
    private GridPane taskCard;
    @FXML
    private TextField txtTitleTask;
    @FXML
    private Pane listStudentAccountWindow;
    @FXML
    private Button trashStudentAccountButton;
    @FXML
    private Pane trashSrudentAccountWindow1;
    @FXML
    private Button ListStudentAccountButton;
    @FXML
    private TableView<GetArchiveStudentAccountData> archiveStudentAccTbl;
    @FXML
    private TableColumn<GetArchiveStudentAccountData, String> archiveStudID;
    @FXML
    private TableColumn<GetArchiveStudentAccountData, String> archivePassword;
    @FXML
    private TableColumn<GetArchiveStudentAccountData, String> archiveRoleID;
    @FXML
    private TableColumn<GetArchiveStudentAccountData, String> archiveSurname;
    @FXML
    private TableColumn<GetArchiveStudentAccountData, String> archiveFirstname;
    @FXML
    private TableColumn<GetArchiveStudentAccountData, String> archiveMiddleName;
    @FXML
    private TableColumn<GetArchiveStudentAccountData, String> archiveSuffix;
    @FXML
    private TableColumn<GetArchiveStudentAccountData, String> archiveCourseStudent;
    @FXML
    private TableColumn<GetArchiveStudentAccountData, String> archiveYearSectionStudent;
    @FXML
    private Pane sideCard;
    @FXML
    private Pane eduhubAccount1;
    @FXML
    private Text lblStudentID1;
    @FXML
    private Text lblPassword1;
    @FXML
    private Text lblSurname1;
    @FXML
    private Text lblFirstName1;
    @FXML
    private Text lblMiddleName1;
    @FXML
    private Text lblCourse1;
    @FXML
    private Text lblYearSection1;
    @FXML
    private Text lblRoleID1;
    @FXML
    private Text lblSuffix1;
    @FXML
    private Label numberItemsStudentTrash;
    @FXML
    private CheckBox selectAllStudentTable;

    /**
     * Initializes the controller class.
     */
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
        Background backgroundObject = new Background(background);
        imageGradientWelcome.setBackground(backgroundObject);

        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
        drawCalendarForBigCalendar();

        sidePanel.setVisible(true);
        homeWindow.setVisible(false);
        announcementWindow.setVisible(true);
        calendarWindow.setVisible(false);
        studentManagementWIndow.setVisible(false);
        toDoWindow.setVisible(false);

        timeNowForDashboard();
        dateLabelForDashboard();

        connect = database.getConnection();
        fetchCourseToComboBox(cbCourse);
        fetchSectionToComboBox(cbSectionYear);

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
        loadStudentAccountData();

        // Add event handler to the table
        tblStudentData.setOnMouseClicked(event -> {
            // Check if a row is clicked
            if (event.getClickCount() == 1) {
                handleTableClick();
            }
        });

        DisplayAnnouncement();

        fetchAudienceToComboBox(cbAudience);
        fetchPriorityLevelToComboBox(cbPriorityLevel);

        fetchAudienceToComboBoxToDo(cbAudienceToDo);
        fetchPriorityLevelToComboBoxToDo(cbPriorityToDo);
        homeDisplayListCard();

        listStudentAccountWindow.setVisible(true);
        trashSrudentAccountWindow1.setVisible(false);

        // Initialize the table columns
        archiveStudID.setCellValueFactory(new PropertyValueFactory<>("tblStudentID"));
        archivePassword.setCellValueFactory(new PropertyValueFactory<>("tblPassword"));
        archiveRoleID.setCellValueFactory(new PropertyValueFactory<>("tblRoleID"));
        archiveSurname.setCellValueFactory(new PropertyValueFactory<>("tblSurname"));
        archiveFirstname.setCellValueFactory(new PropertyValueFactory<>("tblFirstName"));
        archiveMiddleName.setCellValueFactory(new PropertyValueFactory<>("tblMiddlename"));
        archiveSuffix.setCellValueFactory(new PropertyValueFactory<>("tblSuffix"));
        archiveCourseStudent.setCellValueFactory(new PropertyValueFactory<>("tblCourse"));
        archiveYearSectionStudent.setCellValueFactory(new PropertyValueFactory<>("tblYearSection"));

        // Additional initialization logic if needed
        loadArchiveStudentData();

        sideCard.setTranslateX(489);
        sideCard.setVisible(true);

        tblStudentData.setOnMouseClicked(event -> {
            // Check if a row is clicked
            if (event.getClickCount() == 1) {
                handleTableClick();
            }
        });

        userDashboradWindow.setOnMouseClicked(event -> {
            Node clickedNode = event.getPickResult().getIntersectedNode();

            // Check if the clicked node is not the side panel
            if (!isNodeInsideSidePanel(clickedNode)) {
                // Close the side panel
                closeSideNavigation(new ActionEvent()); // Pass a dummy ActionEvent
            }
        });

        tblStudentData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private boolean isNodeInsideSidePanel(Node node) {
        // Check if the node or its parent is the side panel
        while (node != null) {
            if (node == sidePanel) {
                return true;
            }
            node = node.getParent();
        }
        return false;
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

    private Pane lastClickedButton = studentManagementButton;

    @FXML
    public void SwitchForm(MouseEvent event) {

        Pane clickedButton = (Pane) event.getSource();

        if (clickedButton == lastClickedButton) {
            // Ignore the click if the same button was clicked twice in a row
            return;
        }

        if (clickedButton == studentManagementButton) {
            // ... (rest of the code remains the same)
        }

        try {
            // Update the last clicked button
            lastClickedButton = clickedButton;
            if (clickedButton == announcementButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, true);
                setButtonColor(calendarButton, false);
                setButtonColor(studentManagementButton, false);
                setButtonColor(toDoButton, false);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(true);
                calendarWindow.setVisible(false);
                studentManagementWIndow.setVisible(false);
                toDoWindow.setVisible(false);

            } else if (clickedButton == calendarButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, false);
                setButtonColor(calendarButton, true);
                setButtonColor(studentManagementButton, false);
                setButtonColor(toDoButton, false);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(false);
                calendarWindow.setVisible(true);
                studentManagementWIndow.setVisible(false);
                toDoWindow.setVisible(false);

            } else if (clickedButton == studentManagementButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, false);
                setButtonColor(calendarButton, false);
                setButtonColor(studentManagementButton, true);
                setButtonColor(toDoButton, false);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(false);
                calendarWindow.setVisible(false);
                studentManagementWIndow.setVisible(true);
                toDoWindow.setVisible(false);

            } else if (clickedButton == toDoButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, false);
                setButtonColor(calendarButton, false);
                setButtonColor(studentManagementButton, false);
                setButtonColor(toDoButton, true);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(false);
                calendarWindow.setVisible(false);
                studentManagementWIndow.setVisible(false);
                toDoWindow.setVisible(true);
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

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendarBig.getChildren().clear();
        calendarSmall.getChildren().clear();
        drawCalendar();
        drawCalendarForBigCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendarBig.getChildren().clear();
        calendarSmall.getChildren().clear();
        drawCalendar();
        drawCalendarForBigCalendar();
    }

    private void drawCalendarForBigCalendar() {
        yearBig.setText(String.valueOf(dateFocus.getYear()));
        monthBig.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendarBig.getPrefWidth();
        double calendarHeight = calendarBig.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendarBig.getHgap();
        double spacingV = calendarBig.getVgap();

        // List of activities for a given month
        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        // Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone())
                .getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = -(rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        date.setFill(Color.WHITE); // Set the font color to white
                        stackPane.getChildren().add(date);

                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if (calendarActivities != null) {
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                            // If there are notes, fill the rectangle with red color
                            rectangle.setFill(Color.rgb(77, 79, 83, 0.5));

                            // Optionally, you can add other visual cues for having notes, such as a border or different text color.
                            date.setFill(Color.WHITE); // Set the text color to black for visibility, adjust as needed.
                        }

                        stackPane.setOnMouseClicked(mouseEvent -> {
                            //   Parent root = FXMLLoader.load(getClass().getResource("/view/calendarInfo.fxml"));
//
//                                // You can get the controller and pass any data if needed
//                                // YourControllerClass controller = loader.getController();
//                                // controller.setData(...); // Pass data if needed
//                                // Create a new stage
//                                Stage newStage = new Stage();
//                                newStage.setWidth(349);
//                                newStage.setHeight(348);
//
//                                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//                                double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
//                                double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
//                                newStage.setX(centerX - 174.5);
//                                newStage.setY(centerY - 174);
//                                Scene scene = new Scene(root, 349, 348);
//                                newStage.initStyle(StageStyle.TRANSPARENT);
//                                newStage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
//                                newStage.setScene(scene);
//
//                                root.setOnMousePressed((mousePressEvent) -> {
//                                    x = mousePressEvent.getSceneX();
//                                    y = mousePressEvent.getSceneY();
//                                });
//
//                                root.setOnMouseDragged((mouseDragEvent) -> {
//                                    newStage.setX(mouseDragEvent.getScreenX() - x);
//                                    newStage.setY(mouseDragEvent.getScreenY() - y);
//
//                                    newStage.setOpacity(.8);
//                                });
//
//                                root.setOnMouseReleased((mouseReleaseEvent) -> {
//                                    newStage.setOpacity(1);
//                                });
//
//                                // Set the new scene and title
//                                newStage.setTitle("New Window");
//
//                                // Show the new stage
//                                newStage.show();
                            int noteYear = 1898;
                            String noteMonth = "January";
                            String noteMessage = "Lorem ipsum dolor sit amet";
                            // Set values to the labels and text fields
                            yearNote.setText(String.valueOf(noteYear));
                            monthNote.setText(noteMonth);
                            infoNote.setText(noteMessage);
                        });
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth()
                            && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendarBig.getChildren().add(stackPane);
            }
        }
    }

    private void drawCalendar() {
        yearSmall.setText(String.valueOf(dateFocus.getYear()));
        monthSmall.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendarSmall.getPrefWidth();
        double calendarHeight = calendarSmall.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendarSmall.getHgap();
        double spacingV = calendarSmall.getVgap();

        // List of activities for a given month
        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        // Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone())
                .getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j + 1) + (7 * i);
                if (calculatedDate > dateOffset) {
                    int currentDate = calculatedDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = -(rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        date.setFill(Color.WHITE); // Set the font color to white
                        stackPane.getChildren().add(date);

                        List<CalendarActivity> calendarActivities = calendarActivityMap.get(currentDate);
                        if (calendarActivities != null) {
                            // If there are notes, fill the rectangle with red color
                            rectangle.setFill(Color.rgb(77, 79, 83, 0.5));

                            // Optionally, you can add other visual cues for having notes, such as a border or different text color.
                            date.setFill(Color.WHITE); // Set the text color to black for visibility, adjust as needed.
                        }

                        stackPane.setOnMouseClicked(mouseEvent -> {
                            int noteYear = 1898;
                            String noteMonth = "January";
                            String noteMessage = "Lorem ipsum dolor sit amet";

                            // Set values to the labels and text fields
                            yearNote.setText(String.valueOf(noteYear));
                            monthNote.setText(noteMonth);
                            infoNote.setText(noteMessage);
                        });
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth()
                            && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendarSmall.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight,
            double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if (k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    // On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }
            Text text = new Text(
                    calendarActivities.get(k).getClientName() + ", " + calendarActivities.get(k).getDate().toLocalTime());
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                // On Text clicked
                System.out.println(text.getText());
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:pink");
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity : calendarActivities) {
            int activityDate = activity.getDate().getDayOfMonth();
            if (!calendarActivityMap.containsKey(activityDate)) {
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<CalendarActivity> OldListByDate = calendarActivityMap.get(activityDate);

                List<CalendarActivity> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return calendarActivityMap;
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<CalendarActivity> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            ZonedDateTime time = ZonedDateTime.of(year, month, random.nextInt(27) + 1, 16, 0, 0, 0,
                    dateFocus.getZone());
            calendarActivities.add(new CalendarActivity(time, "Hans", 111111));
        }

        return createCalendarMap(calendarActivities);
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
    private void editCalendarNote(ActionEvent event) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/calendarInfoWindow.fxml"));
            Parent root = loader.load();

            // Get the controller and set the data
            CalendarInfoWindowController calendarInfoController = loader.getController();
            int noteYear = 1898;
            String noteMonth = "January";
            String noteMessage = "Lorem ipsum dolor sit amet";
            calendarInfoController.setData(noteYear, noteMonth, noteMessage);

            // Create a new stage
            Stage newStage = new Stage();
            newStage.setWidth(349);
            newStage.setHeight(348);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
            double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
            newStage.setX(centerX - 174.5);
            newStage.setY(centerY - 174);

            Scene scene = new Scene(root, 349, 348);
            newStage.initStyle(StageStyle.TRANSPARENT);
            newStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            newStage.setScene(scene);

            root.setOnMousePressed((mousePressEvent) -> {
                x = mousePressEvent.getSceneX();
                y = mousePressEvent.getSceneY();
            });

            root.setOnMouseDragged((mouseDragEvent) -> {
                newStage.setX(mouseDragEvent.getScreenX() - x);
                newStage.setY(mouseDragEvent.getScreenY() - y);

                newStage.setOpacity(.8);
            });

            root.setOnMouseReleased((mouseReleaseEvent) -> {
                newStage.setOpacity(1);
            });

            // Set the new scene and title
            newStage.setTitle("Edit Calendar Note");

            // Show the new stage
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it or show an error message)
        }
    }

    public void setData(int noteYear, String noteMonth, String noteMessage) {
        yearNote.setText(String.valueOf(noteYear));
        monthNote.setText(noteMonth);
        infoNote.setText(noteMessage);
    }

    private java.sql.Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private void fetchCourseToComboBox(ComboBox<String> comboBox) {
        String sql = "SELECT CourseAbb FROM filter_course";
        connect = database.getConnection();
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

        String sql = "SELECT SectionName FROM filter_section";
        connect = database.getConnection();
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

    @FXML
    private void handleCreateAccount(ActionEvent event) {

        if (txtStudentID.getText().isEmpty() || txtPassword.getText().isEmpty() || txtSurname.getText().isEmpty()
                || txtFirstname.getText().isEmpty() || cbCourse.getValue() == null || cbSectionYear.getValue() == null) {
            showErrorAlert("Please fill in all required fields");
            return;
        }

        // Check for datatype errors
        try {
            // Assuming StudentID is an integer
            Integer.parseInt(txtStudentID.getText());
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid StudentID. Please enter a valid integer.");
            return;
        }

        String sql = "INSERT INTO account_student (StudentID, Password, RoleID, Surname, Firstname, Middlename, Suffix, CourseID, SectionID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connect = database.getConnection();
            prepare = connect.prepareStatement(sql);

            // Set parameters for the prepared statement
            String studentID = txtStudentID.getText();
            prepare.setString(1, txtStudentID.getText());
            prepare.setString(2, txtPassword.getText());
            prepare.setInt(3, 3); // Assuming RoleID has a default value of 2
            prepare.setString(4, txtSurname.getText());
            prepare.setString(5, txtFirstname.getText());
            prepare.setString(6, txtMiddlename.getText());
            prepare.setString(7, txtSuffix.getText());
            prepare.setString(8, cbCourse.getValue()); // Assuming you're using the value from the ComboBox
            prepare.setString(9, cbSectionYear.getValue()); // Assuming you're using the value from the ComboBox

            // Execute the SQL query
            prepare.executeUpdate();

            // Add the additional column to the tables and insert the StudentID
            addStudentIDColumnAndInsertValue("mod_announce", studentID);
            addStudentIDColumnAndInsertValue("mod_announce_archive", studentID);
            addStudentIDColumnAndInsertValue("backup_announce", studentID);

            // Show success alert
            showSuccessAlert("Account created successfully!");

            // Load and refresh the TableView
            loadStudentAccountData();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("controller.AdminDashboardController.handleCreateAccount()");
        }
    }

    // Method to add the StudentID column to a table and insert the StudentID value
    private void addStudentIDColumnAndInsertValue(String tableName, String studentID) {
        String alterTableSql = "ALTER TABLE `" + tableName + "` ADD `" + studentID + "` BOOLEAN NOT NULL DEFAULT FALSE";

        try {
            // Execute the SQL query to add the column
            prepare = connect.prepareStatement(alterTableSql);
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add StudentID column and insert value to " + tableName);
        }
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String content) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }

    private ObservableList<StudentAccountDataList> studentAccountDataList;

    private void loadStudentAccountData() {
        studentAccountDataList = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            // Assume your database connection is already established
            prepare = connect.prepareStatement("SELECT StudentID, Password, RoleID, Surname, Firstname, Middlename, Suffix, CourseID, SectionID FROM account_student WHERE RoleID = 3");
            result = prepare.executeQuery(); // Execute the query and obtain the result set

            while (result.next()) {
                StudentAccountDataList studentAccountData = new StudentAccountDataList();
                studentAccountData.setTblStudentID(result.getString("StudentID"));
                studentAccountData.setTblPassword(result.getString("Password"));
                studentAccountData.setTblRoleID(result.getString("RoleID"));
                studentAccountData.setTblSurname(result.getString("Surname"));
                studentAccountData.setTblFirstName(result.getString("Firstname"));
                studentAccountData.setTblMiddlename(result.getString("Middlename"));
                studentAccountData.setTblSuffix(result.getString("Suffix"));
                studentAccountData.setTblCourse(result.getString("CourseID"));
                studentAccountData.setTblYearSection(result.getString("SectionID"));

                studentAccountDataList.add(studentAccountData);
            }
            tblStudentData.setItems(studentAccountDataList);

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
        StudentAccountDataList selectedAccount = tblStudentData.getSelectionModel().getSelectedItem();

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

            lblStudentID1.setText(selectedAccount.getTblStudentID());
            lblPassword1.setText(selectedAccount.getTblPassword());
            lblSurname1.setText(selectedAccount.getTblSurname());
            lblFirstName1.setText(selectedAccount.getTblFirstName());
            lblMiddleName1.setText(selectedAccount.getTblMiddlename());
            lblSuffix1.setText(selectedAccount.getTblSuffix());
            lblRoleID1.setText(selectedAccount.getTblRoleID());
            lblCourse1.setText(selectedAccount.getTblCourse());
            lblYearSection1.setText(selectedAccount.getTblYearSection());
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
    private void btnDelete(ActionEvent event) throws SQLException {
        ObservableList<StudentAccountDataList> selectedItems = tblStudentData.getSelectionModel().getSelectedItems();

        if (!selectedItems.isEmpty()) {
            // Check the state of the "Select All" checkbox
            if (selectAllStudentTable.isSelected()) {
                // "Select All" is active, delete all selected items

                // Show confirmation alert
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Delete Officer Accounts");
                alert.setContentText("Are you sure you want to delete all selected officer accounts?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // User clicked OK, proceed with deletion

                    // Remove from UI
                    tblStudentData.getItems().removeAll(selectedItems);

                    // Remove from the database and archive
                    for (StudentAccountDataList selectedOfficer : selectedItems) {
                        deleteStudentFromDatabase(selectedOfficer);
                        insertIntoArchiveStudentAccountTable(connect, selectedOfficer);
                    }

                    // Reload data after deletion
                    loadStudentAccountData();
                    loadArchiveStudentData();

                    // Inform the user about successful deletion
                    showAlert("Success", "User Accounts Deleted", "User accounts removed successfully.");

                    // Clear other UI elements
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
                // "Select All" is not active, delete only the first selected item

                StudentAccountDataList selectedOfficer = selectedItems.get(0);

                // Show confirmation alert
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Delete Officer Account");
                alert.setContentText("Are you sure you want to delete this officer account?");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // User clicked OK, proceed with deletion

                    // Remove from UI
                    tblStudentData.getItems().remove(selectedOfficer);

                    // Remove from the database and archive
                    deleteStudentFromDatabase(selectedOfficer);
                    insertIntoArchiveStudentAccountTable(connect, selectedOfficer);

                    // Reload data after deletion
                    loadStudentAccountData();
                    loadArchiveStudentData();

                    // Inform the user about successful deletion
                    showAlert("Success", "User Account Deleted", "User account removed successfully.");

                    // Clear other UI elements
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
            }
        } else {
            // Inform the user that no item is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No User Accounts Selected");
            alert.setContentText("Please select user accounts in the table.");
            alert.showAndWait();
        }
    }

    private void deleteStudentFromDatabase(StudentAccountDataList studentAccountDataList) {
        connect = database.getConnection();

        try {
            // Check if the "Select All" statement is active
            if (selectAllStudentTable.isSelected()) {
                // If "Select All" is active, delete all records without specifying the StudentID
                String deleteQuery = "DELETE FROM account_student WHERE RoleID = 3";
                prepare = connect.prepareStatement(deleteQuery);
            } else {
                // If "Select All" is not active, delete the specific record based on StudentID
                String deleteQuery = "DELETE FROM account_student WHERE StudentID = ?";
                prepare = connect.prepareStatement(deleteQuery);
                prepare.setString(1, studentAccountDataList.getTblStudentID());
            }

            int affectedRows = prepare.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("User account deleted successfully from the database.");
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
        StudentAccountDataList selectedOfficer = tblStudentData.getSelectionModel().getSelectedItem();

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
            showAlert("Success", "User Account Updated", "User account updated successfully.");

            // Clear and reload the data in the TableView
            clearAndLoadStudentAccountData();
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
            alert.setHeaderText("No User Account Selected");
            alert.setContentText("Please select an officer account in the table.");
            alert.showAndWait();
        }
    }

    private void updateOfficerInDatabase(StudentAccountDataList officer, String updatedPassword, String updatedSurname,
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

    private void clearAndLoadStudentAccountData() {
        // Clear the existing data
        tblStudentData.getItems().clear();

        // Reload the data
        loadStudentAccountData();
    }

    @FXML
    private void searchStudent(ActionEvent event) {
        // Assuming you have a TextField named searchStudent for user input
        String searchKeyword = searchStudent.getText().trim().toLowerCase();

        if (!searchKeyword.isEmpty()) {
            ObservableList<StudentAccountDataList> searchResultList = FXCollections.observableArrayList();

            for (StudentAccountDataList studentAccount : studentAccountDataList) {
                // Iterate through all columns and check if the search keyword matches any column value
                if (containsIgnoreCase(studentAccount.getTblStudentID(), searchKeyword)
                        || containsIgnoreCase(studentAccount.getTblPassword(), searchKeyword)
                        || containsIgnoreCase(studentAccount.getTblRoleID(), searchKeyword)
                        || containsIgnoreCase(studentAccount.getTblSurname(), searchKeyword)
                        || containsIgnoreCase(studentAccount.getTblFirstName(), searchKeyword)
                        || containsIgnoreCase(studentAccount.getTblMiddlename(), searchKeyword)
                        || containsIgnoreCase(studentAccount.getTblSuffix(), searchKeyword)
                        || containsIgnoreCase(studentAccount.getTblCourse(), searchKeyword)
                        || containsIgnoreCase(studentAccount.getTblYearSection(), searchKeyword)) {

                    searchResultList.add(studentAccount);
                }
            }

            // Update the TableView with the search results
            tblStudentData.setItems(searchResultList);
        } else {
            // If the search field is empty, display all students
            tblStudentData.setItems(studentAccountDataList);
        }

        // Add listener to detect when search text is cleared
        searchStudent.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                loadStudentAccountData(); // Call the loadStudentAccountData() method to show all data
            }
        });
    }

// Helper method to check if a string contains another string (case-insensitive)
    private boolean containsIgnoreCase(String source, String target) {
        return source.toLowerCase().contains(target);
    }

    private String user_StudentID;
    private String user_CourseID;
    private String user_SectionID;
    private String user_Surname;

    public void user_StudentID(String studentID) {
        this.user_StudentID = studentID;
        DisplayAnnouncement();
        homeDisplayListCard();
    }

    public void user_CourseID(String courseID) {
        this.user_CourseID = courseID;
        DisplayAnnouncement();
        homeDisplayListCard();
    }

    public void user_SectionID(String sectionID) {
        this.user_SectionID = sectionID;
        DisplayAnnouncement();
        homeDisplayListCard();
    }

    public void user_Surname(String surname) {
        this.user_Surname = surname;
        DisplayAnnouncement();
        homeDisplayListCard();
    }

    private void fetchAudienceToComboBox(ComboBox<String> comboBox) {

        try {
            prepare = connect.prepareStatement("SELECT AudienceName FROM filter_audience");
            result = prepare.executeQuery();

            List<String> items = new ArrayList<>();
            while (result.next()) {
                String itemName = result.getString("AudienceName");
                items.add(itemName);
            }

            comboBox.getItems().addAll(items);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching course data: " + e.getMessage());
        }
    }

    private void fetchPriorityLevelToComboBox(ComboBox<String> comboBox) {

        try {
            prepare = connect.prepareStatement("SELECT PriorityName FROM filter_priority");
            result = prepare.executeQuery();

            List<String> items = new ArrayList<>();
            while (result.next()) {
                String itemName = result.getString("PriorityName");
                items.add(itemName);
            }

            comboBox.getItems().addAll(items);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching course data: " + e.getMessage());
        }
    }

    @FXML
    private void handlePostAnnouncement(ActionEvent event) {

        if (txtTitle.getText().isEmpty() && txtArea.getText().isEmpty() && cbPriorityLevel.getValue() == null && cbAudience.getValue() == null) {
            // Show an alert indicating that Title, Note, and Priority are required
            showErrorAlert("Please enter Title, Note, and select Priority");
            return; // Stop further execution
        }

        // Check which fields are missing
        if (txtTitle.getText().isEmpty()) {
            showErrorAlert("Please enter Title");
            return;
        }

        if (cbAudience.getValue() == null) {
            showErrorAlert("Please select Audience");
            return;
        }

        if (cbPriorityLevel.getValue() == null) {
            showErrorAlert("Please select Priority");
            return;
        }

        if (txtArea.getText().isEmpty()) {
            showErrorAlert("Please enter Note");
            return;
        }

        String sql = "INSERT INTO mod_announce (Title, Body,AudienceID,PriorityID,StudentID,Surname, CourseID, SectionID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        connect = database.getConnection();

        try {

            prepare = connect.prepareStatement(sql);

            // Set parameters for the prepared statement
            prepare.setString(1, txtTitle.getText());
            prepare.setString(2, txtArea.getText());
            prepare.setString(3, cbAudience.getValue());
            prepare.setString(4, cbPriorityLevel.getValue());
            prepare.setString(5, user_StudentID);
            prepare.setString(6, user_Surname);
            prepare.setString(7, user_CourseID); // Assuming you're using the value from the ComboBox
            prepare.setString(8, user_SectionID); // Assuming you're using the value from the ComboBox

            // Execute the SQL query
            prepare.executeUpdate();

            // Show success alert
            showSuccessAlert("Announcement created successfully!");

            // Load and refresh the TableView
            DisplayAnnouncement();
            clearFieldPost();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Post announced");
        }
    }

    private void clearFieldPost() {
        txtTitle.clear();
        txtArea.clear();
        cbAudience.setValue(null);
        cbPriorityLevel.setValue(null);
    }

    private ObservableList<AnnouncementData> Announcement = FXCollections.observableArrayList();

    public ObservableList<AnnouncementData> getAnnouncementData() throws SQLException {

        String sql = "SELECT Title, Body, AudienceID, PriorityID, StudentID, Surname, CourseID, SectionID, postDate FROM mod_announce WHERE CourseID = ? AND SectionID = ? AND AudienceID IN (?, ?, ? ,?) ORDER BY AnnouncementID DESC, PostDate DESC";
        ObservableList<AnnouncementData> Announcement = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user_CourseID);
            prepare.setString(2, user_SectionID);
            prepare.setString(3, "Everyone");  // Replace with the actual value or variable
            prepare.setString(4, "Homeroom");  // Replace with the actual value or variable
            prepare.setString(5, "Officer");
            prepare.setString(6, "Only Me");
            result = prepare.executeQuery();

            while (result.next()) {
                String title = result.getString("Title");
                String body = result.getString("Body");
                String audience = result.getString("AudienceID");
                String priority = result.getString("PriorityID");
                String courseID = result.getString("CourseID");
                String sectionID = result.getString("SectionID");
                String surname = result.getString("Surname");
                String postDate = result.getString("postDate");
                String studentID = result.getString("StudentID");

                AnnouncementData announcementData = new AnnouncementData(title, audience, priority, courseID, sectionID, body, postDate, studentID, surname);

                Announcement.add(announcementData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (result, prepare, connect) if needed
        }

        return Announcement;
    }

    private int currentDisplayIndex = 0;

    public void DisplayAnnouncement() {
        try {
            Announcement.clear();
            Announcement.addAll(getAnnouncementData());

            int maxColumns = 1;
            int row = 0;
            int column = 0;

            announcementCard.getChildren().clear();
            announcementCard.getRowConstraints().clear();
            announcementCard.getColumnConstraints().clear();

            for (int q = 0; q < Announcement.size(); q++) {
                try {
                    if (column >= maxColumns) {
                        // Move to the next row when the maximum number of columns is reached
                        column = 0;
                        row++;
                    }

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/announcementCard.fxml"));
                    AnchorPane pane = loader.load();
                    AnnouncementCardController cardController = loader.getController();
                    cardController.setData(Announcement.get(q));

                    announcementCard.add(pane, column++, row);

                    GridPane.setMargin(pane, new Insets(5));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            currentDisplayIndex = 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchAudienceToComboBoxToDo(ComboBox<String> comboBox) {

        try {
            prepare = connect.prepareStatement("SELECT AudienceName FROM filter_audience");
            result = prepare.executeQuery();

            List<String> items = new ArrayList<>();
            while (result.next()) {
                String itemName = result.getString("AudienceName");
                items.add(itemName);
            }

            comboBox.getItems().addAll(items);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching course data: " + e.getMessage());
        }
    }

    private void fetchPriorityLevelToComboBoxToDo(ComboBox<String> comboBox) {

        try {
            prepare = connect.prepareStatement("SELECT PriorityName FROM filter_priority");
            result = prepare.executeQuery();

            List<String> items = new ArrayList<>();
            while (result.next()) {
                String itemName = result.getString("PriorityName");
                items.add(itemName);
            }

            comboBox.getItems().addAll(items);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching course data: " + e.getMessage());
        }
    }

    @FXML
    private void handleButtonCreateTask(ActionEvent event) throws IOException {
        connect = database.getConnection();

        String sql = "INSERT INTO mod_todo_pending (StudentID, Title, Note, Deadline, AudienceID, PriorityID, Surname, CourseID, SectionID) VALUES (?, ?, ?, ?, ? , ? ,? ,?, ?)";

        try {
            System.out.println("Current studentID: " + user_StudentID);

            // Set values from the user input
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user_StudentID);
            prepare.setString(2, txtTitleTask.getText());
            prepare.setString(3, txtBodyTask.getText());
            prepare.setDate(4, java.sql.Date.valueOf(datePickerTask.getValue())); // Convert LocalDate to java.sql.Date
            prepare.setString(5,cbAudienceToDo.getValue());
            prepare.setString(6, cbPriorityToDo.getValue());
            prepare.setString(7, user_Surname);
            prepare.setString(8,user_CourseID);
            prepare.setString(9, user_SectionID);

            // Debug: Print the prepared statement
            System.out.println("Prepared Statement: " + prepare);

            // Execute the SQL statement
            prepare.executeUpdate();

            // Show a success alert
            showSuccessAlert();
            homeDisplayListCard();

        } catch (SQLException e) {
            // Handle any SQL errors
            e.printStackTrace();
            showErrorAlert("Error", "Failed to insert values into the database.");
        } finally {
            // Close resources
            try {
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
    }
    
    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Values successfully inserted into the database.");
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
     private ObservableList<ToDoListData> toDoList = FXCollections.observableArrayList();

    public ObservableList<ToDoListData> getToDoListData() throws SQLException {

        String sql = "SELECT Title, Note, Deadline, AudienceID, PriorityID, StudentID, Surname, CourseID, SectionID, postDate FROM mod_todo_pending WHERE CourseID = ? AND SectionID = ? AND AudienceID IN (?, ?, ? ,?) ORDER BY PostDate DESC, PriorityID ASC";
        ObservableList<ToDoListData> toDoList = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user_CourseID);
            prepare.setString(2, user_SectionID);
            prepare.setString(3, "Everyone");  // Replace with the actual value or variable
            prepare.setString(4, "Homeroom");  // Replace with the actual value or variable
            prepare.setString(5, "Officers");
            prepare.setString(6, "Only Me");
            result = prepare.executeQuery();

            while (result.next()) {
                String title = result.getString("Title");
                String note = result.getString("Note");
                String deadline = result.getString("Deadline");
                String audience = result.getString("AudienceID");
                String priority = result.getString("PriorityID");
                String surname = result.getString("Surname");
                String courseID = result.getString("CourseID");
                String sectionID = result.getString("SectionID");
                String studentID = result.getString("StudentID");
                String postDate = result.getString("PostDate");

                ToDoListData todoListData = new ToDoListData(title, note, deadline, audience, priority, courseID, sectionID, surname,studentID, postDate);

                toDoList.add(todoListData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (result, prepare, connect) if needed
        }

        return toDoList;
    }

    public void homeDisplayListCard() {
        try {
            toDoList.clear();
            toDoList.addAll(getToDoListData());

            int maxColumns = 2;
            int row = 0;
            int column = 0;

            taskCard.getChildren().clear();
            taskCard.getRowConstraints().clear();
            taskCard.getColumnConstraints().clear();

            for (int q = 0; q < toDoList.size(); q++) {
                try {
                    if (column >= maxColumns) {
                        // Move to the next row when the maximum number of columns is reached
                        column = 0;
                        row++;
                    }

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/displayList.fxml"));
                    AnchorPane pane = loader.load();
                    controller.DisplayListController cardController = loader.getController();
                    cardController.setData(toDoList.get(q));

                    taskCard.add(pane, column++, row);

                    GridPane.setMargin(pane, new Insets(5));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            currentDisplayIndex = 0;
            if (!toDoList.isEmpty()) {
                ToDoListData firstToDo = toDoList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Button lastClickedButtonStudentAcc = ListStudentAccountButton;

    private void setButtonStudentAcc(Button button, boolean isSelected) {
        if (isSelected) {
            button.getStyleClass().add("selected-button");
        } else {
            button.getStyleClass().remove("selected-button");
        }
    }

    @FXML
    private void SwitchFormStudentAccount(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        System.out.println("changing");

        if (clickedButton == lastClickedButtonStudentAcc) {
            // Ignore the click if the same button was clicked twice in a row
            return;
        }

        try {
            // Update the last clicked button
            lastClickedButtonStudentAcc = clickedButton;

            if (clickedButton == ListStudentAccountButton) {
                setButtonStudentAcc(ListStudentAccountButton, true);
                setButtonStudentAcc(trashStudentAccountButton, false);

                listStudentAccountWindow.setVisible(true);
                trashSrudentAccountWindow1.setVisible(false);

            } else if (clickedButton == trashStudentAccountButton) {
                setButtonStudentAcc(ListStudentAccountButton, false);
                setButtonStudentAcc(trashStudentAccountButton, true);

                listStudentAccountWindow.setVisible(false);
                trashSrudentAccountWindow1.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////// 
    // STUDENT MANAGEMENT GOES TO ARCHIVE
    private ObservableList<GetArchiveStudentAccountData> archiveStudentData;

    private void loadArchiveStudentData() {
        archiveStudentData = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement("SELECT StudentID, Password, RoleID, Surname, FirstName, MiddleName, Suffix, CourseID, SectionID FROM trash_student");
            result = prepare.executeQuery();

            int rowCount = 0; // Counter for the number of rows

            while (result.next()) {
                GetArchiveStudentAccountData studentAccount = new GetArchiveStudentAccountData();
                studentAccount.setTblStudentID(result.getString("StudentID"));
                studentAccount.setTblPassword(result.getString("Password"));
                studentAccount.setTblRoleID(result.getString("RoleID"));
                studentAccount.setTblSurname(result.getString("Surname"));
                studentAccount.setTblFirstName(result.getString("FirstName"));
                studentAccount.setTblMiddlename(result.getString("MiddleName"));
                studentAccount.setTblSuffix(result.getString("Suffix"));
                studentAccount.setTblCourse(result.getString("CourseID"));
                studentAccount.setTblYearSection(result.getString("SectionID"));
                archiveStudentData.add(studentAccount);
                rowCount++;
            }

            archiveStudentAccTbl.setItems(archiveStudentData);

            // Set the label text to the number of rows concatenated with " Items"
            numberItemsStudentTrash.setText(rowCount + " Items");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    @FXML
    private void restoreStudentAccount(ActionEvent event) {
        GetArchiveStudentAccountData selectedStudent = archiveStudentAccTbl.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            try {
                connect = database.getConnection();
                insertIntoStudentAccountTable(connect, selectedStudent);
                deleteFromArchiveStudentAccountTable(connect, selectedStudent);

                showSuccessAlert("Student account retrieved successfully!");

                loadArchiveStudentData();
                // Additional method calls if needed
                loadStudentAccountData();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            } finally {
                closeResources();
            }
        } else {
            showWarningAlert("Please select a student account to retrieve.");
        }
    }

    @FXML
    private void deleteStudentAccountArchive(ActionEvent event) {
        GetArchiveStudentAccountData selectedStudent = archiveStudentAccTbl.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            try {
                connect = database.getConnection();
                insertIntoBackupStudentAccountDatabase(connect, selectedStudent);
                deleteFromArchiveStudentAccountTable(connect, selectedStudent);

                showSuccessAlert("Student account permanently deleted!");

                loadArchiveStudentData();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions appropriately (show error message, log, etc.)
            } finally {
                closeResources();
            }
        } else {
            showWarningAlert("Please select a student account to permanently delete.");
        }
    }

    private void insertIntoArchiveStudentAccountTable(java.sql.Connection conn, StudentAccountDataList studentAccount) throws SQLException {
        String insertQuery = "INSERT INTO trash_student (StudentID, Password, RoleID, Surname, FirstName, MiddleName, Suffix, CourseID, SectionID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setString(1, studentAccount.getTblStudentID());
            prepare.setString(2, studentAccount.getTblPassword());
            prepare.setString(3, studentAccount.getTblRoleID());
            prepare.setString(4, studentAccount.getTblSurname());
            prepare.setString(5, studentAccount.getTblFirstName());
            prepare.setString(6, studentAccount.getTblMiddlename());
            prepare.setString(7, studentAccount.getTblSuffix());
            prepare.setString(8, studentAccount.getTblCourse());
            prepare.setString(9, studentAccount.getTblYearSection());
            prepare.executeUpdate();
        }
    }

    private void insertIntoStudentAccountTable(java.sql.Connection conn, GetArchiveStudentAccountData studentAccount) throws SQLException {
        String insertQuery = "INSERT INTO account_student (StudentID, Password, RoleID, Surname, FirstName, MiddleName, Suffix, CourseID, SectionID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setString(1, studentAccount.getTblStudentID());
            prepare.setString(2, studentAccount.getTblPassword());
            prepare.setString(3, studentAccount.getTblRoleID());
            prepare.setString(4, studentAccount.getTblSurname());
            prepare.setString(5, studentAccount.getTblFirstName());
            prepare.setString(6, studentAccount.getTblMiddlename());
            prepare.setString(7, studentAccount.getTblSuffix());
            prepare.setString(8, studentAccount.getTblCourse());
            prepare.setString(9, studentAccount.getTblYearSection());
            prepare.executeUpdate();
        }

    }

    private void deleteFromArchiveStudentAccountTable(java.sql.Connection conn, GetArchiveStudentAccountData studentAccount) throws SQLException {
        String deleteQuery = "DELETE FROM trash_student WHERE StudentID = ?";
        try (PreparedStatement prepare = conn.prepareStatement(deleteQuery)) {
            prepare.setString(1, studentAccount.getTblStudentID());
            prepare.executeUpdate();
        }
    }

    private void insertIntoBackupStudentAccountDatabase(java.sql.Connection conn, GetArchiveStudentAccountData studentAccount) throws SQLException {
        String insertQuery = "INSERT INTO backup_student (StudentID, Password, RoleID, Surname, FirstName, MiddleName, Suffix, CourseID, SectionID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prepare = conn.prepareStatement(insertQuery)) {
            prepare.setString(1, studentAccount.getTblStudentID());
            prepare.setString(2, studentAccount.getTblPassword());
            prepare.setString(3, studentAccount.getTblRoleID());
            prepare.setString(4, studentAccount.getTblSurname());
            prepare.setString(5, studentAccount.getTblFirstName());
            prepare.setString(6, studentAccount.getTblMiddlename());
            prepare.setString(7, studentAccount.getTblSuffix());
            prepare.setString(8, studentAccount.getTblCourse());
            prepare.setString(9, studentAccount.getTblYearSection());
            prepare.executeUpdate();
        }
    }

    private void showWarningAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

    @FXML
    private void PrintMasterList(ActionEvent event) {
        List<StudentAccountDataList> data = tblStudentData.getItems();
        showSaveDialogAndGenerate(data);
    }

    private void showSaveDialogAndGenerate(List<StudentAccountDataList> data) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        // Show save file dialog
        Stage stage = (Stage) userDashboradWindow.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            if (file.getName().toLowerCase().endsWith(".csv")) {
                generateCSVFromTableView(data, file);
            } else if (file.getName().toLowerCase().endsWith(".pdf")) {
                generatePDFFromTableView(data, file);

            } else {
                showAlert("Unsupported file format");
            }
        }
    }

    public static void generateCSVFromTableView(List<StudentAccountDataList> data, File file) {
        try (FileWriter csvWriter = new FileWriter(file)) {
            // Write header
            csvWriter.append("StudentID,Password,RoleID,Surname,FirstName,MiddleName,Suffix,Course,YearSection\n");

            // Write data
            for (StudentAccountDataList item : data) {
                csvWriter.append(item.getTblStudentID()).append(",");
                csvWriter.append(item.getTblPassword()).append(",");
                csvWriter.append(item.getTblRoleID()).append(",");
                csvWriter.append(item.getTblSurname()).append(",");
                csvWriter.append(item.getTblFirstName()).append(",");
                csvWriter.append(item.getTblMiddlename()).append(",");
                csvWriter.append(item.getTblSuffix()).append(",");
                csvWriter.append(item.getTblCourse()).append(",");
                csvWriter.append(item.getTblYearSection()).append("\n");
            }

            System.out.println("CSV file generated successfully!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("CSV file generated successfully!");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generatePDFFromTableView(List<StudentAccountDataList> data, File file) {
        // Code to generate PDF using a library like Apache PDFBox
        // Example: Use Apache PDFBox to create a table in PDF
        // (Add Apache PDFBox library to your project)
        // ...

        showAlert("PDF file generated successfully!");
    }

    private void generatePNGFromTableView(List<OfficerAccountData> data, File file) {
        WritableImage image = tblStudentData.snapshot(new SnapshotParameters(), null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            showAlert("PNG file generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error generating PNG file");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void PrintDataOfficer(ActionEvent event) {
        // Capture the content of the specified pane
        WritableImage writableImage = new WritableImage((int) eduhubAccount1.getWidth(), (int) eduhubAccount1.getHeight());
        SnapshotParameters snapshotParameters = new SnapshotParameters();
        eduhubAccount1.snapshot(snapshotParameters, writableImage);

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

    @FXML
    private void showSideCardDetailsOfficer(ActionEvent event) {
        applyBlurEffect(true);

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sideCard);
        slider1.setToX(0);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();
    }

    @FXML
    private void closeSideCardOfficer(ActionEvent event) {
        // Apply blur effect during closing animation
        applyBlurEffect(false);

        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sideCard);
        slider1.setToX(489);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();
    }

    @FXML
    private void refreshStudentTable(ActionEvent event) {
        loadStudentAccountData();
    }

    @FXML
    private void refreshStudentTableArchive(ActionEvent event) {
        loadArchiveStudentData();
    }

    private void selectAllRows(boolean isSelected) {
        tblStudentData.getItems().forEach(item -> {
            tblStudentData.getSelectionModel().select(item);
        });
    }

    @FXML
    private void selectAllStudentTable(ActionEvent event) {
        if (selectAllStudentTable.isSelected()) {
            // Select all rows in the table
            tblStudentData.getSelectionModel().selectAll();
        } else {
            // Deselect all rows in the table
            tblStudentData.getSelectionModel().clearSelection();
        }
    }

}
