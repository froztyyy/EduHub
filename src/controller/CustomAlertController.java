/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jcarl
 */
public class CustomAlertController implements Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    private Label lblContent;
   
    @FXML
    private Button btnCancel;
    
    private Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
   
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTitle(String title) {
        lblTitle.setText(title);
    }

    public void setContent(String content) {
        lblContent.setText(content);
    }
    
     public void setButton(String button) {
        btnCancel.setText(button);
    }

    @FXML
    private void handleBtnBack(ActionEvent event) {
        stage.close(); 
    }   
}
