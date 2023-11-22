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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author lugtu
 */
public class CalendarInfoWindowController implements Initializable {

    @FXML
    private Label monthInfo;
    @FXML
    private Label yearInfo;
    @FXML
    private Text msgInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(int noteYear, String noteMonth, String noteMessage) {
        yearInfo.setText(String.valueOf(noteYear));
        monthInfo.setText(noteMonth);
        msgInfo.setText(noteMessage);
    }

}
