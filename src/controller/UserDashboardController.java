/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private Label year;

    @FXML
    private Label month;

    @FXML
    private FlowPane calendar;
    @FXML
    private AnchorPane clockPane;
    @FXML
    private Label lblTime;
    @FXML
    private AnchorPane timerPane;
    @FXML
    private Label lblTimerTime;
    @FXML
    private AnchorPane stopwatchPane;
    @FXML
    private Label lblTimer;
    @FXML
    private Pane bottomNavigation;
    @FXML
    private Button btnClock;
    @FXML
    private Button btnTimer;
    @FXML
    private Button btnStopwatch;
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

        sidePanel.setVisible(true);
        homeWindow.setVisible(true);
        announcementWindow.setVisible(false);
        calendarWindow.setVisible(false);
        todoWindow.setVisible(false);
        timeClockWindow.setVisible(false);

        bottomNavigation.setVisible(true);
        clockPane.setVisible(true);
        timerPane.setVisible(false);
        stopwatchPane.setVisible(false);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::updateTimer));
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeNow();
        timeNowForDashboard();
        dateLabelForDashboard();

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
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

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
                        }

                        stackPane.setOnMouseClicked(mouseEvent -> {
                            try {

                                Parent root = FXMLLoader.load(getClass().getResource("/view/calendarInfo.fxml"));

                                // You can get the controller and pass any data if needed
                                // YourControllerClass controller = loader.getController();
                                // controller.setData(...); // Pass data if needed
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
                                newStage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
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
                                newStage.setTitle("New Window");

                                // Show the new stage
                                newStage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth()
                            && today.getDayOfMonth() == currentDate) {
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
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

    private void setButtonColorforTime(Button button, boolean isSelected) {
        if (isSelected) {
            button.getStyleClass().add("selected-button");
        } else {
            button.getStyleClass().remove("selected-button");
        }
    }

    private Button lastClickedButtonTime = null;

    @FXML
    public void SwitchFormForTime(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if (clickedButton == lastClickedButtonTime) {
            // Ignore the click if the same button was clicked twice in a row
            return;
        }

        // Reset the color of the last clicked button
        if (lastClickedButton != null) {
            setButtonColor(lastClickedButton, false);
        }

        // Update the last clicked button
        lastClickedButtonTime = clickedButton;

        if (clickedButton == btnClock) {
            setButtonColorforTime(btnClock, true);
            setButtonColorforTime(btnTimer, false);
            setButtonColorforTime(btnStopwatch, false);

            bottomNavigation.setVisible(true);
            clockPane.setVisible(true);
            timerPane.setVisible(false);
            stopwatchPane.setVisible(false);

        } else if (clickedButton == btnTimer) {
            setButtonColorforTime(btnClock, false);
            setButtonColorforTime(btnTimer, true);
            setButtonColorforTime(btnStopwatch, false);

            bottomNavigation.setVisible(true);
            clockPane.setVisible(false);
            timerPane.setVisible(true);
            stopwatchPane.setVisible(false);

        } else if (clickedButton == btnStopwatch) {
            setButtonColorforTime(btnClock, false);
            setButtonColorforTime(btnTimer, false);
            setButtonColorforTime(btnStopwatch, true);

            bottomNavigation.setVisible(true);
            clockPane.setVisible(false);
            timerPane.setVisible(false);
            stopwatchPane.setVisible(true);
        }
    }

    private void timeNow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh : mm : ss");
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                final String timeNow = sdf.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(timeNow);
                });
            }
        });

        thread.start();
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

}
