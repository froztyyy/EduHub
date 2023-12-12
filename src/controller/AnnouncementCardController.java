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
import javafx.scene.layout.VBox;

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
    @FXML
    private Button EditButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private VBox cardVBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private AnnouncementData announcementData;
    private boolean isEditing = false;

    public void setData(AnnouncementData announcementData) {
        this.announcementData = announcementData; // Set the announcementData field
        txtTitle.setText(announcementData.getTitle());
        lblSurname.setText(announcementData.getUser_Surname());
        lblAudience.setText(announcementData.getAudience());
        lblPriorityLevel.setText(announcementData.getPriority());
        txtBody.setText(announcementData.getBody());
        lblPostDate.setText(announcementData.getPostDate());

        // Set text and text color based on Audience and Priority
        setAudienceTextAndColor(announcementData.getAudience());
        setPriorityTextAndColor(announcementData.getPriority());

        // Set border color based on Priority Level
        setBorderColor(announcementData.getPriority());

        // Set background color based on AudienceID
        setBackgroundColor(announcementData.getAudience());
    }

    public void setRemoveButtonVisible(boolean visible) {
        btnRemove.setVisible(visible);
    }

    public void setEditButtonVisible(boolean visible) {
        EditButton.setVisible(visible);
    }

    public void setUpdateButtonVisible(boolean visible) {
        UpdateButton.setVisible(visible);
    }

    private void setBorderColor(String priority) {
        // Set text color based on Priority

        cardVBox.getStyleClass().removeAll("urgent", "high", "low", "everyone");

        if ("Everyone".equals(announcementData.getAudience())) {
            // Set background color for Everyone audience
            cardVBox.getStyleClass().add("everyone");
        } else {
            switch (priority.toLowerCase()) {
                case "1 urgent":
                    cardVBox.getStyleClass().add("urgent");
                    break;
                case "2 high":
                    cardVBox.getStyleClass().add("high");
                    break;
                case "3 low":
                    cardVBox.getStyleClass().add("low");
                    break;
                default:
                    // Reset border color for other cases
                    break;
            }
        }

//        cardVBox.setStyle("-fx-border-color: transparent; -fx-border-width: 0px; -fx-border-radius: 0px;");
//        switch (priority.toLowerCase()) {
//            case "1 urgent":
//                cardVBox.getStyleClass().add("urgent");
//                lblPriorityLevel.setStyle("-fx-text-fill: #df5b4e;");
//                break;
//            case "2 high":
//                cardVBox.getStyleClass().add("high");
//                lblPriorityLevel.setStyle("-fx-text-fill: #fff799;");
//                break;
//            case "3 low":
//                cardVBox.getStyleClass().add("low");
//                lblPriorityLevel.setStyle("-fx-text-fill: #a3d39c;");
//                break;
//            default:
//                // Reset text color and style for other cases
//                cardVBox.getStyleClass().removeAll("urgent", "high", "low");
//                lblPriorityLevel.setStyle("-fx-text-fill: #686868;"); // Default color
//                break;
//        }
    }

    private void setBackgroundColor(String audience) {
        if ("Everyone".equals(audience)) {
            cardVBox.setStyle("-fx-background-color: #007a79;");
        } else {
            // Reset background color for other cases
            cardVBox.setStyle("-fx-background-color: transparent;");
        }
    }

    private void setAudienceTextAndColor(String audience) {
        lblAudience.setText(audience);

        // Set text color based on Audience
        if ("Everyone".equals(audience)) {
            lblAudience.setTextFill(javafx.scene.paint.Color.WHITE);
        } else {
            // Reset text color for other cases
            lblAudience.setTextFill(javafx.scene.paint.Color.web("#686868")); // Default color
        }
    }

    private void setPriorityTextAndColor(String priority) {
        lblPriorityLevel.setText(priority);

        // Set text color based on Priority
        switch (priority.toLowerCase()) {
            case "1 urgent":
                lblPriorityLevel.setStyle("-fx-text-fill: #fc0303;");
                break;
            case "2 high":
                lblPriorityLevel.setStyle("-fx-text-fill: #fff799;");
                break;
            case "3 low":
                lblPriorityLevel.setStyle("-fx-text-fill: #a3d39c;");
                break;
            default:
                // Reset text color for other cases
                lblPriorityLevel.setStyle("-fx-text-fill: #686868;"); // Default color
                break;
        }
    }

    @FXML
    private void handleUpdateButton(ActionEvent event) {
        saveEditedAnnouncement(); // Call the method responsible for saving edited data
        disableEditing(); // Disable editing mode after updating
    }

    @FXML
    private void handleEditButton(ActionEvent event) {
        enableEditing();
    }
    // Method to enable editing of announcement data

    public void enableEditing() {
        isEditing = true;
        txtTitle.setEditable(true);
        txtBody.setEditable(true);
    }

    public void saveEditedAnnouncement() {
        if (isEditing) {
            // Save changes to the announcementData object
            announcementData.setTitle(txtTitle.getText());
            announcementData.setBody(txtBody.getText());
            // Update other fields as needed

            // Perform any necessary database update or save operations here
            // For example, update the announcement in the database
            updateAnnouncementInDatabase();

            // Disable editing after saving changes
            disableEditing();
        }
    }

    // Method to update the announcement in the database
    private void updateAnnouncementInDatabase() {
        try (Connection conn = database.getConnection()) {
            if (conn != null) {
                // Prepare the SQL statement for updating the announcement
                String updateQuery = "UPDATE mod_announce SET Title = ?, Body = ? WHERE AnnouncementID = ?";

                try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, announcementData.getTitle());
                    preparedStatement.setString(2, announcementData.getBody());
                    preparedStatement.setInt(3, announcementData.getAnnouncementID());

                    // Execute the update statement
                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Announcement updated successfully.");
                    } else {
                        System.out.println("No announcement found to update.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions related to database operations
        }
    }

    private void disableEditing() {
        isEditing = false;
        txtTitle.setEditable(false);
        txtBody.setEditable(false);
        // Disable other fields if needed
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
