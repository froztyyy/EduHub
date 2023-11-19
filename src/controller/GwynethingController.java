/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.BorderPane;
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
public class GwynethingController implements Initializable {

    @FXML
    private BorderPane borderPaneMain;
    private ImageView gradientCursor;
    private Pane sidePanel;
    private boolean isPanelVisible = false;
    private boolean slideButtonClicked = false;
    private HBox homeWindow;
    @FXML
    private HBox sidePanelTitleColor;
    @FXML
    private AnchorPane imageGradientWelcome;
    @FXML
    private Label fontsizeGrow;
    private double x = 0;
    private double y = 0;
    private Button lastClickedButton;
    private Pane lastActivePane;

    // Create buttons
    Button homeButton = createSidePanelButton("Home");
    Button announcementButton = createSidePanelButton("Announcement");
    Button toDoListButton = createSidePanelButton("To Do List");
    Button calendarButton = createSidePanelButton("Calendar");
    Button clockButton = createSidePanelButton("Clock");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideSidePanel();
        gradientCursor = new ImageView(new Image("media/f4a64b5df91d325a4c7a3b673827c312.jpg"));
        gradientCursor.setFitWidth(100); // Set the width of the image
        gradientCursor.setPreserveRatio(true); // Maintain the image's aspect ratio
        gradientCursor.setSmooth(true); // Enable image smoothing

        // Add the gradientCursor to the main pane
        borderPaneMain.getChildren().add(0, gradientCursor);

        // Apply BoxBlur effect
        applyBoxBlurEffect(gradientCursor);

        // Add mouse move event handler
        borderPaneMain.setOnMouseMoved(this::handleMouseMove);

        sidePanel = new Pane();
        sidePanel.setStyle("-fx-background-color: #0D1117;");
        sidePanel.setPrefWidth(200);
        sidePanel.setPrefHeight(800); // Set the preferred height

        // Add buttons to the side panel
    sidePanel.getChildren().addAll(clockButton, calendarButton, announcementButton, toDoListButton, homeButton);

        // Set the layout for each button
        layoutSidePanelButton(homeButton, 0);
        layoutSidePanelButton(announcementButton, 1);
        layoutSidePanelButton(toDoListButton, 2);
        layoutSidePanelButton(calendarButton, 3);
        layoutSidePanelButton(clockButton, 4);

        hideSidePanel();

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

        // Initialize the first active pane and button
        lastActivePane = homePane;
        lastClickedButton = homeButton;

        
        // Set styles for the initial active button
        setButtonStyles(homeButton, true);
    }

    private Button createSidePanelButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setPrefSize(150, 50);
        button.setOnAction(event -> handleSidePanelButtonClick(event, button));

        button.setStyle(
                "-fx-background-color: transparent; "
                + "-fx-text-fill: white; "
                + "-fx-font-family: 'Arial'; "
                + "-fx-font-size: 15px;"
                + "-fx-border-color: transparent;"
        );

        return button;
    }

    private void handleButtonAction(String buttonText) {
        // Implement the action for each button
        System.out.println("Button Clicked: " + buttonText);
    }

    private void layoutSidePanelButton(Button button, int index) {
        double panelWidth = sidePanel.getPrefWidth();
        double panelHeight = sidePanel.getPrefHeight();
        double buttonWidth = button.getPrefWidth();
        double buttonHeight = button.getPrefHeight();
        double buttonX = (panelWidth - buttonWidth) / 2;
        double buttonY = (panelHeight - buttonHeight) / 2 - 40 * index; // Adjust vertical position
        button.setLayoutX(buttonX);
        button.setLayoutY(buttonY);
    }

    private void calendarNavigation() {
        hideSidePanel();
        homeWindow.setVisible(false);
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
        Stage stage = (Stage) borderPaneMain.getScene().getWindow();
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
    private void maximizeButton(ActionEvent event) {
        Stage stage = (Stage) borderPaneMain.getScene().getWindow();

        if (stage.isMaximized()) {
            // If already maximized, restore to its original size
            stage.setMaximized(false);
        } else {
            // Maximize the stage
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            // Set the stage dimensions to match the screen size (excluding the taskbar)
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());

            // Mark the stage as maximized
            stage.setMaximized(true);
        }
    }

    private void showSidePanel() {
        if (!isPanelVisible) {
            borderPaneMain.setLeft(sidePanel);
//            applyBlurEffect(); // Apply blur when side panel is shown
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), sidePanel);
            transition.setToX(0);
            transition.play();
            isPanelVisible = true;
        }
    }

    private void hideSidePanel() {
        if (isPanelVisible) {
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), sidePanel);
            transition.setToX(-sidePanel.getWidth());
            transition.setOnFinished(e -> {
                borderPaneMain.setLeft(null);
//                removeBlurEffect(); // Remove blur when side panel is hidden
            });
            transition.play();
            isPanelVisible = false;
        }
    }

    @FXML
    private void slidePanelButton(ActionEvent event) {
        if (isPanelVisible) {
            hideSidePanel();
        } else {
            showSidePanel();
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

            borderPaneMain.getScene().getWindow().hide();

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

    private void handleSidePanelButtonClick(ActionEvent event, Button button) {
        Button clickedButton = (Button) event.getSource();

        if (clickedButton == lastClickedButton) {
            // Ignore the click if the same button was clicked twice in a row
            return;
        }

        // Reset styles for all buttons
        resetButtonStyles();

        // Close the active pane
        if (lastActivePane != null) {
            lastActivePane.setVisible(false);
        }

        // Apply styles based on the clicked button
        if (clickedButton == homeButton) {
            setButtonStyles(homeButton, true);
            homePane.setVisible(true);
            lastActivePane = homePane;
        } else if (clickedButton == announcementButton) {
            setButtonStyles(announcementButton, true);
            announcementPane.setVisible(true);
            lastActivePane = announcementPane;
        } else if (clickedButton == toDoListButton) {
            setButtonStyles(toDoListButton, true);
            toDoListPane.setVisible(true);
            lastActivePane = toDoListPane;
        } else if (clickedButton == calendarButton) {
            setButtonStyles(calendarButton, true);
            calendarPane.setVisible(true);
            lastActivePane = calendarPane;
        } else if (clickedButton == clockButton) {
            setButtonStyles(clockButton, true);
            clockPane.setVisible(true);
            lastActivePane = clockPane;
        }

        // Update the last clicked button
        lastClickedButton = clickedButton;
    }

    private void resetButtonStyles() {
        setButtonStyles(homeButton, false);
        setButtonStyles(announcementButton, false);
        setButtonStyles(toDoListButton, false);
        setButtonStyles(calendarButton, false);
        setButtonStyles(clockButton, false);
    }

    private void setButtonStyles(Button button, boolean isSelected) {
        if (isSelected) {
            button.getStyleClass().add("selected-button");
        } else {
            button.getStyleClass().remove("selected-button");
        }
    }
    
    

}

