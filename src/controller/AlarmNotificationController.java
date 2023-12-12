package controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AlarmNotificationController {

    @FXML
    private AnchorPane URGENTPanel;
    
    @FXML
    private AnchorPane HIGHPanel;
    
    @FXML
    private AnchorPane LOWPanel;
    
    @FXML
    private AnchorPane AlarmPanel;
    
    @FXML
    private Button handleCloseButton;
    
    @FXML
    private Button Snoozebutton;

    @FXML
    private Label notificationLabel;
    
    @FXML
    private Button dismiss;

    @FXML
    private StackPane notificationPane;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize(String type) {
        // Initialize the controller based on the notification type
        switch (type) {
            case "URGENT":
                UrgentPanel();
                break;
            case "HIGH":
                HighPanel();
                break;
            case "LOW":
                LowPanel();
                break;
            case "alarm":
                LowPanel();
                break;
            // Add more cases if needed
        }

        // Set up your initial notification message
        showNotification("DEADLINE IS UP!");
    }

    private void showNotification(String message) {
        // Set the notification message
        notificationLabel.setText(message);

        // Set the position of the notification pane
        double xPosition = 50; // Adjust this value as needed
        double yPosition = 100; // Adjust this value as needed

        notificationPane.setLayoutX(xPosition);
        notificationPane.setLayoutY(yPosition);

        // Show the notification pane
        notificationPane.setVisible(true);

        // Set up a timeline to hide the notification after 3 seconds
        Duration timelineDuration = Duration.seconds(5);
        FadeTransition fadeOut = new FadeTransition(timelineDuration, notificationPane);
        fadeOut.setToValue(0); // Fades the entire content to completely transparent

        // Event handler for when the fade-out animation is finished
        fadeOut.setOnFinished(event -> {
            notificationPane.setVisible(false);
            if (stage != null) {
                stage.close(); // Close the stage after fade-out
            }
        });

        // Start the fade-out animation
        fadeOut.play();
    }
    
    private void UrgentPanel(){
        URGENTPanel.setVisible(true);
        HIGHPanel.setVisible(false);
        LOWPanel.setVisible(false);     
        AlarmPanel.setVisible(false);  
    }
     private void HighPanel(){
        URGENTPanel.setVisible(false);
        HIGHPanel.setVisible(true);
        LOWPanel.setVisible(false);    
         AlarmPanel.setVisible(false);  
    }
      private void LowPanel(){
        URGENTPanel.setVisible(false);
        HIGHPanel.setVisible(false);
        LOWPanel.setVisible(true);     
         AlarmPanel.setVisible(false);  
    }
        private void AlarmPanel(){
        URGENTPanel.setVisible(false);
        HIGHPanel.setVisible(false);
        LOWPanel.setVisible(false);     
         AlarmPanel.setVisible(true);  
    }
      
       @FXML
    private void handleCloseBtn(ActionEvent event) {
        // Handle the close button click (optional)
        // You can use this method if you want to close the notification immediately
        stage.close();
    }
    
     @FXML
    private void Snoozebtn(ActionEvent event) {
    
    }
    
      @FXML
    void DISMISS(ActionEvent event) {
             // Handle the close button click (optional)
        // You can use this method if you want to close the notification immediately
        stage.close();
    }
    
}
