/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package changebuttonimageview;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jcarl
 */
public class UiController implements Initializable {

    @FXML
    ImageView btnPlay, btnPause, btnStop, btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void handleBtnPlay (MouseEvent event){
        enableDisableControls(true, btnPlay);
        enableDisableControls(false, btnPause);
        enableDisableControls(false, btnStop);
        enableDisableControls(false, btnSave);
    }
    @FXML
    private void handleBtnPause (MouseEvent event){
        enableDisableControls(false, btnPlay);
        enableDisableControls(true, btnPause);
        enableDisableControls(false, btnStop);
        enableDisableControls(false, btnSave);
    }
    @FXML
    private void handleBtnStop (MouseEvent event){
        enableDisableControls(false, btnPlay);
        enableDisableControls(false, btnPause);
        enableDisableControls(true, btnStop);
        enableDisableControls(false, btnSave);
    }
    @FXML
    private void handleBtnSave (MouseEvent event){
        enableDisableControls(false, btnPlay);
        enableDisableControls(false, btnPause);
        enableDisableControls(false, btnStop);
        enableDisableControls(true, btnSave);
    }
    
    public void enableDisableControls(boolean disable, ImageView imageView) {
        String baseDirectory = System.getProperty("user.dir") + "\\src\\media\\";

        if (disable) {
            if (imageView.equals(btnPlay)) {
                btnPlay.setDisable(true);
                btnPlay.setImage(new Image(new File(baseDirectory + "goldPlay.png").toURI().toString()));
            } else if (imageView.equals(btnPause)) {
                btnPause.setDisable(true);
                btnPause.setImage(new Image(new File(baseDirectory + "goldPause.png").toURI().toString()));
            } else if (imageView.equals(btnStop)) {
                btnStop.setDisable(true);
                btnStop.setImage(new Image(new File(baseDirectory + "goldStop.png").toURI().toString()));
            } else if (imageView.equals(btnSave)) {
                btnSave.setDisable(true);
                btnSave.setImage(new Image(new File(baseDirectory + "goldSave.png").toURI().toString()));
            }
        } else {
            if (imageView.equals(btnPlay)) {
                btnPlay.setDisable(true);
                btnPlay.setImage(new Image(new File(baseDirectory + "play.png").toURI().toString()));
            } else if (imageView.equals(btnPause)) {
                btnPause.setDisable(true);
                btnPause.setImage(new Image(new File(baseDirectory + "pause.png").toURI().toString()));
            } else if (imageView.equals(btnStop)) {
                btnStop.setDisable(true);
                btnStop.setImage(new Image(new File(baseDirectory + "stop.png").toURI().toString()));
            } else if (imageView.equals(btnSave)) {
                btnSave.setDisable(true);
                btnSave.setImage(new Image(new File(baseDirectory + "save.png").toURI().toString()));
            }
        }
    }

}
