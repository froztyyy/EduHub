/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package eduhub;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jcarl
 */
public class StudentLoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCancel;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleBtnLogin(ActionEvent event) throws ClassNotFoundException {
        String sql = "SELECT * FROM account WHERE Username = ? and Password = ?";
        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, txtUsername.getText());
            prepare.setString(2, txtPassword.getText());
            result = prepare.executeQuery();

            if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                showAlert(event, "Error Message!", "Please fill all blank fields", "Back");
            } else {
                if (result.next()) {
                    openStudentDashboard(event);
                } else {
                    showAlert(event, "Error Message!", "Wrong Username/Password", "Back");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(ActionEvent event, String title, String content, String Button) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomAlert.fxml"));
            Parent root = loader.load();

            Stage customAlertStage = new Stage();
            customAlertStage.initStyle(StageStyle.UNDECORATED);
            customAlertStage.initModality(Modality.APPLICATION_MODAL);
            customAlertStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            customAlertStage.setResizable(false);

            Scene scene = new Scene(root);
            customAlertStage.setScene(scene);

            CustomAlertController controller = loader.getController();
            controller.setTitle(title);
            controller.setContent(content);
            controller.setButton(Button);
            controller.setStage(customAlertStage);

            customAlertStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openStudentDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectRole.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(true);
            javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
            stage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void handleBtnCancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectRole.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(true);
            javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
            stage.show();
        } catch (IOException e) {
        }
    }
}
