/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jcarl
 */
public class AddListWindowController implements Initializable {

    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea txtDetails;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private Button btnSubmit;
    @FXML
    private AnchorPane addlistwindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private int studentID;

    private UserDashboardController toDoListUiController;

    public void setToDoListUiController(UserDashboardController toDoListUiController) {
        this.toDoListUiController = toDoListUiController;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
        
    }

    @FXML
    private void handleButtonSubmit(ActionEvent event) throws IOException {
        connect = database.getConnection();

        String sql = "INSERT INTO todo (StudentID, Title, Note, Deadline) VALUES (?, ?, ?, ?)";

        try {
            System.out.println("Current studentID: " + studentID);
            System.out.println("Setting values - StudentID: " + studentID + ", Title: " + txtDescription.getText() + ", Note: " + txtDetails.getText() + ", Deadline: " + dueDatePicker.getValue());

            // Set values from the user input
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, studentID);
            prepare.setString(2, txtDescription.getText());
            prepare.setString(3, txtDetails.getText());
            prepare.setDate(4, java.sql.Date.valueOf(dueDatePicker.getValue())); // Convert LocalDate to java.sql.Date

            // Debug: Print the prepared statement
            System.out.println("Prepared Statement: " + prepare);

            // Execute the SQL statement
            prepare.executeUpdate();

            // Show a success alert
            showSuccessAlert();

        } catch (SQLException e) {
            // Handle any SQL errors
            e.printStackTrace();
            showErrorAlert("Error", "Failed to insert values into the database.");
        } finally {
            // Close resources
            try {
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Values successfully inserted into the database.");
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) addlistwindow.getScene().getWindow();

        // Close the Stage
        stage.close();
    }
    
    private void fetchStudentID(){
    
    }

}
