/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class DisplayListController implements Initializable {

    @FXML
    private AnchorPane toDoDisplayCard;
    @FXML
    private Button btnDisplayCard;
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

    private ToDoListData todoData;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void setData(ToDoListData todoData) throws SQLException {
        this.todoData = todoData;

        lblDescription.setText(todoData.getDescription());
        lblDueDate.setText(todoData.getDue_date());
        txtDetailsDisplay.setText(todoData.getDetails());

    }
    
    private UserDashboardController toDoListUiController;

    public void setToDoListUiController(UserDashboardController toDoListUiController) {
        this.toDoListUiController = toDoListUiController;
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
            toDoDisplayCard.getChildren().clear();
            // Get the parent grid pane
            GridPane parentGridPane = (GridPane) toDoDisplayCard.getParent();

            // Remove the displayListPane
            parentGridPane.getChildren().remove(toDoDisplayCard);

            // Adjust the layout of the remaining cards
            adjustLayout(parentGridPane);
            
           
            // Save data to the "archive" table in the database
            saveToArchiveAndDeleteFromTask();

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

    private void saveToArchiveAndDeleteFromTask() {
        try (Connection conn = database.getConnection()) {
            if (conn != null) {
                // First, delete the task from the task table
                deleteFromTaskTable(conn);

                // Now, insert the task into the archive table
                insertIntoArchiveTable(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    private void deleteFromTaskTable(Connection conn) throws SQLException {
        String deleteQuery = "DELETE FROM todo WHERE title = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, todoData.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    private void insertIntoArchiveTable(Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO archive (title, deadline, note) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, todoData.getDescription());
            preparedStatement.setString(2, todoData.getDue_date());
            preparedStatement.setString(3, todoData.getDetails());
            preparedStatement.executeUpdate();
        }
    }
}
