/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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
    private Label lblTitle;
    @FXML
    private Label lblPostDate;
    @FXML
    private TextArea txtBody;

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
        lblTitle.setText(announcementData.getTitle());
        lblSurname.setText(announcementData.getUser_Surname());
        lblAudience.setText(announcementData.getAudience());
        lblPriorityLevel.setText(announcementData.getPriority());
        txtBody.setText(announcementData.getBody());
        lblPostDate.setText(announcementData.getPostDate());
    }
}
