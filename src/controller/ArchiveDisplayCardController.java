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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author jcarl
 */
public class ArchiveDisplayCardController implements Initializable {

    @FXML
    private AnchorPane archiveDisplayCard;
    @FXML
    private Button btnRetrieve;
    @FXML
    private Button btnRemove;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblDueDate;
    @FXML
    private TextArea txtDetailsDisplay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private ArchiveToDoListData archiveTodoData;
    private UserDashboardController toDoListUiController;

    // Add this method to set the reference to ToDoListUiController
    public void setToDoListUiController(UserDashboardController toDoListUiController) {
        this.toDoListUiController = toDoListUiController;
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        // Show a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete this item?");
        alert.setContentText("This action cannot be undone.");

        // Wait for user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, proceed with removal

            archiveDisplayCard.getChildren().clear();
            // Get the parent grid pane
            GridPane parentGridPane = (GridPane) archiveDisplayCard.getParent();

            // Remove the displayListPane
            parentGridPane.getChildren().remove(archiveDisplayCard);

            // Adjust the layout of the remaining cards
            adjustLayout(parentGridPane);

            // Save data to the "archive" table in the database
            deleteFromArchive();

            // Call the refresh method in ToDoListUiController
            if (toDoListUiController != null) {
                toDoListUiController.refreshArchiveDisplay();
            }
        }
    }

    private void adjustLayout(GridPane gridPane) {
        // Iterate through the children of the grid pane and update their row and column indices
        int maxColumns = 3;
        int row = 0;
        int column = 0;

        for (Node node : gridPane.getChildren()) {
            if (column >= maxColumns) {
                // Move to the next row when the maximum number of columns is reached
                column = 0;
                row++;
            }

            // Set the new row and column indices for the remaining cards
            GridPane.setRowIndex(node, row);
            GridPane.setColumnIndex(node, column);

            column++;
        }
    }

    @FXML
    private void handleRetrieveButton(ActionEvent event) {
        // Show a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to retrieve this item?");
        alert.setContentText("This action will retrieve the selected task to your Todo List");

        // Wait for user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, proceed with retrieval
            archiveDisplayCard.getChildren().clear();
            // Get the parent grid pane
            GridPane parentGridPane = (GridPane) archiveDisplayCard.getParent();

            // Remove the archiveDisplayCard
            parentGridPane.getChildren().remove(archiveDisplayCard);

            // Adjust the layout of the remaining cards
            adjustLayout(parentGridPane);

            // Save data to the "archive" table in the database
            deleteFromArchive();
            insertIntoTask();

            // Call the refresh method in ToDoListUiController
            if (toDoListUiController != null) {
                toDoListUiController.refreshArchiveDisplay();
            }
        }
    }

    private void deleteFromArchive() {
        try (Connection conn = database.getConnection()) {
            if (conn != null) {
                // First, delete the task from the task table
                deleteFromArchiveTable(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private void insertIntoTask() {
        try (Connection conn = database.getConnection()) {
            if (conn != null) {
                // First, delete the task from the task table
                insertIntoTaskTable(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private void deleteFromArchiveTable(Connection conn) throws SQLException {
        String deleteQuery = "DELETE FROM archive WHERE title = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, archiveTodoData.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    private void insertIntoTaskTable(Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO todo (title, deadline, note) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, archiveTodoData.getDescription());
            preparedStatement.setString(2, archiveTodoData.getDue_date());
            preparedStatement.setString(3, archiveTodoData.getDetails());
            preparedStatement.executeUpdate();
        }
    }

    public void setArchiveData(ArchiveToDoListData archiveToDolistData) throws SQLException {
        this.archiveTodoData = archiveToDolistData;

        lblDescription.setText(archiveTodoData.getDescription());
        lblDueDate.setText(archiveTodoData.getDue_date());
        txtDetailsDisplay.setText(archiveTodoData.getDetails());

    }
}
