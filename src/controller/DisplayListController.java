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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
    private Label lblDueDate;
    @FXML
    private TextArea txtDetailsDisplay;
    @FXML
    private CheckBox cbComplete;
    @FXML
    private Label txtSurname;
    @FXML
    private Label txtAudience;
    @FXML
    private Label txtPriority;
    @FXML
    private Pane paneGrey;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnRemove1;
    @FXML
    private Label txtIDtodo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbComplete.setOnAction(event -> handleCheckBoxClick());
    }

    private ToDoListData todoData;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void setData(ToDoListData todoData) throws SQLException {

        this.todoData = todoData;
        txtIDtodo.setText(String.valueOf(todoData.getTodoId()));
        lblTitle.setText(todoData.getTitle());
        lblDueDate.setText(todoData.getDeadline());
        txtDetailsDisplay.setText(todoData.getBody());
        txtSurname.setText(todoData.getUser_Surname());
        txtAudience.setText(todoData.getAudience());
        txtPriority.setText(todoData.getPriority());
    }

    private UserDashboardController userDashboardController;

    // Other fields and methods
    public void setUserDashboardController(UserDashboardController userDashboardController) {
        this.userDashboardController = userDashboardController;
    }

    private OfficerDashboardController officerDashboardController;

    public void setOfficerDashboardController(OfficerDashboardController officerDashboardController) {
        this.officerDashboardController = officerDashboardController;
    }

    @FXML
    private void handleToDoDisplayCardClick(ActionEvent event) {
        // Handle the click event here
        System.out.println("toDoDisplayCard clicked!");

        try {
            // Pass data to UserDashboardController
            if (userDashboardController != null) {
                System.out.println("Passing data to UserDashboardController");

                LocalDate deadline = LocalDate.parse(todoData.getDeadline());

                userDashboardController.setTaskData(
                        todoData.getTodoId(), // Assuming getTodoId() returns an int
                        todoData.getTitle(),
                        todoData.getBody(),
                        todoData.getPriority(),
                        deadline
                );
            } else if (officerDashboardController != null) {
                System.out.println("Passing data to OfficerDashboardController");

                LocalDate deadline = LocalDate.parse(todoData.getDeadline());

                officerDashboardController.setTaskData(
                        todoData.getTodoId(), // Assuming getTodoId() returns an int
                        todoData.getTitle(),
                        todoData.getBody(),
                        todoData.getAudience(), // Assuming getAudience() returns a String
                        todoData.getPriority(),
                        LocalDate.parse(todoData.getDeadline())
                );

            } else {
                System.err.println("Both UserDashboardController and OfficerDashboardController are null.");
            }
        } catch (Exception e) {
            // Handle the exception and print an error message
            System.err.println("Error handling toDoDisplayCard click: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for detailed error information
        }
    }

    public void disableRemoveButton() {
        btnRemove.setDisable(true);
    }

    public void disableRemoveButton1() {
        btnRemove1.setDisable(true);
    }
    
    private void adjustLayout(GridPane gridPane) {
        // Iterate through the children of the grid pane and update their row and column indices
        int maxColumns = 2;
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
        String deleteQuery = "DELETE FROM mod_todo_pending WHERE Title = ? ";
        try (PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, todoData.getTitle());
            preparedStatement.executeUpdate();
        }
    }
    

    private void insertIntoArchiveTable(Connection conn) throws SQLException {
        String insertQuery = "INSERT INTO mod_todo_archive (Title, Note, AudienceID, PriorityID, StudentID, Surname, CourseID, SectionID, PostDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, todoData.getTitle());
            preparedStatement.setString(2, todoData.getBody());
            preparedStatement.setString(3, todoData.getAudience());
            preparedStatement.setString(4, todoData.getPriority());
            preparedStatement.setString(5, todoData.getUser_StudentID());
            preparedStatement.setString(6, todoData.getUser_Surname());
            preparedStatement.setString(7, todoData.getUser_CourseID());
            preparedStatement.setString(8, todoData.getUser_SectionID());
            preparedStatement.setString(9, todoData.getPostDate());
            preparedStatement.executeUpdate();
        }
    }

    private String studentID;

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    private void handleCheckBoxClick() {
        // Check if the CheckBox is selected
        if (cbComplete.isSelected()) {
            // Enable the visibility of the Pane
            paneGrey.setVisible(true);

            insertIntoCompletedTable();

            deleteFromPendingTable();

            btnRemove.setDisable(true);
            btnRemove1.setDisable(true);

            if (userDashboardController != null) {
                userDashboardController.numberCompletedTask();
            };

        } else {
            // Disable the visibility of the Pane
            paneGrey.setVisible(false);
            deleteFromCompletedTable();
            btnRemove.setDisable(false);

            if (userDashboardController != null) {
                userDashboardController.numberCompletedTask();
            };
            // You can add additional logic here if needed when the CheckBox is deselected
        }
    }

    private void insertIntoCompletedTable() {
        connect = database.getConnection();
        String sql = "INSERT INTO mod_todo_completed (Title,Note,Deadline, AudienceID, PriorityID, StudentID, Surname, CourseID, SectionID, PostDate)" + "Values( ?,?,?,?,?,?,?,?, ?, ?) ";

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, todoData.getTitle());
            prepare.setString(2, todoData.getBody());
            prepare.setString(3, todoData.getDeadline());
            prepare.setString(4, todoData.getAudience());
            prepare.setString(5, todoData.getPriority());

            // Use the studentID from DisplayListController
            prepare.setString(6, studentID);

            prepare.setString(7, todoData.getUser_Surname());
            prepare.setString(8, todoData.getUser_CourseID());
            prepare.setString(9, todoData.getUser_SectionID());
            prepare.setString(10, todoData.getPostDate());

            System.out.println("SQL Query: " + sql); // Print the SQL query for debugging

            // Print values before executing the query
            System.out.println("TodoData Title: " + todoData.getTitle());
            System.out.println("TodoData Body: " + todoData.getBody());
            System.out.println("TodoData Deadline: " + todoData.getDeadline());
            System.out.println("TodoData Audience: " + todoData.getAudience());
            System.out.println("TodoData Priority: " + todoData.getPriority());
            System.out.println("StudentID: " + studentID);
            System.out.println("TodoData Surname: " + todoData.getUser_Surname());
            System.out.println("TodoData CourseID: " + todoData.getUser_CourseID());
            System.out.println("TodoData SectionID: " + todoData.getUser_SectionID());
            System.out.println("TodoData PostDate: " + todoData.getPostDate());

            prepare.executeUpdate();

            System.out.println("Query executed successfully.");

            paneGrey.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error executing query. StudentID: " + studentID);
        }
    }

    private void deleteFromPendingTable() {
        connect = database.getConnection();
        String sql = "Delete from mod_todo_pending where TodoID = ?";
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, todoData.getTodoId());
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteFromCompletedTable() {
        connect = database.getConnection();
        String sql = "DELETE FROM mod_todo_completed WHERE title = ? and StudentID = ?";

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, todoData.getTitle());
            prepare.setString(2, studentID);

            System.out.println("SQL Query: " + sql); // Print the SQL query for debugging

            // Print value before executing the query
            System.out.println("Title to delete: " + todoData.getTitle());

            // Confirmation Dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete?");
            alert.setContentText("Title: " + todoData.getTitle());

            // Show the confirmation dialog and wait for user response
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Deletion successful. Rows affected: " + rowsAffected);
                } else {
                    System.out.println("No rows were deleted. Title: " + todoData.getTitle());
                }

                // Additional actions or UI updates can be added here as needed
            } else {
                System.out.println("Deletion canceled by user.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error executing delete query. Title: " + todoData.getTitle());
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
