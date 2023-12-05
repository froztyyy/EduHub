/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
    private Pane todoWindow;
    @FXML
    private Pane timeClockWindow;
    @FXML
    private Pane homeButton;
    @FXML
    private Pane announcementButton;
    @FXML
    private Pane calendarButton;
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
        timeClockWindow.setVisible(false);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::updateTimer));
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeNowForDashboard();
        dateLabelForDashboard();
        TimeAndDateLocation();

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
                setButtonColor(timeClockButton, false);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(true);
                calendarWindow.setVisible(false);
                studentManagementWIndow.setVisible(false);
                timeClockWindow.setVisible(false);

            } else if (clickedButton == calendarButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, false);
                setButtonColor(calendarButton, true);
                setButtonColor(studentManagementButton, false);
                setButtonColor(timeClockButton, false);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(false);
                calendarWindow.setVisible(true);
                studentManagementWIndow.setVisible(false);
                timeClockWindow.setVisible(false);

            } else if (clickedButton == studentManagementButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, false);
                setButtonColor(calendarButton, false);
                setButtonColor(studentManagementButton, true);
                setButtonColor(timeClockButton, false);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(false);
                calendarWindow.setVisible(false);
                studentManagementWIndow.setVisible(true);
                timeClockWindow.setVisible(false);

            } else if (clickedButton == timeClockButton) {
                setButtonColor(homeButton, false);
                setButtonColor(announcementButton, false);
                setButtonColor(calendarButton, false);
                setButtonColor(studentManagementButton, false);
                setButtonColor(timeClockButton, true);

                homeWindow.setVisible(false);
                announcementWindow.setVisible(false);
                calendarWindow.setVisible(false);
                studentManagementWIndow.setVisible(false);
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

    private java.sql.Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private void fetchCourseToComboBox(ComboBox<String> comboBox) {
        String sql = "SELECT CourseAbb FROM course";
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

        String sql = "SELECT SectionName FROM section";
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
        String sql = "INSERT INTO account_student (StudentID, Password, RoleID, Surname, Firstname, Middlename, Suffix, CourseID, SectionID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connect = database.getConnection();
            prepare = connect.prepareStatement(sql);

            // Set parameters for the prepared statement
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

            // Show success alert
            showSuccessAlert("Account created successfully!");

            // Load and refresh the TableView
            loadStudentAccountData();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("controller.AdminDashboardController.handleCreateAccount()");
        }
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
        StudentAccountDataList selectedOfficer = tblStudentData.getSelectionModel().getSelectedItem();

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
                tblStudentData.getItems().remove(selectedOfficer);

                // Remove from the database
                deleteStudentFromDatabase(selectedOfficer);

                tblStudentData.getItems().remove(selectedOfficer);

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

    private void deleteStudentFromDatabase(StudentAccountDataList studentAccountDataList) {
        connect = database.getConnection();

        try {
            String deleteQuery = "DELETE FROM account_student WHERE StudentID = ?";
            prepare = connect.prepareStatement(deleteQuery);
            prepare.setString(1, studentAccountDataList.getTblStudentID());

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
            showAlert("Success", "Officer Account Updated", "Officer account updated successfully.");

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
            alert.setHeaderText("No Officer Account Selected");
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

}
