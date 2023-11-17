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
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    private BorderPane borderPaneMain;
    private ImageView gradientCursor;
    private Pane sidePanel;
    private boolean isPanelVisible = false;
    private boolean slideButtonClicked = false;
    @FXML
    private HBox homeWindow;

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
        sidePanel.setStyle("-fx-background-color: transparent;");
        sidePanel.setPrefWidth(200);
        // Create a button
        Button Calendar = new Button("Calendar");

        // Set the preferred size of the button
        Calendar.setPrefSize(150, 50);

        // Add an event handler to the button
        Calendar.setOnAction(event -> calendarNavigation());

        // Add the button to the side panel
        sidePanel.getChildren().add(Calendar);

        // Center the button in the side panel
        double panelWidth = sidePanel.getPrefWidth();
        double buttonWidth = Calendar.getPrefWidth();
        double buttonX = (panelWidth - buttonWidth) / 2;
        Calendar.setLayoutX(buttonX);
        
        Calendar.setStyle("-fx-background-color: transparent;");
        Calendar.setOnMouseEntered(e -> Calendar.setStyle("-fx-background-color: #3F3F3F;"));
        Calendar.setOnMouseExited(e -> Calendar.setStyle("-fx-background-color: transparent;"));
        Calendar.setOnMousePressed(e -> Calendar.setStyle("-fx-background-color: #3C3F41;"));


        hideSidePanel();

    }

//    private void applyBlurEffect() {
//        BoxBlur boxBlur = new BoxBlur(10, 10, 3); // You can adjust the values as needed
//        borderPaneMain.setEffect(boxBlur);
//    }
//
//    private void removeBlurEffect() {
//        borderPaneMain.setEffect(null);
//    }
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

}
