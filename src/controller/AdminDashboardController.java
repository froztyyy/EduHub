/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.sun.jdi.connect.spi.Connection;
import java.net.URL;
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
    private TextField txtCourseID;
    private TextField txtSectionID;
    @FXML
    private Button btnCreateAccount;
    @FXML
    private TextField txtCourse;
    @FXML
    private TextField txtSection;

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

}
