/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
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
    private Pane sidePanel;
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
    private Button homeButton;
    @FXML
    private Button announcementButton;
    @FXML
    private Button calendarButton;
    @FXML
    private Button toDolistButton;
    @FXML
    private Button timeClockButton;
    @FXML
    private AnchorPane homePane;
    @FXML
    private AnchorPane announcementPane;
    @FXML
    private AnchorPane toDoListPane;
    @FXML
    private AnchorPane calendarPane;
    @FXML
    private AnchorPane clockPane;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sidePanel.setPrefWidth(375);
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
    }

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
    private void slidePanelButton(ActionEvent event) {
        double startWidth = sidePanel.getPrefWidth();
        double endWidth = isSidePanelOpen ? 0 : 357;

        Timeline timeline = new Timeline();

        // Update the width of the side panel
        KeyValue keyValueWidth = new KeyValue(sidePanel.prefWidthProperty(), endWidth);
        KeyFrame keyFrameWidth = new KeyFrame(Duration.millis(500), keyValueWidth);

        // Update the blur effect on the blurringEffect pane
        BoxBlur boxBlur = new BoxBlur();
        if (!isSidePanelOpen) {
            // Adjust these parameters for a milder blur effect
            boxBlur.setWidth(5.0);
            boxBlur.setHeight(5.0);
            boxBlur.setIterations(2);
        } else {
            boxBlur.setWidth(0);
            boxBlur.setHeight(0);
            boxBlur.setIterations(0);
        }

        KeyValue keyValueBlur = new KeyValue(blurringEffect.effectProperty(), boxBlur);
        KeyFrame keyFrameBlur = new KeyFrame(Duration.millis(500), keyValueBlur);

        timeline.getKeyFrames().addAll(keyFrameWidth, keyFrameBlur);
        timeline.play();

        isSidePanelOpen = !isSidePanelOpen;
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
    private void closeSideNavigation(ActionEvent event) {
        // Close the side panel
        double endWidth = 0;

        Timeline timeline = new Timeline();

        // Update the width of the side panel
        KeyValue keyValueWidth = new KeyValue(sidePanel.prefWidthProperty(), endWidth);
        KeyFrame keyFrameWidth = new KeyFrame(Duration.millis(500), keyValueWidth);

        // Reset the blur effect on the blurringEffect pane
        BoxBlur boxBlur = new BoxBlur(0, 0, 0);

        KeyValue keyValueBlur = new KeyValue(blurringEffect.effectProperty(), boxBlur);
        KeyFrame keyFrameBlur = new KeyFrame(Duration.millis(500), keyValueBlur);

        timeline.getKeyFrames().addAll(keyFrameWidth, keyFrameBlur);
        timeline.play();

        isSidePanelOpen = false;
    }

    private Button lastClickedButton = null;

    @FXML
    public void SwitchForm(ActionEvent event) {

        Button clickedButton = (Button) event.getSource();

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

    private void setButtonColor(Button pane, boolean isSelected) {
        if (isSelected) {
            pane.getStyleClass().add("selected-button");
        } else {
            pane.getStyleClass().remove("selected-button");
        }
    }



}
