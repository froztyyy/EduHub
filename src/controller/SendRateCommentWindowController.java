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
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lugtu
 */
public class SendRateCommentWindowController implements Initializable {

    @FXML
    private Button btn1stStar1;
    @FXML
    private Button btn1stStar2;
    @FXML
    private Button btn1stStar3;
    @FXML
    private Button btn1stStar4;
    @FXML
    private Button btn1stStar5;

    @FXML
    private Button btn2ndStar1;
    @FXML
    private Button btn2ndStar2;
    @FXML
    private Button btn2ndStar3;
    @FXML
    private Button btn2ndStar4;
    @FXML
    private Button btn2ndStar5;

    @FXML
    private Button btn3rdStar1;
    @FXML
    private Button btn3rdStar2;
    @FXML
    private Button btn3rdStar3;
    @FXML
    private Button btn3rdStar4;
    @FXML
    private Button btn3rdStar5;

    @FXML
    private TextArea txtFeedback;
    @FXML
    private Button btnSubmit;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private int designRating;
    private int functionRating;
    private int experienceRating;
    @FXML
    private AnchorPane sendRateWindow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeStarButtons();
        btnSubmit.setOnAction(event -> handleSubmitButton());
    }

    private FeedBackWindowController feedbackUiController;

    public void setFeedbackUiController(FeedBackWindowController feedbackUiController) {
        this.feedbackUiController = feedbackUiController;
    }

    private void initializeStarButtons() {
        setStarButtonAction(btn1stStar1, 1, "designRating");
        setStarButtonAction(btn1stStar2, 2, "designRating");
        setStarButtonAction(btn1stStar3, 3, "designRating");
        setStarButtonAction(btn1stStar4, 4, "designRating");
        setStarButtonAction(btn1stStar5, 5, "designRating");

        setStarButtonAction(btn2ndStar1, 1, "functionRating");
        setStarButtonAction(btn2ndStar2, 2, "functionRating");
        setStarButtonAction(btn2ndStar3, 3, "functionRating");
        setStarButtonAction(btn2ndStar4, 4, "functionRating");
        setStarButtonAction(btn2ndStar5, 5, "functionRating");

        setStarButtonAction(btn3rdStar1, 1, "experienceRating");
        setStarButtonAction(btn3rdStar2, 2, "experienceRating");
        setStarButtonAction(btn3rdStar3, 3, "experienceRating");
        setStarButtonAction(btn3rdStar4, 4, "experienceRating");
        setStarButtonAction(btn3rdStar5, 5, "experienceRating");
    }

    private void setStarButtonAction(Button button, int rating, String category) {
        String outlinedStar = getClass().getResource("/media/outlinedStar.png").toExternalForm();
        String filledStar = getClass().getResource("/media/filledStar.png").toExternalForm();

        // Use a variable to track the selected index and default to -1
        AtomicInteger selectedIndex = new AtomicInteger(-1);

        button.setStyle("-fx-background-image: url('" + outlinedStar + "'); -fx-background-size: cover;");

        button.setOnAction(event -> {
            HBox starContainer = (HBox) button.getParent();
            int clickedIndex = starContainer.getChildren().indexOf(button);

            // Deselect all stars by default
            for (Node child : starContainer.getChildren()) {
                Button starButton = (Button) child;
                starButton.setStyle("-fx-background-image: url('" + outlinedStar + "'); -fx-background-size: cover;");
            }

            // If the clicked index is the same as the selected index, set the rating to 0
            if (selectedIndex.get() == clickedIndex) {
                setRating(0, category);
                selectedIndex.set(-1);
            } else {
                // Select stars up to the clicked one
                for (int i = 0; i <= clickedIndex; i++) {
                    Button starButton = (Button) starContainer.getChildren().get(i);
                    starButton.setStyle("-fx-background-image: url('" + filledStar + "'); -fx-background-size: cover;");
                    setRating(rating, category);
                }
                selectedIndex.set(clickedIndex);
            }
        });
    }

    private void setRating(int rating, String category) {
        switch (category) {
            case "designRating":
                designRating = rating;
                break;
            case "functionRating":
                functionRating = rating;
                break;
            case "experienceRating":
                experienceRating = rating;
                break;
        }
    }

    private void handleSubmitButton() {
        connect = database.getConnection();

        try {
            String feedback = txtFeedback.getText();
            String query = "INSERT INTO ratings_feedback (designRating, functionRating, experienceRating, feedbackReport) VALUES (?, ?, ?, ?)";
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, designRating);
            prepare.setInt(2, functionRating);
            prepare.setInt(3, experienceRating);
            prepare.setString(4, feedback);
            prepare.executeUpdate();

            // Show confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Submission Confirmation");
            alert.setHeaderText("Feedback Submitted Successfully");
            alert.setContentText("Thank you for your feedback!");

            // Add close button listener
            ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().add(closeButton);

            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.setOnCloseRequest(event -> closeWindow());

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK || response == closeButton) {
                    closeWindow();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void closeWindow() {
        // Get the reference to the button's stage
        Stage stage = (Stage) btnSubmit.getScene().getWindow();
        // Close the stage
        stage.close();
    }

    private void closeResources() {
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

    @FXML
    private void closeButton(ActionEvent event) {
                // Get the Stage associated with the feedBackWindow
        Stage stage = (Stage) sendRateWindow.getScene().getWindow();

        // Close the Stage
        stage.close();
    }
}