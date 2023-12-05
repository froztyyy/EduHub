/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controller.AnnouncementData;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DisplayAnnouncementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label DisplayAudience;

    @FXML
    private Label DisplayDeadline;

    @FXML
    private Label DisplayPriority;

    @FXML
    private TextArea DisplayText;

    @FXML
    private Label DisplayTitle;

    @FXML
    private AnchorPane AnnouncementDisplay;

    public void setData(AnnouncementData announcementData) {
        this.announcementData = announcementData; // Set the announcementData field
        DisplayTitle.setText(announcementData.getTitle());
        DisplayText.setText(announcementData.getBody());

    }

    private AnnouncementData announcementData;

    @FXML
    private void handleRemoveButton(ActionEvent event) {
        // Show a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to remove this item?");
        alert.setContentText("This will move the task to archive.");

        // Wait for user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, proceed with removal
            // Remove the displayListPane
            AnnouncementDisplay.getChildren().clear();

            // Save data to the "archive" table in the database
            /*saveToArchive();*/
        }
    }
    
    /*
    private void saveToArchive() {
        try (Connection conn = database.getConnection()) {
            if (conn != null) {
                // First, delete the task from the task table
                deleteFromAnnouncementTable(conn);

                // Now, insert the task into the archive table
                insertIntoArchiveTable(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private void deleteFromAnnouncementTable(Connection conn) throws SQLException {
        String deleteQuery = "DELETE FROM announcement WHERE Title = ? ";
        try (PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, announcementData.getTitle());
            preparedStatement.executeUpdate();
        }
    }

    private void insertIntoArchiveTable(Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO announcement_archive (Title, Body,StudentID) VALUES (?, ?, ?,?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, announcementData.getTitle());
            preparedStatement.setString(2, announcementData.getBody());
            preparedStatement.setInt(3, 10000004);
            preparedStatement.executeUpdate();
        }
    }
*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code if needed

    }
}
