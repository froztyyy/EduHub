/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private Pane listPane;
    @FXML
    private GridPane listHandler;
    @FXML
    private Pane archivePane;
    @FXML
    private Button btnAddList;
    @FXML
    private Button btnArchive;
    @FXML
    private GridPane archiveListHandler;
    @FXML
    private Label lblStudentID;
    @FXML
    private Label greetingLabel;
    @FXML
    private ComboBox<String> audiencelist;
    @FXML
    private ComboBox<String> prioritylist;
    @FXML
    private TextField TitleText;
    @FXML
    private TextArea AnnouncementText;
    @FXML
    private Button PostButton;
    @FXML
    private GridPane AnnouncementHandler;
    @FXML
    private Label greetingLabelTime;

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

        homeDisplayListCard();
        archiveDisplayListCard();
        listPane.setVisible(true);
        archivePane.setVisible(false);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            TimeAndDateLocation();
            updateGreeting();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        homeDisplayListCard();
        fetchAudienceNameToComboBox(audiencelist);
        fetchPriorityNameToComboBox(prioritylist);

        DisplayAnnouncement();
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

    @FXML
    public void SwitchFormForTodoList(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if (clickedButton == lastClickedButtonForToDoList) {
            // Ignore the click if the same button was clicked twice in a row
            return;
        }

        // Reset the color of the last clicked button
        if (lastClickedButton != null) {
            setButtonColor(lastClickedButton, false);
        }

        // Update the last clicked button
        lastClickedButtonForToDoList = clickedButton;

        if (clickedButton == btnAddList) {
            setButtonColorForToDoList(btnAddList, true);
            setButtonColorForToDoList(btnArchive, false);

            listPane.setVisible(true);
            archivePane.setVisible(false);

        } else if (clickedButton == btnArchive) {
            setButtonColorForToDoList(btnAddList, false);
            setButtonColorForToDoList(btnArchive, true);

            listPane.setVisible(false);
            archivePane.setVisible(true);

        }
    }

    @FXML
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
            
            homeDisplayListCard();

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

    private ObservableList<ToDoListData> toDoList = FXCollections.observableArrayList();

    public ObservableList<ToDoListData> getToDoListData() throws SQLException {

        String sql = "Select Title, Note, Deadline FROM todo";
        ObservableList<ToDoListData> toDoList = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                String description = result.getString("Title");
                String details = result.getString("Note");
                String due_date = result.getString("Deadline");

                ToDoListData todoListData = new ToDoListData(description, details, due_date);

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

            int maxColumns = 4;
            int row = 0;
            int column = 0;

            listHandler.getChildren().clear();
            listHandler.getRowConstraints().clear();
            listHandler.getColumnConstraints().clear();

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

                    listHandler.add(pane, column++, row);

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

    private ObservableList<ArchiveToDoListData> archiveToDoList = FXCollections.observableArrayList();

    public ObservableList<ArchiveToDoListData> getArchiveToDoListData() throws SQLException {

        String sql = "Select Title, Note, Deadline FROM todo";
        ObservableList<ArchiveToDoListData> archiveToDoList = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                String description = result.getString("Title");
                String details = result.getString("Note");
                String due_date = result.getString("Deadline");

                ArchiveToDoListData archiveToDoListData = new ArchiveToDoListData(description, details, due_date);

                archiveToDoList.add(archiveToDoListData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (result, prepare, connect) if needed
        }

        return archiveToDoList;
    }

    public void archiveDisplayListCard() {
        try {
            archiveToDoList.clear();
            archiveToDoList.addAll(getArchiveToDoListData());

            int maxColumns = 4;
            int row = 0;
            int column = 0;

            archiveListHandler.getChildren().clear();
            archiveListHandler.getRowConstraints().clear();
            archiveListHandler.getColumnConstraints().clear();

            for (int q = 0; q < archiveToDoList.size(); q++) {
                try {
                    if (column >= maxColumns) {
                        // Move to the next row when the maximum number of columns is reached
                        column = 0;
                        row++;
                    }

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/archiveDisplayCard.fxml"));
                    AnchorPane pane = loader.load();
                    controller.ArchiveDisplayCardController archiveCardController = loader.getController();
                    archiveCardController.setArchiveData(archiveToDoList.get(q));

                    archiveListHandler.add(pane, column++, row);

                    GridPane.setMargin(pane, new Insets(5));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            currentDisplayIndex = 0;
            if (!archiveToDoList.isEmpty()) {
                ArchiveToDoListData firstToDo = archiveToDoList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void refreshArchiveDisplay() {
        // Clear the archive display
        archiveListHandler.getChildren().clear();

        try {
            // Reload and display the archive data
            archiveToDoList.clear();
            archiveToDoList.addAll(getArchiveToDoListData());

            int maxColumns = 3;
            int row = 0;
            int column = 0;

            for (int q = 0; q < archiveToDoList.size(); q++) {
                try {
                    if (column >= maxColumns) {
                        // Move to the next row when the maximum number of columns is reached
                        column = 0;
                        row++;
                    }

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("archiveDisplayCard.fxml"));
                    AnchorPane pane = loader.load();
                    ArchiveDisplayCardController archiveCardController = loader.getController();
                    archiveCardController.setArchiveData(archiveToDoList.get(q));

                    archiveListHandler.add(pane, column++, row);

                    GridPane.setMargin(pane, new Insets(5));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            currentDisplayIndex = 0;
            if (!archiveToDoList.isEmpty()) {
                ArchiveToDoListData firstToDo = archiveToDoList.get(0);
            }
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

    private String username;
    private int studentID;

    public void setUsername(String username) {
        this.username = username;
        updateGreeting();
        updateGreetingForTime();
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
        greetingLabel.setText(greeting + ", " + username + "!");
    }

    public void updateGreetingForTime() {
        String greeting = getGreeting();
        greetingLabelTime.setText(greeting + ", " + username + "!");
    }

    public void updateStudentId() {
        lblStudentID.setText(String.valueOf(studentID));
    }

    private void fetchAudienceNameToComboBox(ComboBox<String> comboBox) {

        try {
            prepare = connect.prepareStatement("SELECT AudienceName FROM audience");
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

    private void fetchPriorityNameToComboBox(ComboBox<String> comboBox) {

        try {
            prepare = connect.prepareStatement("SELECT PriorityName FROM priority_level");
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
    private void handlepostsubmit(ActionEvent event) throws IOException {

        try {
            // Establish a database connection
            connect = database.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO announcement (Title,Body,AudienceID,PriorityID,StudentID) VALUES (?, ?,?,?,?)";
            prepare = connect.prepareStatement(sql);

            // Set values from the user input
            prepare.setString(1, TitleText.getText());
            prepare.setString(2, AnnouncementText.getText());
            prepare.setString(3, audiencelist.getValue()); // Set a default value for Homeroom if it's null
            prepare.setString(4, prioritylist.getValue());
            prepare.setInt(5, studentID);// Set a default value for Medium if it's null
            // Convert LocalDate to java.sql.Date

            // Execute the SQL statement
            prepare.executeUpdate();

            DisplayAnnouncement();

            // Show a success alert
            showSuccessAlert();
            
            clearFields();

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

    private void clearFields() {
        // Reset values of input fields
        TitleText.clear();
        AnnouncementText.clear();
        audiencelist.setValue(null); // Set default value or null based on your requirements
        prioritylist.setValue(null);
        // Reset other fields as needed
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

    public void setStudentID(int studentID) throws SQLException {
        this.studentID = studentID;
        updateStudentId();
        DisplayAnnouncement();
    }

    private ObservableList<AnnouncementData> Announcement = FXCollections.observableArrayList();

    public ObservableList<AnnouncementData> getAnnouncementData() throws SQLException {

        String sql = "Select Title, Body FROM announcement where StudentID = ? ORDER BY AnnouncementID DESC";
        ObservableList<AnnouncementData> Announcement = FXCollections.observableArrayList();
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, studentID);
            result = prepare.executeQuery();

            while (result.next()) {
                String title = result.getString("Title");
                String body = result.getString("Body");

                AnnouncementData announcementData = new AnnouncementData(title, body);

                Announcement.add(announcementData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources (result, prepare, connect) if needed
        }

        return Announcement;
    }

    public void DisplayAnnouncement() {
        try {
            Announcement.clear();
            Announcement.addAll(getAnnouncementData());

            Platform.runLater(() -> {
                int maxColumns = 1;
                int row = 0;
                int column = 0;

                AnnouncementHandler.getChildren().clear();
                AnnouncementHandler.getRowConstraints().clear();
                AnnouncementHandler.getColumnConstraints().clear();

                for (int q = 0; q < Announcement.size(); q++) {
                    try {
                        if (column >= maxColumns) {
                            // Move to the next row when the maximum number of columns is reached
                            column = 0;
                            row++;
                        }

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/view/DisplayAnnouncement.fxml"));
                        AnchorPane pane = loader.load();
                        DisplayAnnouncementController cardController = loader.getController();
                        cardController.setData(Announcement.get(q));

                        AnnouncementHandler.add(pane, column++, row);

                        GridPane.setMargin(pane, new Insets(5));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                currentDisplayIndex = 0;
                if (!Announcement.isEmpty()) {
                    AnnouncementData firstAnnounce = Announcement.get(0);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
