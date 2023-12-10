/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author lugtu
 */
public class UserDashboardController implements Initializable {

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
    private Pane homeWindow;
    @FXML
    private Pane announcementWindow;
    @FXML
    private Pane calendarWindow;
    @FXML
    private Pane todoWindow;
    @FXML
    private Pane timeClockWindow;
    @FXML
    private Pane homeButton;
    @FXML
    private Pane announcementButton;
    @FXML
    private Pane calendarButton;
    @FXML
    private Pane toDolistButton;
    @FXML
    private Pane timeClockButton;
    @FXML
    private Pane sidePanel;
    ZonedDateTime dateFocus;
    ZonedDateTime today;
    @FXML
    private AnchorPane clockPane;
    private Label lblTime;
    @FXML
    private Label lblTimerTime;
    @FXML
    private Label lblTimer;
    private Pane bottomNavigation;
    @FXML
    private TextField txtHour;
    @FXML
    private TextField txtMinute;
    @FXML
    private TextField txtSecond;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnStop;
    @FXML
    private Button btnTimerPause;
    @FXML
    private Button btnTimerStart;
    @FXML
    private Button btnTimerStop;
    @FXML
    private Label lblTimeDashboard;
    @FXML
    private Label lblDateDashboard;
    @FXML
    private Label dayAndYear;
    @FXML
    private Label monthToday;
    @FXML
    private Label time;
    @FXML
    private Label location;
    @FXML
    private TextField txtHourForAlarm;
    @FXML
    private TextField txtMinuteForAlarm;
    @FXML
    private TextField txtSecondAlarm;
    @FXML
    private Button setAlarm;
    @FXML
    private Button removeAlarm;
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
    private Label lblStudentID;
    @FXML
    private Label greetingLabel;
    @FXML
    private Button PostButton;
    @FXML
    private Label greetingLabelTime;
    @FXML
    private Label lblCourse;
    @FXML
    private Label lblSection;
    @FXML
    private GridPane announcementCardDash;
    @FXML
    private ComboBox<String> cbPriority;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextArea txtBody;
    @FXML
    private Button refreshannounce;
    @FXML
    private TextField txtTitleTask;
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
    private TableView<todolistTaskHub> tblTaskhub;
    @FXML
    private TableColumn<todolistTaskHub, String> tblTaskCol;
    @FXML
    private TableColumn<todolistTaskHub, String> tblDeadlineCol;
    @FXML
    private TableView<OfficerAccountData> tblClassMates;
    @FXML
    private TableColumn<OfficerAccountData, String> tblLastnameCol;
    @FXML
    private TableColumn<OfficerAccountData, String> tblFirstName;
    @FXML
    private TableColumn<OfficerAccountData, String> tblMiddleNameCol;
    @FXML
    private Button btnCreateTask1;
    @FXML
    private Label txtIDtod;
    @FXML
    private Button refreshannounce1;
    @FXML
    private TableColumn<todolistTaskHub, String> tblPriority;
    @FXML
    private Label lblCompletedTask;

    @FXML
    private Label notifNumber;

    @FXML
    private Label announceCount;

    /////////////////////////////////
    private Timestamp latestUpdateTime;

    @FXML
    private ImageView notifIV;

    @FXML
    private Button notifBTN;
    @FXML
    private ImageView notifBox;

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
        /*imageGradientWelcome.setBackground(backgroundObject);*/

        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
        drawCalendarForBigCalendar();

        sidePanel.setVisible(true);
        homeWindow.setVisible(true);
        announcementWindow.setVisible(false);
        calendarWindow.setVisible(false);
        todoWindow.setVisible(false);
        timeClockWindow.setVisible(false);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::updateTimer));
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeNowForDashboard();
        dateLabelForDashboard();
        TimeAndDateLocation();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            TimeAndDateLocation();
            updateGreeting();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        todoCard();

        fetchPriorityNameToComboBox(cbPriorityToDo);

        DisplayAnnouncementDash();

        loadCourseData();

        // Initialize columns
        tblTaskCol.setCellValueFactory(new PropertyValueFactory<>("Title"));

        tblPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));

        tblDeadlineCol.setCellValueFactory(new PropertyValueFactory<>("Deadline"));

        tblLastnameCol.setCellValueFactory(new PropertyValueFactory<>("tblSurname"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("tblFirstName"));
        tblMiddleNameCol.setCellValueFactory(new PropertyValueFactory<>("tblMiddlename"));
        loadOfficerAccountData();

        numberCompletedTask();
        checkAndUpdateNotificationVisibility();
        notifBTN.setVisible(true);
        notifBox.setVisible(false);
        notifNumber.setVisible(false);
        announceCount.setVisible(false);
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

    public void applyBlurEffectMainWindow(boolean apply) {
        BoxBlur boxBlur = new BoxBlur(5, 5, 3); // You can adjust these values based on your preference

        if (apply) {
            // Apply blur effect
            userDashboradWindow.setEffect(boxBlur);
        } else {
            // Remove blur effect
            userDashboradWindow.setEffect(null);
        }
    }

    private void closeDefaultNavi() {
        TranslateTransition slider1 = new TranslateTransition();
        slider1.setNode(sidePanel);
        slider1.setToX(-370);
        slider1.setDuration(Duration.seconds(.5));
        slider1.play();
    }

    private Pane lastClickedButton = homeButton;

    @FXML
    public void SwitchForm(MouseEvent event) {

        Pane clickedButton = (Pane) event.getSource();

        if (clickedButton == lastClickedButton) {
            // Ignore the click if the same button was clicked twice in a row
            return;
        }

        if (clickedButton == homeButton) {
            // ... (rest of the code remains the same)
        }

        try {
            // Update the last clicked button
            lastClickedButton = clickedButton;
            if (clickedButton == homeButton) {
                setButtonColor(homeButton, true);
                setButtonColor(announcementButton, false);
                setButtonColor(calendarButton, false);
                setButtonColor(toDolistButton, false);
                setButtonColor(timeClockButton, false);

                homeWindow.setVisible(true);
                announcementWindow.setVisible(false);
                calendarWindow.setVisible(false);
                todoWindow.setVisible(false);
                timeClockWindow.setVisible(false);

            } else if (clickedButton == announcementButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, true);
                setButtonColor(calendarButton, false);
                setButtonColor(toDolistButton, false);
                setButtonColor(timeClockButton, false);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(true);
                calendarWindow.setVisible(false);
                todoWindow.setVisible(false);
                timeClockWindow.setVisible(false);

            } else if (clickedButton == calendarButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, false);
                setButtonColor(calendarButton, true);
                setButtonColor(toDolistButton, false);
                setButtonColor(timeClockButton, false);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(false);
                calendarWindow.setVisible(true);
                todoWindow.setVisible(false);
                timeClockWindow.setVisible(false);

            } else if (clickedButton == toDolistButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, false);
                setButtonColor(calendarButton, false);
                setButtonColor(toDolistButton, true);
                setButtonColor(timeClockButton, false);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(false);
                calendarWindow.setVisible(false);
                todoWindow.setVisible(true);
                timeClockWindow.setVisible(false);

            } else if (clickedButton == timeClockButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, false);
                setButtonColor(calendarButton, false);
                setButtonColor(toDolistButton, false);
                setButtonColor(timeClockButton, true);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(false);
                calendarWindow.setVisible(false);
                todoWindow.setVisible(false);
                timeClockWindow.setVisible(true);
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

    public void TimeAndDateLocation() {
        // Set the date format for dd/yy
        SimpleDateFormat dayAndYearFormat = new SimpleDateFormat("dd/yy");

        // Set the date format for the full month name
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);

        // Set the date format for the time
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Set the values to the labels
        dayAndYear.setText(dayAndYearFormat.format(new Date()));
        monthToday.setText(now.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        time.setText(now.format(timeFormatter));

        // Set the location (replace with actual location retrieval logic)
        location.setText(getLocation());// Replace this with actual location retrieval logic

    }

    // Dummy method for location retrieval (replace with actual logic)
    private String getLocation() {
        Locale defaultLocale = Locale.getDefault();
        String city = defaultLocale.getDisplayCountry();
        String country = defaultLocale.getDisplayCountry();

        // Return the formatted location string
        return city + ", " + country;
    }

    @FXML
    public void handleStartTimer(ActionEvent event) {
        // Check if the timer thread is already running
        if (timerThread == null || !timerThread.isAlive()) {
            try {
                hour = txtHour.getText().isEmpty() ? 0 : Integer.parseInt(txtHour.getText());
                minute = txtMinute.getText().isEmpty() ? 0 : Integer.parseInt(txtMinute.getText());
                second = txtSecond.getText().isEmpty() ? 0 : Integer.parseInt(txtSecond.getText());

                // Create a new thread for the timer
                timerThread = new Thread(() -> {
                    while (!Thread.interrupted()) {
                        try {
                            Thread.sleep(1000);
                            second--;
                            if (second < 0) {
                                second = 59;
                                minute--;
                                if (minute < 0) {
                                    minute = 59;
                                    hour--;
                                    if (hour < 0) {
                                        // Timer has expired
                                        break;
                                    }
                                }
                            }

                            Platform.runLater(() -> {
                                lblTimerTime.setText(String.format("%02d : %02d : %02d", hour, minute, second));
                            });
                        } catch (InterruptedException e) {
                            // Thread interrupted, stop the timer
                            break;
                        }
                    }

                    // Timer has expired, play an alarm sound
                    System.out.println("Beep!");
                });

                // Start the timer thread
                timerThread.start();
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                e.printStackTrace(); // You might want to log the error or display a message to the user
            }
        }
    }

    @FXML
    public void handlePauseTimer(ActionEvent event) {
        // Interrupt the timer thread to pause it
        if (timerThread != null) {
            timerThread.interrupt();
        }
    }

    @FXML
    public void handleStopTimer(ActionEvent event) {
        // Interrupt the timer thread to stop it
        if (timerThread != null) {
            timerThread.interrupt();
        }

        // Reset the time to 0
        hour = 0;
        minute = 0;
        second = 0;

        // Update the UI
        Platform.runLater(() -> {
            lblTimerTime.setText(String.format("%02d : %02d : %02d", hour, minute, second));

            txtHour.setText("");
            txtMinute.setText("");
            txtSecond.setText("");

        });
    }

    @FXML
    private void handlePlay(ActionEvent event) {
        if (!running) {
            timeline.play();
            running = true;
        }
    }

    @FXML
    private void handlePause(ActionEvent event) {
        if (running) {
            timeline.pause();
            running = false;
        }
    }

    @FXML
    private void handleStop(ActionEvent event) {
        timeline.stop();
        running = false;
        seconds = 0;
        updateTimer(null);
    }

    private void updateTimer(ActionEvent event) {
        seconds++;
        lblTimer.setText(formatTime(seconds));
    }

    private String formatTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int remainingSeconds = totalSeconds % 60;

        return String.format("%02d : %02d : %02d", hours, minutes, remainingSeconds);
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

    @FXML
    private void sendfeedback(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/feedBackWindow.fxml"));
            Parent root = loader.load();

            Stage feedbackStage = new Stage();
            feedbackStage.setWidth(506);
            feedbackStage.setHeight(390);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
            double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
            feedbackStage.setX(centerX - 253);
            feedbackStage.setY(centerY - 195);

            Scene scene = new Scene(root, 506, 390);
            feedbackStage.initStyle(StageStyle.TRANSPARENT);

            // Set the modality to APPLICATION_MODAL to make it modal
            feedbackStage.initModality(Modality.APPLICATION_MODAL);

            // Get the Window from the Scene associated with the Pane
            Window ownerWindow = userDashboradWindow.getScene().getWindow();
            feedbackStage.initOwner(ownerWindow);

            applyBlurEffectMainWindow(true);
            feedbackStage.setScene(scene);

            // Add a listener to handle the close event of the feedbackStage
            feedbackStage.setOnHidden((WindowEvent e) -> {
                applyBlurEffectMainWindow(false);
            });

            feedbackStage.show();

            // Drag and move logic (if needed)
            root.setOnMousePressed((mouseEvent) -> {
                x = mouseEvent.getSceneX();
                y = mouseEvent.getSceneY();
            });

            root.setOnMouseDragged((mouseEvent) -> {
                feedbackStage.setX(mouseEvent.getScreenX() - x);
                feedbackStage.setY(mouseEvent.getScreenY() - y);

                feedbackStage.setOpacity(.8);
            });

            root.setOnMouseReleased((mouseEvent) -> {
                feedbackStage.setOpacity(1);
            });

            // Controller for the new window (if needed)
            // YourControllerClass controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setButtonColorForToDoList(Button button, boolean isSelected) {
        if (isSelected) {
            button.getStyleClass().add("selected-buttonForToDoList");
        } else {
            button.getStyleClass().remove("selected-buttonForToDoList");
        }
    }

    private Button lastClickedButtonForToDoList = null;

    private void handleButtonAddList(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addListWindow.fxml"));
            Parent root = loader.load();

            Stage feedbackStage = new Stage();
            feedbackStage.setWidth(431);
            feedbackStage.setHeight(444);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
            double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
            feedbackStage.setX(centerX - 215.5);
            feedbackStage.setY(centerY - 222);

            Scene scene = new Scene(root, 431, 444);
            feedbackStage.initStyle(StageStyle.TRANSPARENT);

            // Set the modality to APPLICATION_MODAL to make it modal
            feedbackStage.initModality(Modality.APPLICATION_MODAL);

            // Get the Window from the Scene associated with the Pane
            Window ownerWindow = userDashboradWindow.getScene().getWindow();
            feedbackStage.initOwner(ownerWindow);

            applyBlurEffectMainWindow(true);
            feedbackStage.setScene(scene);

            // Add a listener to handle the close event of the feedbackStage
            feedbackStage.setOnHidden((WindowEvent e) -> {
                applyBlurEffectMainWindow(false);
            });

            feedbackStage.show();

            // Drag and move logic (if needed)
            root.setOnMousePressed((mouseEvent) -> {
                x = mouseEvent.getSceneX();
                y = mouseEvent.getSceneY();
            });

            root.setOnMouseDragged((mouseEvent) -> {
                feedbackStage.setX(mouseEvent.getScreenX() - x);
                feedbackStage.setY(mouseEvent.getScreenY() - y);

                feedbackStage.setOpacity(.8);
            });

            root.setOnMouseReleased((mouseEvent) -> {
                feedbackStage.setOpacity(1);
            });

            todoCard();

            // Controller for the new window (if needed)
            // YourControllerClass controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private int currentDisplayIndex = 0;

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
            prepare.setString(5, "Only Me");
            prepare.setString(6, cbPriorityToDo.getValue());
            prepare.setString(7, user_Surname);
            prepare.setString(8, user_CourseID);
            prepare.setString(9, user_SectionID);

            // Debug: Print the prepared statement
            System.out.println("Prepared Statement: " + prepare);

            // Execute the SQL statement
            prepare.executeUpdate();

            // Show a success alert
            showSuccessAlert();
            todoCard();
            loadCourseData();

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

        String sql = "SELECT ToDoID, Title, Note, Deadline, AudienceID, PriorityID, StudentID, Surname, CourseID, SectionID, postDate FROM mod_todo_pending WHERE CourseID = ? AND SectionID = ? AND AudienceID IN (?, ?, ?) ORDER BY PostDate DESC, PriorityID ASC";
        ObservableList<ToDoListData> toDoList = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user_CourseID);
            prepare.setString(2, user_SectionID);
            prepare.setString(3, "Everyone");  // Replace with the actual value or variable
            prepare.setString(4, "Homeroom");  // Replace with the actual value or variable
            prepare.setString(5, "Only Me");
            result = prepare.executeQuery();

            while (result.next()) {
                int todoId = result.getInt("ToDoID");
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

                ToDoListData todoListData = new ToDoListData(todoId, title, note, deadline, audience, priority, courseID, sectionID, surname, studentID, postDate);

                toDoList.add(todoListData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (result, prepare, connect) if needed
        }

        return toDoList;
    }

    public void todoCard() {
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

                    // Get the controller and set the studentID
                    DisplayListController cardController = loader.getController();
                    cardController.setData(toDoList.get(q));
                    cardController.setStudentID(user_StudentID);
                    cardController.setUserDashboardController(this);
                    if (user_StudentID != null && !user_StudentID.equals(toDoList.get(q).getUser_StudentID())) {
                        cardController.disableRemoveButton();
                        cardController.disableRemoveButton1();
                    }

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

    private String user_StudentID;
    private String user_Password;
    private String user_CourseID;
    private String user_SectionID;
    private String user_Surname;

    public void user_StudentID(String studentID) {
        this.user_StudentID = studentID;
        updateStudentId();
        DisplayAnnouncementDash();
        todoCard();
        loadCourseData();
        loadOfficerAccountData();
        numberCompletedTask();
    }

    public void user_Password(String password) {
        this.user_Password = password;

    }

    public void user_CourseID(String courseID) {
        this.user_CourseID = courseID;
        updateCourseId();
        DisplayAnnouncementDash();
        todoCard();
        loadOfficerAccountData();
        loadCourseData();
        checkAndUpdateNotificationVisibility();
    }

    public void user_SectionID(String sectionID) {
        this.user_SectionID = sectionID;
        updateSectionId();
        DisplayAnnouncementDash();
        todoCard();
        loadOfficerAccountData();
        loadCourseData();
        checkAndUpdateNotificationVisibility();
    }

    public void user_Surname(String surname) {
        this.user_Surname = surname;
        updateGreeting();
        updateGreetingForTime();
        DisplayAnnouncementDash();
        todoCard();
        loadOfficerAccountData();
    }

    private String getGreeting() {
        LocalTime currentTime = LocalTime.now();

        if (currentTime.isBefore(LocalTime.NOON)) {
            return "Good Morning";
        } else if (currentTime.isBefore(LocalTime.of(17, 0))) {
            return "Good Afternoon";
        } else {
            return "Good Evening";
        }
    }

    public void updateGreeting() {
        String greeting = getGreeting();
        greetingLabel.setText(greeting + ", " + user_Surname + "!");
    }

    public void updateGreetingForTime() {
        String greeting = getGreeting();
        greetingLabelTime.setText(greeting + ", " + user_Surname + "!");
    }

    public void updateStudentId() {
        lblStudentID.setText(user_StudentID);
    }

    public void updateCourseId() {
        lblCourse.setText(user_CourseID);
    }

    public void updateSectionId() {
        lblSection.setText(user_SectionID);
    }

    private void fetchPriorityNameToComboBox(ComboBox<String> comboBox) {

        connect = database.getConnection();

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

        // Check if Title and Note are not empty, and PriorityID is selected
        if (txtTitle.getText().isEmpty() && txtBody.getText().isEmpty() && cbPriority.getValue() == null) {
            // Show an alert indicating that Title, Note, and Priority are required
            showErrorAlert("Please enter Title, Note, and select Priority");
            return; // Stop further execution
        }

        // Check which fields are missing
        if (txtTitle.getText().isEmpty()) {
            showErrorAlert("Please enter Title");
            return;
        }

        if (txtBody.getText().isEmpty()) {
            showErrorAlert("Please enter Note");
            return;
        }

        if (cbPriority.getValue() == null) {
            showErrorAlert("Please select Priority");
            return;
        }

        String sql = "INSERT INTO mod_announce (Title, Body,AudienceID,PriorityID,StudentID,Surname, CourseID, SectionID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        connect = database.getConnection();

        try {

            prepare = connect.prepareStatement(sql);

            // Set parameters for the prepared statement
            prepare.setString(1, txtTitle.getText());
            prepare.setString(2, txtBody.getText());
            prepare.setString(3, "Homeroom");
            prepare.setString(4, cbPriority.getValue());
            prepare.setString(5, user_StudentID);
            prepare.setString(6, user_Surname);
            prepare.setString(7, user_CourseID); // Assuming you're using the value from the ComboBox
            prepare.setString(8, user_SectionID); // Assuming you're using the value from the ComboBox

            // Execute the SQL query
            prepare.executeUpdate();

            // Show success alert
            showSuccessAlert("Announcement created successfully!");

            // Load and refresh the TableView
            DisplayAnnouncementDash();
            clearFieldPost();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Post announced");
        }
    }

    @FXML
    private void RefreshAnnouncement(ActionEvent event) {
        try {
            // Call the method to refresh announcements
            refreshAnnouncements();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to refresh announcements
    private void refreshAnnouncements() throws SQLException {
        // Clear existing announcements
        Announcement.clear();
        // Fetch new announcements
        Announcement.addAll(getAnnouncementData());
        // Redisplay announcements
        DisplayAnnouncementDash();
        loadCourseData();
        loadOfficerAccountData();
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

    private void clearFieldPost() {
        txtTitle.clear();
        txtBody.clear();
        cbPriority.setValue(null);
    }

    private ObservableList<AnnouncementData> Announcement = FXCollections.observableArrayList();

    public ObservableList<AnnouncementData> getAnnouncementData() throws SQLException {

        String sql = "Select AnnouncementID,Title, Body,AudienceID,PriorityID,StudentID,Surname, CourseID, SectionID, postDate FROM mod_announce where CourseID = ? and SectionID = ? and AudienceID in (?,?,?) ORDER BY PostDate DESC";
        ObservableList<AnnouncementData> Announcement = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user_CourseID);
            prepare.setString(2, user_SectionID);
            prepare.setString(3, "Everyone");
            prepare.setString(4, "Homeroom");
            prepare.setString(5, "Only Me");
            result = prepare.executeQuery();

            while (result.next()) {
                Integer announcementID = result.getInt("AnnouncementID");
                String title = result.getString("Title");
                String body = result.getString("Body");
                String audience = result.getString("AudienceID");
                String priority = result.getString("PriorityID");
                String courseID = result.getString("CourseID");
                String sectionID = result.getString("SectionID");
                String surname = result.getString("Surname");
                String postDate = result.getString("postDate");
                String studentID = result.getString("StudentID");

                AnnouncementData announcementData = new AnnouncementData(title, audience, priority, courseID, sectionID, body, postDate, studentID, surname, announcementID);
                announcementData.setAnnouncementID(announcementID); // Set AnnouncementID
                Announcement.add(announcementData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (result, prepare, connect) if needed
        }

        return Announcement;
    }

    public void DisplayAnnouncementDash() {
        try {
            Announcement.clear();
            Announcement.addAll(getAnnouncementData());

            int maxColumns = 1;
            int row = 0;
            int column = 0;

            announcementCardDash.getChildren().clear();
            announcementCardDash.getRowConstraints().clear();
            announcementCardDash.getColumnConstraints().clear();

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
                    cardController.setRemoveButtonVisible(false);
                    cardController.setEditButtonVisible(false);
                    cardController.setUpdateButtonVisible(false);

                    announcementCardDash.add(pane, column++, row);

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

    @FXML
    private void viewNowtodo(ActionEvent event) {

        setButtonColor(homeButton, false);
        setButtonColor(announcementButton, false);
        setButtonColor(calendarButton, false);
        setButtonColor(toDolistButton, true);
        setButtonColor(timeClockButton, false);

        homeWindow.setVisible(false);
        announcementWindow.setVisible(false);
        calendarWindow.setVisible(false);
        todoWindow.setVisible(true);
        timeClockWindow.setVisible(false);
    }

    private ObservableList<todolistTaskHub> todolistdatas;

    private void loadCourseData() {
        todolistdatas = FXCollections.observableArrayList();
        connect = database.getConnection();

        // Add logic to retrieve feedback data from the database
        // Replace the placeholders with your actual column names
        try {
            prepare = connect.prepareStatement("SELECT Title, Deadline, PriorityID FROM mod_todo_pending where CourseID = ? and SectionID = ? and AudienceID in ( ?, ?, ?) order by PostDate DESC, PriorityID ASC");
            prepare.setString(1, user_CourseID);
            prepare.setString(2, user_SectionID);
            prepare.setString(3, "Everyone");
            prepare.setString(4, "Homeroom");
            prepare.setString(5, "Only Me");

            result = prepare.executeQuery(); // Execute the query and obtain the result set

            while (result.next()) {
                todolistTaskHub todoListData = new todolistTaskHub();
                todoListData.setTitle(result.getString("Title"));
                todoListData.setDeadline(result.getString("Deadline"));
                todoListData.setPriority(result.getString("PriorityID"));
                todolistdatas.add(todoListData);
            }

            tblTaskhub.setItems(todolistdatas);

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

    private ObservableList<OfficerAccountData> officerAccountDataList;

    private void loadOfficerAccountData() {
        officerAccountDataList = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            // Assume your database connection is already established
            prepare = connect.prepareStatement("SELECT Surname, Firstname, Middlename FROM ACCOUNT_STUDENT WHERE SectionID = ? and CourseID = ?");
            prepare.setString(1, user_SectionID);
            prepare.setString(2, user_CourseID);
            result = prepare.executeQuery();

            while (result.next()) {
                OfficerAccountData officerAccount = new OfficerAccountData();
                officerAccount.setTblSurname(result.getString("Surname"));
                officerAccount.setTblFirstName(result.getString("Firstname"));
                officerAccount.setTblMiddlename(result.getString("Middlename"));

                officerAccountDataList.add(officerAccount);
            }
            tblClassMates.setItems(officerAccountDataList);

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

    public void setTaskData(int todoID, String title, String body, String priority, LocalDate deadline) {
        txtIDtod.setText(String.valueOf(todoID));  // Set the data to the corresponding fields
        txtTitleTask.setText(title);
        txtBodyTask.setText(body);
        cbPriorityToDo.setValue(priority);
        datePickerTask.setValue(deadline);

        // You can continue to set other fields accordingly...
    }

    @FXML
    private void handleUpdateButtonClick(ActionEvent event) {
        ObservableList<ToDoListData> todolistdatass;

        // Fetch data from the UI component
        int selectedID = Integer.parseInt(txtIDtod.getText());
        String updatedTitle = txtTitleTask.getText();
        String updatedBody = txtBodyTask.getText();
        String updatedPriority = cbPriorityToDo.getValue();
        LocalDate updatedDeadline = datePickerTask.getValue();

        // Get the selected task ID (replace this with your actual method to get the selected task ID)
        // Check if all required fields are filled
        if (updatedTitle.isEmpty() || updatedBody.isEmpty() || updatedPriority == null || updatedDeadline == null) {
            showErrorAlert("Error", "Please fill in all fields before updating.");
            return;
        }

        try {
            // Update the corresponding task in the database
            connect = database.getConnection();
            String updateQuery = "UPDATE mod_todo_pending SET Title=?, Note=?, Deadline=?, PriorityID=? WHERE ToDoID=?";
            prepare = connect.prepareStatement(updateQuery);
            prepare.setString(1, updatedTitle);
            prepare.setString(2, updatedBody);
            prepare.setDate(3, java.sql.Date.valueOf(updatedDeadline));
            prepare.setString(4, updatedPriority);
            prepare.setInt(5, selectedID);

            // Execute the update query
            prepare.executeUpdate();

            // Show a success alert
            showSuccessAlert();
            getToDoListData();
            todoCard();
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error", "Failed to update the task in the database.");
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

    @FXML
    private void Refreshtodolist(ActionEvent event) throws SQLException {
        getToDoListData();
        todoCard();
    }

    public void numberCompletedTask() {
        connect = database.getConnection();
        String sql = "SELECT COUNT(*) AS numberOfCompletedTask FROM mod_todo_completed WHERE StudentID = ?";

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user_StudentID);
            ResultSet resultSet = prepare.executeQuery();

            if (resultSet.next()) {
                int numberOfCompletedTasks = resultSet.getInt("numberOfCompletedTask");
                int countText = numberOfCompletedTasks;
                lblCompletedTask.setText(String.valueOf(countText));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showErrorAlert("Error", "Failed to retrieve the completed tasks count from the database.");
        } finally {
            // Close resources in a finally block
            try {
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void checkAndUpdateNotificationVisibility() {
        String todoCountQuery = "SELECT COUNT(*) AS rowCount FROM mod_todo_pending "
                + "WHERE audienceID = 'homeroom' AND courseID = ? AND sectionID = ? AND LastUpdateTime IS NOT NULL";

        String announceCountQuery = "SELECT COUNT(*) AS rowCount FROM mod_announce "
                + "WHERE audienceID = 'homeroom' AND courseID = ? AND sectionID = ? AND LastUpdateTime IS NOT NULL";

        try (Connection connection = database.getConnection(); PreparedStatement todoPreparedStatement = connection.prepareStatement(todoCountQuery); PreparedStatement announcePreparedStatement = connection.prepareStatement(announceCountQuery)) {

            // Set parameters for todoCountQuery
            todoPreparedStatement.setString(1, user_CourseID);
            todoPreparedStatement.setString(2, user_SectionID);

            // Set parameters for announceCountQuery
            announcePreparedStatement.setString(1, user_CourseID);
            announcePreparedStatement.setString(2, user_SectionID);

            // Execute queries to get row counts
            int todoRowCount;
            int announceRowCount;

            try (ResultSet todoResultSet = todoPreparedStatement.executeQuery()) {
                todoRowCount = todoResultSet.next() ? todoResultSet.getInt("rowCount") : 0;
            }

            try (ResultSet announceResultSet = announcePreparedStatement.executeQuery()) {
                announceRowCount = announceResultSet.next() ? announceResultSet.getInt("rowCount") : 0;
            }

            // Check if either table has non-null values
            if (todoRowCount > 0 || announceRowCount > 0) {
                notifIV.setVisible(true);
            } else {
                notifIV.setVisible(false);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    @FXML
    private void handleNotifButtonClick(ActionEvent event) {
        if (user_CourseID != null && user_SectionID != null) {
            String courseId = user_CourseID;
            String sectionId = user_SectionID;

            // Step 1: Count rows where LastUpdateTime is not NULL for mod_todo_pending
            String todoCountQuery = "SELECT COUNT(*) AS rowCount FROM mod_todo_pending "
                    + "WHERE audienceID = 'homeroom' AND courseID = ? AND sectionID = ? AND LastUpdateTime IS NOT NULL";

            // Step 1: Count rows where LastUpdateTime is not NULL for mod_announce
            String announceCountQuery = "SELECT COUNT(*) AS rowCount FROM mod_announce "
                    + "WHERE audienceID = 'homeroom' AND courseID = ? AND sectionID = ? AND LastUpdateTime IS NOT NULL";

            try (Connection connection = database.getConnection(); PreparedStatement todoCountStatement = connection.prepareStatement(todoCountQuery); PreparedStatement announceCountStatement = connection.prepareStatement(announceCountQuery)) {

                // Set parameters for todoCountQuery
                todoCountStatement.setString(1, courseId);
                todoCountStatement.setString(2, sectionId);

                // Set parameters for announceCountQuery
                announceCountStatement.setString(1, courseId);
                announceCountStatement.setString(2, sectionId);

                // Execute queries to get row counts
                int todoRowCount;
                int announceRowCount;

                try (ResultSet todoResultSet = todoCountStatement.executeQuery(); ResultSet announceResultSet = announceCountStatement.executeQuery()) {

                    todoRowCount = todoResultSet.next() ? todoResultSet.getInt("rowCount") : 0;
                    announceRowCount = announceResultSet.next() ? announceResultSet.getInt("rowCount") : 0;
                }

                // Assuming notifNumber is a Label object for mod_todo_pending
                notifNumber.setText(String.valueOf(todoRowCount));

                // Assuming announceCount is a Label object for mod_announce
                announceCount.setText(String.valueOf(announceRowCount));

                // Step 2: Update LastUpdateTime to NULL for mod_todo_pending
                String updateTodoQuery = "UPDATE mod_todo_pending "
                        + "SET LastUpdateTime = NULL "
                        + "WHERE audienceID = 'homeroom' AND courseID = ? AND sectionID = ? AND LastUpdateTime IS NOT NULL";

                // Step 2: Update LastUpdateTime to NULL for mod_announce
                String updateAnnounceQuery = "UPDATE mod_announce "
                        + "SET LastUpdateTime = NULL "
                        + "WHERE audienceID = 'homeroom' AND courseID = ? AND sectionID = ? AND LastUpdateTime IS NOT NULL";

                updateLastUpdateTime(courseId, sectionId, updateTodoQuery);
                updateLastUpdateTime(courseId, sectionId, updateAnnounceQuery);

                // Assuming notifBox is a container for notifNumber and announceCount
                notifBox.setVisible(true);
                notifNumber.setVisible(true);
                announceCount.setVisible(true);

                // Schedule a task to hide notifBox, notifNumber, and announceCount after 3 seconds
                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                executorService.schedule(() -> {
                    Platform.runLater(() -> {
                        notifBTN.setVisible(false);
                        notifBox.setVisible(false);
                        notifNumber.setVisible(false);
                        announceCount.setVisible(false);
                    });
                }, 3, TimeUnit.SECONDS);
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception according to your needs
            }
        }
    }

    private void updateLastUpdateTime(String courseId, String sectionId, String updateQuery) throws SQLException {
        try (Connection connection = database.getConnection(); PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, courseId);
            updateStatement.setString(2, sectionId);

            // Execute the update query
            int rowsUpdated = updateStatement.executeUpdate();

            System.out.println(rowsUpdated + " rows updated.");
        }
    }
}
