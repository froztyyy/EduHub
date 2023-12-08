/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jcarl
 */
public class AnnouncementCardController implements Initializable {

    @FXML
    private Label lblSurname;
    @FXML
    private Label lblPriorityLevel;
    @FXML
    private Label lblAudience;
    @FXML
    private Label lblPostDate;
    @FXML
    private TextArea txtBody;
    @FXML
    private AnchorPane announcementCard;
    @FXML
    private TextField txtTitle;
    @FXML
    private Button btnRemove;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private AnnouncementData announcementData;

    public void setData(AnnouncementData announcementData) {
        this.announcementData = announcementData; // Set the announcementData field
        txtTitle.setText(announcementData.getTitle());
        lblSurname.setText(announcementData.getUser_Surname());
        lblAudience.setText(announcementData.getAudience());
        lblPriorityLevel.setText(announcementData.getPriority());
        txtBody.setText(announcementData.getBody());
        lblPostDate.setText(announcementData.getPostDate());
    }
    
    public void setRemoveButtonVisible(boolean visible) {
        btnRemove.setVisible(visible);
    }


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
            announcementCard.getChildren().clear();

            // Save data to the "archive" table in the database
            saveToArchive();
        }
    }

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
            System.out.println("Post Deleted");
            // Handle the exception as needed
        }
    }

    private void deleteFromAnnouncementTable(Connection conn) throws SQLException {
        String deleteQuery = "DELETE FROM mod_announce WHERE Title = ? ";
        try (PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, announcementData.getTitle());
            preparedStatement.executeUpdate();
        }
    }

    private void insertIntoArchiveTable(Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO mod_announce_archive (Title, Body, AudienceID, PriorityID, StudentID, Surname, CourseID, SectionID, postDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, announcementData.getTitle());
            preparedStatement.setString(2, announcementData.getBody());
            preparedStatement.setString(3, announcementData.getAudience());
            preparedStatement.setString(4, announcementData.getPriority());
            preparedStatement.setString(5, announcementData.getUser_StudentID());
            preparedStatement.setString(6, announcementData.getUser_Surname());
            preparedStatement.setString(7, announcementData.getUser_CourseID());
            preparedStatement.setString(8, announcementData.getUser_SectionID());
            preparedStatement.setString(9, announcementData.getPostDate());
            preparedStatement.executeUpdate();
        }
    }

}
